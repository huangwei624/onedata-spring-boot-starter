package info.onedata.modular.rocketmq.message;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.nio.charset.StandardCharsets;

/**
 * @Auther: huangwei
 * @Date: 2021/12/1 15:25
 * @Description: Body
 * @Version 1.0.0
 */
@Data
public class Body<T> {

    private Long orderId;

    private T data;

    public Body() {
    }

    public Body(T data) {
        this.data = data;
    }

    public Body(Long orderId, T data) {
        this.orderId = orderId;
        this.data = data;
    }

    public byte[] geneBytes() {
        return JSONObject.toJSONString(this).getBytes(StandardCharsets.UTF_8);
    }

}
