package amelie;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    private int               menuID;
    private String            menuName;
    private String            detail;
    private int               price;
    private int               orderFlg;
    private int               typeID;
    private String            typeName;
    private int               priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Menu() {
        super();
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOrderFlg() {
        return orderFlg;
    }

    public void setOrderFlg(int orderFlg) {
        this.orderFlg = orderFlg;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static Menu getOneMenu(int menuID, int typeID) throws IdealException {
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Menu amm = null;
        String sql = "";
        String table = "am_menu";
        String keyColumn = "m_id";
        String name = "m_name";

        if (typeID == 100) {
            table = "am_course";
            keyColumn = "c_id";
            name = "c_name";
        }
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql += "SELECT * FROM " + table;
            sql += " JOIN";
            sql += " am_menutype";
            sql += " USING(t_id)";
            sql += " WHERE ";
            sql += keyColumn + " =? ";
            sql += " ORDER BY priority DESC";

            pst = con.prepareStatement(sql);
            pst.setInt(1, menuID);
            rs = pst.executeQuery();

            if (rs.next()) {
                amm = new Menu();
                amm.setMenuID(rs.getInt(keyColumn));
                amm.setMenuName(rs.getString(name));
                amm.setDetail(rs.getString("detail"));
                amm.setOrderFlg(rs.getInt("orderFlg"));
                amm.setPrice(rs.getInt("price"));
                amm.setTypeID(rs.getInt("t_id"));
                amm.setTypeName(rs.getString("t_name"));
                amm.setPriority(rs.getInt("priority"));
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
                e.printStackTrace();
            }
        }
        return amm;
    }

    public static ArrayList<Menu> getMenuList() throws IdealException {
        ArrayList<Menu> al = new ArrayList<Menu>();
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        String table = "am_menu";

        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();

            sql += "SELECT * FROM " + table;
            sql += " JOIN";
            sql += "	am_menutype";
            sql += " USING(t_id)";

            sql += " ORDER BY priority DESC";

            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            while (rs.next()) {
                Menu amm = new Menu();
                amm.setMenuID(rs.getInt("m_id"));
                amm.setMenuName(rs.getString("m_name"));
                amm.setDetail(rs.getString("detail"));
                amm.setOrderFlg(rs.getInt("orderFlg"));
                amm.setPrice(rs.getInt("price"));
                amm.setTypeID(rs.getInt("t_id"));
                amm.setTypeName(rs.getString("t_name"));
                amm.setPriority(rs.getInt("priority"));
                al.add(amm);
            }
        } catch (Exception e) {
            throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (con != null)
                    con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return al;
    }

    public static ArrayList<Menu> getMenu(int TypeID) throws IdealException {
        ArrayList<Menu> al = new ArrayList<Menu>();
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        String table = "am_menu";
        if (TypeID == 100)
            table = "am_course";
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();

            sql += "SELECT * FROM " + table;
            sql += " JOIN ";
            sql += "	am_menutype";
            sql += " USING(t_id)";
            sql += " WHERE ";
            sql += table + " .t_id = ?";
            sql += " ORDER BY priority DESC";
            pst = con.prepareStatement(sql);
            pst.setInt(1, TypeID);
            rs = pst.executeQuery();

            while (rs.next()) {
                Menu amm = new Menu();
                amm.setMenuID(rs.getInt("m_id"));
                amm.setMenuName(rs.getString("m_name"));
                amm.setDetail(rs.getString("detail"));
                amm.setOrderFlg(rs.getInt("orderFlg"));
                amm.setPrice(rs.getInt("price"));
                amm.setTypeName(rs.getString("t_name"));
                amm.setPriority(rs.getInt("priority"));
                al.add(amm);
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
        return al;
    }

    public static int updateMenu(Menu amm, int mode) throws IdealException {
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;

        String sql = "";
        String table = "";
        String keyColumn = "";
        String name = "";
        int num = 0;
        if (amm.getTypeID() == 100) {
            table = "am_course";
            keyColumn = "c_id";
            name = "c_name";
        } else {
            table = "am_menu";
            keyColumn = "m_id";
            name = "m_name";
        }
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            if (mode == MenuOperationSvl.INSERT) {
                sql += "INSERT INTO ";
                sql += table;
                sql += " VALUES(";
                sql += " DEFAULT,?,?,?,?,?,?)";
                System.out.println("insert sql start");
                System.out.println(sql);

                pst = con.prepareStatement(sql);
                pst.setString(1, amm.getMenuName());
                pst.setString(2, amm.getDetail());
                pst.setInt(3, amm.getOrderFlg());
                pst.setInt(4, amm.getPrice());
                pst.setInt(5, amm.getTypeID());
                pst.setInt(6, amm.getPriority());


            } else if (mode == MenuOperationSvl.UPDATE) {
                System.out.println("update sql start");



               sql ="UPDATE "+table+
                       " SET m_Name=?, detail=?, orderFlg=?, price=?, t_id=?, priority=? WHERE "+keyColumn+"= ?;";


                System.out.println(sql);

                pst = con.prepareStatement(sql);
                pst.setString(1, amm.getMenuName());
                pst.setString(2, amm.getDetail());
                pst.setInt(3, amm.getOrderFlg());
                pst.setInt(4, amm.getPrice());
                pst.setInt(5, amm.getTypeID());
                pst.setInt(6, amm.getPriority());
                pst.setInt(7, amm.getMenuID());

                System.out.println("name "+amm.getMenuName());
                System.out.println("detail "+amm.getDetail());
                System.out.println("flg "+amm.getOrderFlg());
                System.out.println("price "+amm.getPrice());
                System.out.println("type "+amm.getTypeID());
                System.out.println("priority "+amm.getPriority());
                System.out.println("menu "+amm.getMenuID());
                System.out.println("before pst executeUpdate");


            } else if (mode == MenuOperationSvl.DELETE) {

                sql = "DELETE FROM "+table+" WHERE "+keyColumn+"= ?;";
                pst = con.prepareStatement(sql);
                pst.setInt(1, amm.getMenuID());
            }
            num = pst.executeUpdate();
        } catch (Exception e) {
            throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
        } finally {
            try {
                if (pst != null)
                    pst.close();
                if (con != null)
                    con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return num;
    }

    public static ArrayList<Menu> getMenuCategory() throws SQLException {
        ArrayList<Menu> al = new ArrayList<Menu>();
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;

        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT * FROM am_menutype";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Menu m = new Menu();
                m.setTypeID(rs.getInt("t_id"));
                m.setTypeName(rs.getString("t_name"));
                al.add(m);
            }
        } catch (Exception e) {
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


    public static ArrayList<Menu> SelectedMenu(int m_id) throws SQLException {
        ArrayList<Menu> al = new ArrayList<Menu>();
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;

        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT * FROM menu WHERE m_id=?;";
            pst = con.prepareStatement(sql);
            pst.setInt(1, m_id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Menu m = new Menu();
                m.setMenuID(rs.getInt("m_id"));
                m.setMenuName(rs.getString("m_name"));
                m.setDetail(rs.getString("detail"));
                m.setOrderFlg(rs.getInt("orderFlg"));
                m.setPrice(rs.getInt("price"));
                m.setTypeID(rs.getInt("t_id"));
                m.setPriority(rs.getInt("priority"));
                al.add(m);
            }
        } catch (Exception e) {
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

    public static ArrayList<Menu> getCourseInMenu(int c_id) throws SQLException {
        System.out.println("getCourseInMenu beans");
        ArrayList<Menu> al = new ArrayList<Menu>();
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;

        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT * FROM am_coursectl INNER JOIN am_menu ON am_coursectl.m_id=am_menu.m_id WHERE c_id = ?;";
            System.out.println("getCourseInMenu sql : "+sql);
            pst = con.prepareStatement(sql);
            pst.setInt(1, c_id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Menu m = new Menu();
                m.setMenuName(rs.getString("m_name"));
                m.setDetail(rs.getString("detail"));
                al.add(m);
            }
        } catch (Exception e) {
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

}
