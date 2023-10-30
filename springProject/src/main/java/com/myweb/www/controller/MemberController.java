package com.myweb.www.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/member/**")
@Controller
public class MemberController {

   @Inject
   private BCryptPasswordEncoder bcEncoder;
   
   @Inject
   private MemberService msv;
   
   @GetMapping("/register")
   public void register() {
   }
   
   @PostMapping("/register")
   public String register(MemberVO mvo) {
      mvo.setPwd(bcEncoder.encode(mvo.getPwd())); //암호화해서 넣음
      log.info(">>>>register >> mvo{} >" +mvo);
      int isOk = msv.register(mvo);
      return "index";
   }
   
   @GetMapping("/login")
   public void login() {
   }
   
   @PostMapping("/login")
   public String loginPost(HttpServletRequest request, RedirectAttributes re) {
	   //로그인이 실패시 다시 로그인 페이지로 돌오아 오류 메세지 전송 
	   //다시 로그인 유도
	   re.addAttribute("email", request.getAttribute("email"));
	   re.addAttribute("errMsg", request.getAttribute("errMsg"));
	   return "redirect:/member/login";
   }
   
   @GetMapping("/list")
	public void list(Model m, MemberVO mvo) {
		//pagingVO 이거는 mapper에 limit을 하기위해 받음 
		log.info(">>>>> MemverVO" + mvo);
		m.addAttribute("list", mvo.memberList(mvo));
		
	}
	
   
   
}