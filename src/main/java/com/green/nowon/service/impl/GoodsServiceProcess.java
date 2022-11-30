package com.green.nowon.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.GoodsInsertDTO;
import com.green.nowon.service.GoodsService;
import com.green.nowon.utils.MyFileUtils;

@Service
public class GoodsServiceProcess implements GoodsService {
	
	@Value("${multipart.upload.temp}")
	private String TEMP_PATH;

	@Override
	public String tempUpload(MultipartFile img) {
		
		return MyFileUtils.fileUpload(img, TEMP_PATH);
	}

	@Override
	public void save(GoodsInsertDTO dto, MultipartFile img) {
		// TODO Auto-generated method stub
		
	}

}
