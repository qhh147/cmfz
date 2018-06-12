package com.baizhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

@Controller
public class CodeAction{
	@RequestMapping(value="/code")
	@ResponseBody
	public void execute(ServletOutputStream out,HttpSession session) throws Exception {
		CreateValidateCode cvc=new CreateValidateCode();
		String code = cvc.getCode();
		session.setAttribute("code",code);
		cvc.write(out);
	}
	
}
