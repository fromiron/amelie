package amelie;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String            usrId;
    private int               usrIndex;
    private String            usrName;
    private String            password;
    private String            address;
    private String            phone;
    private String            mail;
    private String            exp;

    public User() {
        super();
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public int getUsrIndex() {
        return usrIndex;
    }

    public void setUsrIndex(int usrIndex) {
        this.usrIndex = usrIndex;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public static User login(String usrID, String password) {
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        User user = null;
        String sql = "";

        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT * FROM am_user WHERE usr_id = ? and password = ?;";
            pst = con.prepareStatement(sql);
            pst.setString(1, usrID);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUsrId(rs.getString("usr_id"));
                user.setUsrName(rs.getString("usr_name"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setMail(rs.getString("mail"));
                user.setExp(rs.getString("exp"));

            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return user;
    }

    public static User getUser(String usrId) throws IdealException {
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        User user = null;
        String sql = "";

        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT * FROM am_user WHERE usr_id=?;";
            pst = con.prepareStatement(sql);
            pst.setString(1, usrId);
            rs = pst.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUsrId(rs.getString("usr_id"));
                user.setUsrIndex(rs.getInt("usr_index"));
                user.setUsrName(rs.getString("usr_name"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setMail(rs.getString("mail"));
                user.setExp(rs.getString("exp"));
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
        return user;
    }

    public static ArrayList<User>getUserList() throws IdealException {
        ArrayList<User> al = new ArrayList<User>();
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        User user = null;
        String sql = "";

        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT * FROM am_user ;";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setUsrId(rs.getString("usr_id"));
                user.setUsrIndex(rs.getInt("usr_index"));
                user.setUsrName(rs.getString("usr_name"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setMail(rs.getString("mail"));
                user.setExp(rs.getString("exp"));
                al.add(user);
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
        return al;
    }

    public static void insert(User user) throws SQLException {
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

            sql = "INSERT INTO am_user(usr_id,usr_name,password,address,phone,mail)  VALUES(?,?,?,?,?,?);";

            pst = con.prepareStatement(sql);
            pst.setString(1, user.getUsrId());
            pst.setString(2, user.getUsrName());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getAddress());
            pst.setString(5, user.getPhone());
            pst.setString(6, user.getMail());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null)
                    pst.close();
                if (con != null)
                    con.close();
                if (rs != null)
                    rs.close();
            } catch (Exception e) {

            }
        }
    }

    public static void update(User user) throws SQLException, IdealException {
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        System.out.println("user update beans");

        try {
            sql = "UPDATE am_user SET password=?,address=?,phone=?,mail=? WHERE usr_id=?;";
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, user.getPassword());
            pst.setString(2, user.getAddress());
            pst.setString(3, user.getPhone());
            pst.setString(4, user.getMail());
            pst.setString(5, user.getUsrId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
        } finally {
            try {
                if (pst != null)
                    pst.close();
                if (con != null)
                    con.close();
                if (rs != null)
                    rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void delete(User user) throws SQLException, IdealException {
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            sql = "DELETE FROM am_user WHERE usr_id=?;";
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, user.getUsrId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
        } finally {
            try {
                if (pst != null)
                    pst.close();
                if (con != null)
                    con.close();
                if (rs != null)
                    rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
