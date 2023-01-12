package cn.singlestep.hotel.exception;

import lombok.Data;

/**
 * @author onestep
 * @description 全局自定义异常错误信息类
 * @date 2023/1/7 14:21
 */
@Data
public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    protected String errorCode; //错误码
    protected String errorMsg;//错误信息

    public GlobalException() {
        super();
    }

    public GlobalException(BaseError baseError) {
        super(baseError.resultCode());
        this.errorCode = baseError.resultCode();
        this.errorMsg = baseError.resultMsg();
    }

    public GlobalException(BaseError baseError, Throwable cause) {
        super(baseError.resultCode(), cause);
        this.errorCode = baseError.resultCode();
        this.errorMsg = baseError.resultMsg();
    }

    public GlobalException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public GlobalException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public GlobalException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
