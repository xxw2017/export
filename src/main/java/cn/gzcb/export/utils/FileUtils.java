package cn.gzcb.export.utils;

import cn.gzcb.export.common.constant.ExportConstant;

import java.io.File;
import java.io.IOException;

/**
 * @author xiongxianwei
 * 2018/3/7
 */
public class FileUtils {

    public static File getFile(String name) {
        String path = ExportConstant.FILE_PATH;
        String filename= name+ExportConstant.FILENAME;

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(path + filename);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return file;
    }
}
