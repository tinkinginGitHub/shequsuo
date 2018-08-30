package cn.anyoufang.controller;

import java.util.List;
import java.util.Map;

import cn.anyoufang.annotation.SysLog;
import cn.anyoufang.enumresource.StateEnum;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import cn.anyoufang.entity.File;
import cn.anyoufang.service.FileService;
import cn.anyoufang.utils.PageUtils;
import cn.anyoufang.utils.Query;
import cn.anyoufang.utils.R;


/**
 * 地产附件表
 * 
 * @author
 * @email
 * @date 2017-11-17 11:52:01
 */
@Controller
@RequestMapping("file")
public class FileController {
	@Autowired
	private FileService fileService;
	
	/**
	 * 列表
	 */
    @ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("file:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<File> fileList = fileService.getList(query);
		int total = fileService.getCount(query);
		
		PageUtils pageUtil = new PageUtils(fileList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

    /**
     * 跳转到新增页面
     **/
    @RequestMapping("/add")
    @RequiresPermissions("file:save")
    public String add(){
        return "file/add.jsp";
    }

    /**
     *   跳转到修改页面
     **/
    @RequestMapping("/edit/{id}")
    @RequiresPermissions("file:update")
    public String edit(Model model, @PathVariable("id") String id){
		File file = fileService.get(id);
        model.addAttribute("model",file);
        return "file/edit.jsp";
    }

	/**
	 * 信息
	 */
    @ResponseBody
    @RequestMapping("/info/{id}")
    @RequiresPermissions("file:info")
    public R info(@PathVariable("id") String id){
        File file = fileService.get(id);
        return R.ok().put("file", file);
    }

    /**
	 * 保存
	 */
    @ResponseBody
    @SysLog("保存地产附件表")
	@RequestMapping("/save")
	@RequiresPermissions("file:save")
	public R save(@RequestBody File file){
		fileService.save(file);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
    @ResponseBody
    @SysLog("修改地产附件表")
	@RequestMapping("/update")
	@RequiresPermissions("file:update")
	public R update(@RequestBody File file){
		fileService.update(file);
		
		return R.ok();
	}

    /**
     * 启用
     */
    @ResponseBody
    @SysLog("启用地产附件表")
    @RequestMapping("/enable")
    @RequiresPermissions("file:update")
    public R enable(@RequestBody String[] ids){
        String stateValue= StateEnum.ENABLE.getCode();
		fileService.updateState(ids,stateValue);
        return R.ok();
    }
    /**
     * 禁用
     */
    @ResponseBody
    @SysLog("禁用地产附件表")
    @RequestMapping("/limit")
    @RequiresPermissions("file:update")
    public R limit(@RequestBody String[] ids){
        String stateValue=StateEnum.LIMIT.getCode();
		fileService.updateState(ids,stateValue);
        return R.ok();
    }
	
	/**
	 * 删除
	 */
    @ResponseBody
    @SysLog("删除地产附件表")
	@RequestMapping("/delete")
	@RequiresPermissions("file:delete")
	public R delete(@RequestBody String[] ids){
		fileService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
