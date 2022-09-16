package top.jacktgq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.jacktgq.pojo.Book;

/**
 * @Author CandyWall
 * @Date 2022/1/19--23:00
 * @Description
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
    //使用MP封装好的数据库操作，直接继承BaseMapper

}
