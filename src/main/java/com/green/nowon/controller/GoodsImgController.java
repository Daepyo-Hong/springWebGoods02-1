package com.green.nowon.controller;

import com.green.nowon.service.GoodsImgService;
import com.green.nowon.service.impl.GoodsImgServiceProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GoodsImgController {

    @Autowired
    private GoodsImgService service;

    @ResponseBody
    @DeleteMapping("/admin/goods-img")
    public void delete(String imgUrl){//변수이름이 ajax data의 key와 일치하면 매핑

        service.delete(imgUrl);
        System.out.println("imgUrl: "+imgUrl);
    }
}
