package com.allen.study.thymeleaf.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.allen.study.thymeleaf.user.model.UserBO;
import com.allen.tool.json.JsonUtil;
import com.allen.tool.string.StringUtil;

/**
 * @author Allen
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/{id}")
	public String getUser(@PathVariable Long id, Model model) {
		UserBO user = new UserBO();
		user.setId(id);
		user.setUserName("刘德华");
		user.setAge(50);
		model.addAttribute("user", user);

		return "/user/detail";
	}

	@RequestMapping("/list")
	public String listUser(Model model) {
		List<UserBO> userList = new ArrayList<UserBO>();
		for (int i = 0; i < 9; i++) {
			UserBO dto = new UserBO();
			dto.setId((long) i);
			dto.setUserName("pepstack-" + i);
			dto.setAge(20 + i);
			userList.add(dto);
		}
		model.addAttribute("users", userList);

		return "/user/list";
	}

	@RequestMapping("/formtext")
	public String generate(@ModelAttribute UserBO user, Model model) {
		if(user != null) {
			String jsonStr = user.getUserName();
			if(StringUtil.isNotBlank(jsonStr)) {
				Map<String, Object> jsonMap = JsonUtil.json2Map(jsonStr);
				jsonStr = JsonUtil.object2Json(jsonMap);
				System.out.println("*******************jsonstr="+ jsonStr);
				jsonStr = DigestUtils.md5Hex(jsonStr + "c85ffe4d461ebed31291c087dcd20a8f");
				System.out.println("###############sign=" + jsonStr);
				user.setAddress(jsonStr);;
			}
			model.addAttribute("user", user);
		}
		return "/user/formtext";
	}

}
