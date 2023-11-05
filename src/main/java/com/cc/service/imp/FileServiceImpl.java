package com.cc.service.imp;

import com.cc.dao.NovelMapper;
import com.cc.model.Chapter;
import com.cc.service.FileService;
import com.cc.util.FileUtil;
import com.cc.util.JsonUtil;
import com.cc.util.NovelUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Value("${path.imgPath}")
    String imgRootPath ;
    @Value("${path.bgcPath}")
    String bgcRootPath;

    @Value("${path.chapterPath}")
    //章节文件根路径
    String chapterPath;
    @Resource
    NovelMapper novelMapper;
    @Override
    public byte[] getImg(int id) throws IOException {
        String path = imgRootPath + id + ".png";
        return FileUtil.getFileBytes(path);
    }

    @Override
    public byte[] getBgc(String name) throws IOException {
        String path = imgRootPath + bgcRootPath + name + ".png";
        return FileUtil.getFileBytes(path);
    }

    @Override
    public void serChapters(int id, MultipartFile novelFile) throws IOException {
        HashMap<String,List> chaptersAndTitles= NovelUtil.toChapterList(novelFile);

        NovelUtil.serChaptersAndTitles(chaptersAndTitles,chapterPath, id+"");
    }

    @Override
    public void serChapter(int NovelId, Chapter chapter) throws IOException {
        String path = chapterPath + NovelId + "/" + chapter.getIndex();
        NovelUtil.serChapter(chapter,path);
    }


}
