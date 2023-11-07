package com.cc.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Arrays;

@Slf4j
public class FileUtil {
    private static ObjectInputStream oos;

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
    public static Object disSerObject(String path) throws IOException{
        //反序列化流
        ObjectInputStream oos = new ObjectInputStream(new FileInputStream(path));
        //反序列化对象
        Object obj = null;
        try {
            obj = oos.readObject();
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        } finally {
            if (oos != null) {
                //关闭
                oos.close();
            }
        }


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

    public static void deleteDir(String path) throws FileNotFoundException {
        //判断文件夹是否存在
        if (!dirExist(path)) {
            throw new FileNotFoundException("文件夹不存在:"+path);
        }
        //递归删除
        deleteFolder(new File(path));
        log.warn("已删除:"+path);

    }



    //递归删除文件夹
    private static void deleteFolder(File file){
        //判断是否为文件,显示删除的文件名称
        if(file.isFile()){
            file.delete();
            //不是文件的话,就是文件夹
        }else{
            //获取文件夹中的所有File对象,如果为空,则files.length为0,次处程序不执行
            File[] files=file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFolder(files[i]);
            }
        }
        //最后删除空文件夹!!!
        file.delete();
    }




}
