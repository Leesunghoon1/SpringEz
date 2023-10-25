package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	int register(BoardDTO boardDTO);

	List<BoardVO> getList(PagingVO pagingVO);

	BoardVO getDetail(long bno);

	int postModify(BoardVO bvo);

	int remove(long bno);

//	int getTotalCount();

	int getTotalCount(PagingVO pagingVO);

//	List<BoardVO> getList();

	List<FileVO> FileList(long bno);

	List<FileVO> getFileList(long bno);

	int dele(String fno);

	int FileModify(BoardDTO bdto);
	
	
}
