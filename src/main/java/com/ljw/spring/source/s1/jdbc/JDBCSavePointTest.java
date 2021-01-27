package com.ljw.spring.source.s1.jdbc;

import java.sql.*;


/**
 * SavePoint回滚点
 */
public class JDBCSavePointTest {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            insertTest(connection);
            insertTest1(connection);
            connection.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
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
    public static void insertTest(Connection con){
        PreparedStatement stmt = null;

        /**
         * SavePoint回滚点
         */
        Savepoint a1 = null;
        try {
            stmt = con.prepareStatement("insert into user(username) values (?)");
            stmt.setString(1, "撒加");
            stmt.executeUpdate();
            System.out.println("Data inserted successfully");
            stmt.close();
            a1 = con.setSavepoint("a1");
        } catch (SQLException e) {
            e.printStackTrace();
            if(a1 != null) {
                try {
                    con.rollback(a1);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void insertTest1(Connection con) throws SQLException {
        PreparedStatement stmt = null;
        Savepoint a2 = null;
        try {
            stmt = con.prepareStatement("insert into user1(username) values (?)");
            stmt.setString(1, "阿布罗狄");
            stmt.executeUpdate();
            System.out.println("Data inserted successfully");
            stmt.close();
            a2 = con.setSavepoint("a2");
        } catch (SQLException e) {
            e.printStackTrace();
            if(a2 != null) {
                try {
                    con.rollback(a2);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
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
