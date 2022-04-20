package com.only.huahua.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: tgbClassPathXMLApplicationContext
 * @author: leiming5
 * @date: 2022/2/22 20:30
 */
public class tgbClassPathXMLApplicationContext {

    private List<BeanDefinition> beanDefines = new ArrayList<BeanDefinition>();
    private Map<String, Object> sigletons = new HashMap<String, Object>();


    public tgbClassPathXMLApplicationContext(String filename) {
//        this.readXML(filename);
        this.instanceBeans();
    }

    /**
     * 完成bean的实例化
     */
    private void instanceBeans() {
        for (BeanDefinition beanDefinition : beanDefines) {
            try {
                if (beanDefinition.getClassName() != null && !"".equals(beanDefinition.getClassName().trim())) {
                    sigletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
    /**
     * 读取xml配置文件
     * @param filename
     */
//    private void readXML(String filename) {
//        SAXReader saxReader = new SAXReader(); //创建读取器
//        Document document =null;
//        try{
//            URL xmlpath = this.getClass().getClassLoader().getResource(filename);
//            document =saxReader(xmlpath);
//            Map<String,String> nsMap =new HashMap<String,String>();
//            nsMap.put("ns","http://www.springframework.org/schema/beans"); //加入命名空间
//            XPath xsub = document.createXPath("//ns:beans/ns:bean"); //创建beans/bean查询路径
//            xsub.setNamespaceURIs(nsMap); // 设置命名空间
//            List<Element> beans = xsub.selectNodes(document); // 获取文档下所有的bean节点
//            for(Element element:beans){
//                String id =element.attributeValue("id"); // 获取id属性值
//                String clazz =element.attributeValue("class"); // 获取class属性值
//                BeanDefinition beanDefine =new BeanDefinition(id,clazz);
//                beanDefines.add(beanDefine);
//            }
//
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//
//    }

    /**
     * 获取bean实例
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        return this.sigletons.get(beanName);
    }
}
