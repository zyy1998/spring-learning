package com.github.zyy1998.springlearning.springbase.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CacheApplication.class)
public class CacheTest {
    @Autowired
    UserService userService;

    @Test
    public void test1() throws IOException {
        Long userId = 1L;
        String userName1 = "tom";

        // 生成一个名为tom的数据存到json文件中
        UserDTO dto = new UserDTO().setUserId(userId).setUserName(userName1);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(UserService.jsonFilePath), dto);

        // 从直接取出的文件一定还是tom
        UserDTO resultDto = userService.get(userId);
        assertEquals(userName1, resultDto.getUserName());

        // 把文件里的名字改成john
        String userName2 = "john";
        dto = new UserDTO().setUserId(userId).setUserName(userName2);
        objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(UserService.jsonFilePath), dto);

        // 从缓存中取出的名称应该没变
        resultDto = userService.get(userId);
        assertEquals(userName1, resultDto.getUserName());
    }
}
