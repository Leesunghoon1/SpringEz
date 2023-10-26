package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Service // 서비스
@Slf4j
public class BoardServiceImpl implements BoardService{
		
	@Inject
	private BoardDAO bdao;
	
	@Inject
	private FileDAO fdao; 
	
	@Inject
	private CommentDAO cdao;
	

	/*
	 * @Override public int register(BoardVO bvo) { log.info("register chaeak 2");
	 * return bdao.register(bvo); }
	 */
	
//	 @Override 
//	 public List<BoardVO> getList() { 
//		 log.info("list cheack 2"); 
//	 
//	 return bdao.getList(null); 
//	 
//	 }
	 



	@Override
	public BoardVO getDetail(long bno) {
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
	
	@Transactional
	@Override
	public int remove(long bno) {
		log.info("삭제 체크2");
		cdao.removeC(bno);
		fdao.removeF(bno);
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

	@Transactional
	@Override
	public int register(BoardDTO bdto) {
		// bvo, flist 가져와서 각자 db에 저장
		// 기존 메서드 활용해서 
		int isUP = bdao.register(bdto.getBvo()); //bno 등록
		
		if(isUP > 0 && bdto.getFlist().size() > 0) {
			//bvo insert 후, 파일도 있다면....
			long bno = bdao.selectOneBno(); //가장 최근에 인설트한 bno를 가져오기
			//모든 파일에 bno를 set해줘야함
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isUP *= fdao.insertFile(fvo);
			}
		}
		return isUP;
	}



	@Override
	public List<FileVO> FileList(long bno) {
		// TODO Auto-generated method stub
		return fdao.getFileList(bno);
	}



	@Override
	public List<FileVO> getFileList(long bno) {
		/* bdao.readcount(bno, 1); */
		/* BoardDTO bdto = new BoardDTO(); */
		/* bdto.setBvo(bdao.getDetail(bno)); */
		// // bdao bvo 호출
		
		return fdao.getFileList(bno);
		
	}



	@Override
	public int dele(String fno) {
		// TODO Auto-generated method stub
		return fdao.fileDel(fno);
	}

	@Transactional
	@Override
	public int FileModify(BoardDTO bdto) {
		bdao.readcount(bdto.getBvo().getBno(), -2);
		
		int isOK = bdao.postModify(bdto.getBvo());
		
		if(bdto.getFlist() == null) {
			isOK *= 1;
		}else {
			if(isOK > 0 && bdto.getFlist().size()>0) {
				long bno = bdto.getBvo().getBno();
				//모든 fvo에 bno set
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					isOK *= fdao.insertFile(fvo);
				}
			}
		
		}
		return isOK;
	}





	


	
}
