package com.only.huahua.model;

import com.only.huahua.service.PersonService;

/**
 * @description: PersonServiceBean
 * @author: leiming5
 * @date: 2022/2/22 20:28
 */
public class PersonServiceBean implements PersonService {

    @Override
    public void save() {
        System.out.println("我是save()方法");
    }
}
