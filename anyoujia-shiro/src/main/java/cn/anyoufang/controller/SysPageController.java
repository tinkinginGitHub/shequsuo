package cn.anyoufang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * 
 * @author
 * @email
 * @date 2016年11月24日 下午11:05:27
 */
@Controller
public class SysPageController {
	
//	@RequestMapping("{module}/{url}.html")
//	public String html(@PathVariable("module") String module, @PathVariable("url") String url){
//		return module + "/" + url + ".html";
//	}

	@RequestMapping("/page/{url}.jsp")
	public String jsp( @PathVariable("url") String url){
		return  "/page/" + url + ".jsp";
	}

}
