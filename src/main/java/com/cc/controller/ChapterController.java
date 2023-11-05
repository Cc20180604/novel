package com.cc.controller;

import com.cc.model.Chapter;
import com.cc.model.ChapterPage;
import com.cc.model.ChapterRequest;
import com.cc.model.Novel;
import com.cc.service.ChapterService;
import com.cc.service.FileService;
import com.cc.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xin.altitude.cms.common.entity.AjaxResult;

import java.io.FileNotFoundException;
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

    @CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @PostMapping("/upload")
    public ChapterRequest upload(@RequestBody ChapterRequest chapterRequest){
        try {
            fileService.serChapter(chapterRequest.getId(), chapterRequest.getChapter());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return chapterRequest;
    }

    /**
     * 上传所有
     * @param id
     * @param novelFile
     * @return
     */
    @CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @PostMapping("/uploadAll")
    @ResponseBody
    public AjaxResult uploadAll(
            @RequestParam(value = "id") Integer id,
            @RequestParam("novel") MultipartFile novelFile
    ){
        //返回值
        HashMap<String, String> res = new HashMap<>();
        //判断该小说chapters是否已经存在
        AjaxResult isExist = chaptersExist(id);
        if ((Integer)isExist.get("code") == 400){
            return isExist;
        }
        //持久化章节对象
        try {
            fileService.serChapters(id,novelFile);
        } catch (IOException e) {
            //io异常
            log.error("io异常", e.getMessage());
            return new AjaxResult(500,"io异常",res);
        }

        //上传成功
        return new AjaxResult(200,"success",res);
        //return novelService.maxId();
    }

    /**
     * 小说章节是否已经上传过
     * @param id
     * @return
     */
    @CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @PostMapping("/chaptersExist/{id}")
    @ResponseBody
    public AjaxResult chaptersExist( @PathVariable(value = "id") int id){

        //判断该小说chapters是否已经存在
        if (chapterService.chaptersExist(id)){
            return new AjaxResult(400,"该小说已上传");
        }
        return new AjaxResult(200,"该小说未上传");
    }

    /**
     * 删除小说所有章节
     * @param id
     * @return
     */
    @CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @DeleteMapping("/deleteChapters/{id}")
    @ResponseBody
    public AjaxResult deleteChapters( @PathVariable(value = "id") int id){

        boolean res = false;
        try {
            chapterService.deleteChapters(id);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            return new AjaxResult(400,"找不到该章节目录");
        }
        //成功删除
        return new AjaxResult(200,"success");
    }




}
