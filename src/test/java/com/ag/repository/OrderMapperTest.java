package com.ag.repository;

import com.ag.DemoMybatisplusApplication;
import com.ag.entity.Admin;
import com.ag.entity.Order;
import com.ag.eumn.AdminEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(classes = DemoMybatisplusApplication.class)
@RunWith(SpringRunner.class)
public class OrderMapperTest {
    @Autowired
    private OrderRepository orderMapper;

    @Test
    public void findAll() {
        List<Order> all = orderMapper.findAll();
        all.forEach(e -> System.out.println(e));
    }

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void test26() {
        Optional<Admin> test = adminRepository.findOne("test", null);
        System.out.println(test.get());

    }

    @Test
    public void test34() {

        List<Admin> map = adminRepository.testResultMap("1");
        map.stream().forEach(e -> System.out.println(e));
    }

    @Test
    public void test44() {
        System.out.println(AdminEnum.PASSWORD.getCode().equals("1BBB"));
        System.out.println(AdminEnum.PASSWORD.getIndex());
        Admin admin = new Admin();
        admin.setUsername(AdminEnum.PASSWORD.getCode());
        admin.setPassword(AdminEnum.PASSWORD.getIndex());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = sdf.format(new Date());
        admin.setOther(format);
        int insert = adminRepository.insert(admin);
        System.out.println("insert = " + insert);
    }

    @Test
    public void test60() {
        HashMap<String, Object> map = new HashMap<>();
//        Admin admin = new Admin();
//        admin.setId("d42c1b5b6c613ecba661297fff33eefc");
        map.put("id", "d42c1b5b6c613ecba661297fff33eefc");
        map.put("id", "8bda12385e22cac2da02b00ec967d6bd");
        adminRepository.deleteByMap(map);
    }

