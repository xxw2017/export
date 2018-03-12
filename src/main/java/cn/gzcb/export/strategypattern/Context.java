package cn.gzcb.export.strategypattern;

import cn.gzcb.export.strategypattern.common.Command;
import cn.gzcb.export.strategypattern.common.Strategy;
import cn.gzcb.export.strategypattern.exception.InputIllegalException;
import cn.gzcb.export.strategypattern.exception.ParametersIllegalException;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public String executeStrategy(String importation, Command command) throws ParametersIllegalException, InputIllegalException {
        return strategy.removeSensitive(importation);
    }
}
