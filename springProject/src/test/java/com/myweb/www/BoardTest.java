package com.myweb.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
//SpringJUnit4ClassRunner.class 클래스가 실행하도록 도와주겠다.
@ContextConfiguration(classes = {com.myweb.www.config.RootConfig.class})
//해당하는 클레스를 가져오겠다
public class BoardTest {
//테스트 할때는 라이브러리를 넣어줘야함

	@Inject
	private BoardDAO bdao;
	
	@Test
	public void insertBoard() {
		for(int i =0; i < 300; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("Test title"+i);
			bvo.setWriter("tester"+(int)((Math.random()*30)+1));
			bvo.setContent("test content"+i);
			bdao.register(bvo);
		}
	}

}
