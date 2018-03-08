package cn.gzcb.export.strategypattern.implement;

import cn.gzcb.export.strategypattern.common.Command;
import cn.gzcb.export.strategypattern.common.Constant;
import cn.gzcb.export.strategypattern.common.Strategy;
import cn.gzcb.export.strategypattern.exception.InputIllegalException;
import cn.gzcb.export.strategypattern.exception.ParametersIllegalException;

public class GeneratedSensitive implements Strategy {


    /**
     * 对输入字符串通过命令，进行脱敏操作，并返回脱敏后字符串
     * 通过输入长度，生成随机字符串
     * @param importation 输入字符串
     * @param command 脱敏指令
     *
     * @return
     * @throws InputIllegalException
     * @throws ParametersIllegalException
     */
    @Override
    public String removeSensitive(String importation, Command command) throws InputIllegalException, ParametersIllegalException {
        int length=Integer.parseInt(command.getMap().get(Constant.RANDOM_LENGTH));
        if (length<=0) {

        }
        return null;
    }
}
