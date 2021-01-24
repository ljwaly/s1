package com.ljw.spring.source.s1.jdbc;

import java.sql.*;

public class JDBCTransationTest {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
            //开启事务
            /*
            *
            * */
            connection.setAutoCommit(false);
            insertTest(connection);
            insertTest1(connection);


            connection.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                System.out.println("JDBC Transaction rolled back successfully");
            } catch (SQLException e1) {
                System.out.println("JDBC Transaction rolled back fail" + e1.getMessage());
            }
        } finally {
            if (connection != null) {
                try {
                    selectAll(connection);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void insertTest(Connection con) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("insert into user(username) values (?)");
        stmt.setString(1, "撒加");
        stmt.executeUpdate();
        System.out.println("Data inserted successfully");
        stmt.close();
    }

    public static void insertTest1(Connection con) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("insert into user1(username) values (?)");
        stmt.setString(1, "阿布罗狄");
        stmt.executeUpdate();
        System.out.println("Data inserted successfully");
        stmt.close();
    }

    public static void selectAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from user");
        System.out.println("============test============");
        while (rs.next()) {
            System.out.println(rs.getString("username"));
        }
        ResultSet rs1 = st.executeQuery("select * from user1");
        System.out.println("============test1============");
        while (rs1.next()) {
            System.out.println(rs1.getString("username"));
        }
        st.close();
    }
}
