package com.sm.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sm.domain.LineVO;
import com.sm.domain.LineWhPageMaker;
import com.sm.domain.LineWhPageVO;
import com.sm.domain.PagingVO;
import com.sm.domain.ProductList;
import com.sm.domain.ProductVO;
import com.sm.domain.RawMaterialList;
import com.sm.domain.RawMaterialVO;
import com.sm.domain.WarehouseVO;
import com.sm.service.PerformanceService;

@Controller
@RequestMapping(value = "/performance/*")
public class PerfomanceController {

	// 서비스 객체 주입
	@Autowired
	private PerformanceService service;

	private static final Logger logger = LoggerFactory.getLogger(PerfomanceController.class);

	// ======================================================================================

	// 품목관리 
	// http://localhost:8088/performance/product
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public void productGET(Model model, ProductVO vo, PagingVO pvo,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage) throws Exception {
		logger.debug("productGET() 호출");
		List<ProductVO> products = new ArrayList<ProductVO>();
		model.addAttribute("products", products);
		logger.debug("vo : " + vo);

		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "5";
		}

		if (vo.getProd_code() != null || vo.getProd_name() != null || vo.getProd_category() != null
				|| vo.getProd_unit() != null) {
			int total = service.countProd(vo);
			pvo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			List<ProductVO> list = service.getProdList(vo, pvo);
			model.addAttribute("prodList", list);
			model.addAttribute("paging", pvo);
			model.addAttribute("vo", vo);
			logger.debug("pvo : " + pvo);
			logger.debug("vo : " + vo);
			
			logger.debug("검색 리스트 가져감");

		} else {
			int total = service.countProd();
			pvo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			logger.debug("pvo : " + pvo);
			List<ProductVO> list = service.getProdList(pvo);
			model.addAttribute("prodList", list);
			model.addAttribute("paging", pvo);
			logger.debug(" 모든 리스트 가져감");
		}

	}
  
  	////////////////////// 검색 ajax /////////////////////////
