package com.cc.dao;

import com.cc.model.Novel;

import java.util.List;

public interface NovelMapper {
    //添加小说 成功返回主键, 失败null
    Integer insertNovel(Novel novel);
    // 根据id来删除小说
    int deleteById(int id);
    //更新小说
    int update(Novel novel);
    //查询小说
    Novel selectById(int id);
    //随机取数据
    List<Novel> selectByRand(int Num);
    //获得最大id 表空为null
    Integer selectMaxId();

    int findId(int id);

}