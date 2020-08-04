package com.javaweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Buy extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static String sql = null;
    static ResultSet ret = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        String id = req.getParameter("id");

        int num = Integer.parseInt(req.getParameter("num"));
        sql = "select number from commodity where id =" + id;
        try {
            Connection con = LinkDb.getConnection();
            Statement st = con.createStatement();
            ret = st.executeQuery(sql);
            ret.next();
            int allNum = ret.getInt(1);
            allNum += num;
            sql = "update commodity set number = " + allNum + " where id = " + id;
            st.executeUpdate(sql);
            ret.close();
            con.close();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}