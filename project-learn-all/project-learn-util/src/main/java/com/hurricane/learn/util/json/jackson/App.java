package com.hurricane.learn.util.json.jackson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotationMap;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hurricane.learn.util.json.pojo.Product;
import com.hurricane.learn.util.json.pojo.User;
import com.hurricane.learn.util.json.util.ReName;

public class App {

	public static void main(String[] args) throws Exception{
		App app = new App();
		app.testObjectString();
		app.testSerialization();
		app.testJsonNodeAlone();
	}

	/**
	 * JsonNode的使用
	 * 对应Json对象为ObjectNode
	 * 对应Json数组为ArrayNode
	 */
	private void testJsonNodeAlone() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("name", "小明");
		node.put("name", "小明");
		node.put("age", 12);
		System.out.println(node.toString());
		System.out.println(node.get("name"));//asText()用来去除双引号
		System.out.println(node.get("name").asText());

		ObjectNode node2 = mapper.createObjectNode();
		node2.put("name", "小明");
		node2.put("name", "小明2");//会覆盖
		node2.put("age", 12);
		ArrayNode arrayNode = mapper.createArrayNode();//数组节点使用
		arrayNode.add(node);
		arrayNode.add(node2);
		arrayNode.add("aaa");
		System.out.println(arrayNode.toString());
		JsonNode path = arrayNode.path(0);//获取第几个节点
		System.out.println(path);
		//遍历节点内容
		for (JsonNode jsonNode : arrayNode) {
			System.out.println(jsonNode);
		}
		for (JsonNode jsonNode : arrayNode) {
			System.out.println(jsonNode);
		}
	}

	/**
	 * 序列化writeValue的使用
	 * @throws Exception
	 */
	private void testSerialization() throws Exception {
		User user = new User();
		user.setName("hurricane");
		user.setAge(12);
		user.setId(0);//注释掉这一行测试mapper.setSerializationInclusion(Include.NON_EMPTY)
		List<Product> products = new ArrayList<Product>();
		for (int i = 0; i < 4; i++) {
			Product product = new Product(0,"product"+i,i*22.2f);
			products.add(product);
		}
		user.setProducts(products);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);//指定输出格式是否美化
		//mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
		//输出为流时，对输出的字段进行适当修改
		//只是修改字段名时，使用@JsonProperty注解更方便，并且@JsonProperty优先级比这里指定的高
		mapper.setPropertyNamingStrategy(new PropertyNamingStrategy(){
			@Override
			public String nameForGetterMethod(MapperConfig<?> config,
					AnnotatedMethod method, String defaultName) {
				//修改带自定义注解的属性名
				AnnotationMap allAnnotations = method.getAllAnnotations();
				if (allAnnotations!=null&&allAnnotations.size()>0) {
					ReName reName = allAnnotations.get(ReName.class);
					return super.nameForGetterMethod(config, method, reName.name());
				}

				return super.nameForGetterMethod(config, method, defaultName);
			}
		});
		mapper.setSerializationInclusion(Include.NON_EMPTY);//是否输出包含内容为空的属性
		mapper.writeValue(System.out, user);
	}


	/**
	 * 对象与字符串之间的相互转换，使用到了ObjectMapper
	 * @throws Exception
	 */
	private void testObjectString() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String src = "{\"name\":\"hurricane\",\"age\":20,\"sex\":\"man\"}";//不能使用'，必须使用\"
		//字符串中属性缺失不会报异常，属性多余会报异常
		//User中没有sex的属性，若不加下面这行代码，会抛出异常，使得转换无法正常进行
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		User user = mapper.readValue(src, User.class);
		System.out.println(user);
		user.setId(1000);
		src = mapper.writeValueAsString(user);
		System.out.println(src);
	}




}
