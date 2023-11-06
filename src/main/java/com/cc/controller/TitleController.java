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
import xin.altitude.cms.common.entity.AjaxResult;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class TitleController {
    @Resource
    TitleService titleService;

    //允许跨域请求
    //@CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @GetMapping("/titles/{novelId}")
    @ResponseBody
    public AjaxResult getTitles(@PathVariable("novelId") int novelId) {
        ArrayList<Title> titles = null;
        try {
            titles = titleService.getTitlesById(novelId);
        } catch (Exception e) {
            return new AjaxResult(400,e.getMessage());
        }
        HashMap<String, ArrayList> data = new HashMap<>();
        data.put("titles", titles);
        return new AjaxResult(200, "success", data);
    }



}
