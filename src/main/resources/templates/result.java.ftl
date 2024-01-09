package ${packageName};

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* <p>
* 返回结果包装
* </p>
*
* @author ${author}
* @since ${date}
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ${className}<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> ${className}<T> success() {
        return new ${className}<>(${enumClassName}.SUCCESS.getCode(), ${enumClassName}.SUCCESS.getMessage(), null);
    }

    public static <T> ${className}<T> success(T data) {
        return new ${className}<>(${enumClassName}.SUCCESS.getCode(), ${enumClassName}.SUCCESS.getMessage(), data);
    }

    public static <T> ${className}<T> success(String message, T data) {
        return new ${className}<>(${enumClassName}.SUCCESS.getCode(), message, data);
    }

    public static <T> ${className}<T> failed() {
        return failed(${enumClassName}.SERVER_ERROR.getCode(), ${enumClassName}.SERVER_ERROR.getMessage(), null);
    }

    public static <T> ${className}<T> failed(String message) {
        return failed(${enumClassName}.SERVER_ERROR.getCode(), message, null);
    }

    public static <T> ${className}<T> failed(int code, String message) {
        return failed(code, message, null);
    }

    public static <T> ${className}<T> failed(${enumClassName} resultEnum) {
        return failed(resultEnum.getCode(), resultEnum.getMessage(), null);
    }

    public static <T> ${className}<T> failed(${enumClassName} resultEnum, T data) {
        return failed(resultEnum.getCode(), resultEnum.getMessage(), data);
    }

    public static <T> ${className}<T> failed(int code, String message, T data) {
        return new ${className}<>(code, message, data);
    }

}