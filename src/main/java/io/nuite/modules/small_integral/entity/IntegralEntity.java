package io.nuite.modules.small_integral.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.nuite.datasources.DatabaseNames;
import lombok.Data;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-05-10 09:24:44
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("integral" )
public class IntegralEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(value = "integralId")
	private Integer integralId;
	/**
	 * $column.comments
	 */
	@TableField(value = "integralType")
	private Integer integralType;//1.准点打卡，2.完成任务，3.迟到，4.早退
	/**
	 * $column.comments
	 */
	@TableField(value = "continuous")
	private Integer continuous;
	/**
	 * $column.comments
	 */
	@TableField(value = "taskType")
	private String taskType;
	/**
	 * $column.comments
	 */
	@TableField(value = "integralTotal")
	private Integer integralTotal;
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

	/**
	 * $column.comments
	 */
	@TableField(value = "UserId")
	private Integer UserId;
	
	/**
	 * $column.comments
	 */
	@TableField(value = "finishTime")
	private Date finishTime;
}
