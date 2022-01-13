package info.onedata.dynamicroutecloudgateway;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DynamicRouteCloudGatewayApplicationTests {

    public static void main(String[] args) {
        Mono.just("hello world")
                .map(item -> item + ", append=？？")
                .subscribe(System.out::println);

        System.out.println("============");

        String simpleStr = "1234";

        Mono.just(simpleStr).flatMap(item -> Mono.just(simpleStr + "00")).map(item -> item + "11")
                .subscribe(System.out::println);

        System.out.println("================");

        Integer[] data = {1, 2, 3};

        Flux.just(data).subscribe(System.out::println);
        Mono.just(data).subscribe(System.out::println);
    }

    @Test
    public void t2() {
        Mono.just(1).filter(item -> item < 10).subscribe(System.out::println);

        ArrayList<String> list = new ArrayList<>();
        list.add("123");
        list.add("abc");
        Flux.fromIterable(list).subscribe(System.out::println);
    }


}
