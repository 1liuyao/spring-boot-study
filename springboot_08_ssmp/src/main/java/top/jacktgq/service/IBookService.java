package top.jacktgq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jacktgq.pojo.Book;

/**
 * @Author CandyWall
 * @Date 2022/1/20--12:49
 * @Description
 */
public interface IBookService extends IService<Book> {
    // 利用MP代码生成器自动生成业务层代码，直接继承IService，具备基本增删改查功能

    // 当MP提供的通用方法不满足实际需求时，则可以按照以往自定的方式制定函数；或者使用重载形式
    IPage<Book> getPage(int currentPage, int pageSize);
    IPage<Book> getPage(int currentPage, int pageSize, Book book);
}
