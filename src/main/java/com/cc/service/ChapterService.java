package com.cc.service;

import com.cc.model.Chapter;
import org.springframework.stereotype.Service;


public interface ChapterService {
    Chapter getChapterById(int novelId, int chapterNum) throws Exception;
}
