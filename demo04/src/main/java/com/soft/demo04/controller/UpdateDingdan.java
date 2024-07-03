package com.soft.demo04.controller;

import com.soft.demo04.beans.Dingdan;
import com.soft.demo04.beans.JsonResult;
import com.soft.demo04.beans.Kehu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//编辑订单详情
@RestController
@CrossOrigin
public class UpdateDingdan {
    @Autowired//框架帮你初始化
    private JdbcTemplate jdbc;
    @PostMapping("/updateDingdan")
    public JsonResult updateDingdan(@RequestBody Dingdan dd){
        //接收数据
        JsonResult res = new JsonResult();
        jdbc.update("update dingdan set dingdanid=? and kehuid=? and xiaoshouname=? and xiaoshouid=? and dingdanclass=? and quantity=? and money=? and dingdanstate=? and yonghuid=? and address=? and note=? where dingdanid=? and yonghuid in (select label.yonghuid from label)",
                dd.getDingdanid(),dd.getKehuid(),dd.getXiaoshouname(),dd.getXiaoshouid(),dd.getDingdanclass(),dd.getQuantity(),dd.getMoney(),dd.getDingdanstate(),dd.getDingdanshijian(),dd.getYonghuid(),dd.getKehuname(),dd.getAddress(),dd.getNote());
        Dingdan user = jdbc.queryForObject("select * from dingdan where dingdanid=? and yonghuid in (select label.yonghuid from label)", new BeanPropertyRowMapper<>
                (Dingdan.class),dd.getDingdanid());
        res.setCode(200);
        res.setResult(user);
        System.out.println(res);
        return res;
    }
}
