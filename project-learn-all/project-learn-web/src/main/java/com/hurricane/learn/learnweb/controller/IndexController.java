package com.hurricane.learn.learnweb.controller;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.hurricane.learn.learnweb.pojo.User;

@Controller
@RequestMapping("/index")
public class IndexController {
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	@RequestMapping("home")
	private String index(Model model) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json1 = "{\"a\":{\"b\":{\"c\":\"1.json\"}}}";
		JsonNode readTree = mapper.readTree(json1);
		System.out.println(readTree);
		model.addAttribute("jsonVal", readTree);
		User user = new User();
		user.setAge(12);
		user.setName("hurricane");
		user.setId(1);
		model.addAttribute("user", user);
		return "index";
	}
	
	@RequestMapping("getData")
	@ResponseBody
	public Object getData() {
		User user = new User();
		user.setAge(12);
		user.setName("hurricane");
		user.setId(1);
		user.setRecord(new Date());
		return user;
	}
	
	@RequestMapping("getData2")
	@ResponseBody
	public Object getData2(String callback) {
		logger.trace("callback is "+callback);
//		String result1 = callback+"({\"a\":{\"b\":{\"c\":\"1.json\"}}})";
		User user = new User();
		user.setAge(12);
		user.setName("hurricane");
		user.setId(1);
		user.setRecord(new Date());
//		JSONWrappedObject object = new JSONWrappedObject(callback, "", user);
		MappingJacksonValue value = new MappingJacksonValue(user);
		value.setJsonpFunction(callback);
		return value;
	}
}
