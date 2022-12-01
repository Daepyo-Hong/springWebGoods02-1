package com.green.nowon.domain.entity;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


//@ToString(exclude = "imgs")
@Getter
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "gen_seq_good",
		sequenceName = "seq_good", initialValue = 1, allocationSize = 1)
@Table(name = "goods2")
@Entity
public class Goods  extends BaseEntity{
	
	@Id
	@GeneratedValue(generator = "gen_seq_good", strategy = GenerationType.SEQUENCE)
	private long gno;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false, columnDefinition = "text")
	private String content;
	private int price;
	private int stock;
	
	//양방향설정
	@JoinColumn(name = "gno")
	@Builder.Default
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<GoodsImg> imgs=new Vector<>();

	//이미지 삽입 편의메서드
	public Goods addImg(GoodsImg img){
		imgs.add(img);
		return this;
	}

	public String defImgUrl(){
		GoodsImg defImg = imgs.get(0);
		return defImg.getUrl()+defImg.getNewName();
	}
	//대표이미지만 추출하는 메서드
	public GoodsImg defImg(){

		for(GoodsImg img:imgs){
			if(img.isDef()==true){
				return img;
			}
		}
		return null;
	}


}
