package com.green.nowon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.GoodsInsertDTO;
import com.green.nowon.service.GoodsService;

@Controller
public class GoodsController {
	
	@Autowired
	private GoodsService service;




	//상품등록 페이지 연결
	@GetMapping("/goods-reg")
	public String regPage(){
		return "manytoone/reg";
	}
	//상품리스트 페이지 연결
	@GetMapping("/manytoone")
	public String goods(Model model) {	//DB에서 얻어온 데이터를 페이지에 전달위한 Model객체필요
		service.findAll(model);
		return "manytoone/list";
	}
	@ResponseBody
	@PostMapping("/img-temploading")
	public String tempUpload(MultipartFile img) {
		return service.tempUpload(img);
	}
	
	@PostMapping("/manytoone")
	public String goods(GoodsInsertDTO dto, MultipartFile[] img) {
		service.save(dto, img);
		return "redirect:/manytoone";
	}

	//상품 디테일 페이지
	@GetMapping("/goods/{gno}")
	public String detail(@PathVariable long gno, Model model){
		service.detail(gno, model);
		return "manytoone/detail";
	}
}
