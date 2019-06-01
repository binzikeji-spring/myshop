package com.binzikeji.my.shop.web.admin.service.test;

import com.binzikeji.my.shop.admin.service.TbUserService;
import com.binzikeji.my.shop.domain.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author: Chundekepa
 * @create: 2019-03-03 13:26
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring-context.xml","classpath:/spring-context-druid.xml","classpath:/spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll(){
        List<TbUser> list = tbUserService.selectAll();
        for (TbUser tbUser : list){
            System.out.println(tbUser);
        }
    }

    @Test
    public void testInsert(){
        TbUser tbUser = new TbUser();
        tbUser.setUsername("binzi1");
        tbUser.setPassword("123456");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserService.insert(tbUser);
    }

    @Test
    public void testDelete(){
        tbUserService.delete(39L);
    }

    @Test
    public void testGetById(){
        TbUser tbUser = tbUserService.getById(37L);
        System.out.println(tbUser);
    }

    @Test
    public void testMd5(){
        System.out.println(DigestUtils.md5DigestAsHex("123".getBytes()));
    }
}
