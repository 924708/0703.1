package com.soft.demo04.controller;

import com.soft.demo04.beans.JsonResult;
import com.soft.demo04.beans.Kehu;
import com.soft.demo04.beans.Label;
import com.soft.demo04.beans.Xiansuo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//添加客户
@RestController
@CrossOrigin
public class AddXiansuo {

    @Autowired//框架帮你初始化
    private JdbcTemplate jdbc;
    @PostMapping("/AddXiansuo")
    public JsonResult AddXiansuo(@RequestBody Xiansuo xiansuo){
        JsonResult res = new JsonResult();//示例化  分配内存
        System.out.println("接收到的数据:");
        System.out.println(xiansuo);
        try {
            Label lb = jdbc.queryForObject("select yonghuid from label",Label.class);
            xiansuo.setYonghuid(lb.getYonghuid());
            jdbc.update("INSERT INTO xiansuo(xiansuoid,xiansuostate,xiansuosource,kehuname,kehuttel,description,yonghuid;)VALUES(?,?,?,?,?,?,?)",xiansuo.getXiansuoid(),xiansuo.getXiansuostate(),xiansuo.getXiansuosource(),xiansuo.getKehuname(),xiansuo.getKehutel(),xiansuo.getDescription(),xiansuo.getYonghuid());
            res.setCode(200);
            res.setResult(xiansuo);
            System.out.println(res);
            return res;//result对象返回
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode(201);
            res.setResult("成功失败");
            return res;//result对象返回
        }
    }
}

