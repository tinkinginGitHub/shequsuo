package cn.anyoufang.controller;

import cn.anyoufang.util.SecurityQuestionConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author daiping
 */

@RestController
@RequestMapping("/api")
public class CommonController {

    @RequestMapping("/questions")
    public List<String> getQuestions() {
        return SecurityQuestionConstant.getQuestions();
    }
}
