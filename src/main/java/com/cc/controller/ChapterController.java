package com.cc.controller;

import com.cc.model.Chapter;
import com.cc.model.ChapterPage;
import com.cc.model.Novel;
import com.cc.service.ChapterService;
import com.cc.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;

import java.util.HashMap;

@RestController
@CrossOrigin(originPatterns = "http://localhost:8080")
public class ChapterController {
    @Resource
    ChapterService chapterService;

    //允许跨域请求
    //@CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @GetMapping("/chapter/{novelId}/{chapterNum}")
    @ResponseBody
    public AjaxResult getChapter
    (@PathVariable("novelId") int novelId,
     @PathVariable("chapterNum") int chapterNum)  {
        Chapter chapter ;
        try {
            chapter = chapterService.getChapterById(novelId, chapterNum);
        } catch (Exception e) {
            chapter = null;
        }

        if (chapter == null){
            return new AjaxResult(400, "该章节不存在");
        }else {
            HashMap<String, Chapter> res = new HashMap<>();
            res.put("chapter", chapter);
            return new AjaxResult(200, "获取到章节", res);
        }
    }



}
