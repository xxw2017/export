package cn.gzcb.export.strategypattern.implement;

import cn.gzcb.export.strategypattern.common.Command;
import cn.gzcb.export.strategypattern.common.Strategy;
import cn.gzcb.export.strategypattern.exception.InputIllegalException;
import cn.gzcb.export.strategypattern.exception.ParametersIllegalException;

public class HiddenSensitive implements Strategy {

    /**
     * 对输入字符串通过命令，进行脱敏操作，并返回脱敏后字符串
     * 此实现对主串指定位置、指定长度的子字符串的所有字符，替换为相应字符
     * @param importation 输入字符串
     * @param command 脱敏指令
     * @return
     * @throws InputIllegalException
     * @throws ParametersIllegalException
     */
    @Override
    public String removeSensitive(String importation, Command command) throws InputIllegalException,ParametersIllegalException{
        return null;
    }
}
