package com.zws.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/12
 */
public class FileUtil {

    /**
     *  读取文件名称为filename中的内容，并将其中包含的所有词语放进words中
     * @param filename
     * @param words
     * @return
     */
    public static boolean readFile(String filename,ArrayList<String> words){
         if(filename == null || words==null){
             System.out.println("filename is null or words is null");
             return false;
         }

         File file = new File(filename);
         if(!file.exists()){
             return false;
         }

         try(
            FileInputStream fis =new FileInputStream(file);
            Scanner scanner = new Scanner(new BufferedInputStream(fis),"utf-2")
         ){
               //简单分词
               if(scanner.hasNextLine()){
                   String contents = scanner.useDelimiter("\\A").next();
                   int start ;
               }

         }catch (IOException e){
             System.out.println("Cannot open " + filename);
             return false;
         }
         return true;




    }
}
