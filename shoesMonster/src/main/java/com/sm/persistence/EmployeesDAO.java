package com.sm.persistence;

import java.util.List;

import com.sm.domain.EmployeesVO;
import com.sm.domain.ManagementVO;

public interface EmployeesDAO {
	
	// 로그인
	public EmployeesVO loginEmp(EmployeesVO empvo);
	
	public EmployeesVO loginEmp(String id, String pw);
	
	// 사원 목록 조회 - R
	public List<EmployeesVO> readEmpList() throws Exception;
	
	// 사원 권한 정보 조회 - R
	public List<ManagementVO> readManagement() throws Exception;
	
	
}
