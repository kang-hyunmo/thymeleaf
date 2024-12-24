package com.example.demo.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardCont {

    @GetMapping("/list")
    @ResponseBody
    public String list() {
//        System.out.println("list목록");
        return "list 요청";
    }

    @GetMapping("read/{no}")
    @ResponseBody
    public String read() {
        // System.out.println("글 읽기");
        return "read 요청";
    }
    @GetMapping("/write")
    @ResponseBody
    public String write() {
//        System.out.println("글 쓰기");
        return "write 요청";
    }
}
