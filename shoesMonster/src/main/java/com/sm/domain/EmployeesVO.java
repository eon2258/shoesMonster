package com.sm.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class EmployeesVO {
	
	private String emp_id;
	private String emp_pw;
	private String emp_name;
	private String emp_department;
	private String emp_position;
	private String emp_email;
	private String emp_phone;
	private String emp_work;
	private String emp_addr;
	private Date emp_birth;
	private String emp_gender;
	private String emp_file;
	private Date emp_hiredate;
	private String emp_tel;
	
	
}
