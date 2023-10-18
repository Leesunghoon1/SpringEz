package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.BoardVO;

public interface BoardDAO {

	int register(BoardVO bvo);

	List<BoardVO> getList(BoardVO bvo);

	BoardVO getDetail(int bno);

	int postModify(BoardVO bvo);

	int remove(int bno);

	void readcount(@Param("bno")int bno, @Param("cnt")int cnt);
	//받는게 두개면 parm 으로 들고가야한다

}
