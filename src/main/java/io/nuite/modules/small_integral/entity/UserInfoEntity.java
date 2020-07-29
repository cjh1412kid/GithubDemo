package io.nuite.modules.small_integral.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.nuite.datasources.DatabaseNames;
import lombok.Data;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-05-08 18:15:01
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("USERINFO")
public class UserInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(value = "USERID")
	private Integer userId;
	/**
	 * $column.comments
	 */
	@TableField(value = "BADGENUMBER")
	private String badgeNumber;
	/**
	 * $column.comments
	 */
	@TableField(value = "SSN")
	private String ssn;
	/**
	 * $column.comments
	 */
	@TableField(value = "NAME")
	private String name;
	/**
	 * $column.comments
	 */
	@TableField(value = "GENDER")
	private String gender;
	/**
	 * $column.comments
	 */
	@TableField(value = "TITLE")
	private String title;
	/**
	 * $column.comments
	 */
	@TableField(value = "PAGER")
	private String pager;
	/**
	 * $column.comments
	 */
	@TableField(value = "BIRTHDAY")
	private Date birthday;
	/**
	 * $column.comments
	 */
	@TableField(value = "HIREDDAY")
	private Date hiredday;
	/**
	 * $column.comments
	 */
	@TableField(value = "STREET")
	private String street;
	/**
	 * $column.comments
	 */
	@TableField(value = "CITY")
	private String city;
	/**
	 * $column.comments
	 */
	@TableField(value = "STATE")
	private String state;
	/**
	 * $column.comments
	 */
	@TableField(value = "ZIP")
	private String zip;
	/**
	 * $column.comments
	 */
	@TableField(value = "OPHONE")
	private String ophone;
	/**
	 * $column.comments
	 */
	@TableField(value = "FPHONE")
	private String fphone;
	/**
	 * $column.comments
	 */
	@TableField(value = "VERIFICATIONMETHOD")
	private Integer verificationMethod;
	/**
	 * $column.comments
	 */
	@TableField(value = "DEFAULTDEPTID")
	private Integer defaultDeptId;
	/**
	 * $column.comments
	 */
	@TableField(value = "SECURITYFLAGS")
	private Integer securityFlags;
	/**
	 * $column.comments
	 */
	@TableField(value = "ATT")
	private Integer att;
	/**
	 * $column.comments
	 */
	@TableField(value = "INLATE")
	private Integer inlate;
	/**
	 * $column.comments
	 */
	@TableField(value = "OUTEARLY")
	private Integer outearly;
	/**
	 * $column.comments
	 */
	@TableField(value = "OVERTIME")
	private Integer overTime;
	/**
	 * $column.comments
	 */
	@TableField(value = "SEP")
	private Integer sep;
	/**
	 * $column.comments
	 */
	@TableField(value = "HOLIDAY")
	private Integer holiday;
	/**
	 * $column.comments
	 */
	@TableField(value = "MINZU")
	private String minzu;
	/**
	 * $column.comments
	 */
	@TableField(value = "PASSWORD")
	private String password;
	/**
	 * $column.comments
	 */
	@TableField(value = "LUNCHDURATION")
	private Integer lunchDuration;
	/**
	 * $column.comments
	 */
	@TableField(value = "MVerifyPass")
	private String mverifyPass;
	/**
	 * $column.comments
	 */
	@TableField(value = "PHOTO")
	private String photo;
	/**
	 * $column.comments
	 */
	@TableField(value = "Notes")
	private String notes;
	/**
	 * $column.comments
	 */
	@TableField(value = "privilege")
	private Integer privilege;
	/**
	 * $column.comments
	 */
	@TableField(value = "InheritDeptSch")
	private Integer inheritDeptSch;
	/**
	 * $column.comments
	 */
	@TableField(value = "InheritDeptSchClass")
	private Integer inheritDeptSchClass;
	/**
	 * $column.comments
	 */
	@TableField(value = "AutoSchPlan")
	private Integer autoSchPlan;
	/**
	 * $column.comments
	 */
	@TableField(value = "MinAutoSchInterval")
	private Integer minAutoSchInterval;
	/**
	 * $column.comments
	 */
	@TableField(value = "RegisterOT")
	private Integer registerOT;
	/**
	 * $column.comments
	 */
	@TableField(value = "InheritDeptRule")
	private Integer inheritDeptRule;
	/**
	 * $column.comments
	 */
	@TableField(value = "EMPRIVILEGE")
	private Integer eMPRIVILEGE;
	/**
	 * $column.comments
	 */
	@TableField(value = "CardNo")
	private String cardNo;
	/**
	 * $column.comments
	 */
	@TableField(value = "FaceGroup")
	private Integer faceGroup;
	/**
	 * $column.comments
	 */
	@TableField(value = "AccGroup")
	private Integer accGroup;
	/**
	 * $column.comments
	 */
	@TableField(value = "UseAccGroupTZ")
	private Integer useAccGroupTZ;
	/**
	 * $column.comments
	 */
	@TableField(value = "VerifyCode")
	private Integer verifyCode;
	/**
	 * $column.comments
	 */
	@TableField(value = "Expires")
	private Integer expires;
	/**
	 * $column.comments
	 */
	@TableField(value = "ValidCount")
	private Integer validCount;
	/**
	 * $column.comments
	 */
	@TableField(value = "ValidTimeBegin")
	private Date validTimeBegin;
	/**
	 * $column.comments
	 */
	@TableField(value = "ValidTimeEnd")
	private Date validTimeEnd;
	/**
	 * $column.comments
	 */
	@TableField(value = "TimeZone1")
	private Integer timeZone1;
	/**
	 * $column.comments
	 */
	@TableField(value = "TimeZone2")
	private Integer timeZone2;
	/**
	 * $column.comments
	 */
	@TableField(value = "TimeZone3")
	private Integer timeZone3;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCardNo")
	private String iDCardNo;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCardValidTime")
	private String iDCardValidTime;
	/**
	 * $column.comments
	 */
	@TableField(value = "EMail")
	private String eMail;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCardName")
	private String iDCardName;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCardBirth")
	private String iDCardBirth;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCardSN")
	private String iDCardSN;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCardDN")
	private String iDCardDN;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCardAddr")
	private String iDCardAddr;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCardNewAddr")
	private String iDCardNewAddr;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCardISSUER")
	private String iDCardISSUER;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCardGender")
	private Integer iDCardGender;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCardNation")
	private Integer iDCardNation;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCardReserve")
	private String iDCardReserve;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCardNotice")
	private String iDCardNotice;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCard_MainCard")
	private String iDCardMainCard;
	/**
	 * $column.comments
	 */
	@TableField(value = "IDCard_ViceCard")
	private String iDCardViceCard;
	/**
	 * $column.comments
	 */
	@TableField(value = "FSelected")
	private Boolean fSelected;

	
}
