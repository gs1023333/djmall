package com.dj.mall.model.base;

public class SystemConstant {


	/**
	 * 删除状态,已删除
	 */
	public static final Integer IS_DEL = 0;
	
	/**
	 * 删除状态,未删除
	 */
	public static final Integer IS_NOT_DEL = 1;
	
	/**
	 * 每页显示条数
	 */
	public static final Integer PAGE_SIZE = 5;
	
	/**
	 * 错误提示
	 */
	public static final String ERROR_MSG = "服务器异常,请稍后重试!";
	/**
	 * 验证码五分钟
	 */
	public static final Integer VALID_5 = 5;
	/**
	 * 用户有效状态
	 */
	public static final Integer STATUS_OK = 1;
	/**
	 * 用户无效状态
	 */
	public static final Integer STATUS_ERROR = 0;
	/**
	 * 用户已激活状态
	 */
	public static final Integer ACTIVATE = 1;
	/**
	 * 用户未激活状态
	 */
	public static final Integer ACTIVATE_NOT = 2;
	/**
	 * 用户等级:管理员
	 */
	public static final Integer LEVEL_ADMIN = 1;
	/**
	 * 用户等级:商户
	 */
	public static final Integer LEVEL_SHOP = 2;
	/**
	 * 用户等级:买家
	 */
	public static final Integer LEVEL_BUYER = 3;
	/**
	 * 用户等级:无
	 */
	public static final Integer LEVEL_NO = 4;
	/**
	 * 用户性别:男
	 */
	public static final Integer SEX_MAN = 1;
	/**
	 * 用户性别:女
	 */
	public static final Integer SEX_WOMAN = 2;

	
}
