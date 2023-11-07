package com.cc.controller;

import com.cc.model.Chapter;
import com.cc.model.ChapterPage;
import com.cc.model.Novel;
import com.cc.model.Title;
import com.cc.service.ChapterService;
import com.cc.service.TitleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/titles")
@Slf4j
public class TitleController {
    @Resource
    TitleService titleService;

    /**
     * 获取标题集合
     * @param novelId
     * @return
     */
    @GetMapping("/get/{novelId}")
    @ResponseBody
    public AjaxResult getTitles(@PathVariable("novelId") int novelId) {
        ArrayList<Title> titles = null;
        try {
            titles = titleService.getTitlesById(novelId);
        } catch (Exception e) {
            return new AjaxResult(400,e.getMessage());
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("titles", titles);
        data.put("size",titles.size());
        return new AjaxResult(200, "success", data);
    }
    @PutMapping("/add/{novelId}/{index}")
    @ResponseBody
    public AjaxResult addTitles(@PathVariable Integer novelId, @PathVariable Integer index, @RequestBody Title title){
        HashMap<String, Object> data = new HashMap<>();

        try {
            titleService.addTitle(novelId,index,title);
        } catch (IOException e) {
            log.error(e.getMessage());
            data.put("error", e.getMessage());
            //大概率novelId不存在
            return new AjaxResult(500, "error", data);
        } catch (IndexOutOfBoundsException e){
            log.error(e.getMessage());
            data.put("error", e.getMessage());
            //下标越界
            return new AjaxResult(500, "数组下标越界", data);
        }


        return new AjaxResult(200, "success");
    }


}
