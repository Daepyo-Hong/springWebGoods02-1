package com.green.nowon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.GoodsInsertDTO;
import com.green.nowon.service.GoodsService;

@Controller
public class GoodsController {
	
	@Autowired
	private GoodsService service;

	@GetMapping("/manytoone")
	public String goods() {
		return "manytoone/reg";
	}
	
	@ResponseBody
	@PostMapping("/img-temploading")
	public String tempUpload(MultipartFile img) {
		return service.tempUpload(img);
	}
	
	@PostMapping("/manytoone")
	public String goods(GoodsInsertDTO dto, MultipartFile img) {
		service.save(dto, img);
		return "redirect:/manytoone";
	}
}
