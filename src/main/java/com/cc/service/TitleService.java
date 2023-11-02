package com.cc.service;

import com.cc.model.Chapter;
import com.cc.model.Title;

import java.util.ArrayList;

public interface TitleService {
    ArrayList<Title> getTitlesById(int novelId) throws Exception;
}
