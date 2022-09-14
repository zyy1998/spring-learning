package com.github.zyy1998.springlearning.redis;

import com.github.zyy1998.springlearning.redis.entity.CartTypeEnum;
import com.github.zyy1998.springlearning.redis.entity.Goods;
import com.github.zyy1998.springlearning.redis.entity.TradeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    @Test
    public void testPutAndGetDTO() {
        TradeDTO tradeDTO = generateTradeDto();
        String key = "test_trade_dto_key";
        serializableRedisTemplate.opsForValue().set(key, tradeDTO, 1L, TimeUnit.MINUTES);
        TradeDTO result = (TradeDTO) serializableRedisTemplate.opsForValue().get(key);
        System.out.println(result);
    }

    private TradeDTO generateTradeDto() {
        TradeDTO tradeDTO = new TradeDTO(CartTypeEnum.BUY_NOW);
        tradeDTO.setMemberId("1563070324990455808");
        tradeDTO.setMemberName("test_001");
        tradeDTO.setSn("O202209141563070324990455808");

        Goods goods1 = new Goods();
        goods1.setName("1块钱100积分");
        goods1.setIntro("1块钱100积分");
        goods1.setPrice(1.0);
        tradeDTO.getGoodsList().add(goods1);
        return tradeDTO;
    }

}
