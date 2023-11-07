package com.cc.service;

import com.cc.model.Chapter;
import com.cc.model.Title;

import java.io.IOException;
import java.util.ArrayList;

public interface TitleService {
    ArrayList<Title> getTitlesById(int novelId) throws Exception;

    void addTitle(int novelId, Integer index, Title title) throws IOException;

}
