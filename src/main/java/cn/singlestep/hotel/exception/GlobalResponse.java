package cn.singlestep.hotel.exception;

import lombok.Data;

/**
 * @author onestep
 * @description 全局返回格式
 * @date 2023/1/7 14:27
 */
@Data
public class GlobalResponse {
    private String code; //响应代码
    private String message; //响应消息
    private Object result; //响应结果

    /**
     * 成功
     * @return 返回 Null
     */
    public static GlobalResponse success() {
        return success(null);
    }

    /**
     * 成功
     * @param data 处理成功后的信息 Object 对象
     * @return GlobalExceptionEnum 中的 SUCCESS
     * code -> 200
     * message -> 成功！
     * result -> data
     */
    public static GlobalResponse success(Object data) {
        GlobalResponse rb = new GlobalResponse();
        rb.setCode(GlobalExceptionEnum.SUCCESS.resultCode());
        rb.setMessage(GlobalExceptionEnum.SUCCESS.resultMsg());
        rb.setResult(data);
        return rb;
    }

    /**
     * 失败
     * @param baseError 错误信息
     * @return 错误的Code、Message、Result为Null
     */
    public static GlobalResponse error(BaseError baseError) {
        GlobalResponse rb = new GlobalResponse();
        rb.setCode(baseError.resultCode());
        rb.setMessage(baseError.resultMsg());
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     * @param code 错误码
     * @param message 错误信息
     * @return 返回错误Code、Message、Result为Null
     */
    public static GlobalResponse error(String code, String message) {
        GlobalResponse rb = new GlobalResponse();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败 -> 手动抛异常
     * @param message 错误信息
     * @return 返回错误Code -1、Message、Result为Null
     */
    public static GlobalResponse error(String message) {
        GlobalResponse rb = new GlobalResponse();
        rb.setCode("-1");
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

}