//	@ResponseBody
//	@RequestMapping(value = "/search", method = RequestMethod.POST)
//	public List<ProductVO> searchPOST(Model model,
//			PagingVO pvo,
//			@RequestBody ProductVO vo,
//			@RequestParam(value = "nowPage", required = false) String nowPage,
//			@RequestParam(value = "cntPerPage", required = false) String cntPerPage
//			) throws Exception {
//		logger.debug("searchPOST() 호출");
//		
//		if (nowPage == null && cntPerPage == null) {
//			nowPage = "1";
//			cntPerPage = "5";
//		} else if (nowPage == null) {
//			nowPage = "1";
//		} else if (cntPerPage == null) {
//			cntPerPage = "5";
//		}
//		
//		List<ProductVO> list = new ArrayList<>();
//		
//		if (vo.getProd_code() != null || vo.getProd_name() !=null || 
//			vo.getProd_category() !=null || vo.getProd_unit() !=null ) {
//			int total = service.countProd(vo);
//			pvo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
//			list = service.getProdList(vo,pvo);
//			logger.debug("검색 리스트 가져감");
//			model.addAttribute("paging", pvo);
//			model.addAttribute("vo",vo);
//		}
//		
//		return list;
//	} //searchPOST()
	////////////////////// 검색 ajax /////////////////////////
  
	// 품목관리 정보 추가 
	@RequestMapping(value = "product", method = RequestMethod.POST)
	public String productPOST(ProductList products) throws Exception {

		logger.debug("productPOST() 호출");
		logger.debug("prducts : " + products.getProducts());
		service.insertProd(products.getProducts());
//		service.insertProd(vo);

		return "redirect:/performance/product";
	}
	
	// 품목관리 정보 삭제
	@RequestMapping(value = "/prodDelete", method = RequestMethod.POST)
	public String deleteProd(@RequestParam(value="checked[]") List<String> checked) throws Exception {
		logger.debug("@@@@@ CONTROLLER: deleteProd() 호출");
		logger.debug("@@@@@ CONTROLLER: checked = " + checked);
		
		//서비스 - 품목관리 정보 삭제 
		service.removeProd(checked);
		
		return "redirect:/performance/product";
	} //deleteProd()
	
	//  ======================================================================================================
	
	// 원자재관리
	// http://localhost:8088/performance/rawMaterial
	@RequestMapping(value = "/rawMaterial", method = RequestMethod.GET)
	public void rawMaterialGET(Model model, RawMaterialVO vo, PagingVO pvo,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage) throws Exception {
		
		logger.debug("rawMaterialGET() 호출");
		List<RawMaterialVO> raws = new ArrayList<RawMaterialVO>();
		model.addAttribute("raws", raws);
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "5";
		}
		logger.debug("vo : " + vo);

		if (vo.getRaw_code() != null || vo.getClients().getClient_actname() != null || vo.getRaw_name() != null) {
			
			logger.debug("if문 호출");
			int total = service.countRaw(vo);
			pvo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			List<RawMaterialVO> list = service.getRawList(vo, pvo);
			model.addAttribute("rawList", list);
			model.addAttribute("paging", pvo);
			model.addAttribute("vo", vo);
			logger.debug("pvo : " + pvo);
			logger.debug("vo : " + vo);

			logger.debug("검색 리스트 가져감");

		} else {
			logger.debug("else문 호출");
			int total = service.countRaw();
			pvo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			logger.debug("pvo : " + pvo);
			List<RawMaterialVO> list = service.getRawList(pvo);
			model.addAttribute("rawList", list);
			model.addAttribute("paging", pvo);
			logger.debug(" 모든 리스트 가져감");
		}

	}

	// 원자재관리 정보 추가
	@RequestMapping(value = "rawMaterial", method = RequestMethod.POST)
	public String rawMaterialPOST(RawMaterialList raws) throws Exception {

		logger.debug("rawMaterialPOST() 호출");
		logger.debug("raws : " + raws.getRaws());
		service.insertRaw(raws.getRaws());
		// service.insertProd(vo);

		return "redirect:/performance/rawMaterial";
	}

	// 원자재관리 정보 삭제
	@RequestMapping(value = "/rawMaterialDelete", method = RequestMethod.POST)
	public String deleteRawMaterial(@RequestParam(value = "checked[]") List<String> checked) throws Exception {
		logger.debug("@@@@@ CONTROLLER: deleteRawMaterial() 호출");
		logger.debug("@@@@@ CONTROLLER: checked = " + checked);

		// 서비스 - 원자재관리 삭제
		service.removeRaw(checked);

		return "redirect:/performance/rawMaterial";
	} // deleteProd()
	
	
	
	
	
	//  ======================================================================================================

	// ======== 라인 - /line ================================
	// http://localhost:8088/performance/line
	// http://localhost:8088/performance/line?page=1
	@RequestMapping(value = "/line", method = RequestMethod.GET)
	public void lineGET(Model model, LineVO lvo, 
				        LineWhPageVO vo, LineWhPageMaker lwpm) throws Exception {
		logger.debug("@@lineGET() 호출@@");
//		List<LineVO> boardList = service.getLineList();
//		model.addAttribute("boardList", boardList);
		
		logger.debug("lvo : " + lvo);

		// 검색
		if (lvo.getLine_code() != null || lvo.getLine_name() != null || lvo.getLine_place() != null
				|| lvo.getLine_use() != 0) {

			List<LineVO> searchlist = service.getSearchLine(lvo);
			model.addAttribute("boardList", searchlist);

			logger.debug("searchlist : " + searchlist);

			logger.debug("@@ 검색 리스트 호출 @@");
			
			// 페이징처리 (하단부) 저장
//			lwpm.setLwPageVO(vo);
//			lwpm.setTotalCount(service.getTotalCount());
//			logger.debug("lwpm 검색 : "+lwpm.getTotalCount());
//			model.addAttribute("lwpm", lwpm);
			
			lwpm.setLwPageVO(vo);
			logger.debug("확니!!!!!!!!!!!!!!!!!!!!!용");
			lwpm.setTotalCount(service.getSearchTotalCount(lvo));
			logger.debug("lwpm (제발서치서치) : "+lwpm.getTotalCount());
//			model.addAttribute("lwpm", lwpm);

		} else {
			// 페이징처리된 리스트정보로 수정함!
			List<LineVO> boardList = service.getLineListPage(vo);
			model.addAttribute("boardList", boardList);

			logger.debug("@@ 모든 리스트 호출 @@");
		}
		
		// 페이징처리 (하단부) 저장
		lwpm.setLwPageVO(vo);
		lwpm.setTotalCount(service.getTotalCount());
		logger.debug("lwpm : "+lwpm.getTotalCount());
		model.addAttribute("lwpm", lwpm);
	}
	

//	@RequestMapping(value = "/line", method = RequestMethod.POST)
//	public void linePOST()throws Exception{
//		logger.debug("linePOST() 호출");
//		
//		List<LineVO> boardList = service.getLineList();
//		logger.debug("boardList : "+boardList);
//		
//		model.addAttribute("boardList", boardList);
//		
//	}

	// ======== 라인 - /line ================================

	// ======== 창고 - /warehouse ===========================
	// http://localhost:8088/performance/warehouse
	@RequestMapping(value = "/warehouse", method = RequestMethod.GET)
	public void warehouseGET(Model model) throws Exception {

		logger.debug("@@ warehouseGET() 호출 @@");

		List<WarehouseVO> whList = service.getWhList();
		model.addAttribute("whList", whList);

		logger.debug("whList : " + whList);

		logger.debug("@@ 모든 리스트 호출 @@");
	}

	// 코드 품번 창고명 검색
	// http://localhost:8088/performance/wh_search
//	@RequestMapping(value = "/wh_search", method = RequestMethod.GET)
//	public String whSearchGET(@RequestParam("type") String type, 
//							  Model model) throws Exception{
//		
//		logger.debug("@@ whSearchGET() 호출 @@");

//		if()

//		return "";
//	}
	// ======== 창고 - /warehouse ===========================

}// PerfomanceController
