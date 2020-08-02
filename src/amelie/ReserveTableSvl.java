package amelie;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (urlPatterns = "/ReserveTableSvl")
public class ReserveTableSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final int   INSERT           = 11;
    public static final int   UPDATE           = 12;
    public static final int   DELETE           = 13;

    public ReserveTableSvl() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String url = "";
        String msg = "";
        String reserveDate = "";
        String reserveHour = "";
        String reserveminute = "";
        int reservePerson = 0;
        int reserveCourseId = 0;
        String reserveCourseName = "";
        int mode = 0;
        int reserveFlg = 1;




        HttpSession session = request.getSession(false);

        if (session == null) {
            url = "/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
        try{
        mode = Integer.parseInt(request.getParameter("mode"));
        reserveDate = request.getParameter("date");
        reserveHour = request.getParameter("hour");
        reserveminute = request.getParameter("minute");
        reservePerson = Integer.parseInt(request.getParameter("person"));
        reserveCourseId = Integer.parseInt(request.getParameter("courseId"));
        reserveCourseName = request.getParameter("courseName");

        }catch(Exception e){
            e.printStackTrace();
        }

        String dateSQL = reserveDate + " " + reserveHour + ":" + reserveminute;

        int limiteHour = Integer.parseInt(reserveHour) + 3;
        String dateLimitSQL = reserveDate + " " + limiteHour + ":"
                + reserveminute;

        System.out.println("svl dateSql :" + dateSQL);
        System.out.println("svl dateLimitSql :" + dateLimitSQL);

        // reserved table list set

        ArrayList<TableLoc> reservedTable = null;
        try {
            System.out.println("予約済のテーブルID確認　スタート");
            reservedTable = Reserve.insertChk(reserveDate, dateLimitSQL);
            request.setAttribute("reservedTable", reservedTable);

            for (int i = 0; i < reservedTable.size(); i++) {
                System.out.println("予約済のテーブルID : "
                        + reservedTable.get(i).getTableId());
                System.out.println("予約済のテーブルName : "
                        + reservedTable.get(i).getTableName());
            }
        } catch (Exception e) {
        }

        // all table list set

        ArrayList<TableLoc> allTable = null;
        try {
            System.out.println("予約済のテーブルID確認　スタート");
            allTable = Reserve.getTableList();
            request.setAttribute("allTable", allTable);
        } catch (Exception e) {
        }

        if(allTable.size() == reservedTable.size()){
            reserveFlg = 0;
        }

        request.setAttribute("mode", mode);
        request.setAttribute("reserveFlg", reserveFlg);
        request.setAttribute("dateSQL", dateSQL);
        request.setAttribute("dateLimitSQL", dateLimitSQL);
        request.setAttribute("reservePerson", reservePerson);
        request.setAttribute("reserveCourseId", reserveCourseId);
        request.setAttribute("reserveCourseName", reserveCourseName);
        try{

            request.setAttribute("rsvId",request.getParameter("rsvId"));

        }catch(Exception e){
           System.out.println("rsv Id is null");
        }

        url = "/reserveTable.jsp";

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);

    }

}
