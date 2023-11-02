package com.cc.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;


@Component
@ConfigurationProperties(prefix = "chapter")
public class Chapter implements Serializable {
    @Serial
    private static final long serialVersionUID = 5245733160038769159L;
    //章节索引
    private int index;

    //章节标题
    private Title title;
    //正文数组
    private ArrayList<String> pList;

    public int getIndex() {
        return index;
    }

    public Chapter setIndex(int index) {
        this.index = index;
        return this;
    }

    public Title getTitle() {
        return title;
    }

    public Chapter setTitle(Title title) {
        this.title = title;
        return this;
    }

    public ArrayList<String> getpList() {
        return pList;
    }

    public Chapter setpList(ArrayList<String> pList) {
        this.pList = pList;
        return this;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "index=" + index +
                ", title=" + title +
                ", pList=" + pList +
                '}';
    }
}
