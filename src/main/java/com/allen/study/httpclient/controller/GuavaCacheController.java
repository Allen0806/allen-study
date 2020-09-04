/**
 * 
 */
package com.allen.study.httpclient.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 测试浏览器缓存
 * 
 * @author Allen
 *
 */
@RestController
public class GuavaCacheController {
	
	Cache<String, Long> lastModifiedCache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).build();
	
	@RequestMapping("cache")
	public ResponseEntity<String> cache(
			@RequestHeader(value = "If-Modified-Since", required = false) Date ifModifiedSince) throws Exception {
		DateFormat gmtDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);

		long lastModifiedMillis = getLastModified() / 1000 * 1000;

		long now = System.currentTimeMillis() / 1000 * 1000;
		long maxAge = 20;

		if (ifModifiedSince != null && ifModifiedSince.getTime() == lastModifiedMillis) {
			MultiValueMap<String, String> headers = new HttpHeaders();
			headers.add("Date", gmtDateFormat.format(new Date(now)));
			headers.add("Expires", gmtDateFormat.format(new Date(now + maxAge * 1000)));
			headers.add("Cache-Control", "max-age=" + maxAge);
			return new ResponseEntity<String>(headers, HttpStatus.NOT_MODIFIED);
		}

		String body = "<a href=''>点击访问当前链接</a>";
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Date", gmtDateFormat.format(new Date(now)));
		headers.add("Last-Modified", gmtDateFormat.format(new Date(lastModifiedMillis)));
		headers.add("Expires", gmtDateFormat.format(new Date(now + maxAge * 1000)));
		headers.add("Cache-Control", "max-age=" + maxAge);
		return new ResponseEntity<String>(body, headers, HttpStatus.OK);
	}
	
	@RequestMapping("test")
	public ResponseEntity<String> test(HttpServletRequest request) throws Exception {
		DateFormat gmtDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		long now = System.currentTimeMillis() / 1000 * 1000;
		
		//Thread.sleep(10*1000);
		
		System.out.println("*******header = " + request.getHeaderNames());

		String body = "test";
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Date", gmtDateFormat.format(new Date(now)));
		headers.add("Connection", "keep-alive");
		headers.add("Keep-Alive", "timeout=50");
		return new ResponseEntity<String>(body, headers, HttpStatus.OK);
	}

	public long getLastModified() throws ExecutionException {
		return lastModifiedCache.get("lastModified", () -> {
			return System.currentTimeMillis();
		});
	}
}
