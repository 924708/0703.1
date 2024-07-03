package com.soft.demo04.controller;

import com.soft.demo04.beans.JsonResult;
import com.soft.demo04.beans.Kehu;
import com.soft.demo04.beans.Xiansuo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//线索详情
@RestController
@CrossOrigin
public class XiansuoDetails {

    @Autowired
    private JdbcTemplate jdbc;
    @PostMapping("/XiansuoDetails")
    public JsonResult XiansuoDetails(@RequestBody Xiansuo xs)
    {
        JsonResult res = new JsonResult();//多
        System.out.println(xs); //打印收到数据
        System.out.println("接收到的数据:");
        System.out.println(xs);
        try {
            Xiansuo xiansuo = jdbc.queryForObject("select * from xiansuo where xiansuoid = ? and yonghuid in (select label.yonghuid from label)",new BeanPropertyRowMapper<>(Xiansuo.class),
                    xs.getXiansuoid());
            res.setCode(200);
            res.setResult(xiansuo);//数据放入结果中
            System.out.println(res);
            return res;

        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode(201);
            res.setResult("出现异常"+e.getMessage());//message 异常的信息
            return res;

        }
    }

}

