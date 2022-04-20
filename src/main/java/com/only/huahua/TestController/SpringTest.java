package com.only.huahua.TestController;

import com.only.huahua.model.tgbClassPathXMLApplicationContext;
import com.only.huahua.service.PersonService;
import org.junit.Test;

/**
 * @description: SpringTest
 * @author: leiming5
 * @date: 2022/2/22 20:36
 */
public class SpringTest {

    @Test
    public void instanceSpring(){
        tgbClassPathXMLApplicationContext ctx =new tgbClassPathXMLApplicationContext("beans.xml");
        PersonService personService =(PersonService) ctx.getBean("personService");
        personService.save();
    }

}
