package cn.gzcb.export.strategypattern.common;

import java.util.Random;

public class Util {
    private static IdCardGenerator idCardGenerator=new IdCardGenerator();

    /**
     * 根据〖中华人民共和国国家标准 GB 11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * @return 生成身份证号
     */
    public static String generateIDNumber() {
        return idCardGenerator.generate();
    }

    /**
     * 生成随机数
     * @param start
     * @param end
     * @return
     */
    public static int getNum(int start,int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }
    /**
    * 返回手机号码
    */
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    private static String getTel() {
       int index = getNum(0, telFirst.length - 1);
       String first = telFirst[index];
       String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
       String thrid = String.valueOf(getNum(1, 9100) + 10000).substring(1);
       return first + second + thrid;
    }

    /**
     * 获取count个随机数
     * @param count 随机数个数
     * @return
     */
    public static String game(int count){
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<count;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        return sb.toString();
    }
}
