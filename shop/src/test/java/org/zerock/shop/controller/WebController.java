package org.zerock.shop.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.shop.test.mapper.TestMapper;


@MapperScan("org.zerock.shop.test.mapper")
@Controller
public class WebController {

    @Autowired
    TestMapper testMapper;

    @RequestMapping("/")
    public String startPoint() {
        System.out.println(" / 타는지 ");
        System.out.println(" DB에서 가지고온 현재시간 : " + testMapper.getCurrentTime() );
        return "main";
    }
}
