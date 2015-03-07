package com.yaps.petstore.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:/applicationContext-dao-test.xml" })
@Transactional  
@RunWith(SpringJUnit4ClassRunner.class)
public class AbstractBaseDaoTestCase {

}
