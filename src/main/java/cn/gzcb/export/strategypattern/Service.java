package cn.gzcb.export.strategypattern;

import cn.gzcb.export.model.Customer;
import cn.gzcb.export.strategypattern.Enum.PatternEnum;
import cn.gzcb.export.strategypattern.common.Command;
import cn.gzcb.export.strategypattern.common.Constant;
import cn.gzcb.export.strategypattern.common.Parameter;
import cn.gzcb.export.strategypattern.common.Strategy;
import cn.gzcb.export.strategypattern.exception.InputIllegalException;
import cn.gzcb.export.strategypattern.implement.GeneratedSensitive;
import cn.gzcb.export.strategypattern.implement.HiddenSensitive;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Service<T> {

    //不同字段对应不同的事物处理
    private static Map<String,Strategy> strategy_map =new HashMap<>();

    //指令留根
    private static List<Parameter> parameters;

    //注入指令集合
    public Service(List<Parameter> parameters){
        this.parameters=parameters;
    }


    /*通过对应数据实体对象字段名，获取加密方式*/
    public static Strategy getStrategy(String name) throws InputIllegalException{
        //查看是否存在对应字段注册的解决方案
        if (strategy_map.get(name)==null){
            //如果没有对应的解决方案，则创建解决方案
            //创建过程：在存储的指令集合中找到相对应的指令
            Parameter parameter=null;
            for (Parameter entity:parameters
                 ) {
                if(entity.getTypeName().equals(name)){
                    parameter=entity;
                }
            }
            if (parameter==null){
                return null;
            }
            try{
                //收集指令，并创建相对应的解决方案对象
                Command command=new Command();
                PatternEnum patternEnum=PatternEnum.valueOf(parameter.getPatternEnum());
                Map<String,Object> map=new HashMap<>();
                command.setPatternEnum(patternEnum);
                command.setMap(map);
                map.put(Constant.RANDOM_LENGTH,parameter.getRH());
                map.put(Constant.OFFSET,parameter.getOT());
                map.put(Constant.REPLACE,parameter.getRE());
                map.put(Constant.STRINGTYPE,parameter.getRT());
                map.put(Constant.COVER_LENGTH,parameter.getCL());
                Strategy strategy=null;
                switch (patternEnum){
                    case Hidden_Characters:strategy=new HiddenSensitive(command);break;
                    case Generated_Randomly:strategy=new GeneratedSensitive(command);break;
                    default:break;
                }
                strategy_map.put(name,strategy);
            }catch (Exception e){
                e.printStackTrace();
                //throw new InputIllegalException("指令输入不合法");
            }
        }
        return strategy_map.get(name);
    }


    /*对对象进行修改*/
    public void doSomething(T model){
        // 获取实体类的所有属性，返回Field数组
        Field[] field = model.getClass().getDeclaredFields();
        // 遍历所有属性
        for (int j = 0; j < field.length; j++){
            // 获取属性的名字
            String name = field[j].getName();
            // 获取属性的类型
            String type = field[j].getGenericType().toString();
            if (type.equals("class java.lang.String")) {
                Method g = null;//getter()
                Method s = null;//setter()
                // 调用getter方法获取属性值
                String value = null;
                try {
                    Strategy curStrategy=this.getStrategy(name);
                    if (curStrategy!=null){
                        //g = model.getClass().getMethod("get" + name.substring(0, 1).toUpperCase()+name.substring(1));
                        //判断解决方案集合中是否存在该字段对应的方案
                        value =  field[j].getName();
                        if (value!=null){
                            //设置值
                            field[j].setAccessible(true);
                            field[j].set(model,curStrategy.removeSensitive((String)field[j].get(model)));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

    public static void main(String[] args) {
        List<Parameter> parameters1=new ArrayList<>();
        Parameter p1=new Parameter();
        p1.setPatternEnum(PatternEnum.Generated_Randomly.toString());
        p1.setRH(5);
        p1.setTypeName("cust_name");
        parameters1.add(p1);
        Service<Customer> service=new Service<>(parameters1);

        //测试
        Customer customer=new Customer();
        customer.setCust_name("12qwqwwe");

        service.doSomething(customer);
        System.out.println(customer.getCust_name());
    }
}
