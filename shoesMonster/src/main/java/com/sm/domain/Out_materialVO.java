package com.sm.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Out_materialVO {

	private String out_num;
	private String prod_code;
	private String o_emp_id;
	private String out_text;
	private String out_YN;
	private int out_count;
	private int out_price;
	private Date out_redate;
	private Date out_date;
	
	private OrderStatusVO orders;
	private ClientsVO clients;
	private ProductVO prod;
	private StockVO stock;
	
	public Out_materialVO() {
		orders = new OrderStatusVO();
		clients = new ClientsVO();
		prod = new ProductVO();
		stock = new StockVO();
	}
	
	
}
