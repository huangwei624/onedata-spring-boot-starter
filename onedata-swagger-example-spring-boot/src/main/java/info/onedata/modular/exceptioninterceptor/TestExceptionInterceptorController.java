package info.onedata.modular.exceptioninterceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: huangwei
 * @Date: 2021/11/17 11:49
 * @Description: TestExceptionInterceptorController
 * @Version 1.0.0
 */
@RestController
public class TestExceptionInterceptorController {

    @Api
    @GetMapping("/exception/test")
    public void test() {
        int i = 2 / 0;
    }

}
