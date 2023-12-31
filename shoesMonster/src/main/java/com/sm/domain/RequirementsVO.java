package com.sm.domain;

import lombok.Data;

@Data
public class RequirementsVO {
	private String req_code;
	private String prod_code;
	private String raw_code;
	private String req_dan;
	private String req_note;
	
	private ProductVO prod;
	private RawMaterialVO raw;
	
	public RequirementsVO() {
		prod = new ProductVO();
		raw = new RawMaterialVO();
	}
	
	
} //Requirements
