package cn.gzcb.export.strategypattern.exception;

/**
 * 参数不合法
 */
public class ParametersIllegalException extends Exception {
    public ParametersIllegalException() {
        super();
    }
    public ParametersIllegalException(String message) {
        super(message);
    }
}
