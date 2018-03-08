package cn.gzcb.export.strategypattern.implement;

import cn.gzcb.export.strategypattern.common.Command;
import cn.gzcb.export.strategypattern.common.Constant;
import cn.gzcb.export.strategypattern.common.PatternEnum;
import cn.gzcb.export.strategypattern.common.Strategy;
import cn.gzcb.export.strategypattern.exception.InputIllegalException;
import cn.gzcb.export.strategypattern.exception.ParametersIllegalException;

import java.util.HashMap;
import java.util.Map;

public class GeneratedSensitive implements Strategy {


//
//    /*偏移量（从左到右）*/
//    public final static String OFFSET="OT";
//
//    /*需要替换字符成为*/
//    public final static String REPLACE="RE";

    /**
     * 对输入字符串通过命令，进行脱敏操作，并返回脱敏后字符串
     * 通过输入长度，生成随机字符串
     * @param importation 输入字符串
     * @param command 脱敏指令
     *               command.map
     *
     *
     * @return
     * @throws InputIllegalException
     * @throws ParametersIllegalException
     */
    @Override
    public String removeSensitive(String importation, Command command) throws InputIllegalException, ParametersIllegalException {

        //脱敏前字段长度
        int begin_length=importation.length();
        int offset=0;
        int cover_length=0;
        String replace="";
        Map<String,String> map=command.getMap();
        try{
            offset=Integer.parseInt(map.get(Constant.OFFSET));
            cover_length=Integer.parseInt(map.get(Constant.COVER_LENGTH));
            if (begin_length==0
                    ||offset>(begin_length-1)
                    ||(offset+cover_length)>begin_length
                    ) {
                throw new ParametersIllegalException("参数不合法");
            }
            replace=map.get(Constant.REPLACE);
        }catch (Exception e){
            throw new ParametersIllegalException("参数不合法");
        }
        //计算需要替换为的字符串长度
        StringBuffer stringBuffer=new StringBuffer(importation.substring(0,offset));
        //需要循环追加字符，计算追加数量
        for (int i=0;i<cover_length;i++) {
            stringBuffer.append(replace);
        }
        stringBuffer.append(importation.substring(offset+cover_length));
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        GeneratedSensitive generatedSensitive =new GeneratedSensitive();
        String sss="1234567890";
        Map<String,String> map=new HashMap<String,String>();
        map.put(Constant.OFFSET,"5");
        map.put(Constant.COVER_LENGTH,"2");
        map.put(Constant.REPLACE,"*");
        Command command =new Command();
        command.setMap(map);
        try {
            System.out.println(generatedSensitive.removeSensitive(sss,command));
        } catch (InputIllegalException e) {
            e.printStackTrace();
        } catch (ParametersIllegalException e) {
            e.printStackTrace();
        }
    }
}
