package com.cc.controller;

import com.cc.model.Novel;
import com.cc.service.NovelService;
import com.cc.util.JsonUtil;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
    public AjaxResult addNovel(@RequestBody Novel novel) {
        Integer resId = novelService.addNovel(novel);
        HashMap<String, Object> res = new HashMap();
        if(resId != null){
            //创建成功
            res.put("novelId",resId);
            return new AjaxResult(201,"创建成功", res);
        }else {
            //创建失败 id重复
            res.put("novelId",null);
            return new AjaxResult(202, "id:" + novel.getId() + ",已经存在", res);
        }

    }

    //允许跨域请求
    @CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public AjaxResult deleteNovelById(@PathVariable("id") int id) {
        HashMap<String, String> res = new HashMap<>();
        if(novelService.deleteNovelById(id) == 1){
            //删除成功
            res.put("res", "success");
            return new AjaxResult(200,"小说(id=" + id + ")删除成功",res);
        }else {
            //删除失败 id不存在
            res.put("res", "failure");
            return new AjaxResult(202, "小说(id=" + id + ")不存在", res);
        }
    }

    //允许跨域请求
    @CrossOrigin(originPatterns = "http://127.0.0.1:8848")
    @PutMapping("/update")
    @ResponseBody
    public AjaxResult updateNovel(@RequestBody Novel novel) {
        HashMap<String, String> res = new HashMap<>();
        if(novelService.updateNovel(novel) == 1){
            //更新成功
            res.put("res", "success");
            return new AjaxResult(200,"小说(id=" + novel.getId() + ")修改成功",res);
        }else {
            //更新失败 id不存在
            res.put("res", "failure");
            return new AjaxResult(202, "小说(id=" + novel.getId() + ")不存在", res);
        }
    }

    //允许跨域请求
    @GetMapping("/get/{id}")
    @ResponseBody
    public AjaxResult getNovel(@PathVariable("id") int id) {
        Novel novel =  novelService.getNovelById(id);
        HashMap<String, Novel> res = new HashMap<>();
        if(novel != null){
            //获取成功
            res.put("novel", novel);
            return new AjaxResult(200,"小说(id=" + novel.getId() + ")获取成功",res);
        }else {
            //更新失败 id不存在
            res.put("novel", null);
            return new AjaxResult(202, "小说(id=" + id + ")不存在", res);
        }
    }

    //允许跨域请求

    /**
     * 随机获取基本小说
     * @param num 需要的数量
     * @return
     */
    @CrossOrigin(originPatterns = "http://localhost:8080")
    @GetMapping("/rand/{num}")
    @ResponseBody
    public AjaxResult getRandNovel(@PathVariable("num") int num) {
        List<Novel> novels =  novelService.getNovelByRand(num);
        HashMap<String, List<Novel>> res = new HashMap<>();
        if(novels.size() != 0){
            //获取成功
            res.put("novels", novels);
            return new AjaxResult(200,"随机小说(num=" + num + ")获取成功",res);
        }else {
            //库中未加入小说
            res.put("novels", null);
            return new AjaxResult(202, "库中暂未加入小说", res);
        }
    }


}
