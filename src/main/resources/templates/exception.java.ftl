package ${packageName};

/**
* <p>
* 全局统一业务异常
* </p>
*
* @author ${author}
* @since ${date}
*/
public class ${className} extends RuntimeException {
    public BusnessException() {
        super();
    }

    public BusnessException(String message) {
        super(message);
    }

    public BusnessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusnessException(Throwable cause) {
        super(cause);
    }
}