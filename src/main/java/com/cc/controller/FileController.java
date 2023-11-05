package com.cc.controller;

import com.cc.model.Chapter;
import com.cc.service.FileService;
import com.cc.service.NovelService;
import com.cc.util.FileUtil;
import com.cc.util.JsonUtil;
import com.cc.util.NovelUtil;
import jakarta.annotation.Resource;
import org.apache.commons.io.FileSystem;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/file")
@CrossOrigin(originPatterns = "http://localhost:8080")
public class FileController {

    @Resource
    FileService fileService;
    @Resource
    NovelService novelService;


    //允许跨域请求
    @CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @GetMapping(value = "/img/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public byte[] getImg(@PathVariable("id") int id){
        try {
            return fileService.getImg(id);
        } catch (IOException e) {
            return null;
        }
    }

    //允许跨域请求
    @CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @GetMapping(value = "/bgc/{name}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public byte[] getImg(@PathVariable("name") String name){
        try {
            return fileService.getBgc(name);
        } catch (IOException e) {
            return null;
        }
    }


}
