package com.green.nowon.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.GoodsInsertDTO;

public interface GoodsService {

	String tempUpload(MultipartFile img);

	void save(GoodsInsertDTO dto, MultipartFile[] imgs);

    void findAll(Model model);


	void detail(long gno, Model model);

    void adminDetail(long gno, Model model);

    void save(GoodsInsertDTO dto, MultipartFile[] imgs, String[] newName);
}
