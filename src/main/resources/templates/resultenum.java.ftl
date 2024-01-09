package ${packageName};

public enum ${className} {
    SUCCESS(200, "成功"),
    VALIDATE_FAILED(401, "参数校验失败"),
    FORBIDDEN(403, "没有权限访问"),
    SERVER_ERROR(500, "服务器错误"),
    BUSNESS_ERROR(501, "业务错误")
    ;

    private final Integer code;
    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}