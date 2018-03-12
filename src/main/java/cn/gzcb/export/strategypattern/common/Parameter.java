package cn.gzcb.export.strategypattern.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Parameter {

    /*对应数据实体对象字段名*/
    private String TypeName;
    /*加密方式*/
    private String PatternEnum;
    /*隐藏长度*/
    private int CL;
    /*偏移量*/
    private int OT;
    /*随机长度*/
    private int RH;
    /*替换为*/
    private String RE;
    /*返回的字符串类型*/
    private String RT;

}
