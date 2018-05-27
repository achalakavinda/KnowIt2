package com.edu.knowit.knowit.Util;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Achala Kavinda on 5/12/2018.
 */

public class FileSearch {

    /**
    * search directory and get list of all directories in it
     */
    public static ArrayList<String> getDirectoryPaths(String directory){
        ArrayList<String> pathArray = new ArrayList<>();
        String a= Environment.getExternalStorageDirectory().toString()+"/";
        File file = new File(a);
        File[] listfiles = file.listFiles();
        for(int i = 0; i < listfiles.length; i++){
            if(listfiles[i].isDirectory()){
                pathArray.add(listfiles[i].getAbsolutePath());
            }
        }
        return pathArray;
    }

    /**
     * search directory and get all file inside it
     */
    public static ArrayList<String> getFilePaths(String directory){
        ArrayList<String> pathArray = new ArrayList<>();
        File file = new File(directory);
        File[] listfiles = file.listFiles();

        if (listfiles != null)
        {
            for (File listfile : listfiles)
            {
                if (listfile.isFile())
                {
                    pathArray.add(listfile.getAbsolutePath());
                }
            }
        }
        return pathArray;
    }
}
