package com.example.demo.cont;

import com.example.demo.dto.DataDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class Cont {
    @GetMapping
    public String index() { //@RequestParam("id") String id
        //System.out.println("id: " + id);
        log.info("log test:{}","ok");
        return "index";
    }
    @GetMapping("/intro")
    public String intro(Model model) {
        model.addAttribute("currentDate", LocalDateTime.now());
        return "intro";

    }
    @GetMapping("/t_output")
    public String output(Model model) {
        log.info("/t_output ok");
        model.addAttribute("htmlStr","<h3>h3만든 문자열</h3>");
        Map<String,String> map = new HashMap<>();
        map.put("name","홍길동");
        map.put("age","20");
        map.put("addr","인천");
        model.addAttribute("mapData",map);
        //Dto(또는 vo)데이터 묶음
        //alt+enter 자동완성
        DataDto dto=new DataDto();
        dto.setName("강감찬");
        dto.setAge(20);
        dto.setAddr("인천 서구");
        model.addAttribute("dtoData",dto);
        model.addAttribute("msg","서버로부터의 메세지");

        return "/t_output";
    }
//    @RequestMapping
    @GetMapping("/t_cont")
    public String cont(Model model, HttpSession session) {
        session.setAttribute("user_id","admin");
        model.addAttribute("session",session); //로그인
//        session.invalidate(); //로그아웃
        model.addAttribute("msg","이 문자열이 보입니다");
        model.addAttribute("age",30);

        List<DataDto> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            DataDto dto=new DataDto();
            dto.setName("이름"+i);
            dto.setAge(20+i);
            dto.setAddr("주소"+i);
            list.add(dto);
        }
        model.addAttribute("list",list);
        return "/t_cont";
    }
    @GetMapping("/info/{id}")
    public String info(@PathVariable("id") String id, Model model) {
        System.out.println("id:"+id); //admin
        return null;
    }
    @GetMapping("/sendData")
    public String sendData() {
        return "sendData";
    }
    @GetMapping("/a_send")
    public String aSend(@RequestParam String num1, @RequestParam String num2, Model model) {
        System.out.println("num1:"+num1);
        System.out.println("num2:"+num2);

        model.addAttribute("num1",num1);
        model.addAttribute("num2",num2);
        model.addAttribute("result",num1+num2);

        return "s_result";
    }
    @GetMapping("/nonDtoSend")
    public String nonDtoSend( String name, //@RequestParam 생략
                             @RequestParam(value = "age",defaultValue = "1") int age,
                              String addr, //@RequestParam 생략
                             Model model) {
        System.out.println("name:"+name);
        System.out.println("age:"+age);
        System.out.println("addr:"+addr);

        model.addAttribute("result","non dto ok");

        return "s_result";
    }
    @PostMapping("/dtoSend")
    public String dtoSend(@ModelAttribute DataDto dto,Model model) {
        System.out.println("name:"+dto.getName());
        System.out.println("age:"+dto.getAge());
        System.out.println("addr:"+dto.getAddr());
        model.addAttribute("dtoData","dto ok");
        return "s_result";
    }
    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("user",new DataDto("kang",30,"시흥"));
        return "user";
    }

    @PostMapping("/user")
    public String user(@ModelAttribute DataDto dto,Model model) {
        System.out.println("name:"+dto.getName());
        System.out.println("age:"+dto.getAge());
        System.out.println("addr:"+dto.getAddr());
        return "redirect:/user";
    }
    
}
