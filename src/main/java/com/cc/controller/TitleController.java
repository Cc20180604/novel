package com.cc.controller;

import com.cc.model.Chapter;
import com.cc.model.ChapterPage;
import com.cc.model.Novel;
import com.cc.model.Title;
import com.cc.service.ChapterService;
import com.cc.service.TitleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(originPatterns = "http://localhost:8080")
public class TitleController {
    @Resource
    TitleService titleService;

    //允许跨域请求
    //@CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @GetMapping("/titles/{novelId}")
    @ResponseBody
    public ArrayList<Title> getTitles(@PathVariable("novelId") int novelId) {
        try {
            return titleService.getTitlesById(novelId);
        } catch (Exception e) {
            return null;
        }
    }



}
