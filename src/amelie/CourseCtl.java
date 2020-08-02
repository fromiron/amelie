package amelie;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CourseCtl implements Serializable {
    private static final long serialVersionUID = 1L;
    private int               c_Id;
    private int               m_Id;

    public CourseCtl() {
        super();
    }

    public int getC_Id() {
        return c_Id;
    }

    public int getM_Id() {
        return m_Id;
    }

    public void setC_Id(int c_Id) {
        this.c_Id = c_Id;
    }

    public void setM_Id(int m_Id) {
        this.m_Id = m_Id;
    }

    public static void courseMenuChk(int m_Id) throws IdealException {
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;

        System.out.println("CourseCtlSvl");


        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT * FROM am_coursectl WHERE m_id=? ";
            pst = con.prepareStatement(sql);
            pst.setInt(1, m_Id);

            rs = pst.executeQuery();

            if (rs.next()) {
                throw new IdealException(IdealException.ERR_NO_NOT_MENU_DELETE);
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
    }

}
