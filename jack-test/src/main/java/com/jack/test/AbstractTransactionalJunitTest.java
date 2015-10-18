package com.jack.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
@ContextConfiguration({"classpath:spring-conf/spring-test.xml"})
public abstract class AbstractTransactionalJunitTest extends AbstractTransactionalJUnit4SpringContextTests{

}
