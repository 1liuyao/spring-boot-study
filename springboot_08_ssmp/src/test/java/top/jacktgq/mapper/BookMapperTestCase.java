package top.jacktgq.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.jacktgq.pojo.Book;

import java.util.function.Function;

/**
 * @Author CandyWall
 * @Date 2022/1/20--0:34
 * @Description
 */
@SpringBootTest
public class BookMapperTestCase {
    @Autowired
    private BookMapper bookMapper;

    @Test
    void testSelectById() {
        bookMapper.selectById(1);
    }

    @Test
    void testSave() {
        Book book = new Book();
        book.setName("神秘岛");
        book.setType("科幻");
        book.setDescription("《神秘岛》是法国科幻小说家儒勒·凡尔纳创作的长篇小说，是其三部曲的第三部，全书共3部62章。");

        bookMapper.insert(book);
    }

    @Test
    void testDelete() {
        bookMapper.deleteById(15);
    }

    @Test
    void testGetAll() {
        bookMapper.selectList(null);
    }

    @Test
    void testGetPage() {
        //分页select * from table limit m,n;
        //分页相当于在原有功能的基础上做增强，需要拦截器实现
        IPage<Book> page = new Page(2, 5);
        // Preparing: SELECT id,name,type,description FROM tbl_book LIMIT ?,?
        //==> Parameters: 5(Long), 5(Long)，第一个参数传的是第二页的起始记录索引值

        bookMapper.selectPage(page, null);
        System.out.println("currentPage:" + page.getCurrent());//currentPage:2 当前页
        System.out.println("pageSize:" + page.getSize());//pageSize:5 页容量
        System.out.println("total:" + page.getTotal());//total:14 表行数
        System.out.println("page:" + page.getPages());//page:3 一共几页
        System.out.println("record:" + page.getRecords());//将符合条件的记录封装成list
    }

    @Test
    void testGetByCondition() {
        //条件查询，先申明条件查询器
        //等价于select * from tbl_book where name likes "%spring%"
        QueryWrapper<Book> qw = new QueryWrapper();
        qw.like("name", "spring");
        bookMapper.selectList(qw);
    }

    @Test
    void testGetByCondition2() {
        //lamda条件查询器，解决手动输入列名写错的情况
        String name = null;
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        /*if (name != null) {
            lqw.like(Book::getName, "Spring");
        }*/
        // 等价写法
        lqw.like(name != null, Book::getName, name);
        bookMapper.selectList(lqw);
    }
}
