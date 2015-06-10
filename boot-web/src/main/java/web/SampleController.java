package web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.User;

@Controller
public class SampleController {
	@RequestMapping("/")
	@ResponseBody
	String home(){
		return "hello world";
	}
	
	@RequestMapping("/user")
	@ResponseBody
	User getUser(){
		return new User("홍길동" , 18);
	}
	
	@RequestMapping("/users")
	@ResponseBody
	List<User> getUserList(){
		List<User> list = new ArrayList<User>();
		
		list.add(new User("홍길동" , 18));
		list.add(new User("김철수" , 17));
		
		return list;
	}
}
