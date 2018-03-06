package cn.gzcb.export.xxw;

import cn.gzcb.export.constant.ExportConstant;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiongxianwei
 * 2018/3/6
 */
public class CsvExportRun {

    /**
     * @param args
     */
    public static void main(String[] args) {
        File file = getFile();

        List<Student> stooges = new ArrayList<Student>();
        for (int i = 0; i< ExportConstant.MAX_EXPORT_COUNT; i++){
            Student stu = new Student();
            stu.setAge(i+10);
            stu.setName("name " +i);
            stu.setSex(i/2==0 ?"boy":"girl");
            stooges.add(stu);
        }
        CsvWriter cw = null;
        long startTimne = System.currentTimeMillis();
        try {
// J2EE Web下载时为下面注释的代码，传人的参数是HttpServletResponse
//            cw = new CsvWriter(response.getWriter());
            cw = new CsvWriter(new PrintWriter(file));
            for (Student stu : stooges) {
                cw.writeLine(getCsvLine(stu));
            }
            cw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cw != null) {
                    cw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Done.用时="+((endTime-startTimne)/1000)+"秒");
    }

    private static List<String> getCsvLine(Student stu) {
        List<String> csvLine = new ArrayList<String>();
        csvLine.add(stu.getName());
        csvLine.add(Integer.toString(stu.getAge()));
        csvLine.add(stu.getSex());
        return csvLine;
    }

    private static File getFile() {
        String path = ExportConstant.FILE_PATH;
        String filename= ExportConstant.FILENAME;

        File directory = new File(path);
        if (!directory.exists())
            directory.mkdirs();
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