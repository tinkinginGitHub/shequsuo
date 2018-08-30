package cn.anyoufang.controller;

import java.util.List;
import java.util.Map;

import cn.anyoufang.annotation.SysLog;
import cn.anyoufang.entity.SysCode;
import cn.anyoufang.utils.RRException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import cn.anyoufang.service.SysCodeService;
import cn.anyoufang.utils.PageUtils;
import cn.anyoufang.utils.Query;
import cn.anyoufang.utils.R;

import javax.servlet.http.HttpServletRequest;


/**
 * 表码信息表
 * 
 * @author
 * @email
 * @date 2017-06-29 10:46:14
 */
@Controller
@RequestMapping("/sys/code")
public class SysCodeController {
	@Autowired
	private SysCodeService sysCodeService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysCode> sysCodeList = sysCodeService.queryList(query);
		int total = sysCodeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysCodeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/getCodeValues")
	public R getCodeValues(@RequestParam Map<String, Object> params){
		List<SysCode> sysCodeList=null;
		if(params.get("code")!=null&&!"".equals(params.get("code"))){
			 sysCodeList = sysCodeService.getCodeValues(params);
		}
		return R.ok().put("page", sysCodeList);
	}
	/**
	 * @author chenyi
	 * @Description 跳转到新增页面
	 * @param
	 * @date 2017/6/27 11:17
	 **/
	@RequestMapping("/add")
	@RequiresPermissions("sys:code:list")
	public String add(){
		return "code/add.jsp";
	}
	/**
	 * @author chenyi
	 * @Description  跳转到修改页面
	 * @param
	 * @date 2017/6/27 11:17
	 **/
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("sys:code:list")
	public String edit(HttpServletRequest request, Model model, @PathVariable("id") Long id){
		SysCode sysCode = sysCodeService.queryObject(id);
		model.addAttribute("model", sysCode);
		return "code/edit.jsp";
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		SysCode sysCode = sysCodeService.queryObject(id);
		
		return R.ok().put("sysCode", sysCode);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@SysLog("保存表码")
	@RequestMapping("/save")
	public R save(@RequestBody SysCode sysCode){
		//同一编码名 下的 参数值不能相同
		List<SysCode> sysCodeList=sysCodeService.findByVerify(sysCode);
		if(sysCodeList!=null&&sysCodeList.size()>0){
			throw new RRException(sysCode.getCodeName()+" 下已存在该参数值!");
		}
		sysCodeService.save(sysCode);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@SysLog("修改表码")
	@RequestMapping("/update")
	public R update(@RequestBody SysCode sysCode){
		SysCode oldSysCode=sysCodeService.queryObject(sysCode.getId());
		if(!oldSysCode.getCodeName().equals(sysCode.getCodeName())||!oldSysCode.getCode().equals(sysCode.getCode())){
			//同一编码名 下的 参数值不能相同
			List<SysCode> sysCodeList=sysCodeService.findByVerify(sysCode);
			if(sysCodeList!=null&&sysCodeList.size()>0){
				throw new RRException(sysCode.getCodeName()+" 下已存在该参数值!");
			}
		}
		sysCodeService.update(sysCode);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@SysLog("删除表码")
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] ids){
		sysCodeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
