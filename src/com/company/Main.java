package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet r = null;
        try {
            conn = com.util.JDBCUtil.getConn();
//            3创建Statement 跟数据库打交道一定需要这个对象
            st = conn.createStatement();
//            执行查询 executeQuery
//            得到结果
            r = st.executeQuery("select * from t_student");
            while (r.next()){
                int studentid = r.getInt("id");
                String s_name = r.getString("name");
                int s_age = r.getInt("age");
                System.out.println("id:"+ studentid + "....name：" + s_name + ".....age:" + s_age);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            com.util.JDBCUtil.release(r,st,conn);
        }
    }
}
