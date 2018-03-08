package cn.gzcb.export.strategypattern.exception;

/**
 * 输入不合法
 */
public class InputIllegalException extends Exception {
    public InputIllegalException() {
        super();
    }
    public InputIllegalException(String message) {
        super(message);
    }
}
