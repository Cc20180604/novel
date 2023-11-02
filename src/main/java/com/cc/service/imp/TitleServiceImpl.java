package com.cc.service.imp;

import com.cc.model.Chapter;
import com.cc.model.Title;
import com.cc.service.TitleService;
import com.cc.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class TitleServiceImpl implements TitleService {
    @Value("${path.chapterPath}")
    String chapterPath;
    @Override
    public  ArrayList<Title> getTitlesById(int novelId) throws Exception {
        //反序列化章节
        ArrayList<Title> titles = (ArrayList<Title>) FileUtil.disSerObject(this.chapterPath + "/" + novelId +  "/"+novelId+".titles");
        return titles;
    }
}
