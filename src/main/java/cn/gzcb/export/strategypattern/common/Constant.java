package cn.gzcb.export.strategypattern.common;


/**
 * 常量配置，常量用于加密指令传参，作为所传递参数的KEY
*/
public class Constant {

//    可控制隐藏规则（脱敏前字段长度、脱敏后将会保存的字段长度、偏移量（从左到右）、需要替换字符成为）
//
//    Eg:
//    加密前，字符串为：123456789
//    传参：长度（length：9）、保存的字段长度（saveLength：9）、偏移量（offset：3）、替换（targ：9）9

    /*脱敏前字段长度*/
    public final static String BEGIN_LENGTH="BL";

    /*脱敏后将会保存的字段长度*/
    public final static String AFFTER_LENGTH="AL";

    /*偏移量（从左到右）*/
    public final static String OFFSET="OT";

    /*覆盖的子串长度*/
    public final static String COVER_LENGTH="CL";

    /*需要替换字符成为*/
    public final static String REPLACE="RE";


//    随机生成 (生成随机数据，以替代原数据)
//    Eg:
//    加密前:123456789
//    传参：生产随机数长度（length：6）
//    输出：

    /*生产随机数长度（length：6）*/
    public final static String RANDOM_LENGTH="RH";

    public final static String ENCRYPTION="EN";
}
