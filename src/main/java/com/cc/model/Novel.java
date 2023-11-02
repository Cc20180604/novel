package com.cc.model;

import com.cc.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
@Component

@Data
public class Novel {

    private Integer id;

    private String type;

    private String name;

    private String author;

    private String describes;

    private String wordcount;

    private String lastChapter;

}