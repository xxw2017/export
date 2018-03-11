package cn.gzcb.export.strategypattern.implement;

import cn.gzcb.export.strategypattern.Enum.StringTypeEnum;
import cn.gzcb.export.strategypattern.common.Command;
import cn.gzcb.export.strategypattern.common.Constant;
import cn.gzcb.export.strategypattern.common.Strategy;
import cn.gzcb.export.strategypattern.common.Util;
import cn.gzcb.export.strategypattern.exception.InputIllegalException;
import cn.gzcb.export.strategypattern.exception.ParametersIllegalException;

import java.util.HashMap;
import java.util.Map;

public class GeneratedSensitive implements Strategy {

    /*脱敏指令*/
    private Command command;

    public GeneratedSensitive(Command command){
        this.command=command;
    }
    /**
     * 对输入字符串通过命令，进行脱敏操作，并返回脱敏后字符串
     * 通过输入长度，生成随机字符串
     * @param importation 输入字符串
     *
     * @return
     * @throws InputIllegalException
     * @throws ParametersIllegalException
     */
    @Override
    public String removeSensitive(String importation) throws InputIllegalException, ParametersIllegalException {
        //获得随机字符串类型 （IDNO,手机号，纯数字等）
        StringTypeEnum outStringType=(StringTypeEnum)command.getMap().get(Constant.STRINGTYPE);
        //获取输出字符串长度指令
        int stringLength=command.getMap().get(Constant.RANDOM_LENGTH)==null?0:(int)command.getMap().get(Constant.RANDOM_LENGTH);
        switch (outStringType){
            /*身份证号*/
            case IDNUMBER:return Util.generateIDNumber();
            /*移动电话号码*/
            case PHONENUMBER:return Util.getTel();
            /*纯数字*/
            case PURE_NUMBERS:{
                if(stringLength==0){
                    throw new InputIllegalException("参数不合法");
                }
                return Util.getNumberStringByLength(stringLength);
            }
            /*纯字母*/
            case PURE_ALPHABET:{
                if(stringLength==0){
                    throw new InputIllegalException("参数不合法");
                }
                return Util.getAStringByLength(stringLength);
            }
            default:
                throw new InputIllegalException("参数不合法");
        }
    }

    public static void main(String[] args) {
////        GeneratedSensitive generatedSensitive =new GeneratedSensitive();
//        String sss="1234567890";
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put(Constant.RANDOM_LENGTH,15);
//        map.put(Constant.STRINGTYPE,StringTypeEnum.PURE_ALPHABET);
//        Command command =new Command();
//        command.setMap(map);
//        try {
////            System.out.println(generatedSensitive.removeSensitive(sss,command));command
//        } catch (InputIllegalException e) {
//            e.printStackTrace();
//        } catch (ParametersIllegalException e) {
//            e.printStackTrace();
//        }
    }
}
