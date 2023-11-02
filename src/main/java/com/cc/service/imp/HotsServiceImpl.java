package com.cc.service.imp;

import com.cc.dao.HotsMapper;
import com.cc.model.Hots;
import com.cc.service.HotsService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HotsServiceImpl implements HotsService {
    @Mapper
    HotsMapper hotsMapper;
    @Override
    public List<Hots> getHots(int num) {
        return hotsMapper.getHotNovel(num);
    }
}
