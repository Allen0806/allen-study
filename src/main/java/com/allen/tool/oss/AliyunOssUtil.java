package com.allen.tool.oss;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.allen.tool.string.StringUtil;

/**
 * 阿里云OSS工具类
 * 
 * @author lxt
 * @since 1.0
 *
 */
public class AliyunOssUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(AliyunOssUtil.class);

	/**
	 * OSSClient对象
	 */
	private static OSSClient ossClient;

	/**
	 * 禁止实例化
	 */
	private AliyunOssUtil() {

	}

	static {
		init();
	}

	/**
	 * 上传文件，OSS文件存储路径的组成规则为：系统编码/模块编码/业务编码/当前日期/UUID+文件名。
	 * 系统编码、模块编码在application.properties文件中的配置，业务编码为通过方法参数传入，参数值建议都为小写：
	 * 系统编码对应的参数名：aliyun.oss.file.path.system，
	 * 模块编码对应的参数名：aliyun.oss.file.path.module
	 * 
	 * @param file         待上传的文件
	 * @param bucketName   OSS存储空间，一般为租户ID。只能包括小写字母、数字和短横线（-）；必须以小写字母或者数字开头和结尾，长度必须在3–63字节之间。
	 * @param businessCode 业务编码，比如：ocrcredit-ocr授信，建议所有字符小写
	 * @return OSS文件存储路径
	 */
	public static String upload(File file, String bucketName, String businessCode) {
		if (file == null) {
			LOGGER.error("OSS文件上传失败，给定的文件为空");
			return null;
		}

		if (StringUtil.isBlank(businessCode)) {
			LOGGER.error("OSS文件上传失败，给定的业务编码为空，文件名为：{}", file.getName());
			return null;
		}

		LOGGER.info("OSS文件上传开始：{}", file.getName());

		String filePath = generateOssFilePath(businessCode, file.getName());
		try {

			// 上传文件
			PutObjectResult result = ossClient.putObject(bucketName, filePath, file);
			if (result != null) {
				LOGGER.info("OSS文件上传成功，OSS地址：{}", filePath);
				return filePath;
			}
		} catch (Exception e) {
			LOGGER.error("文件上传失败，文件名：{}", file.getName(), e);
		}
		return null;
	}

	/**
	 * 上传文件，OSS文件存储路径的组成规则为：系统编码/模块编码/业务编码/当前日期/UUID+文件名。
	 * 系统编码、模块编码在application.properties文件中的配置，业务编码为通过方法参数传入，参数值建议都为小写：
	 * 系统编码对应的参数名：aliyun.oss.file.path.system，
	 * 模块编码对应的参数名：aliyun.oss.file.path.module
	 * 
	 * @param in           文件流
	 * @param fileName     文件名，不包含路径
	 * @param bucketName   OSS存储空间，一般为租户ID。只能包括小写字母、数字和短横线（-）；必须以小写字母或者数字开头和结尾，长度必须在3–63字节之间。
	 * @param businessCode 业务编码，比如：ocrcredit-ocr授信，建议所有字符小写，用于拼接OSS文件名
	 * @return OSS文件存储路径
	 */
	public static String upload(InputStream in, String fileName, String bucketName, String businessCode) {
		if (StringUtil.isBlank(fileName)) {
			LOGGER.error("OSS文件上传失败，给定的文件名为空");
			return null;
		}

		if (in == null) {
			LOGGER.error("OSS文件上传失败，给定的文件流为空，文件名为：{}", fileName);
			return null;
		}

		if (StringUtil.isBlank(businessCode)) {
			LOGGER.error("OSS文件上传失败，给定的业务编码为空，文件名为：{}", fileName);
			return null;
		}

		LOGGER.info("OSS文件上传开始：{}", fileName);

		String filePath = generateOssFilePath(businessCode, fileName);
		try {
			// 上传文件
			PutObjectResult result = ossClient.putObject(bucketName, filePath, in);
			if (result != null) {
				LOGGER.info("OSS文件上传成功，OSS地址：{}", filePath);
				return filePath;
			}
		} catch (Exception e) {
			LOGGER.error("文件上传失败，文件名：{}", fileName, e);
		}
		return null;
	}

	/**
	 * 下载文件
	 * 
	 * @param ossFilePath OSS文件存储路径
	 * @param filePaht    文件下载后的存储路径，包含文件名
	 * @param bucketName  OSS存储空间，一般为租户ID。只能包括小写字母、数字和短横线（-）；必须以小写字母或者数字开头和结尾，长度必须在3–63字节之间。
	 * @return 要下载的文件
	 */
	public static boolean download(String ossFilePath, String filePath, String bucketName) {
		if (StringUtil.isBlank(ossFilePath)) {
			LOGGER.error("OSS文件上传失败，给定的OSS文件存储路径为空，文件下载后的存储路径为：{}", filePath);
			return false;
		}
		if (StringUtil.isBlank(filePath)) {
			LOGGER.error("OSS文件上传失败，给定的文件下载后的存储路径为空，OSS文件存储路径为：{}", ossFilePath);
			return false;
		}

		ossClient.getObject(new GetObjectRequest(bucketName, ossFilePath), new File(filePath));
		return true;
	}

	/**
	 * 下载文件
	 * 
	 * @param ossFilePath OSS文件存储路径
	 * @param bucketName  OSS存储空间，一般为租户ID。只能包括小写字母、数字和短横线（-）；必须以小写字母或者数字开头和结尾，长度必须在3–63字节之间。
	 * @return 要下载的文件流
	 */
	public static InputStream download(String ossFilePath, String bucketName) {
		if (StringUtil.isBlank(ossFilePath)) {
			LOGGER.error("OSS文件上传失败，给定的OSS文件存储路径为空");
			return null;
		}
		OSSObject ossObject = ossClient.getObject(new GetObjectRequest(bucketName, ossFilePath));
		if (ossObject != null) {
			return ossObject.getObjectContent();
		}
		return null;
	}

	/**
	 * 生成下载文件的URL，该URL在给定的有效时间内有效
	 * 
	 * @param ossFilePath OSS文件存储路径
	 * @param bucketName  OSS存储空间，一般为租户ID。只能包括小写字母、数字和短横线（-）；必须以小写字母或者数字开头和结尾，长度必须在3–63字节之间。
	 * @param duration    有效时间，单位：毫秒
	 * @return 下载文件的URL
	 */
	public URL generatePresignedUrl(String ossFilePath, String bucketName, final long duration) {
		if (StringUtil.isBlank(ossFilePath)) {
			LOGGER.error("生成下载文件的URL失败，给定的OSS文件存储路径为空");
			return null;
		}
		if (duration <= 0) {
			LOGGER.error("生成下载文件的URL失败，给定的有效时间不正确，OSS文件存储路径为空为：{}，有效时间为{}", ossFilePath, duration);
			return null;
		}
		long time = System.currentTimeMillis() + duration;
		Date expiration = new Date(time);
		return ossClient.generatePresignedUrl(bucketName, ossFilePath, expiration);
	}

	/**
	 * 初始化ossClient
	 */
	private static void init() {
		if (ossClient != null) {
			return;
		}
		String endpoint = OssConstantProperties.OSS_END_POINT;
		String accessKeyId = OssConstantProperties.OSS_ACCESS_KEY_ID;
		String accessKeySecret = OssConstantProperties.OSS_ACCESS_KEY_SECRET;
 		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

		// JVM停止或重启时，关闭ossClient
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				ossClient.shutdown();
			}
		});
	}

	/**
	 * 生成OSS文件存储路径
	 * 
	 * @param fileName
	 */
	private static String generateOssFilePath(String businessCode, String fileName) {
		String filePathSytem = OssConstantProperties.OSS_FILE_PATH_SYSTEM;
		String filePathModule = OssConstantProperties.OSS_FILE_PATH_MODULE;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = format.format(new Date());

		// 创建文件路径：系统编码/模块编码/业务编码/当前日期/UUID+文件名
		String filePath = filePathSytem + "/" + filePathModule + "/" + businessCode + "/" + dateStr
				+ UUID.randomUUID().toString().replace("-", "") + "-" + fileName;
		return filePath;
	}
}
