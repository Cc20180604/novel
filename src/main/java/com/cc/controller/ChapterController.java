package com.cc.controller;

import com.cc.model.Chapter;
import com.cc.model.ChapterPage;
import com.cc.model.Novel;
import com.cc.service.ChapterService;
import com.cc.service.FileService;
import com.cc.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xin.altitude.cms.common.entity.AjaxResult;

import java.io.IOException;
import java.util.HashMap;

@RestController
@CrossOrigin(originPatterns = "http://localhost:8080")
@RequestMapping("/chapter")
@Slf4j
public class ChapterController {
    @Resource
    ChapterService chapterService;
    @Resource
    FileService fileService;
    //允许跨域请求
    /**
     * 获取小说章节
     * @param novelId
     * @param chapterNum
     * @return
     */
    //@CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @GetMapping("/{novelId}/{chapterNum}")
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

    /**
     * 上传章节
     * @param id
     * @param novelFile
     * @return
     */
    //允许跨域请求

    @CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult upload(
            @RequestParam(value = "id", defaultValue = "-1")
            Integer id,
            @RequestParam("novel") MultipartFile novelFile
    ){
        HashMap<String, String> res = new HashMap<>();
        //持久化章节对象
        try {
            fileService.serChapters(id,novelFile);
        } catch (IOException e) {
            log.error("io异常", e.getMessage());
            return new AjaxResult(500,"io异常",res);
        }

        //上传成功
        return new AjaxResult(200,"success",res);
        //return novelService.maxId();
    }


}
