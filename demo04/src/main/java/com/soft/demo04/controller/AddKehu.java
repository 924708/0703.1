package com.soft.demo04.controller;

import com.soft.demo04.beans.JsonResult;
import com.soft.demo04.beans.Kehu;
import com.soft.demo04.beans.Label;
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
public class AddKehu {
    //接受json型数据，插入数据库
    // 接受kehuname，kehutel，age，kehusex，city，identity，把label.yonghuid填入kehu.yonghuid，插入数据库，自动生成kehuid，返回kehuid，kehuname，kehutel，age，kehusex，city，identity，yonghuid
    @Autowired//框架帮你初始化
    private JdbcTemplate jdbc;
    @PostMapping("/AddKehu")
    public JsonResult Addkehu(@RequestBody Kehu kehu){
        JsonResult res = new JsonResult();//示例化  分配内存
        System.out.println("接收到的数据:");
        System.out.println(kehu);
        try {
            Label lb = jdbc.queryForObject("select yonghuid from label",Label.class);
            kehu.setYonghuid(lb.getYonghuid());
            jdbc.update("INSERT INTO kehu(kehuname,kehutel,age,kehusex,city,identity,yonghuid)VALUES(?,?,?,?,?,?,?)",kehu.getKehuname(),kehu.getKehutel(),kehu.getAge(),kehu.getKehusex(),kehu.getCity(),kehu.getIdentity(),kehu.getYonghuid());
            res.setCode(200);
            res.setResult(kehu);
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

