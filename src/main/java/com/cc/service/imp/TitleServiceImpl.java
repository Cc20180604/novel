package com.cc.service.imp;

import com.cc.model.Chapter;
import com.cc.model.Title;
import com.cc.service.TitleService;
import com.cc.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
@Service
public class TitleServiceImpl implements TitleService {
    @Value("${path.chapterPath}")
    String chapterPath;
    @Override
    public  ArrayList<Title> getTitlesById(int novelId) throws IOException {
        //反序列化章节
        ArrayList<Title> titles = (ArrayList<Title>) FileUtil.disSerObject(this.chapterPath + "/" + novelId +  "/titles");
        return titles;
    }

    /**
     * 插入一个标题
     * @param novelId 小说id
     * @param index 插入的标题下标 null在末尾插入
     * @param title 章节名对象
     * @throws Exception
     */
    @Override
    public void addTitle(int novelId, Integer index, Title title) throws IOException, IndexOutOfBoundsException {
        //原来的标题集合
        ArrayList<Title> titles = getTitlesById(novelId);
        //为空加入末尾
        if (index == null){
            titles.add(title);
        }else {
            titles.add(index, title);
        }



        //更改后面的的所有章节文件的文件名都+1
        for(int i = titles.size()-3; i >= index; i--){
            File file = new File(chapterPath+ novelId +"/"+i+".chapter");
            System.out.println(file.exists());
            if ( !file.renameTo(new File(chapterPath + novelId  + "/" + (i+1) +".chapter")) ){
                throw new FileNotFoundException( chapterPath + novelId +"/"+ (i) +".chapter: 重命名失败");
            }
        }
        //持久化
        serTitles(novelId,titles);

    }

    /**
     * 持久化章节数组
     * @param novelId
     * @param titles
     * @throws IOException
     */
    private void serTitles(int novelId, ArrayList<Title> titles) throws IOException {
        FileUtil.serObject(titles, chapterPath + novelId + "/" + "titles");
    }

    /**
     * 章节名位移
     * @param chapterRootPath
     * @param startIndex
     * @param endIndex
     */
    public static void chapterNameToNext(String chapterRootPath, int startIndex, int endIndex){

    }
}
