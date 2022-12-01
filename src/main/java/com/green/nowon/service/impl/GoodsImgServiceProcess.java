package com.green.nowon.service.impl;

import com.green.nowon.domain.entity.GoodsImg;
import com.green.nowon.domain.entity.GoodsImgRepository;
import com.green.nowon.domain.entity.GoodsRepository;
import com.green.nowon.service.GoodsImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GoodsImgServiceProcess implements GoodsImgService {

    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private GoodsImgRepository goodsImgRepository;

    @Value("${multipart.upload.path}")
    private String PATH;
    @Transactional
    @Override
    public void delete(String imgUrl) {

        //db에서 삭제, 업로드폴더에서도 삭제
        String[] strs = imgUrl.split("[/]");//나누는기준이하나면 "/" 사용해도 되요
        //String url="";
        String fileName;
        /*
        for(int i=0;i<strs.length-1;i++){
            url += strs[i]+"/";
        }
        */
        fileName = strs[strs.length-1];
        /*System.out.println("url: "+url);
        System.out.println("name: "+fileName );*/

        //삭제쿼리메서드 생성시 @Transactional적용해서 삭제가능합니다.
        goodsImgRepository.deleteByUrlAndNewName(PATH,fileName);

        //GoodsImg deleteImage = goodsImgRepository.findByUrlAndName(PATH, fileName).orElseThrow();
        //goodsImgRepository.delete(deleteImage);


    }
}
