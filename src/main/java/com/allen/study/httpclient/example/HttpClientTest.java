/**
 * 
 */
package com.allen.study.httpclient.example;

import java.io.InputStream;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

/**
 * @author Allen
 *
 */
public class HttpClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		testEntity();
	}
	
	public static void testEntity() throws Exception {
		StringEntity stringEntity = new StringEntity("test entity", ContentType.create("text/plain", "UTF-8"));
		System.out.println(stringEntity.getContentType());
		System.out.println(stringEntity.getContentLength());
		System.out.println(EntityUtils.toString(stringEntity));
		System.out.println(EntityUtils.toByteArray(stringEntity).length);
		InputStream in = stringEntity.getContent();
		
		byte[] byteArray = in.readAllBytes();
		System.out.println(byteArray.length);
		System.out.println(new String(byteArray));
	}

}
