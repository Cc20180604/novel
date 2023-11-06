package com.cc.controller;

import com.cc.dao.HotsMapper;
import com.cc.model.Hots;
import com.cc.model.Novel;
import com.cc.util.JsonUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/novel")
public class NovelListController {
    @Resource
    HotsMapper hotsMapper;
    //获取热度表
    //允许跨域请求
    @GetMapping("/hots")
    @ResponseBody
    public List<Hots> getHots() {
        return hotsMapper.getHotNovel(10);
    }


    //获取推荐表
    //允许跨域请求
    @GetMapping("/recommend")
    @ResponseBody
    public List<Hots> getRecommends() {
        return hotsMapper.getHotNovel(10);
    }
}
