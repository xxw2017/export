package cn.gzcb.export.utils;

import java.util.Random;

/**
 * @author xiongxianwei
 * 2018/3/7
 */
public class NumProductUtil {
    /**
     * 根据指定长度生成纯数字的随机数
     */
    public static String createData(int length) {
        StringBuilder sb=new StringBuilder();
        Random rand=new Random();
        for(int i=0;i<length;i++)
        {
            sb.append(rand.nextInt(10));
        }
        String data=sb.toString();
        return data;
    }
}
