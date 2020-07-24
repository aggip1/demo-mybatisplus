package com.ag.repository;

import com.ag.DemoMybatisplusApplication;
import com.ag.entity.Admin;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest(classes = DemoMybatisplusApplication.class)
@RunWith(SpringRunner.class)
public class AdminRepositoryTest {
    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void test5() throws SQLException {
        Admin admin = new Admin();
//        Integer insert = adminRepository.insert(admin);
//        System.out.println("insert = " + insert);
        admin.setId("393de0e9f32014fd8a73828ee257d8ec");
//        admin.setUsername("test1");
//        admin.setPassword("test1");
        admin.setOther("");
        Wrapper<Admin> ux = new UpdateWrapper<>(admin);
        int update = adminRepository.updateById(admin);
        System.out.println("update = " + update);
    }

    @Test
    public void test33() {
        Page<Object> objects = PageHelper.offsetPage(0, 1);
        List<Admin> admins = adminRepository.selectList(null);
        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        System.out.println("adminPageInfo = " + adminPageInfo);
        System.out.println("修改代码？？？？？");
    }
    @Test
    public void test48(){
        String s = "6";
        boolean notEmpty = StringUtil.isNotEmpty(s);
        System.out.println(notEmpty);

    }
}