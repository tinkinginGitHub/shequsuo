package cn.anyoufang.util;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 安全问题工具类，省略数据库建表
 * @author daiping
 */
public class SecurityQuestionConstant {

    private static final List<String> questions = Arrays.asList("您母亲的姓名是?",
                                                                "您父亲的姓名是?",
                                                                "您的出生地是?",
                                                                 "您毕业于那个初中?","您最喜欢看的电影的名字是?","您最喜欢的食物是?");

    public static List<String> getQuestions(){
        return questions;
    }

}
