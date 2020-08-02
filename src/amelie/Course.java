package amelie;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Course implements Serializable {
    private static final long serialVersionUID      = 1L;
    private int               courseId;
    private int               price;
    private int               orderFlg;
    private int               typeId;
    private int               menuId;
    private int               menuTypeId;
    private int               priority;
    private String            courseName;
    private String            detail;
    private String            typeName;
    private String            menuName;
    public static final int   COURSE_MENU_TYPE_ID[] = { 200, 210, 220, 300,
            310, 400                               };

    public Course() {
        super();
    }

    public int getCourseId() {
        return courseId;
    }

    public int getPrice() {
        return price;
    }

    public int getOrderFlg() {
        return orderFlg;
    }

    public int getTypeId() {
        return typeId;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDetail() {
        return detail;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getMenuName() {
        return menuName;
    }

    public static int[] getCourseMenuTypeId() {
        return COURSE_MENU_TYPE_ID;
    }

    public int getPriority() {
        return priority;
    }

    public int getMenuTypeId() {
        return menuTypeId;
    }

    public void setMenuTypeId(int menuTypeId) {
        this.menuTypeId = menuTypeId;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOrderFlg(int orderFlg) {
        this.orderFlg = orderFlg;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public static Course getCourse(int c_Id) throws IdealException,
            SQLException, NamingException {

        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;
        Course c = null;
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT * FROM am_course WHERE c_id=? ";
            pst = con.prepareStatement(sql);
            pst.setInt(1, c_Id);

            rs = pst.executeQuery();

            if (rs.next()) {
                c = new Course();
                c.setCourseId(rs.getInt("c_id"));
                c.setPrice(rs.getInt("price"));
                c.setOrderFlg(rs.getInt("OrderFlg"));
                c.setTypeId(rs.getInt("t_id"));
                c.setPriority(rs.getInt("priority"));
                c.setCourseName(rs.getString("c_name"));
                c.setDetail(rs.getString("detail"));
            }
        } catch (Exception e) {
            throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pst != null)
                    pst.close();
                if (con != null)
                    con.close();
            } catch (Exception e) {

            }
        }
        return c;
    }

    public static ArrayList<Course> getOneCourse(int c_Id)
            throws IdealException {
        ArrayList<Course> al = new ArrayList<Course>();
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;
        Course c = null;
        System.out.println("こj1");
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT am_course.c_id,c_name,am_course.detail,am_course.orderFlg, "
                    + "am_course.price,am_course.priority,am_course.t_id,am_menu.m_id,am_menu.t_id,m_Name "
                    + "FROM am_course JOIN am_coursectl "
                    + "ON am_course.c_id = am_coursectl.c_id "
                    + "left JOIN am_menu "
                    + "ON am_coursectl.m_id = am_menu.m_id WHERE am_course.c_id  = ? ";
            pst = con.prepareStatement(sql);
            pst.setInt(1, c_Id);
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("am_menu.m_id"));
                c = new Course();
                c.setCourseId(rs.getInt("c_id"));
                c.setPrice(rs.getInt("price"));
                c.setOrderFlg(rs.getInt("OrderFlg"));
                c.setTypeId(rs.getInt("t_id"));
                c.setPriority(rs.getInt("priority"));
                c.setCourseName(rs.getString("c_name"));
                c.setDetail(rs.getString("detail"));
                c.setMenuId(rs.getInt("am_menu.m_id"));
                c.setMenuTypeId(rs.getInt("am_menu.t_id"));
                c.setMenuName(rs.getString("m_Name"));
                al.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pst != null)
                    pst.close();
                if (con != null)
                    con.close();
            } catch (Exception e) {

            }
        }
        return al;
    }

    public static ArrayList<Course> getCourseList() throws IdealException {
        ArrayList<Course> al = new ArrayList<Course>();
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;
        Course c = null;
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT am_course.c_id,c_name,am_course.detail,am_course.orderFlg, "
                    + "am_course.price,am_course.t_id,am_menu.m_id,am_menu.t_id,m_Name "
                    + "FROM am_course JOIN am_coursectl "
                    + "ON am_course.c_id = am_coursectl.c_id "
                    + "left JOIN am_menu "
                    + "ON am_coursectl.m_id = am_menu.m_id WHERE am_course.orderFlg = 1 ORDER BY priority DESC";
            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            while (rs.next()) {
                c = new Course();
                c.setCourseId(rs.getInt("c_id"));
                c.setPrice(rs.getInt("price"));
                c.setOrderFlg(rs.getInt("OrderFlg"));
                c.setTypeId(rs.getInt("t_id"));
                c.setPriority(rs.getInt("priority"));
                c.setCourseName(rs.getString("c_name"));
                c.setDetail(rs.getString("detail"));
                c.setMenuId(rs.getInt("m_id"));
                c.setMenuName(rs.getString("m_Name"));

                al.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pst != null)
                    pst.close();
                if (con != null)
                    con.close();
            } catch (Exception e) {

            }
        }
        return al;
    }

    public static ArrayList<Course> getOneCourseList() throws IdealException {
        ArrayList<Course> al = new ArrayList<Course>();
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;
        Course c = null;
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT * FROM am_course ORDER BY priority DESC";
            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            while (rs.next()) {
                c = new Course();
                c.setCourseId(rs.getInt("c_id"));
                c.setPrice(rs.getInt("price"));
                c.setOrderFlg(rs.getInt("OrderFlg"));
                c.setTypeId(rs.getInt("t_id"));
                c.setPriority(rs.getInt("priority"));
                c.setCourseName(rs.getString("c_name"));
                c.setDetail(rs.getString("detail"));

                al.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pst != null)
                    pst.close();
                if (con != null)
                    con.close();
            } catch (Exception e) {

            }
        }
        return al;
    }

    public static ArrayList<Course> getTypeCourseList(int t_Id)
            throws IdealException {
        ArrayList<Course> al = new ArrayList<Course>();
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;
        Course c = null;
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT am_course WHERE t_id=? ";
            pst = con.prepareStatement(sql);
            pst.setInt(1, t_Id);
            rs = pst.executeQuery();

            while (rs.next()) {
                c = new Course();
                c.setCourseId(rs.getInt("c_id"));
                c.setPrice(rs.getInt("price"));
                c.setOrderFlg(rs.getInt("OrderFlg"));
                c.setTypeId(rs.getInt("t_id"));
                c.setPriority(rs.getInt("priority"));
                c.setCourseName(rs.getString("c_name"));
                c.setDetail(rs.getString("detail"));

                al.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pst != null)
                    pst.close();
                if (con != null)
                    con.close();
            } catch (Exception e) {

            }
        }
        return al;
    }

    public static int updateCourse(Course c, int mode,
            ArrayList<CourseCtl> coursectl) throws IdealException {
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;

        if (mode == CourseOperationSvl.INSERT) {
            try {
                ic = new InitialContext();
                ds = (DataSource) ic.lookup("java:comp/env/mysql");
                con = ds.getConnection();

                sql = "INSERT INTO am_course(c_name,detail,orderFlg,price,t_id,priority)"
                        + "VALUES(?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, c.getCourseName());
                pst.setString(2, c.getDetail());
                pst.setInt(3, c.getOrderFlg());
                pst.setInt(4, c.getPrice());
                pst.setInt(5, c.getTypeId());
                pst.setInt(6, c.getPriority());
                pst.executeUpdate();

                sql = "SELECT * FROM am_course ORDER BY c_id DESC ";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                    c.setCourseId(rs.getInt("c_id"));
                }

                for (CourseCtl cc : coursectl) {
                    sql = "INSERT INTO am_coursectl(c_id,m_id)" + "VALUES("
                            + c.getCourseId() + ",?)";
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, cc.getM_Id());
                    pst.executeUpdate();
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
            } finally {
                try {
                    con.close();
                    pst.close();
                } catch (Exception e) {

                }
            }
        } else if (mode == CourseOperationSvl.UPDATE) {
            System.out.println("Update sql");
            try {
                ic = new InitialContext();
                ds = (DataSource) ic.lookup("java:comp/env/mysql");
                con = ds.getConnection();

                sql = "UPDATE am_course SET c_name=?,detail=?,orderFlg=?,price=?,t_id=?,priority=? WHERE c_id=?";
                pst = con.prepareStatement(sql);
                pst.setString(1, c.getCourseName());
                pst.setString(2, c.getDetail());
                pst.setInt(3, c.getOrderFlg());
                pst.setInt(4, c.getPrice());
                pst.setInt(5, c.getTypeId());
                pst.setInt(6, c.getPriority());
                pst.setInt(7, c.getCourseId());
                pst.executeUpdate();

                sql = "DELETE FROM am_courseCtl WHERE c_id=?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, c.getCourseId());
                pst.executeUpdate();

                for (CourseCtl cc : coursectl) {
                    sql = "INSERT INTO am_coursectl(c_id,m_id)" + "VALUES("
                            + c.getCourseId() + ",?)";
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, cc.getM_Id());
                    pst.executeUpdate();
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
            } finally {
                try {
                    con.close();
                    pst.close();
                } catch (Exception e) {

                }
            }
        } else if (mode == CourseOperationSvl.DELETE) {
            try {
                ic = new InitialContext();
                ds = (DataSource) ic.lookup("java:comp/env/mysql");
                con = ds.getConnection();

                sql = "DELETE FROM am_coursectl WHERE c_id=?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, c.getCourseId());
                pst.executeUpdate();

                sql = "DELETE FROM am_course WHERE c_id=?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, c.getCourseId());
                pst.executeUpdate();


            } catch (Exception e) {
                e.printStackTrace();
                throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
            } finally {
                try {
                    con.close();
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        mode = 0;
        return mode;
    }
}
