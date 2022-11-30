package com.green.nowon.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@ToString(exclude = "goods")
@Getter
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "gen_seq_img",
sequenceName = "seq_img", initialValue = 1, allocationSize = 1)
@Table(name = "goods_img2")
@Entity
public class GoodsImg extends BaseEntity{
	
	@Id
	@GeneratedValue(generator = "gen_seq_img", strategy = GenerationType.SEQUENCE)
	private long ino;
	@Column(nullable = false)
	private String url;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private long size;
	
	//양방향설정 GoodsImg 주엔티티
	@JoinColumn(name = "gno")
	@ManyToOne(cascade = CascadeType.DETACH )//N:1관계에서 물리FK 무조건 다쪽 테이블에생성
	private Goods goods;

	private boolean def;

	//대표이미지를 세팅해주는 편의메서드
	public GoodsImg def(boolean b) {
		this.def=b;
		return this;
	}

}
