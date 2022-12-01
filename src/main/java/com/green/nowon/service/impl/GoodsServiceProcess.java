package com.green.nowon.service.impl;

import com.green.nowon.domain.dto.GoodsDetailDTO;
import com.green.nowon.domain.dto.GoodsListDTO;
import com.green.nowon.domain.entity.Goods;
import com.green.nowon.domain.entity.GoodsImg;
import com.green.nowon.domain.entity.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.GoodsInsertDTO;
import com.green.nowon.service.GoodsService;
import com.green.nowon.utils.MyFileUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoodsServiceProcess implements GoodsService {
	
	@Value("${multipart.upload.temp}")
	private String TEMP_PATH;

	@Value("${multipart.upload.path}")
	private String GOODS_PATH;

	@Autowired
	private GoodsRepository goodsRepository;

	@Override
	public String tempUpload(MultipartFile img) {
		
		return MyFileUtils.fileUpload(img, TEMP_PATH);
	}


	@Override
	public void save(GoodsInsertDTO dto, MultipartFile[] imgs) {

		//파일저장 : 파일엔티티가 필요, temp->goods
		Goods goods=dto.toEntity();
		for(int i=0;i<imgs.length;i++) {
			//이미지파일이 없으면(size==0) 파일저장 막음
			if(imgs[i].getSize()==0){
				System.out.println("정상적인 이미지가 아니어서 객체생성하지않고 패스");
				continue;
			}

			GoodsImg imgEntity = MyFileUtils.tempToGoods(imgs[i], GOODS_PATH);
			//첫번째것을 대표이미지로 설정
			if(i==0){
				imgEntity.def(true);
			}
			goods.addImg(imgEntity);
		}
		goodsRepository.save(goods);
	}

	//@Transactional	: 트랜잭션적용 안하려면 fetch: EAGER로 해주면 됩니다
	@Override
	public void findAll(Model model) {
		List<GoodsListDTO> result = goodsRepository.findAll().stream()
				.map(GoodsListDTO::new)
				.collect(Collectors.toList()); // List<GoodsListDTO>
		model.addAttribute("list",result);

	}

	@Override
	public void detail(long gno, Model model) {
		GoodsDetailDTO result =goodsRepository.findById(gno)
				.map(GoodsDetailDTO::new)
				.orElseThrow();
		model.addAttribute("detail",result);
	}


}