    @Test
    public void test72() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "一");
        hashMap.put("2", "二");
        String map = Joiner.on("  ").withKeyValueSeparator("=").join(hashMap);
        System.out.println("map = " + map);//map = 1=一  2=二
    }

    @Test
    public void test82() {
        String str = "1,2,3,4";
        List<String> list = Splitter.on(",").splitToList(str);
        /***
         * e = 1
         * e = 2
         * e = 3
         * e = 4
         */
        list.stream().forEach(e -> System.out.println("e = " + e));
        Optional<String> first = list.stream().findFirst();
        //first = 1
        System.out.println("first = " + first.get());
        long count = list.stream().count();
        //count = 4
        System.out.println("count = " + count);
    }

    @Test
    public void test102() {
        /***
         *  PageHelper.startPage(0,2 );
         * adminPageInfo = PageInfo{pageNum=0, pageSize=2, size=2, startRow=1, endRow=2, total=8, pages=4, list=Page{count=true, pageNum=0, pageSize=2, startRow=0, endRow=0, total=8, pages=4, reasonable=false, pageSizeZero=false}[Admin(id=1, username=, password=111111, other=, orders=null, isLast=null), Admin(id=2, username=aaa, password=222222, other=null, orders=null, isLast=null)], prePage=0, nextPage=1, isFirstPage=false, isLastPage=false, hasPreviousPage=false, hasNextPage=true, navigatePages=8, navigateFirstPage=1, navigateLastPage=4, navigatepageNums=[1, 2, 3, 4]}
         * e = Admin(id=1, username=, password=111111, other=, orders=null, isLast=null)
         * e = Admin(id=2, username=aaa, password=222222, other=null, orders=null, isLast=null)
         */
        /***
         * PageHelper.startPage(1,2 );
         * adminPageInfo = PageInfo{pageNum=1, pageSize=2, size=2, startRow=1, endRow=2, total=8, pages=4, list=Page{count=true, pageNum=1, pageSize=2, startRow=0, endRow=2, total=8, pages=4, reasonable=false, pageSizeZero=false}[Admin(id=1, username=, password=111111, other=, orders=null, isLast=null), Admin(id=2, username=aaa, password=222222, other=null, orders=null, isLast=null)], prePage=0, nextPage=2, isFirstPage=true, isLastPage=false, hasPreviousPage=false, hasNextPage=true, navigatePages=8, navigateFirstPage=1, navigateLastPage=4, navigatepageNums=[1, 2, 3, 4]}
         * e = Admin(id=1, username=, password=111111, other=, orders=null, isLast=null)
         * e = Admin(id=2, username=aaa, password=222222, other=null, orders=null, isLast=null)
         */
        /***
         * PageHelper.startPage(2,2 );
         * adminPageInfo = PageInfo{pageNum=2, pageSize=2, size=2, startRow=3, endRow=4, total=8, pages=4, list=Page{count=true, pageNum=2, pageSize=2, startRow=2, endRow=4, total=8, pages=4, reasonable=false, pageSizeZero=false}[Admin(id=3, username=bbb, password=bbbbbb, other=null, orders=null, isLast=null), Admin(id=4, username=test1, password=test1, other=, orders=null, isLast=null)], prePage=1, nextPage=3, isFirstPage=false, isLastPage=false, hasPreviousPage=true, hasNextPage=true, navigatePages=8, navigateFirstPage=1, navigateLastPage=4, navigatepageNums=[1, 2, 3, 4]}
         * e = Admin(id=3, username=bbb, password=bbbbbb, other=null, orders=null, isLast=null)
         * e = Admin(id=4, username=test1, password=test1, other=, orders=null, isLast=null)
         */
        PageHelper.startPage(2, 2);
        List<Admin> admins = adminRepository.selectList(null);
        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        System.out.println("adminPageInfo = " + adminPageInfo);
        adminPageInfo.getList().stream().forEach(e -> System.out.println("e = " + e));
    }

    @Test
    public void test131() {
        String[] s = {"1", "2", "3"};
        List<String> list = Arrays.asList(s);
//        System.out.println("list = " + list);
        Stream<String> limit = list.stream().limit(1);
        List<String> collect = limit.collect(Collectors.toList());
        for (String s1 : collect) {
            System.out.println("s1 = " + s1);
        }
        Optional<String> first = list.stream().findFirst();
        System.out.println("first.get() = " + first.get());
    }

    @Test
    public void test144() {
        //英文
        String yw = "abcd";
        //数字
        String sz = "9876";
//        Character
        char[] yws = yw.toCharArray();
        boolean letter = Character.isLetter(yws[yws.length - 1]);
        System.out.println("letter = 英文" + letter + "----" + yws[yws.length - 1]);

        //如果是英文
        if (letter) {
            //创建新字符
            StringBuilder builder = new StringBuilder(yw);

            //删除最后一个字符
            builder.deleteCharAt(builder.length() - 1);
            System.out.println("builder = " + builder.toString());
        }
        char[] chars = sz.toCharArray();
        boolean letter1 = Character.isLetter(chars[chars.length - 1]);
        System.out.println("letter1 = 数字" + letter1 + "-------" + chars[chars.length - 1]);
    }

    @Test
    public void test168() {
        String str = "abcdefg";
        String after = str.substring(6); //substring(1),获取索引位置1后面所有剩余的字符串
        System.out.println("after = " + after.toLowerCase());
        System.out.println("after = " + after.toUpperCase());
        String substring = str.substring(0, 6);
        System.out.println("substring = " + substring);
        System.out.println("substring = " + substring + after.toUpperCase());
    }

    @Test
    public void test180() {
        IPage<Order> orderIPage = orderMapper.selectPage((IPage<Order>) RowBounds.DEFAULT, null);
        List<Order> records = orderIPage.getRecords();
        records.forEach(e -> {
            System.out.println("e = " + e);
        });

    }

    @Test
    public void test192() {
        ArrayList<String> list = new ArrayList<>();
        System.out.println(CollectionUtils.isEmpty(list));
        list.add("1");
        System.out.println(CollectionUtils.isEmpty(list));
    }

    @Test
    public void test201() {
        LocalDateTime now = LocalDateTime.now().minusHours(20);
        System.out.println(now);

    }

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void test209() {

        String s = "12312312A";
        System.out.println("s.toUpperCase() = " + s.toUpperCase());
    }

    @Test
    public void test217() {
        List<Order> all = orderRepository.findAll();
        all.forEach(e-> System.out.println("e = " + e));

    }

}