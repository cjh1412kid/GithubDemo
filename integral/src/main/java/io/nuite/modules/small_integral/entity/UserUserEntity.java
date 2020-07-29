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
@TableName("NWUser_User" )
public class UserUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(value = "Seq")
	private Integer seq;
	/**
	 * $column.comments
	 */
	@TableField(value = "Name")
	private String name;

	/**
	 * $column.comments
	 */
	@TableField(value = "Telephone")
	private String telephone;
	/**
	 * $column.comments
	 */
	@TableField(value = "ZKTimeUserID")
	private Integer ZKTimeUserId;
	/**
	 * $column.comments
	 */
	@TableField(value = "picUrl")
	private String picUrl;
	/**
	 * $column.comments
	 */
	@TableField(value = "nickName")
	private String nickName;
	/**
	 * $column.comments
	 */
	@TableField(value = "gender")
	private Integer gender;
	/**
	 * $column.comments
	 */
	
	@TableField(value = "openId")
	private String openId;
	/**
	 * $column.comments
	 */
	@TableField(value = "unionId")
	private String unionId;

	@TableField(value = "InputTime")
	private Date inputTime;
	
	@TableLogic
	@TableField(value = "Del")
	private Integer del;

}
