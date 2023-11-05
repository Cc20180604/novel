package com.cc.model;

public class ChapterRequest {
    private Integer id;
    Chapter chapter;

    public Integer getId() {
        return id;
    }

    public ChapterRequest setId(Integer id) {
        this.id = id;
        return this;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public ChapterRequest setChapter(Chapter chapter) {
        this.chapter = chapter;
        return this;
    }
}
