package com.allen.tool.oss;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * 阿里云OSS服务器初始化参数
 * 
 * @author lxt
 * @since 1.0
 *
 */
public class OssConstantProperties implements InitializingBean {

	/**
	 * OSS的endpoint
	 */
	@Value("${aliyun.oss.endpoint}")
	private String endPoint;

	/**
	 * OSS访问账号ID
	 */
	@Value("${aliyun.access.key.id}")
	private String accessKeyId;

	/**
	 * OSS访问账号密钥
	 */
	@Value("${aliyun.access.key.secret}")
	private String accessKeySecret;

	/**
	 * 生成OSS文件路径的系统编码，如：preloan-贷前，postloan-贷后
	 */
	@Value("${aliyun.oss.file.path.system}")
	private String systemCode;

	/**
	 * 生成OSS文件路径的模块编码，如：cred-授信，urm-用户
	 */
	@Value("${aliyun.oss.file.path.module}")
	private String moduleCode;

	/**
	 * OSS的endpoint
	 */
	public static String OSS_END_POINT;

	/**
	 * OSS访问账号ID
	 */
	public static String OSS_ACCESS_KEY_ID;

	/**
	 * OSS访问账号密钥
	 */
	public static String OSS_ACCESS_KEY_SECRET;

	/**
	 * 生成OSS文件路径的系统编码，如：preloan-贷前，postloan-贷后
	 */
	public static String OSS_FILE_PATH_SYSTEM;

	/**
	 * 生成OSS文件路径的模块编码，如：cred-授信，urm-用户
	 */
	public static String OSS_FILE_PATH_MODULE;

	@Override
	public void afterPropertiesSet() throws Exception {
		OSS_END_POINT = endPoint;
		OSS_ACCESS_KEY_ID = accessKeyId;
		OSS_ACCESS_KEY_SECRET = accessKeySecret;
		OSS_FILE_PATH_SYSTEM = systemCode;
		OSS_FILE_PATH_MODULE = moduleCode;
	}
}
