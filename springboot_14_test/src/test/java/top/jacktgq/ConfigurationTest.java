package top.jacktgq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import top.jacktgq.config.MsgConfig;

/**
 * @Author CandyWall
 * @Date 2022/1/23--23:01
 * @Description
 */
@SpringBootTest
//导入配置，现在yml和test环境的配置均生效
@Import(MsgConfig.class)
public class ConfigurationTest {
    @Autowired
    private String msg;

    @Test
    public void testMsgConfig() {
        System.out.println("msg = " + msg);
    }
}
