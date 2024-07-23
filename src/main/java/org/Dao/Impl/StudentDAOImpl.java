package org.Dao.Impl;


import org.Dao.StudentDAO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 阿巴阿巴
 * @author 胡邹梁
 * @date 2024/7/22 下午3:57
 */
public class StudentDAOImpl implements StudentDAO {
    private static Scanner sc = new Scanner(System.in);


    @Override
    public void insert(Student student) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (
                Connection c = DBUtil.getConnection();
                Statement st = c.createStatement();
//                1.传参麻烦
//                2.性能较差
//                3.存在SQL注入攻击问题

//                PreparedStatement
//                先编译后传参，效率更高
//                传参方便
//                有效防止SQL注入攻击问题
        ) {
            String sql = "insert into student(name,gender,birthday,addr,qqnumber) values('%s','%s','%s','%s','%d')";
            sql = String.format(sql, student.getName(), student.getGender(), sdf.format(student.getBirthday()), student.getAddr(), student.getQqnumber());
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        Connection c = null;
        String sql = "delete from student where id=?";
        try {
            c = DBUtil.getConnection();
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            c.commit();
        } catch (SQLException e) {
            try{
                c.rollback();
            }catch(SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                c.close();
            }catch (SQLException e2){
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void update(Student student) {
        String sql = "update student set name=?,gender=?,birthday=?,addr=?,qqnumber=? where id=?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getGender());
            ps.setDate(3, new Date(student.getBirthday().getTime()));
            ps.setString(4, student.getAddr());
            ps.setLong(5, student.getQqnumber());
            ps.setInt(6, student.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer count() {
        String sql = "select count(*) from student";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
//                每次从结果集读取一行数据
//                根据读取字段数据不同，使用不同的get方法
//                方法参数有两种 一种是获取的字段在查询结果中出现的顺序
//                可以不写字段出现顺序
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Student findById(Integer id) {
        String sql = "select * from student where id=?";
        Student student = new Student();
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(id);
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddr(rs.getString("addr"));
                student.setQqnumber(rs.getLong("qqnumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        return findWithLimit(0, Integer.MAX_VALUE);
    }

    @Override
    public List<Student> findByNameLike(String name) {
        return findByNameLikeWithLimt(name, 0, Integer.MAX_VALUE);
    }

    @Override
    public List<Student> findByNameLikeWithLimt(String name, int start, int limit) {
        String sql = "select * from student where name like concat('%',?,'%') limit ?,?";
        List<Student> list = new ArrayList<Student>();
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            ps.setInt(2, start);
            ps.setInt(3, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddr(rs.getString("addr"));
                student.setQqnumber(rs.getLong("qqnumber"));
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() == 0) {
            return null;
        } else {
            return list;
        }
    }

    @Override
    public List<Student> findWithLimit(int start, int limit) {
        String sql = "select * from student limit ?,?";
        List<Student> list = new ArrayList<>();
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, start);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
//            遍历结果集
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddr(rs.getString("addr"));
                student.setQqnumber(rs.getLong("qqnumber"));
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() == 0) {
            return null;
        } else {
            return list;
        }
    }
}
