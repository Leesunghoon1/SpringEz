package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.repository.CommentDAO;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Inject
	private CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		
		return cdao.insert(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		return cdao.getList(bno);
	}

	@Override
	public int remove(int cno) {
 
		return cdao.remove(cno);
	}

}
