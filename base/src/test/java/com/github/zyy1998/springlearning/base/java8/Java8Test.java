package com.github.zyy1998.springlearning.base.java8;

import com.github.zyy1998.springlearning.base.java8.entity.Model;
import com.github.zyy1998.springlearning.base.java8.entity.ModelVO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class Java8Test {

    /**
     * 从一个list复制数据到另一个list
     */
    @Test
    public void test1() {
        List<Model> list1 = new ArrayList<>();
        Model model1 = new Model(1L, "model1", "xxxx");
        Model model2 = new Model(2L, "model2", "yyyy");
        Model model3 = new Model(3L, "model3", "zzz");
        Model model4 = new Model(4L, "model4", "aaaa");
        list1.add(model1);
        list1.add(model2);
        list1.add(model3);
        list1.add(model4);

        List<ModelVO> list2 = list1.stream()
                .map(res -> new ModelVO(res.getId(), res.getName()))
                .collect(Collectors.toList());

        assertEquals(list1.size(), list2.size());
        assertEquals(list1.get(0).getName(), list2.get(0).getName());
        assertEquals(list1.get(3).getName(), list2.get(3).getName());
    }
}
