package com.cc.controller;

import com.cc.model.Novel;
import com.cc.service.NovelService;
import com.cc.util.JsonUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/novel")
@CrossOrigin(originPatterns = "http://localhost:8080")
public class NovelController {
    @Resource
    NovelService novelService;

    //允许跨域请求
    @CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @PostMapping("/add")
    @ResponseBody
    public Integer addNovel(@RequestBody Novel novel) {
        return novelService.addNovel(novel);
    }

    //允许跨域请求
    @CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Boolean deleteNovelById(@PathVariable("id") int id) {
        return novelService.deleteNovelById(id) == 1;
    }

    //允许跨域请求
    @CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @PutMapping("/update")
    @ResponseBody
    public Boolean updateNovel(@RequestBody Novel novel) {
        return novelService.updateNovel(novel) == 1;
    }

    //允许跨域请求

    @GetMapping("/get/{id}")
    @ResponseBody
    public String getNovel(@PathVariable("id") int id) {
        Novel novel =  novelService.getNovelById(id);
        return JsonUtil.obj2JsonStr(novel);
    }

    //允许跨域请求
    @CrossOrigin(originPatterns = "http://localhost:8080")
    @GetMapping("/rand/{num}")
    @ResponseBody
    public List getRandNovel(@PathVariable("num") int num) {
        List<Novel> novels =  novelService.getNovelByRand(num);
        return novels;
    }


}
