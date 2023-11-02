package com.cc.util;

import com.cc.model.Chapter;
import com.cc.model.Title;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class NovelUtil {
    //小说文件转字符串数组
    public static ArrayList<String> multipartFile2StringList(MultipartFile novelFile){
        InputStreamReader reader;
        BufferedReader br;
        //段落数组
        ArrayList ps = new ArrayList();
        try {
            reader = new InputStreamReader(novelFile.getInputStream());
            br = new BufferedReader(reader);
            char[] buf = new char[1024*8];
            //char数组长度
            int len = 0;
            //上一个结尾字符串(未到换行符与下一个拼接成一段)
            StringBuilder lastP = new StringBuilder();


            //遍历出段落数组
            while( (len = br.read(buf)) != -1){

                //拼接char数组为一个字符串
                String charStr = new String(buf, 0, len);
                //切出当前缓冲区所有段落
                String[] tempPs = charStr.replace("\r","").split("\n");


                //如果最后一位不是换行符
                //将数组最后一项保留到下一次循环lastP拼接
                if ('\n' == (charStr.charAt(charStr.length() - 1)) ){
                    //拼接上次循环未完结的段落(第一项)
                    tempPs[0] = lastP + tempPs[0];
                    //保存临时字符串数组最后一项(最后一项)
                    lastP = new StringBuilder(tempPs[tempPs.length-1]);
                }else{
                    //以换行符结尾
                    //清空前一次的结尾
                    lastP = new StringBuilder("");
                }

                //遍历加入到集合中 不取最后一位
                for (var i=0 ; i<tempPs.length - 1 ; i++){
                    ps.add(tempPs[i]);
                }


            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ps;

    }
    //提取章节集合
    //返回标题集合和章节集合
    public static HashMap<String,List> getChaptersAndTitles(List<String> pList){
        Iterator<String> it = pList.iterator();
        //章节集合
        ArrayList<Chapter> chapters = new ArrayList<>();
        //章节对象
        Chapter chapter = null;
        //段落集合
        ArrayList<String> ps = null;
        //第一次读取到 ===
        boolean firstRead = true;
        //所有标题
        ArrayList<Title> titles = new ArrayList<>();
        //遍历段落集合
        while (it.hasNext()) {
            String str =  it.next();

            //第一次读到标题段
            if (str.startsWith("===") && firstRead){
                firstRead = false;
                //新建标题对象
                chapter = new Chapter().setIndex(0);
                //提取章节名
                Title chapterTitle = getTitle(str);
                titles.add(chapterTitle);
                chapter.setTitle(chapterTitle);
                //新建段落集合
                ps = new ArrayList<>();
            }else if(str.startsWith("===") && !firstRead){
                //第二次读取到标题段
                //上一章结束 设置上一章正文集合并加入到章节集合中
                chapters.add(chapter.setpList(ps));
                //新建标题对象
                chapter = new Chapter().setIndex(chapters.size());
                //提取章节名
                Title chapterTitle = getTitle(str);
                titles.add(chapterTitle);
                chapter.setTitle(chapterTitle);
                //新建段落集合
                ps = new ArrayList<>();
            }else {
                //读取过标题
                //将正文内容加入集合
                if(!firstRead){
                    ps.add(str);
                }
            }


        }
        HashMap res = new HashMap<String,List>();
        res.put("titles",titles);
        res.put("chapters",chapters);
        return res;
    }

    public static HashMap<String,List> toChapterList(MultipartFile novelFile){
        //将小说输入流转为list
        List<String> ps = multipartFile2StringList(novelFile);
        //提取所有章节
        return getChaptersAndTitles(ps);
    }
    //提取title对象
    public static Title getTitle(String str){
        //章节名对象
        Title title = new Title();
        //去除指定字符
        str = str.replace("=","");
        //章节名是否符合规范
        //第*章 标题内容
        //标题规范
        if(str.contains("章 ")){
            int begin = str.indexOf("章 ");
            //获取章节数 与 章节名
            String num = str.substring(0, begin+1);
            String name = str.substring(begin+1);
            //标题赋值给章节对象
            title.setNum(num).setName(name);

        }else {
            //不符合章节规范
            title.setIllegalName(str);
        }
        return title;


    }
    //批量序列化章节集合
    public static void serChapters(List<Chapter> chapters, String rootPath, String novelId) throws IOException {
        //该小说的根路径
        String chapterRootPath = rootPath + novelId + "/";

        //新建章节路径
        FileUtil.mkdirs(chapterRootPath);

        //序列化每个章节 0 1 2
        for(int i = 0; i<chapters.size(); i++){
            //持久化为chapter文件
            FileUtil.serObject(chapters.get(i), chapterRootPath + i + ".chapter");
        }
    }
    //序列化标题
    public static void serTitles(List<Title> titles,  String rootPath,  String novelId) throws IOException {
        //该小说的根路径
        String chapterRootPath = rootPath + novelId + "/";

        //新建标题路径
        FileUtil.mkdirs(chapterRootPath);

        //持久化为chapter文件
        FileUtil.serObject(titles, chapterRootPath + novelId  + ".titles");

    }

    public static void serChaptersAndTitles(HashMap<String,List> chaptersAndTitles,  String rootPath,  String novelId) throws IOException {
        serChapters(chaptersAndTitles.get("chapters"),rootPath,novelId);
        serTitles(chaptersAndTitles.get("titles"),rootPath,novelId);
    }
}
