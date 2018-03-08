package cn.gzcb.export.strategypattern.common;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * 脱敏操作所需命令
 */
@Getter
@Setter
@ToString
public class Command {
    /*指令类型*/
    private PatternEnum patternEnum;
    /*具体指令命令*/
    private Map<String,String> map;
}
