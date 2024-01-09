package ${packageName}.global;

import ${packageName}.exception.BusnessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.Set;

import static ${packageName}.global.ResultEnum.*;

/**
 * 统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class ${className} {

    /**
     * 参数校验异常
     * MethodArgumentNotValidException 对应 {@code @RequestBody} 参数校验异常
     * ConstraintViolationException 对应 {@code @PathVariable} 和 {@code @RequestParam} 参数校验异常
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> argException(Exception e) {
        log.error("参数校验异常", e);

        String msg = null;
        // MethodArgumentNotValidException 异常错误信息提取
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e1 = ((MethodArgumentNotValidException) e);
            ObjectError objectError = e1.getBindingResult().getAllErrors().get(0);
            msg = objectError.getDefaultMessage();

        }
        // ConstraintViolationException 异常错误信息提取
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException e1 = ((ConstraintViolationException) e);
            Set<ConstraintViolation<?>> set = e1.getConstraintViolations();
            for (ConstraintViolation<?> cv : set) {
                msg = cv.getMessage();
                break;
            }
        }
        if (!StringUtils.hasLength(msg)) {
            msg = VALIDATE_FAILED.getMessage();
        }

        return Result.failed(VALIDATE_FAILED.getCode(), msg);
    }

    /**
     * 自定义业务异常
     */
    @ExceptionHandler(BusnessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> winningException(BusnessException e) {
        log.error("业务异常", e);
        return Result.failed(BUSNESS_ERROR.getCode(), e.getMessage());
    }

    /**
     * 其他异常统一处理，返回的数据屏蔽具体的错误堆栈信息
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> otherException(Exception e) {
        log.error("系统异常", e);
        return Result.failed(SERVER_ERROR);
    }

}