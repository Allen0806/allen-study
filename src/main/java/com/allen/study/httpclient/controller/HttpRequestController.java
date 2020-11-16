package com.allen.study.httpclient.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allen.study.httpclient.model.Person;

@RestController
public class HttpRequestController {

	/**
	 * 通过参数名接收参数，适用于get及post请求。
	 * get请求方式：http://localhost:8080/addPerson1?firstName=John&lastName=James;
	 * post请求方式：form方式
	 * 
	 * @param firstName 名
	 * @param lastName  姓
	 * @return 成功标记
	 */
	@RequestMapping("addPerson1")
	public String addPerson1(String firstName, String lastName) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		System.out.println(person);
		return "success";
	}

	/**
	 * 通过request接收参数，适用于get及post请求。
	 * get请求方式：http://localhost:8080/addPerson1?firstName=John&lastName=James;
	 * post请求方式：form方式
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("addPerson2")
	public String addPerson2(HttpServletRequest request) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		System.out.println(person);
		return "success";
	}

	/**
	 * 通过Bean接收参数，适用于get及post请求。
	 * get请求方式：http://localhost:8080/addPerson1?firstName=John&lastName=James;
	 * post请求方式：form方式
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("addPerson3")
	public String addPerson3(Person person) {
		System.out.println(person);
		return "success";
	}

	/**
	 * 通过Bean接收Json，适用于post请求。
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("addPerson4")
	public String addPerson4(@RequestBody Person person) {
		System.out.println(person);
		return "success";
	}

	/**
	 * 通过Map接收Json，适用于post请求。
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("addPerson5")
	public String addPerson5(@RequestBody Map<String, String> paramMap) {
		String firstName = paramMap.get("firstName");
		String lastName = paramMap.get("lastName");
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		System.out.println(person);
		return "success";
	}

	/**
	 * 通过@PathVariable获取路径中的参数，适用于get请求。
	 * get请求方式：http://localhost:8080/addPerson1/John/James;
	 * 
	 * @param firstName 名
	 * @param lastName  姓
	 * @return 成功标记
	 */
	@RequestMapping(value = "addPerson6/{firstName}/{lastName}", method = RequestMethod.GET)
	public String addPerson6(@PathVariable String firstName, @PathVariable String lastName) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		System.out.println(person);
		return "success";
	}

	/**
	 * 用注解@RequestParam绑定请求参数到方法入参，适用于get及post请求
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	@RequestMapping("addPerson7")
	public String addPerson7(@RequestParam(value = "first") String firstName,
			@RequestParam(value = "last") String lastName) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		System.out.println(person);
		return "success";
	}
}
