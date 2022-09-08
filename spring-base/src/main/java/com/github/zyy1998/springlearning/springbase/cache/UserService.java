package com.github.zyy1998.springlearning.springbase.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * CacheConfig一定要加
 */
@Service
@CacheConfig(cacheNames = "just-a-folder-name")
public class UserService {
    @Autowired
    CacheManager cacheManager;
    public static final String jsonFilePath = "./src/main/resources/userDTO1.json";

    /**
     * 除了Cacheable，还有 CachePut CacheEvict
     * 具体见 https://docs.spring.io/spring-framework/docs/5.3.20/reference/html/integration.html#cache-annotations
     * https://docs.spring.io/spring-boot/docs/2.7.3/reference/html/io.html#io.caching.provider.caffeine
     *
     * @param userId
     * @return
     * @throws IOException
     */
    @Cacheable(key = "#userId")
    public UserDTO get(long userId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO dto = mapper.readValue(new File(jsonFilePath), UserDTO.class);
        return dto;
    }
}
