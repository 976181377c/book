package com.javaweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class shop extends HttpServlet {
    static String sql = null;
    static ResultSet ret = null;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        String id = req.getParameter("id");

        PrintWriter out = resp.getWriter();
        JSONObject json = new JSONObject();
        sql = "select title,price,image,introduce from commodity where id =" + id;
        try {

            Connection con = LinkDb.getConnection();
            Statement st = con.createStatement();
            ret = st.executeQuery(sql);
            ret.next();
            Commodity commodity = new Commodity();
            commodity.setTitle(ret.getString(1));
            commodity.setPrice(ret.getString(2));
            commodity.setImage(ret.getString(3));
            commodity.setIntroduce(ret.getString(4));
            json.put("commodity", commodity);
            System.out.println(ret.getString(4));
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
        super.doPost(req, resp);
    }
}
