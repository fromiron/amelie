package amelie;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Reserve implements Serializable {
    private static final long serialVersionUID = 1L;
    private int               rsvId;
    private String            usrId;
    private int               usrIndex;
    private String            usrName;
    private String            rsv_date;
    private int               rsvYy;
    private int               rsvMm;
    private int               rsvDd;
    private int               rsvHh;
    private int               rsvMi;
    private int               person;
    private int               tableId;
    private String            tableName;
    private int               courseId;
    private String            courseName;
    private String            appDate;
    private int               appYy;
    private int               appMm;
    private int               appDd;
    private int               appHh;
    private int               appMi;

    public Reserve() {
        super();
    }

    public int getRsvId() {
        return rsvId;
    }

    public void setRsvId(int rsvId) {
        this.rsvId = rsvId;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getRsv_date() {
        return rsv_date;
    }

    public void setRsv_date(String rsv_date) {
        this.rsv_date = rsv_date;
    }

    public int getRsvYy() {
        return rsvYy;
    }

    public void setRsvYy(int rsvYy) {
        this.rsvYy = rsvYy;
    }

    public int getRsvMm() {
        return rsvMm;
    }

    public void setRsvMm(int rsvMm) {
        this.rsvMm = rsvMm;
    }

    public int getRsvDd() {
        return rsvDd;
    }

    public void setRsvDd(int rsvDd) {
        this.rsvDd = rsvDd;
    }

    public int getRsvHh() {
        return rsvHh;
    }

    public void setRsvHh(int rsvHh) {
        this.rsvHh = rsvHh;
    }

    public int getRsvMi() {
        return rsvMi;
    }

    public void setRsvMi(int rsvMi) {
        this.rsvMi = rsvMi;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public int getAppYy() {
        return appYy;
    }

    public void setAppYy(int appYy) {
        this.appYy = appYy;
    }

    public int getAppMm() {
        return appMm;
    }

    public void setAppMm(int appMm) {
        this.appMm = appMm;
    }

    public int getAppDd() {
        return appDd;
    }

    public void setAppDd(int appDd) {
        this.appDd = appDd;
    }

    public int getAppHh() {
        return appHh;
    }

    public void setAppHh(int appHh) {
        this.appHh = appHh;
    }

    public int getAppMi() {
        return appMi;
    }

    public void setAppMi(int appMi) {
        this.appMi = appMi;
    }

    public int getUsrIndex() {
        return usrIndex;
    }

    public void setUsrIndex(int usrIndex) {
        this.usrIndex = usrIndex;
    }

    public static ArrayList<Reserve> getReserveList(int usrIndex)
            throws IdealException {
        System.out.println("getReserveList start");

        ArrayList<Reserve> al = new ArrayList<Reserve>();
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;

        try {
            System.out.println("getReserveList db start");

            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "select * from am_reserve left join am_table_loc on am_reserve.table_id = am_table_loc.table_id left join  am_course on am_reserve.c_id = am_course.c_id WHERE usr_index=?;";
            System.out.println(sql);

            pst = con.prepareStatement(sql);
            pst.setInt(1, usrIndex);
            rs = pst.executeQuery();

            System.out.println("getReserveList pst.executeQuery finished");

            while (rs.next()) {
                System.out.println("rs roop");

                Reserve rsv = new Reserve();
                rsv.setRsvId(rs.getInt("rsv_id"));
                rsv.setUsrIndex(rs.getInt("usr_index"));
                rsv.setRsv_date(rs.getString("rsv_date"));
                rsv.setPerson(rs.getInt("person"));
                rsv.setTableId(rs.getInt("table_id"));
                rsv.setTableName(rs.getString("table_name"));
                rsv.setCourseId(rs.getInt("c_id"));
                rsv.setCourseName(rs.getString("c_name"));
                rsv.setAppDate(rs.getString("app_date"));

                System.out.println("rsv_id" + rs.getInt("rsv_id"));
                System.out.println("usr_index" + rs.getInt("usr_index"));
                System.out.println("rsv_date" + rs.getString("rsv_date"));
                System.out.println("person" + rs.getInt("person"));
                System.out.println("table_id" + rs.getInt("table_id"));
                System.out.println("table_name" + rs.getString("table_name"));
                System.out.println("course_id" + rs.getInt("c_id"));
                System.out.println("course_name" + rs.getString("c_name"));
                System.out.println("app_date" + rs.getString("app_date"));

                al.add(rsv);
            }
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
        return al;
    }

    public static Reserve getReserve(int rsvId) throws IdealException {
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Reserve rsv = null;
        String sql = null;

        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/evn/mysql");
            con = ds.getConnection();
            sql = "SELECT * FROM am_reserve WHERE rsv_id=?;";
            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();
            if (rs.next()) {
                rsv = new Reserve();
                rsv.setRsvId(rs.getInt(rsvId));
                rsv.setUsrId(rs.getString("usr_id"));
                rsv.setUsrName(rs.getString("usr_name"));
                rsv.setRsv_date(rs.getString("rsv_date"));
                rsv.setPerson(rs.getInt("person"));
                rsv.setTableId(rs.getInt("table_id"));
                rsv.setTableName(rs.getString("table_name"));
                rsv.setCourseId(rs.getInt("course_id"));
                rsv.setCourseName(rs.getString("course_name"));
                rsv.setAppDate(rs.getString("app_date"));
            }
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
        return rsv;
    }

    public static boolean reserveCourseChk(int c_id) throws IdealException {
        System.out.println("reserveCourseChk");

        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;
        boolean reserveCnt = false;

        try {
            System.out.println("sql start");
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/evn/mysql");
            con = ds.getConnection();
            sql = "SELECT * FROM am_reserve WHERE c_id=?;";
            System.out.println("sql : " + sql);
            pst = con.prepareStatement(sql);
            pst.setInt(1, c_id);
            rs = pst.executeQuery();
            if (rs.isBeforeFirst()) {
                System.out.println("予約されているデーターはありません。");
                reserveCnt = true;
            }
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
                return reserveCnt;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("reserveCnt is "+reserveCnt);
        return reserveCnt;
    }

    public static ArrayList<TableLoc> insertChk(String reserveDate,
            String limiteDate) throws IdealException {

        ArrayList<TableLoc> list = new ArrayList<TableLoc>();
        TableLoc tbl = null;

        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;

        System.out.println("beans reserveDate :" + reserveDate);
        System.out.println("beans limitDate :" + limiteDate);

        System.out.println("reserve insertChk");

        try {
            System.out.println("insertChk sql start");
            ic = new InitialContext();

            ds = (DataSource) ic.lookup("java:comp/env/mysql");

            con = ds.getConnection();

            sql = "SELECT am_reserve.table_id, am_reserve.usr_index,am_reserve.rsv_date, am_table_loc.table_name "
                    + "FROM am_reserve JOIN am_table_loc ON am_reserve.table_id = am_table_loc.table_id "
                    + "WHERE rsv_date between ? and ? ORDER BY table_id ASC;";

            System.out.println(sql);

            System.out.println("reserve insertChk SQL");

            pst = con.prepareStatement(sql);
            pst.setString(1, reserveDate);
            pst.setString(2, limiteDate);
            rs = pst.executeQuery();

            while (rs.next()) {
                tbl = new TableLoc();
                tbl.setTableId(rs.getInt("table_id"));
                tbl.setTableName(rs.getString("table_name"));
                list.add(tbl);

            }
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
        System.out.println("return table list");
        return list;
    }

    public static TableLoc updateChk(int rsvID, String dateStr, int personNum)
            throws IdealException {
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        TableLoc tbl = null;
        String sql = null;

        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/evn/mysql");
            con = ds.getConnection();
            sql = "UPDATE am_table_loc SET table_id=?,table_name=?,max_capacity=? WHERE table_id=?;";
            if (rs.next()) {
                pst = con.prepareStatement(sql);
                pst.setInt(1, tbl.getTableId());
                pst.setString(2, tbl.getTableName());
                pst.setInt(3, tbl.getMaxCapacity());
            }
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
        return tbl;
    }

    @SuppressWarnings("null")
    public static Reserve insert(Reserve rsv) throws IdealException,
            SQLException {

        System.out.println("reserve insert start");

        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;

        try {
            System.out.println("reserve insert db start");

            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "INSERT INTO am_reserve(usr_index,rsv_date,person,table_id,c_id) VALUES(?,?,?,?,?);";

            System.out.println(sql);

            System.out.println("db pst 1 set getUsrIndex : "
                    + rsv.getUsrIndex());
            System.out.println("db pst 2 set getRsv_date : "
                    + rsv.getRsv_date());
            System.out.println("db pst 3 set getPerson : " + rsv.getPerson());
            System.out.println("db pst 4 set getTableId : " + rsv.getTableId());
            System.out.println("db pst 5 set getCourseId : "
                    + rsv.getCourseId());
            pst = con.prepareStatement(sql);

            pst.setInt(1, rsv.getUsrIndex());
            pst.setString(2, rsv.getRsv_date());
            pst.setInt(3, rsv.getPerson());
            pst.setInt(4, rsv.getTableId());
            pst.setInt(5, rsv.getCourseId());
            pst.executeUpdate();
            System.out.println("reserve insert db pst executeUpdate finished");

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
        return rsv;
    }

    public static Reserve update(Reserve rsv) throws IdealException,
            SQLException {

        System.out.println("reserve update start");

        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;

        try {
            System.out.println("reserve update db start");

            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "UPDATE am_reserve SET rsv_date=?, person=?, table_id=?, c_id=? WHERE rsv_id=?;";

            System.out.println(sql);

            System.out.println("db pst 1 set getRsv_date : "
                    + rsv.getRsv_date());
            System.out.println("db pst 2 set getPerson : " + rsv.getPerson());
            System.out.println("db pst 3 set getTableId : " + rsv.getTableId());
            System.out.println("db pst 4 set getCourseId : "
                    + rsv.getCourseId());
            System.out.println("db pst 5 set rsv_id : " + rsv.getRsvId());
            pst = con.prepareStatement(sql);

            pst.setString(1, rsv.getRsv_date());
            pst.setInt(2, rsv.getPerson());
            pst.setInt(3, rsv.getTableId());
            pst.setInt(4, rsv.getCourseId());
            pst.setInt(5, rsv.getRsvId());
            pst.executeUpdate();
            System.out.println("reserve update db pst executeUpdate finished");

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
        return rsv;
    }

    public static void delete(int rsvId) throws IdealException, SQLException {
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
            sql = "DELETE FROM am_reserve WHERE rsv_id=?;";
            pst = con.prepareStatement(sql);
            pst.setInt(1, rsvId);
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

    // table id, name
    public static ArrayList<TableLoc> getTableList() throws IdealException {
        ArrayList<TableLoc> list = new ArrayList<TableLoc>();
        TableLoc tbl = null;
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;

        try {
            System.out.println("getTableList sql start");
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT * FROM am_table_loc;";
            System.out.println(sql);
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                tbl = new TableLoc();
                tbl.setTableId(rs.getInt("table_id"));
                tbl.setTableName(rs.getString("table_name"));
                tbl.setMaxCapacity(rs.getInt("max_capacity"));
                list.add(tbl);

            }
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
        System.out.println("return all table list");
        return list;
    }

    @SuppressWarnings("null")
    public static ArrayList<Reserve> getAllReserve() throws IdealException {
        ArrayList<Reserve> list = new ArrayList<Reserve>();
        Reserve rsv = null;
        InitialContext ic = null;
        DataSource ds = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;

        try {
            System.out.println("getTableList sql start");
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/mysql");
            con = ds.getConnection();
            sql = "SELECT * FROM am_reserve INNER JOIN am_user ON am_user.usr_index=am_reserve.usr_index INNER JOIN am_course ac on am_reserve.c_id = ac.c_id INNER JOIN am_table_loc atl on am_reserve.table_id = atl.table_id;";
            System.out.println(sql);
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Reserve set");
                rsv = new Reserve();

                System.out.println("rsv_id : " + rs.getInt("rsv_id"));
                System.out.println("usr_id : " + rs.getString("usr_id"));
                System.out.println("usr_name : " + rs.getString("usr_name"));
                System.out.println("rsv_date : " + rs.getString("rsv_date"));
                System.out.println("person : " + rs.getInt("person"));
                System.out
                        .println("table_name : " + rs.getString("table_name"));
                System.out.println("c_name : " + rs.getString("c_name"));
                System.out.println("app_date : " + rs.getString("app_date"));

                rsv.setRsvId(rs.getInt("rsv_id"));
                rsv.setUsrId(rs.getString("usr_id"));
                rsv.setUsrName(rs.getString("usr_name"));
                rsv.setRsv_date(rs.getString("rsv_date"));
                rsv.setPerson(rs.getInt("person"));
                rsv.setTableName(rs.getString("table_name"));
                rsv.setCourseName(rs.getString("c_name"));
                rsv.setAppDate(rs.getString("app_date"));
                list.add(rsv);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        System.out.println("return all reserve list");
        return list;
    }

}
