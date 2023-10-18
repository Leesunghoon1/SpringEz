package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board/*") // 매핑
@Slf4j
public class BoardController {
// 폴더명 : board / mapping : board
	//mapping => /board/register
	//목적지 -> /board/register
	
	
	@Inject // 언어테이션으로 new 대신 생성해줌
	private BoardService bsv;
	
	@GetMapping("/register")
	public String boardregisterGet() {
		return "/board/register";
	}
	
	@PostMapping("/register")
	public String register(BoardVO bvo) {
		log.info(">>>>>>>> bvo "+bvo);
		int isOK = bsv.register(bvo);
		log.info(">>>> board register >>"+(isOK>0? "ok" : "fail"));
		return "index";
	}
	
	@GetMapping("/list")
	public String list(Model model, BoardVO bvo) {
		//items="${list }" 쓰려고 model 객체로 보냄
		log.info(">>>>>>>> list bvo "+bvo);	
		List<BoardVO> list = bsv.getList(bvo);
		
		model.addAttribute("list", list);
		log.info(">>>>>>>> list model "+list);
		return "/board/list"; 
	}
	
	
	
	
	@GetMapping({"/detail", "/modify"})
	public void detail(Model model, @RequestParam("bno")int bno) {
		log.info("디테일 bno확인 " + bno);
		BoardVO bvo = bsv.getDetail(bno);
		log.info("디테일 확인 bvo" + bvo);
		model.addAttribute("bvo", bvo);
		
	}
	
	@PostMapping("/modify")
	public String modify(Model model, BoardVO bvo) {
		log.info("모디파이 bno 확인" + bvo);
		int isOK = bsv.postModify(bvo);
		model.addAttribute("bvo", bvo);
		
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")int bno, RedirectAttributes reAttr) {
		log.info(">>>> remove bno >>>" + bno);
		int isOK = bsv.remove(bno);
		return "redirect:/board/list";	
	}
}
