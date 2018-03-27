package com.konantech.spring;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonTests  {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void CommonTests() throws Exception {

    }
    public static void printObj(Object obj) {

        if (obj instanceof Map) {
            System.out.println(obj);
        } else {
            System.out.println(ReflectionToStringBuilder.toString(obj, ToStringStyle.MULTI_LINE_STYLE));
        }

    }
    public static void printList(List<?> list) {
        if(list != null) {
            for (Object obj : list) {
                printObj(obj);
            }
        }
    }

}

