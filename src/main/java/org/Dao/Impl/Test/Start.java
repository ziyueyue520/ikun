package org.Dao.Impl.Test;


import org.Dao.Impl.Student;
import org.Dao.Impl.StudentDAOImpl;
import org.Dao.StudentDAO;

import java.util.Date;

/**
 * java database connection
 *
 * @author 胡邹梁
 * @date 2024/7/22 下午2:42
 */
public class Start {
    public static void main(String[] args) {
//        驱动加载
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("驱动器加载成功：");
//        获取jdbc连接
        String url = "jdbc:mysql://localhost:3306/nuist?characterEncoding=utf8";
        String user = "root";
        String password = "a12345";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("获取连接成功！！");
//
        try {
            Statement s = connection.createStatement();
            String sql= "INSERT INTO student\n" +
                    "(NAME,gender,birthday,addr,qqnumber)\n" +
                    "VALUESVALUES('李负责','女','2003-04-05','江苏南京',486546554)";
            s.execute(sql);
        }catch (SQLException e) {
            e.printStackTrace();
//            驱动加载 连接获取
        }*/
        Student s1 = new Student(0, "Jo", "男", new Date(), "南京", 110l);
        StudentDAO studentDAO = new StudentDAOImpl();
//        studentDAO.insert(s1);
        Student s2 = new Student(5, "Bob", "男", new Date(), "南京", 110l);
        studentDAO.update(s2);
    }
}