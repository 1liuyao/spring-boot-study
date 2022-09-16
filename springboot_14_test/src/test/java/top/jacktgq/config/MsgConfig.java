package top.jacktgq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author CandyWall
 * @Date 2022/1/23--23:00
 * @Description
 */
@Configuration
// 仅在单元测试时使用这个bean，所以该测试类不应该定义在src下，应该定义在test下
public class MsgConfig {
    @Bean
    public String msg() {
        return "bean msg";
    }
}
