package io.nuite.modules.small_integral.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-05-10 09:24:44
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("NWUser_Integral" )
public class UserIntegralEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * $column.comments
	 */
	@TableField(value = "UserSeq")
	private Integer userSeq;//1.准点打卡，2.完成任务，3.迟到，4.早退
	/**
	 * $column.comments
	 */
	@TableField(value = "IntegralType")
	private Integer integralType;

	/**
	 * $column.comments
	 */
	@TableField(value = "integral")
	private Integer integral;
	
	/**
	 * $column.comments
	 */
	@TableField(value = "remark")
	private String remark;
	
	
	/**
	 * $column.comments
	 */
	@TableField(value = "InputTime")
	private Date inputTime;
	/**
	 * $column.comments
	 */
	@TableLogic
	@TableField(value = "Del")
	private Integer del;
}
