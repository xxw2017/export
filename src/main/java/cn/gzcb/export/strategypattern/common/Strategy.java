package cn.gzcb.export.strategypattern.common;

import cn.gzcb.export.strategypattern.exception.InputIllegalException;
import cn.gzcb.export.strategypattern.exception.ParametersIllegalException;

public interface Strategy {

    /**
     * 对输入字符串通过命令，进行脱敏操作，并返回脱敏后字符串
     * @param importation 输入字符串
     * @param command 脱敏指令
     * @return
     * @throws InputIllegalException 字符串输入不合法
     * @throws ParametersIllegalException 脱敏参数输入不合法
     */
    String removeSensitive(String importation, Command command)throws InputIllegalException,ParametersIllegalException;
}
