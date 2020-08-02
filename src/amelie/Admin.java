package amelie;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    private String            admName;
    private String            password;
    private String            exp;

    public Admin() {
        super();
    }

    public String getAdmName() {
        return admName;
    }

    public void setAdmName(String admName) {
        this.admName = admName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public static Admin login(String adminName, String password) {
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Admin adm = null;
        String sql = "";

        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT * FROM am_admin WHERE adm_name = ? and password = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, adminName);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                adm = new Admin();
                adm.setAdmName(rs.getString("adm_name"));
                adm.setPassword(rs.getString("password"));
                adm.setExp(rs.getString("exp"));
            }
        } catch (Exception e) {
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
        return adm;
    }
}
