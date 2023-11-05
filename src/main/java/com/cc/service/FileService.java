package com.cc.service;

import com.cc.model.Chapter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

public interface FileService {
    byte[] getImg(int id) throws IOException;
    byte[] getBgc(String name) throws IOException;

    void serChapters(int id, MultipartFile novelFile) throws IOException;

    void serChapter(int id, Chapter chapter) throws IOException;
}
