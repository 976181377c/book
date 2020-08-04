package com.javaweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import com.javaweb.LinkDb;

public class index extends HttpServlet {

    static String sql = null;
    static ResultSet ret = null;

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");

        PrintWriter out = resp.getWriter();
        JSONObject json = new JSONObject();
        sql = "select title,price,image,id from commodity LIMIT 8";
        try {
            Connection con = LinkDb.getConnection();
            Statement st = con.createStatement();

            ret = st.executeQuery(sql);
            int i = 1;
            while (ret.next()) {
                Commodity commodity = new Commodity();
                commodity.setTitle(ret.getString(1));
                commodity.setPrice(ret.getString(2));
                commodity.setImage(ret.getString(3));
                commodity.setId(ret.getString(4));
                String key = "commodity" + i;
                i++;
                json.put(key, commodity);
                
            } // 显示数据

            

            sql = "select id,image,number from commodity ORDER BY number DESC LIMIT 4";
            ret = st.executeQuery(sql);
            i = 1;
            while (ret.next()) {
                Commodity commodity = new Commodity();
                commodity.setId(ret.getString(1));
                commodity.setImage(ret.getString(2));
                commodity.setNumber(ret.getString(3));
                String Top = "Top" + i;
                i++;
                json.put(Top, commodity);
            }

            sql = "select type from type";
            ret = st.executeQuery(sql);
            String str = new String();
            while (ret.next()) {
                str += "&" + ret.getString(1);
            }
            json.put("type", str);

            ret.close();
            con.close();// 关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

}