package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.repository.AuthDAO;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject
	private MemberDAO mdao;
	
	@Inject
	private AuthDAO adao;
	
	@Transactional
	@Override
	public int register(MemberVO mvo) {
		int isOK = mdao.register(mvo); 
		return mdao.insertAuthInit(mvo.getEmail());
	}

	@Override
	public boolean updateLastLogin(String authEmail) {

		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}

	@Override
	public MemberVO MemberDetail(String email) {
		// TODO Auto-generated method stub
		return mdao.MemberDetail(email);
	}

	@Override
	public int MemberModify(MemberVO mvo) {
		return mdao.MemberModify(mvo);
	}

	@Override
	public List<AuthVO> MeberList() {
		// TODO Auto-generated method stub
		return adao.MomberList();
	}

	@Override
	public int remove(String email) {
		
			   adao.remove2(email);
		return mdao.remove(email);
	}

}
