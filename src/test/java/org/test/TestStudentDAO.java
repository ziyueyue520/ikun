package org.test;

import org.Impl.StudentDAOImpl;
import org.Dao.StudentDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * 测试类，用于测试StudentDAO
 *
 * @author 胡邹梁
 * @Test 将指定方法标记为测试方法
 * @Before 在所有测试方法运行之前执行的代码 一般用于环境的初始化
 * @After 在所有测试方法执行之前执行的代码 一般用于资源回收
 * @date 2024/7/23 上午11:02
 */
public class TestStudentDAO {
    private StudentDAO studentDAO;

    @Before
    public void init() {
        studentDAO = new StudentDAOImpl();
    }

    @Test
    public void testFindById() {
        Assert.assertNotNull(studentDAO.findById(1));
        System.out.println(studentDAO.findById(1));
    }

    @Test
    public void testCount() {
        Assert.assertEquals(5l, (long) studentDAO.count());
    }

    @Test
    public void testFindAll() {
        Assert.assertNotNull(studentDAO.findAll());
    }

    @Test
    public void testFindWithLimit() {
        Assert.assertNotNull(studentDAO.findWithLimit(2, 5));
    }

    @Test
    public void findByNameLikeWithLimt() {
        Assert.assertNotNull(studentDAO.findByNameLikeWithLimt("六%", 0, 5));
        System.out.println(studentDAO.findByNameLikeWithLimt("六%", 0, 5));
    }

    @Test
    public void findByNameLike() {
        Assert.assertNotNull(studentDAO.findByNameLike("六责"));
        System.out.println(studentDAO.findByNameLike("六责"));
    }
}
