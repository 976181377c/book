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

public class login extends HttpServlet {

    static String sql = null;
    static ResultSet ret = null;

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");

        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String type = req.getParameter("type");

        PrintWriter out = resp.getWriter();
        JSONObject json = new JSONObject();
        boolean bl = true;
        if (type == "")
            sql = "insert into user(id,password) values (\"" + id + "\",\"" + password + "\")";
        else
            sql = "SELECT * FROM `user` where id = " + id + "  AND password = " + password;
        try {
            Connection con = LinkDb.getConnection();
            Statement st = con.createStatement();
            if(type == "")
               st.execute(sql);
            else
            {
                 ret = st.executeQuery(sql);
                 ret.next();
                 ret.getString(1);                    
                 ret.close();
            }
               
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            bl = !bl;
        }
        json.put("bl",bl);
        out.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}