package com.sm.domain.b;

import java.sql.Date;

import lombok.Data;

@Data
public class In_materialVO {

	private String in_num;
	private String raw_order_num;
	private String emp_id;
	private String client_code;
	private String in_text;
	private Date in_date;
	private int in_count ;
	private String in_YN;
}
