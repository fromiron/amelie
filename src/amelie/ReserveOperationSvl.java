package amelie;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (urlPatterns = "/ReserveOperationSvl")
public class ReserveOperationSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final int   INSERT           = 11;
    public static final int   UPDATE           = 12;
    public static final int   DELETE           = 13;

    // public static final int COMPLETE = 21;

    public ReserveOperationSvl() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @SuppressWarnings("unused")
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession(false);

        System.out.println("Reserve Operation Svl");

        int mode = 0;
        int person = 0;
        int courseId = 0;
        int tableId = 0;
        int rsvId =0;
        String msg;
        String url = "";

        String date = "";
        String dateLimit = "";
        String courseName = "";
        String tableName = "";

        String id = (String) session.getAttribute("usrId");
        int usrIndex = 0;
        User u = null;

        try {
            new User();
            u = User.getUser(id);
        } catch (Exception e) {
        }

        usrIndex = u.getUsrIndex();
        System.out.println("user id : " + id);
        System.out.println("user index : " + usrIndex);

        if (session == null) {
            url = "/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
        mode = Integer.parseInt(request.getParameter("mode"));

        if (mode == INSERT) {

            try {
                System.out
                        .println("Reserve Operation Svl get parameter - insert");

                mode = Integer.parseInt((String) request.getParameter("mode"));
                date = (String) request.getParameter("dateSQL");
                dateLimit = (String) request.getParameter("dateLimitSQL");
                person = Integer.parseInt((String) request
                        .getParameter("reservePerson"));
                courseId = Integer.parseInt((String) request
                        .getParameter("reserveCourseId"));
                courseName = (String) request.getParameter("reserveCourseName");
                tableName = (String) request.getParameter("tableName");
                tableId = Integer.parseInt((String) request
                        .getParameter("tableId"));

                System.out.println("mode : " + mode);
                System.out.println("date : " + date);
                System.out.println("dateLimit : " + dateLimit);
                System.out.println("person : " + person);
                System.out.println("courseId : " + courseId);
                System.out.println("tableId : " + tableId);

            }

            catch (Exception e) {
            }
            Reserve r = new Reserve();
            r.setUsrIndex(usrIndex);
            r.setRsv_date(date);
            r.setPerson(person);
            r.setTableId(tableId);
            r.setCourseId(courseId);

            try {
                Reserve.insert(r);

                request.setAttribute("date", date);
                request.setAttribute("person", person);
                request.setAttribute("tableId", tableId);
                request.setAttribute("tableName", tableName);
                request.setAttribute("courseName", courseName);

                url = "/reserveCompletion.jsp";
            } catch (IdealException | SQLException e) {
                e.printStackTrace();
                IdealException ie = new IdealException(
                        IdealException.ERR_NO_DB_EXCEPTION);
                msg = ie.getMsg();
                System.out.println(msg);
                request.setAttribute("msg", msg);
                url = "/userInsert.jsp";
            }

        } else if (mode == UPDATE) {

            try {
                System.out
                        .println("Reserve Operation Svl get parameter - UPDATE");

                mode = Integer.parseInt((String) request.getParameter("mode"));
                date = (String) request.getParameter("dateSQL");
                dateLimit = (String) request.getParameter("dateLimitSQL");
                person = Integer.parseInt((String) request
                        .getParameter("reservePerson"));
                courseId = Integer.parseInt((String) request
                        .getParameter("reserveCourseId"));
                courseName = (String) request.getParameter("reserveCourseName");
                tableName = (String) request.getParameter("tableName");
                tableId = Integer.parseInt((String) request
                        .getParameter("tableId"));
                rsvId = Integer.parseInt((String) request
                        .getParameter("rsvId"));


                System.out.println("mode : " + mode);
                System.out.println("date : " + date);
                System.out.println("dateLimit : " + dateLimit);
                System.out.println("person : " + person);
                System.out.println("courseId : " + courseId);
                System.out.println("tableId : " + tableId);
                System.out.println("rsvId : " + rsvId);

            }

            catch (Exception e) {
            }
            Reserve r = new Reserve();
            r.setUsrIndex(usrIndex);
            r.setRsv_date(date);
            r.setPerson(person);
            r.setTableId(tableId);
            r.setCourseId(courseId);
            r.setRsvId(rsvId);

            try {
                Reserve.update(r);

                request.setAttribute("date", date);
                request.setAttribute("person", person);
                request.setAttribute("tableId", tableId);
                request.setAttribute("tableName", tableName);
                request.setAttribute("courseName", courseName);

                url = "/reserveCompletion.jsp";
            } catch (IdealException | SQLException e) {
                e.printStackTrace();
                IdealException ie = new IdealException(
                        IdealException.ERR_NO_DB_EXCEPTION);
                msg = ie.getMsg();
                System.out.println(msg);
                request.setAttribute("msg", msg);
                url = "/userUpdate.jsp";
            }

        } else if (mode == DELETE) {
            System.out.println("ReserveOperation Delete");

            rsvId = Integer.parseInt(request.getParameter("rsvId"));

            System.out.println("rsvId "+ rsvId);
            Reserve r = new Reserve();

            try {
                Reserve.delete(rsvId);
                msg="予約取り消し成功";
                System.out.println("予約ナンバー"+rsvId+" 予約取り消し成功");
                url = "/ReserveListSvl";
            } catch (SQLException | IdealException e) {
                IdealException ie = new IdealException(
                        IdealException.ERR_NO_DB_EXCEPTION);
                msg = ie.getMsg();
                System.out.println(msg);
                request.setAttribute("msg", msg);
                url = "/ReserveDeleteSlv";
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);

    }

}
