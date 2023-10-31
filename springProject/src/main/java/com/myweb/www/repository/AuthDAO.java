package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.security.AuthVO;

public interface AuthDAO {

	List<AuthVO> MomberList();

	void remove2(String email);

}
