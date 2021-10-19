package info.onedata.modular.exceptioninterceptor;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @Auther: huangwei
 * @Date: 2021/11/17 11:45
 * @Description: GlobalApiExceptionInteceptor
 * @Version 1.0.0
 */
@RestControllerAdvice(annotations = Api.class)
@Order(-1)
@Slf4j
public class GlobalApiExceptionInterceptor {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData<Void> permissionException(RuntimeException e) {
        log.error("权限许可异常：", e);
        ResponseData<Void> result = new ResponseData<>();
        result.setCode(500);
        result.setMessage("服务器错误");
        return result;
    }

    @Data
    public static class ResponseData<T> {
        private Integer code;
        private String message;
        private T data;
    }

}
