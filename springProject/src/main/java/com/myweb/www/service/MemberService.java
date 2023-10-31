package com.myweb.www.service;

import java.util.List;

import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	boolean updateLastLogin(String authEmail);

	MemberVO MemberDetail(String email);

	int MemberModify(MemberVO mvo);

	List<AuthVO> MeberList();

	int remove(String email);
}
