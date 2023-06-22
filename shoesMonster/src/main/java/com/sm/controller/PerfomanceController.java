package com.sm.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sm.domain.LineVO;
import com.sm.domain.PagingVO;
import com.sm.domain.ProductList;
import com.sm.domain.ProductVO;
import com.sm.service.PerformanceService;

@Controller
@RequestMapping(value = "/performance/*")
public class PerfomanceController {
	
	// 서비스 객체 주입
	@Autowired
	private PerformanceService service;

	private static final Logger logger = LoggerFactory.getLogger(PerfomanceController.class);
	
	// http://localhost:8088/performance/product
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public void productGET(Model model, ProductVO vo,PagingVO pvo,
						   @RequestParam(value = "nowPage", required = false)String nowPage,
						   @RequestParam(value = "cntPerPage", required = false)String cntPerPage) throws Exception{
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
		
		
		if (vo.getProd_code() != null || vo.getProd_name() !=null || 
			vo.getProd_category() !=null || vo.getProd_unit() !=null ) {
			int total = service.countProd(vo);
			pvo = new PagingVO(total,Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			List<ProductVO> list = service.getProdList(vo,pvo);
			model.addAttribute("prodList", list);
			model.addAttribute("paging", pvo);
			model.addAttribute("vo", vo);
			logger.debug("검색 리스트 가져감");
			
		}else {
			int total = service.countProd();
			pvo = new PagingVO(total,Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			logger.debug("pvo : " +pvo);
			List<ProductVO> list = service.getProdList(pvo);
			model.addAttribute("prodList", list);
			model.addAttribute("paging", pvo);
			logger.debug(" 모든 리스트 가져감");
		}
		
	}
	
	@RequestMapping(value = "product", method = RequestMethod.POST)
	public String productPOST(ProductList products) throws Exception {
		
		logger.debug("productPOST() 호출");
		logger.debug("prducts : " + products.getProducts());
		service.insertProd(products.getProducts());
//		service.insertProd(vo);
		
		return "redirect:/performance/product";
	}
	
	// 라인 - /line 
	// http://localhost:8088/performance/line
	@RequestMapping(value = "/line", method = RequestMethod.GET)
	public void lineGET() throws Exception{
		logger.debug("@@lineGET() 호출@@");
	}
	
	@RequestMapping(value = "/line", method = RequestMethod.POST)
	public void linePOST(LineVO vo) throws Exception{
		logger.debug("@@linePOST() 호출@@");
		
		logger.debug("@@vo : "+vo);
		
		service.insertLine(vo);
	}
	
	// http://localhost:8088/performance/linelist
	@RequestMapping(value = "/linelist", method = RequestMethod.GET)
	public void lineListGET(Model model) throws Exception{
		logger.debug("@@lineListGET() 호출@@");
		
		List<LineVO> boardList = service.getLineList();
		logger.debug("boardList : "+boardList);
		
		model.addAttribute("boardList", boardList);
	}
}
