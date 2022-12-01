package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.Goods;
import com.green.nowon.domain.entity.GoodsImg;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter		//페이지에서 데이터추출하기 위한 Getter
public class GoodsListDTO {

	private long gno;
	private String title;
	private int price;
	private int stock;
	private LocalDateTime updatedDate;

	//이미지 테이블에 저장되어 있음
	private String imgUrl;

	public GoodsListDTO(Goods e) {
		this.gno=e.getGno();
		this.title = e.getTitle();
		this.price = e.getPrice();
		this.stock = e.getStock();
		this.updatedDate=e.getUpdatedDate();
		GoodsImg img = e.defImg();
		if(img!=null) {
			this.imgUrl = img.getUrl() + img.getNewName();
		}
	}
}
