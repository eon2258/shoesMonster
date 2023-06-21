package com.sm.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class OrderStatusVO {
	private String order_code;
	private Date order_date;
	private Date order_deliveryDate;
	private String emp_id;
	private int order_count;
	private String client_code;
	private String order_finish;
	private String oreder_note;
	
	private ClientsVO clients;
	private EmployeesVO employees;
	
}