package cn.anyoufang.controller;

import cn.anyoufang.annotation.SysLog;
import cn.anyoufang.entity.Commpara;
import cn.anyoufang.enumresource.StateEnum;
import cn.anyoufang.service.CommparaService;
import cn.anyoufang.utils.PageUtils;
import cn.anyoufang.utils.Query;
import cn.anyoufang.utils.R;
import cn.anyoufang.utils.RRException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 字典管理
 * 
 * @author
 * @email
 * @date 2017-11-06 14:49:28
 */
@Controller
@RequestMapping("commpara")
public class CommparaController {
	@Autowired
	private CommparaService commparaService;
	
	/**
	 * 列表
	 */
    @ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("commpara:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<Commpara> commparaList = commparaService.queryList(query);
		int total = commparaService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(commparaList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

    /**
     * 跳转到新增页面
     **/
    @RequestMapping("/add")
    @RequiresPermissions("commpara:save")
    public String add(){
        return "commpara/add.jsp";
    }

    /**
     *   跳转到修改页面
     **/
    @RequestMapping("/edit/{id}")
    @RequiresPermissions("commpara:update")
    public String edit(Model model, @PathVariable("id") Integer id){
		Commpara commpara = commparaService.queryObject(id);
        model.addAttribute("model",commpara);
        return "commpara/edit.jsp";
    }

	/**
	 * 信息
	 */
    @ResponseBody
    @RequestMapping("/info/{paraId}")
    @RequiresPermissions("commpara:info")
    public R info(@PathVariable("paraId") Integer paraId){
        Commpara commpara = commparaService.queryObject(paraId);
        return R.ok().put("commpara", commpara);
    }

    /**
	 * 保存
	 */
    @ResponseBody
    @SysLog("保存字典管理")
	@RequestMapping("/save")
	@RequiresPermissions("commpara:save")
	public R save(@RequestBody Commpara commpara){
        //同一参数编码 下的 参数值不能相同
        List<Commpara> commparaList=commparaService.findByVerify(commpara);
        if(commparaList!=null&&commparaList.size()>0){
           throw  new RRException(commpara.getParaCode()+"下已存在该参数值");
        }

        commparaService.save(commpara);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
    @ResponseBody
    @SysLog("修改字典管理")
	@RequestMapping("/update")
	@RequiresPermissions("commpara:update")
	public R update(@RequestBody Commpara commpara){
        Commpara oldCommpara=commparaService.queryObject(commpara.getParaId());
        if(!oldCommpara.getParaCode().equals(commpara.getParaCode())||!oldCommpara.getParaKey().equals(commpara.getParaKey())){
            //同一参数编码 下的 参数值不能相同
            List<Commpara> commparaList=commparaService.findByVerify(commpara);
            if(commparaList!=null&&commparaList.size()>0){
                throw  new RRException(commpara.getParaCode()+"下已存在该参数值");
            }
        }

        commparaService.update(commpara);
		
		return R.ok();
	}

    /**
     * 启用
     */
    @ResponseBody
    @SysLog("启用字典管理")
    @RequestMapping("/enable")
    @RequiresPermissions("commpara:update")
    public R enable(@RequestBody Integer[] ids){
        String stateValue= StateEnum.ENABLE.getCode();
		commparaService.updateState(ids,stateValue);
        return R.ok();
    }
    /**
     * 禁用
     */
    @ResponseBody
    @SysLog("禁用字典管理")
    @RequestMapping("/limit")
    @RequiresPermissions("commpara:update")
    public R limit(@RequestBody Integer[] ids){
        String stateValue=StateEnum.LIMIT.getCode();
		commparaService.updateState(ids,stateValue);
        return R.ok();
    }
	
	/**
	 * 删除
	 */
    @ResponseBody
    @SysLog("删除字典管理")
	@RequestMapping("/delete")
	@RequiresPermissions("commpara:delete")
	public R delete(@RequestBody Integer[] paraIds){
		commparaService.deleteBatch(paraIds);
		
		return R.ok();
	}

    /**
     * 上移
     */
    @ResponseBody
    @SysLog("上移")
    @RequestMapping("/moveTop/{id}/{number}")
    public R moveTop(@PathVariable String id,@PathVariable String number){

        return R.ok();
    }

    /**
     * 下移
     */
    @ResponseBody
    @SysLog("下移")
    @RequestMapping("/moveBottom/{id}/{number}")
    public R moveBottom(@PathVariable String id,@PathVariable String number){

        return R.ok();
    }
}
