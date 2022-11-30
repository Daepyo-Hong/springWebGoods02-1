package com.green.nowon.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.entity.GoodsImg;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MyFileUtils {
	
	

	//파일업로드 처리이후 DB에저장할 GoodsImg Entity객체를 리턴
	public static GoodsImg fileUploadAndGetEntity(MultipartFile img, String uploadPath) {
		fileUpload(img, uploadPath);
		
		return GoodsImg.builder()
				.url(uploadPath)
				.name(img.getOriginalFilename())
				.size(img.getSize())
				.build();
	}

	//대상위치(path) 경로에 파일 업로드
	public static String fileUpload(MultipartFile img, String path) {
		ClassPathResource cpr=new ClassPathResource("static"+path);
		String fileName=null;
		try {
			File uploadFolder=cpr.getFile();
			fileName=img.getOriginalFilename();//파일원본이름으로
			
			img.transferTo(new File(uploadFolder, fileName));
			log.info(">>> 파일업로드 테스트 : file upload done!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path+fileName; //파일업로드 위치 "/images/goods/temp/img_1.jpg"
		
	}

	public static GoodsImg tempToGoods(MultipartFile img, String uploadPath) {
		//ClassPathResource cpr=new ClassPathResource("static"+uploadPath);
		
		ClassPathResource temp=new ClassPathResource("static"+uploadPath+"temp");
		try {
			File target=new File( temp.getFile(), img.getOriginalFilename());
			
			System.out.println(">>>temp -> goods : "+ target.renameTo(new File(temp.getFile().getParent(), img.getOriginalFilename())));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return GoodsImg.builder()
				.url(uploadPath)
				.name(img.getOriginalFilename())
				.size(img.getSize())
				.build();
		
	}

}
