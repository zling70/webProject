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
		System.out.println("ͨ���˿�����");
		return new ModelAndView("test1","message",message);
	}
	
	@RequestMapping("/testContext")
	public ModelAndView testContext(HttpServletRequest request,HttpServletResponse response){
		String message="hello world,Spring4.0.6  ";
		System.out.println("ͨ���˿�����");
		String json=request.getParameter("user");
		System.out.println("����ֱ�ӻ���������"+json);
		return new ModelAndView("test2_context","message",json);
	}
	
	@RequestMapping(value="/getuser",method=RequestMethod.POST)
	public ModelAndView testForm(@ModelAttribute("pojo") User user){
		String message="hello world,Spring4.0.6  ";
		System.out.println("ͨ���˿�����");
		return new ModelAndView("test2_context","message",user.getUsername());
	}
	
	
	//�����û������json��ʽ����
	@RequestMapping("/finduser")
	public @ResponseBody User getusers(){
		String message="hello world,Spring4.0.6  ";
		System.out.println("ͨ���˿�����");
		User user=new User();
		user.setId("001");
		user.setUsername("����");
		user.setPassword("123123");
		user.setEmail("liubei@163.com");
		Address addr=new Address();
		addr.setUid("001");
		addr.setAddr("������");
		user.getAlist().add(addr);
		Address addr2=new Address();
		addr2.setUid("001");
		addr2.setAddr("̩ɽ·");
		user.getAlist().add(addr2);
		return user;
	}
	//ֱ�ӷ����û��б�
	@RequestMapping("/findlist")
	public @ResponseBody List<User> getuserlist(){
		String message="hello world,Spring4.0.6  ";
		System.out.println("ͨ���˿�����");
		User user=new User();
		user.setId("001");
		user.setUsername("����");
		user.setPassword("123123");
		user.setEmail("liubei@163.com");
		List<User> list=new ArrayList<User>();
		list.add(user);
		
		User user2=new User();
		user2.setId("002");
		user2.setUsername("�ŷ�");
		user2.setPassword("123123");
		user2.setEmail("zhangfei@163.com");
		list.add(user2);
		return list;
	}
	@RequestMapping(value="/createuser",method=RequestMethod.POST)
	public @ResponseBody User addUser(@ModelAttribute("pojo") User user){
		System.out.println("ͨ���˿�����");
		System.out.println("���ú�̨���������û����ݵ�table,�����ظճɹ�������");
		System.out.println(user);
		return user;
	}
	
	@RequestMapping(value="/redirectone", method=RequestMethod.GET)  
	public String redirectone(RedirectAttributes redirectAttrs) {  
	    redirectAttrs.addAttribute("date", new LocalDate(2014, 8, 22));  // Appended as a query parameter  
	    redirectAttrs.addAttribute("account", "a123");  // Used as URI template variable 
	    //redirectAttrs.addAttribute("username","scott");//����ŵ����������
	    //redirectAttrs.addAttribute("password","tiger");
	    redirectAttrs.addFlashAttribute("accounta", "a1234");//
	    System.out.println(new LocalDate(2014, 8, 22).toString());
	    System.out.println("��ת���Ƚ���ĵ�һ��controller������");
	    return "redirect:/spmvc/{account}";  //������·���а����ɱ���ֵ
	    //Ҳ����ֱ��ת����һ��jsp(redirected.jsp)ҳ��,����jsp�в��ܻ�ȡֵ${requestScop.account}
	    //return "redirect/redirected";
	}  
	  
    //{account}�ɱ�����·��������ʱ��̬ȷ��
	@RequestMapping(value="/{account}", method=RequestMethod.GET)
	//show()������·���н�������account
	public String show(@PathVariable String account, @RequestParam(required=false) LocalDate date) {
		System.out.println("����ĵڶ���controller������ǰ������ת��ҳ��");
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
		System.out.println("������forwardת��������"+account);
	    return "forward:/spmvc/forwarded";  
	}
	
	@RequestMapping(value="/forwarded", method=RequestMethod.GET)
	public String goforward(@RequestParam(required=false) String account) {
		System.out.println("����ĵڶ���controller������ǰ����ת����ҳ��");
		System.out.println("�������������"+account);
	    return "redirect/forwarded";  
	}
}
