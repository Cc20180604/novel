package com.cc.model;

import org.springframework.stereotype.Component;

@Component
public class ChapterPage {
    private Novel novel;
    private Chapter chapter;

    public Novel getNovel() {
        return novel;
    }

    public ChapterPage setNovel(Novel novel) {
        this.novel = novel;
        return this;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public ChapterPage setChapter(Chapter chapter) {
        this.chapter = chapter;
        return this;
    }

    @Override
    public String toString() {
        return "ChapterPage{" +
                "novel=" + novel +
                ", chapter=" + chapter +
                '}';
    }
}
