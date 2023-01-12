package cn.singlestep.hotel.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author onestep
 * @description 使用 @ControllerAdvice 开启全局异常处理
 * 需在自定义方法使用 @ExceptionHandler 注解
 * 然后定义捕获异常的类型即可对这些捕获的异常进行统一的处理
 * @date 2023/1/7 14:41
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    // 控制台调试日志
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 自定义的异常处理
     * @param httpServletRequest 请求客户端
     * @param e 全局自定义异常
     * @return 返回Code、Msg、Result -> Null
     */
    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public GlobalResponse globalExceptionHandler(HttpServletRequest httpServletRequest, GlobalException e){
        logger.error("全局异常拦截：{}", e.getErrorMsg());
        return GlobalResponse.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 空指针异常处理
     * @param httpServletRequest 请求客户端
     * @param e 空指针异常
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public GlobalResponse exceptionHandler(HttpServletRequest httpServletRequest, NullPointerException e){
        logger.error("空指针异常:", e);
        return GlobalResponse.error(GlobalExceptionEnum.BODY_NOT_MATCH);
    }

    /**
     * 客户端请求方式错误异常
     * */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public GlobalResponse requestMethodExceptionHandler(HttpServletRequest httpServletRequest, NullPointerException e){
        logger.error("客户端请求方式错误异常:", e);
        return GlobalResponse.error(GlobalExceptionEnum.REQUEST_METHOD_ERROR);
    }

    /**
     * 数据库信息为空错误异常
     * */
    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    @ResponseBody
    public GlobalResponse emptyExceptionHandler(HttpServletRequest httpServletRequest, EmptyResultDataAccessException e){
        logger.error("数据库信息为空错误异常:", e);
        return GlobalResponse.error(GlobalExceptionEnum.EMPTY_RESULT_DATA);
    }

    /**
     * 其他异常处理
     * @param httpServletRequest 请求客户端
     * @param e Exception
     * @return 服务器内部错误!(未知异常)
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public GlobalResponse exceptionHandler(HttpServletRequest httpServletRequest, Exception e){
        logger.error("未知异常:",e);
        return GlobalResponse.error(GlobalExceptionEnum.INTERNAL_SERVER_ERROR);
    }
}
