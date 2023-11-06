package com.cc.service;

import com.cc.model.Chapter;
import com.cc.model.Title;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public interface ChapterService {
    Chapter getChapterById(int novelId, int chapterNum) throws Exception;
    boolean chaptersExist(int novelId);
    void deleteChapters(int novelId) throws FileNotFoundException;

}
