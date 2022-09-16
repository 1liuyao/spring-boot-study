package top.jacktgq.controller.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author CandyWall
 * @Date 2022/1/21--17:33
 * @Description
 */
public class LogUtils {
    // 当每个类中想要使用log记录日志时，都需要在该类内部创建logger对象
    // 因此将这种获取logger对象的操作整合成日志工具类LogUtils
    // 后续想要使用日志功能的类继承这个工具类就可以了
    protected static Logger log;

    public LogUtils() {
        log = LoggerFactory.getLogger(this.getClass());
    }
}
