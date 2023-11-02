package com.cc.service.imp;

import com.cc.dao.NovelMapper;
import com.cc.model.Novel;
import com.cc.service.NovelService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class NovelServiceImpl implements NovelService {
    @Resource
    private NovelMapper novelMapper;

    //加入小说返回自增主键 加入失败返回null
    @Override
    public Integer addNovel(Novel novel) {
        try {
            //返回自增主键 添加失败返回null
            return novelMapper.insertNovel(novel) == 1 ? novel.getId() : null;
        }catch (Exception exp){

            log.error("【novel添加失败】,"+novel+exp.getMessage());
            return null;
        }

    }

    @Override
    public int deleteNovelById(int id) {
        return novelMapper.deleteById(id);
    }

    @Override
    public int updateNovel(Novel novel) {
        return novelMapper.update(novel);
    }

    @Override
    public Novel getNovelById(int id) {
        return novelMapper.selectById(id);
    }

    @Override
    public List<Novel> getNovelByRand(int num) {
        return novelMapper.selectByRand(num);
    }

    @Override
    public boolean idExist(int id) {
        return novelMapper.findId(id) == 1;
    }

    @Override
    public Integer maxId() {
        return novelMapper.selectMaxId();
    }


}
