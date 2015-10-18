package com.jack.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
@ContextConfiguration({"classpath:spring-conf/spring-test.xml"})
public class AbstractJunitTest extends AbstractJUnit4SpringContextTests {

}
