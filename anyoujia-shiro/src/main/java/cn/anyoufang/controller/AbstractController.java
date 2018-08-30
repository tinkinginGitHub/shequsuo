package cn.anyoufang.controller;

import cn.anyoufang.entity.SysUser;
import cn.anyoufang.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 * 
 * @author
 * @email
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUser getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Long getUserId() {
		if(getUser() == null){
			return 1L;
		}
		return getUser().getUserId();
	}
}
