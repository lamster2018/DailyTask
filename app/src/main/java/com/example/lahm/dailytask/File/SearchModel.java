package com.example.lahm.dailytask.File;

/**
 * Project Name:MyApplication
 * Package Name:com.example.lahm.myapplication
 * Created by lahm on 17/2/11 下午12:15 .
 * https://github.com/lamster2018
 */

public class SearchModel {

    private String fileName;
    private String filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static SearchModel formatData(String a, String b) {
        SearchModel s = new SearchModel();
        s.setFileName(a);
        s.setFilePath(b);
        return s;
    }
}