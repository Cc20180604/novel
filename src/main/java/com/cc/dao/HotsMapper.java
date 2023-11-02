package com.cc.dao;

import com.cc.model.Hots;
import com.cc.model.Novel;

import java.util.List;

public interface HotsMapper {
    List<Hots> getHotNovel(int num);
}