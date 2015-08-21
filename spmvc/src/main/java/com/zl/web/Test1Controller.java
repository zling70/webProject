package com.zl.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.zl.pojo.Address;
import com.zl.pojo.User;

@Controller
@RequestMapping("/spmvc")
public class Test1Controller {
	@RequestMapping("/test")
	public ModelAndView helloworld(){
		String message="hello world,Spring4.0.6  ";
		System.out.println("通过了控制器");
		return new ModelAndView("test1","message",message);
	}
	
	@RequestMapping("/testContext")
	public ModelAndView testContext(HttpServletRequest request,HttpServletResponse response){
		String message="hello world,Spring4.0.6  ";
		System.out.println("通过了控制器");
		String json=request.getParameter("user");
		System.out.println("可以直接获得请求参数"+json);
		return new ModelAndView("test2_context","message",json);
	}
	
	@RequestMapping(value="/getuser",method=RequestMethod.POST)
	public ModelAndView testForm(@ModelAttribute("pojo") User user){
		String message="hello world,Spring4.0.6  ";
		System.out.println("通过了控制器");
		return new ModelAndView("test2_context","message",user.getUsername());
	}
	
	
	//返回用户对象的json格式数据
	@RequestMapping("/finduser")
	public @ResponseBody User getusers(){
		String message="hello world,Spring4.0.6  ";
		System.out.println("通过了控制器");
		User user=new User();
		user.setId("001");
		user.setUsername("刘备");
		user.setPassword("123123");
		user.setEmail("liubei@163.com");
		Address addr=new Address();
		addr.setUid("001");
		addr.setAddr("神龙城");
		user.getAlist().add(addr);
		Address addr2=new Address();
		addr2.setUid("001");
		addr2.setAddr("泰山路");
		user.getAlist().add(addr2);
		return user;
	}
	//直接返回用户列表
	@RequestMapping("/findlist")
	public @ResponseBody List<User> getuserlist(){
		String message="hello world,Spring4.0.6  ";
		System.out.println("通过了控制器");
		User user=new User();
		user.setId("001");
		user.setUsername("刘备");
		user.setPassword("123123");
		user.setEmail("liubei@163.com");
		List<User> list=new ArrayList<User>();
		list.add(user);
		
		User user2=new User();
		user2.setId("002");
		user2.setUsername("张飞");
		user2.setPassword("123123");
		user2.setEmail("zhangfei@163.com");
		list.add(user2);
		return list;
	}
	@RequestMapping(value="/createuser",method=RequestMethod.POST)
	public @ResponseBody User addUser(@ModelAttribute("pojo") User user){
		System.out.println("通过了控制器");
		System.out.println("调用后台方法插入用户数据到table,并返回刚成功的数据");
		System.out.println(user);
		return user;
	}
	
	@RequestMapping(value="/redirectone", method=RequestMethod.GET)  
	public String redirectone(RedirectAttributes redirectAttrs) {  
	    redirectAttrs.addAttribute("date", new LocalDate(2014, 8, 22));  // Appended as a query parameter  
	    redirectAttrs.addAttribute("account", "a123");  // Used as URI template variable 
	    //redirectAttrs.addAttribute("username","scott");//都会放到请求参数中
	    //redirectAttrs.addAttribute("password","tiger");
	    redirectAttrs.addFlashAttribute("accounta", "a1234");//
	    System.out.println(new LocalDate(2014, 8, 22).toString());
	    System.out.println("跳转首先进入的第一个controller方法！");
	    return "redirect:/spmvc/{account}";  //在请求路径中包含可变量值
	    //也可以直接转发到一个jsp(redirected.jsp)页面,但在jsp中不能获取值${requestScop.account}
	    //return "redirect/redirected";
	}  
	  
    //{account}可变请求路径，请求时动态确定
	@RequestMapping(value="/{account}", method=RequestMethod.GET)
	//show()方法从路径中解析变量account
	public String show(@PathVariable String account, @RequestParam(required=false) LocalDate date) {
		System.out.println("进入的第二个controller方法，前往被跳转的页面");
	    return "redirect/redirected";  
	}
	
	@RequestMapping(value="/uriComponentsBuilder", method=RequestMethod.GET)  
	public String uriComponentsBuilder() {  
	    String date =new LocalDate(2014, 8, 21).toString();  
	    UriComponents redirectUri = UriComponentsBuilder.fromPath("/redirect/{account}").queryParam("date", date)  
	            .build().expand("a123").encode();  
	    return "redirect:" + redirectUri.toUriString();  
	}  
	  	  
	// http://127.0.0.1:8010/redirect/forward  
	@RequestMapping(value="/forward", method=RequestMethod.GET)  
	public String forward(@RequestParam(required=false) String account) { 
		System.out.println("进入了forward转发方法！"+account);
	    return "forward:/spmvc/forwarded";  
	}
	
	@RequestMapping(value="/forwarded", method=RequestMethod.GET)
	public String goforward(@RequestParam(required=false) String account) {
		System.out.println("进入的第二个controller方法，前往被转发的页面");
		System.out.println("请求参数还在吗："+account);
	    return "redirect/forwarded";  
	}
}
