package com.cc.controller;

import com.cc.model.Chapter;
import com.cc.model.ChapterPage;
import com.cc.model.Novel;
import com.cc.service.ChapterService;
import com.cc.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(originPatterns = "http://localhost:8080")
public class ChapterController {
    @Resource
    Chapter chapter;
    @Resource
    Novel novel;
    @Resource
    ChapterPage chapterPage;
    @Resource
    ChapterService chapterService;
    @RequestMapping("/")
    public String home(){
        return "hi ,welcome";
    }

    //允许跨域请求
    //@CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @GetMapping("/getChapter/{novelId}/{chapterNum}")
    @ResponseBody
    public Chapter getChapter(@PathVariable("novelId") int novelId, @PathVariable("chapterNum") int chapterNum) throws JsonProcessingException {
        System.out.println("master");
        try {
            return chapterService.getChapterById(novelId, chapterNum);
        } catch (Exception e) {
            return null;
        }
    }



}
