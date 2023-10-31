package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.security.AuthVO;
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
   
   @GetMapping({"/detail", "modify"})
	public String detial(Model m, @RequestParam("email")String email) {
		log.info(">>>>> MemverVO" + email);
		MemberVO mvo = msv.MemberDetail(email);
		
		m.addAttribute("mvo", mvo);
		return "/member/modify";
	}
   
   @PostMapping("/modify")
   public String modify(MemberVO mvo, HttpServletRequest request, HttpServletResponse response) {
	   log.info("email >>>> " + mvo);
	   mvo.setPwd(bcEncoder.encode(mvo.getPwd())); //암호화해서 넣음
	   int isOK = msv.MemberModify(mvo);
		logout(request, response);
	   
	   return "index";
   }
   
   
   @GetMapping("/list")
   public void list(Model m) {
	   
	   List<AuthVO> Mlist = msv.MeberList();
	   m.addAttribute("Mlist", Mlist);
	   
   }
   
   @GetMapping("/remove")
   public String remove(@RequestParam("email")String email, HttpServletRequest request, HttpServletResponse response) {
	   log.info(">>>> remove email >>>" + email);
		int isOK = msv.remove(email);
		logout(request, response);
		return "index";	
   }
   private void logout(HttpServletRequest request, HttpServletResponse response) {
	      //사용자 정보를 찾는 인자 ?
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      new SecurityContextLogoutHandler().logout(request, response, auth);
	   }
}