package cn.anyoufang.controller;


import cn.anyoufang.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemberController extends AbstractController {

    @Autowired
    private MemberService memberService;


    @RequestMapping("/regist")
    public boolean register(@RequestParam String account,@RequestParam String pwd) {

        return false;
    }



}
