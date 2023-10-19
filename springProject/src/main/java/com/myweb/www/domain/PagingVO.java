package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@ToString
public class PagingVO {
	
	private int pageNo;
	//현재 페이지 넘버
	private int qty;
	//한 페이지의 개시글의 수
	private String type;
	//검색 처리용
	private String keyword;
	
	
	public PagingVO() {
		//아무것도 없는 기본생성자
		this(1,10);
	}
	
	
	
	public PagingVO(int pageNo, int qty) {
		// int pageNo, int qty 가 들어온다면 해당하는 pageNo, int qty 로 넣어주세요
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	//pageNo = 1 2 3 4 로 넘어감
	//limit 시작, qty : 시작 페이지 번호를 구하기
	//해당 값이 처음엔 0(qty),10 / 10,10 / 20,10 ....
	
	public int getPageStart() {
		return (this.pageNo-1) * qty;
	}
	
	
	
	public String[] getTypeToArray() {
		//타입의 형태를 여러가지 형태로 복합적인 검색을 하기위해서
		//타입의 키워드 t,c,w,tc,tw,cw,tcw
		//복합타입을 배열로 나누기위해 사용
		return this.type == null ? new String[] {} : this.type.split("");
		
	}
	

}
