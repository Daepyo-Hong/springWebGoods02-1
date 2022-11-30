package com.green.nowon.domain.entity;

import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


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
	@Builder.Default
	@OneToMany(mappedBy = "goods")
	List<GoodsImg> imgs=new Vector<>();
	
	

}
