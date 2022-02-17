package com.topic3;


import java.sql.*;

public class JdbcTest {
    public static void main(String[] args) throws SQLException {
        Student stu = new Student();
        stu.setName("zhangSan");
        stu.setSex("man");
        insert(stu);
        stu.setName("lisi");
        update(stu);
        delete("lisi");

    }

    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/demo";
        String username = "root";
        String password = "";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static int insert(Student student) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into students (Name,Sex) values(?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getSex());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    private static int update(Student student) {
        Connection conn = getConn();
        int i = 0;
        String sql = "update students set Sex='" + student.getSex() + "' where Name='" + student.getName() + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    private static int delete(String name) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from students where Name='" + name + "'";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
