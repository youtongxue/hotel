package cn.singlestep.hotel.exception;

/**
 * @author onestep
 * @description 定义全局 Exception 接口方法
 * @date 2023/1/7 13:59
 */
public interface BaseError {

    // 错误码
    String resultCode();

    // 错误描述
    String resultMsg();
}
