package com.sm.persistence;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.sm.domain.LineVO;
import com.sm.domain.LineWhPageVO;
import com.sm.domain.PagingVO;
import com.sm.domain.ProductVO;
import com.sm.domain.RawMaterialVO;
import com.sm.domain.WarehouseVO;
import com.sm.domain.Wh_prodVO;

@Repository
public interface PerformanceDAO {
	
	// 품목관리 리스트 총갯수
	public int countProd();
		
	// 품목관리 목록 불러오기
	public List<ProductVO> readProdList(PagingVO pvo) throws Exception;
	
	// 품목관리 리스트 검색갯수
	public int countProd(ProductVO vo);
	
	// 품목관리 검색리스트 불러오기
		public List<ProductVO> readProdList(ProductVO vo,PagingVO pvo) throws Exception;
		
	// 품목관리 정보 다중 저장
	public void insertProdList(ProductVO product); 
	
	// 품목관리  삭제
	public void deleteProd(List<String> checked) throws Exception;
		
	//==========================================================================

	// 원자재관리 리스트 총갯수
	public int countRaw();

	// 원자재관리 목록 불러오기
	public List<RawMaterialVO> readRawList(PagingVO pvo) throws Exception;

	// 원자재관리 리스트 검색갯수
	public int countRaw(RawMaterialVO vo);

	// 원자재관리 검색리스트 불러오기
	public List<RawMaterialVO> readRawList(RawMaterialVO vo, PagingVO pvo) throws Exception;

	// 원자재관리 정보 다중 저장
	public void insertRawList(RawMaterialVO raw);

	// 원자재관리 삭제
	public void deleteRaw(List<String> checked) throws Exception;
	
	//==========================================================================
	
	// 라인 조회 - getLineList
	public List<LineVO> getLineList() throws Exception;
	
	// 라인 조회(페이징처리) - getLineListPage
//	public List<LineVO> getLineListPage(Integer page) throws Exception;
	
	public List<LineVO> getLineListPage(LineWhPageVO vo) throws Exception;
	
	// 라인 검색 - getSearchLine
	public List<LineVO> getSearchLine(LineVO lvo) throws Exception;
	
	// 총 글 개수 계산
	public int getTotalCount() throws Exception;
	
	// 서치시 총 글 개수 계산
	public int getSearchTotalCount(LineVO lvo) throws Exception;
	
	// 창고 조회 - R
	public List<WarehouseVO> readWhList() throws Exception;
	
	// 창고 조회 처리 
	public List<Wh_prodVO> readWh_prodList() throws Exception;
	
	// 창고 검색
	public List<WarehouseVO> searchWarehouse(HashMap<String, Object> search) throws Exception;
	
}
