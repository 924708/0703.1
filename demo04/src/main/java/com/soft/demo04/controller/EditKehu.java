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
//编辑客户详情
@RestController
@CrossOrigin
public class EditKehu {

    @Autowired//框架帮你初始化
    private JdbcTemplate jdbc;
    @PostMapping("/EditKehu")
    public JsonResult EditKehu(@RequestBody Kehu client) {
        JsonResult res = new JsonResult();

        // 更新 kehu 表
        jdbc.update("UPDATE kehu SET kehuname = ?, kehusex = ?, kehutel = ? WHERE kehuid = ? AND yonghuid IN (SELECT yonghuid FROM label)",
                client.getKehuname(), client.getKehusex(), client.getKehutel(), client.getKehuid());

        jdbc.update("UPDATE dingdan SET dingdanid = ? WHERE kehuid = ?",
                client.getKehuid());

        // 查询更新后的 kehu 数据
        Kehu user = jdbc.queryForObject("SELECT * FROM kehu WHERE kehuid = ?", new BeanPropertyRowMapper<>(Kehu.class), client.getKehuid());

        res.setCode(200);
        res.setResult(user);
        System.out.println(res);
        return res;
    }


}
