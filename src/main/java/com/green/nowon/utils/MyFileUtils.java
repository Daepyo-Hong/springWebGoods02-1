package com.green.nowon.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
                .orgName(img.getOriginalFilename())
                .size(img.getSize())
                .build();
    }

    //대상위치(path) 경로에 파일 업로드
    public static String fileUpload(MultipartFile img, String path) {
        ClassPathResource cpr = new ClassPathResource("static" + path);
        String newFileName = null;
        try {
            File uploadFolder = cpr.getFile();
            String orgFileName = img.getOriginalFilename();//파일원본이름으로
            String[] strs = orgFileName.split("[.]");    //파일이름.확장자 인 경우

            System.out.println("UUID: " + UUID.randomUUID());
            long num = System.nanoTime() / 1000000;
            System.out.println("nanoTime/1000000: " + num);

            newFileName = strs[0] + "_" + num + "." + strs[1];
            img.transferTo(new File(uploadFolder, newFileName));
            log.info(">>> 파일업로드 테스트 : file upload done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path + newFileName; //파일업로드 위치 "/images/goods/temp/img_1.jpg"

    }

    public static GoodsImg tempToGoods(MultipartFile img, String uploadPath) {
        //ClassPathResource cpr=new ClassPathResource("static"+uploadPath);

        ClassPathResource temp = new ClassPathResource("static" + uploadPath + "temp");
        try {
            File target = new File(temp.getFile(), img.getOriginalFilename());

            boolean result = target.renameTo(new File(temp.getFile().getParent(), img.getOriginalFilename()));
            System.out.println(">>>temp -> goods : "+result);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return GoodsImg.builder()
                .url(uploadPath)
                .orgName(img.getOriginalFilename())
                .size(img.getSize())
                .build();

    }

    public static GoodsImg tempToGoods(MultipartFile img, String newName, String uploadPath) {
        ClassPathResource temp = new ClassPathResource("static" + uploadPath + "temp");
        try {
            File target = new File(temp.getFile(), newName);//temp경로에 업로드된 파일

           boolean result = target.renameTo(
                   new File(temp.getFile().getParent(), newName));
            System.out.println(">>>temp -> goods : "+result);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return GoodsImg.builder()
                .url(uploadPath)
                .orgName(img.getOriginalFilename())
                .newName(newName)
                .size(img.getSize())
                .build();
    }
}
