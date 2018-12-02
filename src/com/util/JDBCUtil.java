package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    static String url = null;
    static String user = null;
    static String password = null;
    static String Drivername = null;
    static {
        try {
//        创建一个属性配置对象
            Properties properties = new Properties();
//        关联
//            用类的加载器，在加载字节码的时候顺便将一个文件资源变成流。注意此方法仅对bin下的文件有效
            InputStream is =JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
//             放在根目录下加载成流
//            InputStream is = new FileInputStream("jdbc.properties");
            properties.load(is);
//        读
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            Drivername = properties.getProperty("Drivername");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     * @return
     */
    public static Connection getConn(){
        Connection conn = null;
        try {
            conn = null;
//        改动，因为类中有一段静态代码块，所以在第一次加载时候就已经注册过了，不用重复注册
//            所以用Class.forName方法找到该类，.newInstance生成对应类的对象.
            Class.forName(Drivername).newInstance();
//
            conn = DriverManager.getConnection(url, user, password);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * 装模作样的写个注释
     * 释放资源
     * @param r
     * @param st
     * @param conn
     */
    public static void release(ResultSet r,Statement st,Connection conn){
        closeRs(r);
        closeST(st);
        closeCON(conn);
    }
    private static void closeRs(ResultSet r){
        try{
            if (r!=null){
                r.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            r=null;
        }
    }

    private static void closeST(Statement st){
        try{
            if (st!=null){
                st.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            st=null;
        }
    }

    private static void closeCON(Connection coon){
        try{
            if (coon!=null){
                coon.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            coon=null;
        }
    }

}
