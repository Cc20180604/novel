package com.cc.model;

import java.io.Serial;
import java.io.Serializable;

public class Title implements Serializable {
    @Serial
    private static final long serialVersionUID = -8619833961061330385L;
    //章节数
    //第*章
    private String num;
    //章节名
    private String name;
    //不规范章节
    private String illegalName;

    public String getNum() {
        return num;
    }

    public Title setNum(String num) {
        this.num = num;
        return this;
    }

    public String getName() {
        return name;
    }

    public Title setName(String name) {
        this.name = name;
        return this;
    }

    public String getIllegalName() {
        return illegalName;
    }

    public Title setIllegalName(String illegalName) {
        this.illegalName = illegalName;
        return this;
    }

    @Override
    public String toString() {
        return "Title{" +
                "num='" + num + '\'' +
                ", name='" + name + '\'' +
                ", illegalName='" + illegalName + '\'' +
                '}';
    }
}
