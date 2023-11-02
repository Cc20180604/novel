package com.cc.service;

import com.cc.model.Novel;

import java.util.List;

public interface NovelService {
    Integer addNovel(Novel novel);
    int deleteNovelById(int id);
    int updateNovel(Novel novel);
    // 根据id来查询用户
    Novel getNovelById(int id);

    List<Novel> getNovelByRand(int num);

    boolean idExist(int id);

    Integer maxId();
}
