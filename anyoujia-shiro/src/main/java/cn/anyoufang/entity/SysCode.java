package cn.anyoufang.entity;

import java.io.Serializable;


/**
 * 表码信息表
 * 
 * @author
 * @email
 * @date 2017-06-29 10:46:14
 */
public class SysCode implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//编码值
	private String code;
	//编码名称
	private String codeName;
	//参数值
	private String value;
	//参数名
	private String name;
	//状态   0：隐藏   1：显示
	private Integer status;
	//备注
	private String remark;

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：所属编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：所属编码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：参数值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：参数值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：参数名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：参数名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：状态   0：隐藏   1：显示
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态   0：隐藏   1：显示
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
}
