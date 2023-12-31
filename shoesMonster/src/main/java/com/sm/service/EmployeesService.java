package com.sm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sm.domain.ClientPageVO;
import com.sm.domain.EmployeesVO;
import com.sm.domain.LineWhPageVO;
import com.sm.domain.ManagementVO;
import com.sm.domain.WorkOrderVO;

public interface EmployeesService {
	
	// 로그인
	public EmployeesVO empLogin(EmployeesVO empvo);
	
	// 사원 목록 조회 - Read
	public List<EmployeesVO> getEmpList(ClientPageVO cpvo) throws Exception;
	
	// 사원 관리 정보 조회 - Read
	public List<ManagementVO> getManagement() throws Exception;
		
	// 사원 검색
	public List<EmployeesVO> getSearchEmployeesList(HashMap<String, Object> search) throws Exception;

	// 사원 전체 수
	public int getTotalEmployees() throws Exception;
	
	// 사원 검색 수
	public int getSearchEmployees(HashMap<String, Object> search) throws Exception;
	
	// 사원 추가
	public void regEmployees(EmployeesVO evo) throws Exception;
	
	//사원 삭제
	public void removeEmployees(List<String> checked) throws Exception;
	
	//사원 상세 조회
	public EmployeesVO getEmployees(String emp_id) throws Exception;
	
	//사원 수정
	public void modifyEmployees(Map map) throws Exception;
	
	// 사원 사진
	public void updateEmployeesImg(String file,String emp_id) throws Exception;

		
	
}
