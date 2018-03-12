package cn.gzcb.export.strategypattern;

import cn.gzcb.export.strategypattern.Enum.PatternEnum;
import cn.gzcb.export.strategypattern.common.Command;
import cn.gzcb.export.strategypattern.common.Constant;
import cn.gzcb.export.strategypattern.common.Parameter;
import cn.gzcb.export.strategypattern.common.Strategy;
import cn.gzcb.export.strategypattern.exception.InputIllegalException;
import cn.gzcb.export.strategypattern.implement.GeneratedSensitive;
import cn.gzcb.export.strategypattern.implement.HiddenSensitive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Service {

    //不同字段对应不同的事物处理
    private static Map<String,Strategy> map =new HashMap<>();

    //指令留根
    private static List<Parameter> parameters;

    //注入指令集合
    public Service(List<Parameter> parameters){
        this.parameters=parameters;
    }


    /*通过对应数据实体对象字段名，获取加密方式*/
    public Strategy getStrategy(String name) throws InputIllegalException{
        //查看是否存在对应字段注册的解决方案
        if (map.get(name)==null){
            //如果没有对应的解决方案，则创建解决方案
            //创建过程：在存储的指令集合中找到相对应的指令
            Parameter parameter=null;
            for (Parameter entity:parameters
                 ) {
                if(entity.getTypeName().equals(name)){
                    parameter=entity;
                }
            }
            try{
                Command command=new Command();
                PatternEnum patternEnum=PatternEnum.valueOf(parameter.getPatternEnum());
                Map<String,Object> map=new HashMap<>();
                command.setPatternEnum(patternEnum);
                command.setMap(map);
                map.put(Constant.RANDOM_LENGTH,parameter.getRH());
                map.put(Constant.OFFSET,parameter.getOT());
                map.put(Constant.REPLACE,parameter.getRE());
                map.put(Constant.STRINGTYPE,parameter.getRT());
                Strategy strategy=null;
                switch (patternEnum){
                    case Hidden_Characters:strategy=new HiddenSensitive(command);break;
                    case Generated_Randomly:strategy=new GeneratedSensitive(command);break;
                    default:throw new InputIllegalException("指令输入不合法");
                }
                map.put(name,strategy);
            }catch (Exception e){
                throw new InputIllegalException("指令输入不合法");
            }
        }
        return map.get(name);
    }
}
