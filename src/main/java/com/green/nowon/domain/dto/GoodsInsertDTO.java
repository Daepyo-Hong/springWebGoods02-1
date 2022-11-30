package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.Goods;

import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public class GoodsInsertDTO {
	
	private String title;
	private int price;
	private int stock;
	private String content;
	
	public Goods toEntity() {
		return Goods.builder()
				.title(title)
				.content(content)
				.price(price).stock(stock)
				.build();
	}
	

}
