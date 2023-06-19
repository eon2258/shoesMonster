package com.sm.service;

import java.util.List;

import com.sm.domain.LineVO;
import com.sm.domain.ProductVO;

public interface PerformanceService {
		
	// 품목관리 리스트 불러오기
	public List<ProductVO> getProdList() throws Exception; 
	
	// 품목관리 검색리스트 불러오기
	public List<ProductVO> getProdList(ProductVO vo) throws Exception;
	
	// 품목관리 정보 다중 저장
	public void insertProd(List<ProductVO> products); 

	// 라인 
	public void insertLine(LineVO vo) throws Exception;
	public List<LineVO> getLineList() throws Exception;
	
}
		