package com.jack.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class AbstractJunitTest extends AbstractJUnit4SpringContextTests{

}
