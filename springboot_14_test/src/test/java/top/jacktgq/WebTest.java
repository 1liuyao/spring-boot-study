package top.jacktgq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.HeaderResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

/**
 * @Author CandyWall
 * @Date 2022/1/23--23:19
 * @Description
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// 开启虚拟mvc调用
// 对controller层进行测试，需要模拟http请求
// 这样我们在开发完成后，整个项目通过maven package时一定会经过生命周期test
// 相当于所有层的测试用例都执行了一遍，更加安全可靠

// 开启虚拟mvc的调用
@AutoConfigureMockMvc
public class WebTest {
    @Test
    public void testRandomPort() {
    }

    @Test
    //创建一个虚拟的httpClient发送http请求controller接口
    public void testWeb(@Autowired MockMvc mvc) throws Exception {
        // 创建虚拟请求，当前访问 /books
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/5");
        mvc.perform(builder);
    }

    @Test
    // 断言验证码
    public void testStatus(@Autowired MockMvc mvc) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books1/6");
        ResultActions action = mvc.perform(builder);
        // 设定预期值，与真实值进行比较，成功测试通过，失败测试不通过
        // 定义本次调用的预期值
        // 相当于设置断言
        StatusResultMatchers srm = MockMvcResultMatchers.status();
        // 预计本次调用成功的状态码：200
        ResultMatcher ok = srm.isOk();
        // 添加预计值到本次调用过程中进行匹配
        action.andExpect(ok);
    }
    // 断言String类型响应体
    @Test
    public void testBody(@Autowired MockMvc mvc) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/6");
        ResultActions action = mvc.perform(builder);
        // 设定预期值，与真实值进行比较，成功测试通过，失败测试不通过
        // 定义本次调用的预期值
        ContentResultMatchers crm = MockMvcResultMatchers.content();
        // 预计本次调用成功的状态码：200
        ResultMatcher rm = crm.string("getById...");
        // 添加预计值到本次调用过程中进行匹配
        action.andExpect(rm);
    }

    @Test
    // 断言json类型响应体
    public void testJson(@Autowired MockMvc mvc) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/7");
        ResultActions action = mvc.perform(builder);
        // 设定预期值，与真实值进行比较，成功测试通过，失败测试不通过
        // 定义本次调用的预期值
        ContentResultMatchers jsonMatcher = MockMvcResultMatchers.content();
        ResultMatcher rm = jsonMatcher.json("{\"id\":5,\"name\":\"神秘岛\",\"type\":\"科幻\",\"description\":\"《神秘岛》是法国科幻小说家儒勒·凡尔纳创作的长篇小说，是他写的三部曲之一。1\"}");
        action.andExpect(rm);
    }

    @Test
    // 断言header
    public void testContentType(@Autowired MockMvc mvc) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/7");
        ResultActions action = mvc.perform(builder);
        // 设定预期值，与真实值进行比较，成功测试通过，失败测试不通过
        // 定义本次调用的预期值
        HeaderResultMatchers hrm = MockMvcResultMatchers.header();
        ResultMatcher rm = hrm.string("Content-Type", "application/json");
        action.andExpect(rm);
    }

    @Test
    // 完整测试
    public void testGetById(@Autowired MockMvc mvc) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/8");
        ResultActions action = mvc.perform(builder);

        // 1、比较状态码
        StatusResultMatchers statusResultMatchers = MockMvcResultMatchers.status();
        ResultMatcher statusResultMatcher = statusResultMatchers.isOk();
        action.andExpect(statusResultMatcher);

        // 2、比较响应header
        HeaderResultMatchers headerResultMatchers = MockMvcResultMatchers.header();
        ResultMatcher headerResultMatcher = headerResultMatchers.string("Content-Type", "application/json");
        action.andExpect(headerResultMatcher);

        /// 3、比较响应json返回值
        ContentResultMatchers contentResultMatchers = MockMvcResultMatchers.content();
        ResultMatcher jsonResultMatcher = contentResultMatchers.json("{\"id\":5,\"name\":\"神秘岛\",\"type\":\"科幻\",\"description\":\"《神秘岛》是法国科幻小说家儒勒·凡尔纳创作的长篇小说，是他写的三部曲之一。\"}");
        action.andExpect(jsonResultMatcher);
    }
}
