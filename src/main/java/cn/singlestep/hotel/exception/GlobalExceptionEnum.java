package cn.singlestep.hotel.exception;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/7 14:15
 */
public enum GlobalExceptionEnum implements BaseError{
    // 数据操作错误定义
    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400","请求的数据格式错误!"),
    REQUEST_METHOD_ERROR("4001","HHTP请求方式错误!"),
    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!"),

    //Hotel用户相关
    HAVE_USER("1000","存在此用户"),

    EMPTY_RESULT_DATA("1001","不存在此信息!"),
    ROOM_STATE_ERROR("1002","RoomState 值错误"),
    WORKER_STATE_ERROR("1003","WorkStateState 值错误"),
    ADMIN_NAME_ERROR("1004", "管理员用户名长度大于10"),
    NO_ADMIN_ERROR("1005", "无此管理员"),
    HAVE_ADMIN_ERROR("1006", "存在此管理员"),
    NO_USER_ERROR("1007", "无此用户信息"),
    PASSWORD_ERROR("1008", "密码错误"),
    LOGIN_OTHER_ERROR("1009", "登录未知错误"),
    PERSON_ID_LENGTH_ERROR("1010", "身份证长度错误"),
    NOT_SUPER_ERROR("1011", "无权限"),
    ADMIN_TYPE_ERROR("1012", "管理员权限值错误"),
    USER_NAME_ERROR("1013", "用户名长度错误"),
    HAVE_NO_ROOM("1014", "无此房间信息"),
    ROOM_USR_ERROR("1015", "此房间正在使用"),
    HAVE_NO_PERSON_ORDER("1016", "无此用户订单"),
    HAVE_NO_HISTORY_ORDER("1017", "无此历史订单"),
    HAVE_NO_ORDER_ORDER("1018", "无此订单信息"),
    ;



    private final String resultCode; //错误码
    private final String resultMsg; //错误描述
    GlobalExceptionEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String resultCode() {
        return resultCode;
    }

    @Override
    public String resultMsg() {
        return resultMsg;
    }
}
