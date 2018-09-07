package cn.anyoufang.controller;

import cn.anyoufang.entity.SpMember;
import cn.anyoufang.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 * 
 * @author
 * @email
 * @date 2018年08月31日
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SpMember getUser() {
		return ShiroUtils.getUserEntity();
	}
	protected boolean isLogin() {return ShiroUtils.isLogin();}

}
