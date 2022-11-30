package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.Goods;
import com.green.nowon.domain.entity.GoodsImg;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter		//페이지에서 데이터추출하기 위한 Getter
public class GoodsDetailDTO {

	private long gno;
	private String title;
	private int price;
	private int stock;
	private String content;
	private LocalDateTime updatedDate;

	private String defImg;
	//이미지 테이블에 저장되어 있음
	private List<String> imgs;

    public String imgUrl(GoodsImg img){
        return img.getUrl()+img.getName();
    }

	public GoodsDetailDTO(Goods e) {
		this.gno=e.getGno();
		this.title = e.getTitle();
		this.price = e.getPrice();
		this.stock = e.getStock();
		this.content=e.getContent();
		this.updatedDate=e.getUpdatedDate();
		this.defImg=e.defImgUrl();

		//Goods 와 연결관계에 있는 GoodsImg 엔티티에 접근하므로
		// LAZY인 경우엔 @Transactional 추가하거나, EAGER면 가능
		imgs = e.getImgs().stream()
				.map(this::imgUrl)
                //.map(el->el.getUrl()+el.getName())
				.collect(Collectors.toList());

	}
}
