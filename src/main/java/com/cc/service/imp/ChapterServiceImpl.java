package com.cc.service.imp;

import com.cc.model.Chapter;
import com.cc.model.Title;
import com.cc.service.ChapterService;
import com.cc.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Value("${path.chapterPath}")
    String chapterPath;
    @Override
    public Chapter getChapterById(int novelId, int chapterNum) throws Exception {
        //反序列化章节
        Chapter chapter = (Chapter) FileUtil.disSerObject(this.chapterPath + "/" + novelId + "/" + chapterNum + ".chapter");
        return chapter;
    }

    @Override
    public boolean chaptersExist(int novelId) {
        String chapterPath = this.chapterPath + "/" + novelId;
        return FileUtil.dirExist(chapterPath);
    }

    @Override
    public void deleteChapters(int novelId) throws FileNotFoundException {
        String chapterPath = this.chapterPath + "/" + novelId;
        FileUtil.deleteDir(chapterPath);
    }


}
