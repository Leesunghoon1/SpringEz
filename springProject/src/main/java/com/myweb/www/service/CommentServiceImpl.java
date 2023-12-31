package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Inject
	private CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		
		return cdao.insert(cvo);
	}

	/*
	 * @Override public List<CommentVO> getList(int bno) { return cdao.getList(bno);
	 * }
	 */

	@Override
	public int remove(int cno) {
 
		return cdao.remove(cno);
	}

	@Override
	public int edit(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.edit(cvo);
	}
	
	@Transactional
	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		int totalCount = cdao.selectOntBnoTotalCount(bno);
		// totalCount 구하기
		// bno를 주고 해당하는 계신글의 전체 데이터
		
		List<CommentVO> list = cdao.selectListPaging(bno, pgvo);
		// Comment List 찾아오기
		
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		// PagingHandler 값 완성 후 리턴
		return ph;
		
	}


}
