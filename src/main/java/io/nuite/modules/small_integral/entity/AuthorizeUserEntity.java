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
@TableName("authorizeUser" )
public class AuthorizeUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(value = "id")
	private Integer id;
	/**
	 * $column.comments
	 */
	@TableField(value = "phone")
	private String phone;
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
	@TableField(value = "userId")
	private Integer userId;


}
