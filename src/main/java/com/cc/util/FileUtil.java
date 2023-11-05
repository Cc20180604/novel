package com.cc.util;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileUtil {
    //序列化对象
    public static void serObject(Object obj, String path) throws IOException {
        //序列化流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        //序列化对象
        oos.writeObject(obj);
        //刷新关闭
        oos.flush();
        oos.close();


    }


    //反序列化对象
    public static Object disSerObject(String path) throws Exception {
        //反序列化流
        ObjectInputStream oos = new ObjectInputStream(new FileInputStream(path));
        //反序列化对象
        Object obj = oos.readObject();
        //刷新关闭
        oos.close();

        return obj;
    }

    //创建多级目录
    public static boolean mkdirs(String dirStr) {
        File dir = new File(dirStr);
        return dir.mkdirs();
    }

    public static byte[] getFileBytes(String path) throws IOException {
        return IOUtils.toByteArray(new BufferedInputStream(new FileInputStream(path)));

    }

    /**
     * 判断文件夹是否存在
     * @param path
     * @return
     */
    public static boolean dirExist(String path){
        File file = new File(path);
        return file.isDirectory() && file.exists();
    }



}
