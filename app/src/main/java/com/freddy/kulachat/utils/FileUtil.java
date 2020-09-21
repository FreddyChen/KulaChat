package com.freddy.kulachat.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/25 12:40
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class FileUtil {

    private static final String TAG = FileUtil.class.getSimpleName();

    public static void saveContentToSDCard(Context context, String fileName, String content) {
        FileUtil.saveContentToSDCard(context, null, fileName, content, false);
    }

    public static void saveContentToSDCard(Context context, String fileName, String content, boolean append) {
        FileUtil.saveContentToSDCard(context, null, fileName, content, append);
    }

    public static void saveContentToSDCard(Context context, String directoryName, String fileName, String content, boolean append) {
        if (context == null) {
            return;
        }

        if (StringUtil.isEmpty(fileName)) {
            return;
        }

        File directory = getCompatDirectory(context, Environment.DIRECTORY_DOCUMENTS, directoryName);
        if (directory == null) {
            return;
        }

        if (!directory.exists() || !directory.isDirectory()) {
            directory.mkdirs();
        }

        FileWriter writer = null;
        try {
            File file = new File(getFilePath(directory.getPath(), fileName));
            if (!append) {
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
            } else {
                if (!file.exists()) {
                    file.createNewFile();
                }
            }

            writer = new FileWriter(file, append);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readContentFromSDCard(Context context, String directoryName, String fileName) {
        if (context == null) {
            return null;
        }

        if (StringUtil.isEmpty(fileName)) {
            return null;
        }

        File directory = getCompatDirectory(context, Environment.DIRECTORY_DOCUMENTS, directoryName);
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            return null;
        }

        File file = new File(getFilePath(directory.getPath(), fileName));
        Log.d("Freddy", "file path = " + file.getPath());
        if (!file.exists() || !file.isFile()) {
            return null;
        }

        InputStream is = null;
        try {
            is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private static File getCompatDirectory(Context context, String directoryType) {
        return FileUtil.getCompatDirectory(context, directoryType, null);
    }

    private static String getFilePath(String directoryPath, String fileName) {
        return directoryPath + File.separator + fileName;
    }

    public static String getFilePath(Context context, String directoryType, String directoryName) {
        File directory = getCompatDirectory(context, directoryType, directoryName);
        if(directory == null || !directory.isDirectory() || !directory.exists()) {
            return null;
        }
        return directory.getPath();
    }

    private static File getCompatDirectory(Context context, String directoryType, String directoryName) {
        if (context == null) {
            return null;
        }

        if (StringUtil.isEmpty(directoryType)) {
            return null;
        }

        File directory = context.getExternalFilesDir(directoryType);
        if (directory != null && directory.exists() && directory.isDirectory()) {
            if (StringUtil.isEmpty(directoryName)) {
                directory = new File(directory.getPath());
            } else {
                directory = new File(directory.getPath() + File.separator + directoryName);
            }
        }

        return directory;
    }

    public static boolean isFileExists(Context context, String directoryType, String directoryName, String fileName) {
        if (context == null) {
            return false;
        }

        if (StringUtil.isEmpty(fileName)) {
            return false;
        }

        File directory = getCompatDirectory(context, directoryType, directoryName);
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            return false;
        }

        File file = new File(getFilePath(directory.getPath(), fileName));
        return file.exists() && file.isFile();
    }

    public static boolean deleteFile(Context context, String directoryType, String directoryName, String fileName) {
        if (context == null) {
            return false;
        }

        if (StringUtil.isEmpty(fileName)) {
            return false;
        }

        File directory = getCompatDirectory(context, directoryType, directoryName);
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            return false;
        }

        File file = new File(getFilePath(directory.getPath(), fileName));
        if (file.exists() && file.isFile()) {
            return file.delete();
        }

        return false;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("交换前" + list);
        list.remove(2);
        list.add(0, 2);
        System.out.println("交换后" + list);
    }
}
