package com.javaweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class select extends HttpServlet {
    static String sql = null;
    static ResultSet ret = null;
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        String type = req.getParameter("type");
        String where = req.getParameter("where");

        PrintWriter out = resp.getWriter();
        JSONObject json = new JSONObject();

        try {
            Connection con = LinkDb.getConnection();
            Statement st = con.createStatement();

            sql = "select type from type";
            ret = st.executeQuery(sql);
            String str = new String();
            while (ret.next()) {
                str += "&" + ret.getString(1);
            }
            json.put("type", str);

            sql = "SELECT title,price,image,id FROM commodity ";
            if (type == "")
                sql = "SELECT title,price,image,id FROM commodity ";
            else
                sql = "SELECT title,price,image,id FROM commodity WHERE type = \"" + type + "\"";

            if (where != "")
                sql += " ORDER BY " + where + " DESC ";

            System.out.println(where);
            if (where.equals("number"))
                System.out.println("相同");

            else
                System.out.println("不相同");

            System.out.println(sql);
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
            }
            ret.close();
            con.close();// 关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}