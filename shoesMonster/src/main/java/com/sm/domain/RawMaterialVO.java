package com.sm.domain;

import lombok.Data;

@Data
public class RawMaterialVO {
	private String raw_code;
	private String raw_name;
	private String raw_color;
	private String raw_unit;
	private String raw_size;
	private float raw_price;
	private String client_code;
	private String raw_note;
	private String wh_code;
	
	private ClientsVO clients;
	private Wh_prodVO wh;
	
	public RawMaterialVO() {
		clients = new ClientsVO();
		wh = new Wh_prodVO();
	}
	
} //RawMaterailVO