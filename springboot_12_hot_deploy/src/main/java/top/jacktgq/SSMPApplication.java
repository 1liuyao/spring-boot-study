package top.jacktgq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SSMPApplication {

    public static void main(String[] args) {
        // 这里设置关闭热部署，优先级比配置文件高，
        // 防止配置文件多的时候自己通过配置文件设置的热部署参数被其他配置文件覆盖
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SSMPApplication.class, args);
    }

}
