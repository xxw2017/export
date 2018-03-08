package cn.gzcb.export.strategypattern.Enum;


/**
 * 加密方式枚举
 */
public enum PatternEnum {
    /*通过特殊字符隐藏一定连续长度的字符的方式*/
    Hidden_Characters,
    /*生成随机长度的随机字符串，替代原串的方式*/
    Generated_Randomly,
    /*通过加密算法加密字符串，例如MD5,返回机密后的字符串*/
    Encryption
}
