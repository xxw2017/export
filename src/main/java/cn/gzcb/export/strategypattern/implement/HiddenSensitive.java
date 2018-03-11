package cn.gzcb.export.strategypattern.implement;

import cn.gzcb.export.strategypattern.common.Command;
import cn.gzcb.export.strategypattern.common.Constant;
import cn.gzcb.export.strategypattern.common.Strategy;
import cn.gzcb.export.strategypattern.exception.InputIllegalException;
import cn.gzcb.export.strategypattern.exception.ParametersIllegalException;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;

import java.util.HashMap;
import java.util.Map;

public class HiddenSensitive implements Strategy {

    /*脱敏指令*/
    private Command command;

    public HiddenSensitive(Command command){
        this.command=command;
    }

    /**
     * 对输入字符串通过命令，进行脱敏操作，并返回脱敏后字符串
     * 此实现对主串指定位置、指定长度的子字符串的所有字符，替换为相应字符
     * @param importation 输入字符串
     * @return
     * @throws InputIllegalException
     * @throws ParametersIllegalException
     */
    @Override
    public String removeSensitive(String importation) throws InputIllegalException,ParametersIllegalException{
        //脱敏前字段长度
        int begin_length=importation.length();
        int offset=0;
        int cover_length=0;
        String replace="";
        Map<String,Object> map=command.getMap();
        try{
            offset=(int)map.get(Constant.OFFSET);
            cover_length=(int)map.get(Constant.COVER_LENGTH);
            if (begin_length==0
                    ||offset>(begin_length-1)
                    ||(offset+cover_length)>begin_length
                    ) {
                throw new ParametersIllegalException("参数不合法");
            }
            replace=map.get(Constant.REPLACE).toString();
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
//        GeneratedSensitive generatedSensitive =new GeneratedSensitive();
//        String sss="1234567890";
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put(Constant.OFFSET,5);
//        map.put(Constant.COVER_LENGTH,2);
//        map.put(Constant.REPLACE,"*");
//        Command command =new Command();
//        command.setMap(map);
//        try {
//            System.out.println(generatedSensitive.removeSensitive(sss,command));
//        } catch (InputIllegalException e) {
//            e.printStackTrace();
//        } catch (ParametersIllegalException e) {
//            e.printStackTrace();
//        }
    }
}
