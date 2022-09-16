package top.jacktgq.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import top.jacktgq.pojo.Book;

/**
 * @Author CandyWall
 * @Date 2022/1/24--1:11
 * @Description
 */
@SpringBootTest
// 添加该注解表示单元测试对数据库进行的操作并不进行事务的提交，
// 因此可以避免package时，执行test，导致数据库插入脏数据
@Transactional
//如果将@Rollback设置为false则test对数据库操作仍会生效，即使添加了@Transactional
//@Rollback(value = false)
public class BookServiceTest {
    @Autowired
    private IBookService bookService;
    @Test
    public void testSave() {
        Book book = new Book();
        book.setName("springboot");
        book.setType("springboot");
        book.setDescription("springboot");
        bookService.save(book);
    }
}
