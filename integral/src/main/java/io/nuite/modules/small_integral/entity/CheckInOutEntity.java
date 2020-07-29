package io.nuite.modules.small_integral.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-05-08 18:14:43
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("CHECKINOUT" )
public class CheckInOutEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableField(value = "USERID")
	private Integer userId;
	/**
	 * $column.comments
	 */
	@TableField(value = "CHECKTIME")
	private Date checkTime;
	/**
	 * $column.comments
	 */
	@TableField(value = "CHECKTYPE")
	private String checkType;
	/**
	 * $column.comments
	 */
	@TableField(value = "VERIFYCODE")
	private Integer verifyCode;
	/**
	 * $column.comments
	 */
	@TableField(value = "SENSORID")
	private String sensorId;
	/**
	 * $column.comments
	 */
	@TableField(value = "Memoinfo")
	private String memoinfo;
	/**
	 * $column.comments
	 */
	@TableField(value = "WorkCode")
	private String workCode;
	/**
	 * $column.comments
	 */
	@TableField(value = "sn")
	private String sn;
	/**
	 * $column.comments
	 */
	@TableField(value = "UserExtFmt")
	private Integer userExtFmt;


}
