package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Service // 서비스
@Slf4j
public class BoardServiceImpl implements BoardService{
		
	@Inject
	private BoardDAO bdao;

	@Override
	public int register(BoardVO bvo) {
		log.info("register chaeak 2");
		return bdao.register(bvo);
	}

	/*
	 * @Override public List<BoardVO> getList() { log.info("list cheack 2"); return
	 * bdao.getList(); }
	 */


	@Override
	public BoardVO getDetail(int bno) {
		log.info("디테일 체크 2");
		
		int cnt = 1;
		bdao.readcount(bno, cnt);
		return bdao.getDetail(bno);
	}

	@Override
	public int postModify(BoardVO bvo) {
		log.info("모디파이 체크 2");
		return bdao.postModify(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info("삭제 체크2");
		return bdao.remove(bno);
	}

	@Override
	public List<BoardVO> getList(PagingVO pagingVO) {
		log.info("list cheack 2");
		return bdao.getList(pagingVO);
	}

	@Override
	public int getTotalCount(PagingVO pagingVO) {

		return bdao.getTotalCount(pagingVO);
	}
	


	
}
