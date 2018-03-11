package cn.gzcb.export.strategypattern;

import cn.gzcb.export.strategypattern.Enum.PatternEnum;
import cn.gzcb.export.strategypattern.common.Parameter;
import cn.gzcb.export.strategypattern.common.Strategy;
import cn.gzcb.export.strategypattern.implement.GeneratedSensitive;
import cn.gzcb.export.strategypattern.implement.HiddenSensitive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {

    //不同字段对应不同的事物处理
    private static Map<String,Strategy> map =new HashMap<>();

    //指令留根
    private static List<Parameter> parameters=null;

    //注入指令集合
    public Service(List<Parameter> parameters){
        this.parameters=parameters;
    }


    /*通过对应数据实体对象字段名，获取加密方式*/
    public Strategy getStrategy(String name){
        //查看是否存在对应字段注册的解决方案
        if (map.get(name)==null){
            //如果没有对应的解决方案，则创建解决方案
            //创建过程：在存储的指令集合中找到相对应的指令
//            Parameter parameter=parameters.
//            switch ()

        }
        return map.get(name);
    }
}
