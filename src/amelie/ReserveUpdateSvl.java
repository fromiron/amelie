package amelie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (urlPatterns = "/ReserveUpdateSvl")
public class ReserveUpdateSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final int   INSERT           = 11;
    public static final int   UPDATE           = 12;
    public static final int   DELETE           = 13;

    public ReserveUpdateSvl() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reserve Update Svl");

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String url = "";
        String msg;
        HttpSession session = request.getSession(false);

        int rsvId = 0;
        int person = 0;
        int courseId = 0;
        int tableId = 0;

        String date = "";
        String hour ="";
        String minute="";
        String dateTemp = "";
        String courseName = "";
        String talbeName = "";

        if (session == null) {
            url = "/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
        url = "/reserveUpdate.jsp";

        try {
            rsvId = Integer.parseInt(request.getParameter("rsvId"));
            person = Integer.parseInt(request.getParameter("person"));
            courseId = Integer.parseInt(request.getParameter("courseId"));
            tableId = Integer.parseInt(request.getParameter("tableId"));
            dateTemp = request.getParameter("date").replace(".0", "");
            courseName = request.getParameter("courseName");
            talbeName = request.getParameter("talbeName");

            System.out.println("date str slice");
            date = dateTemp.substring(0,10);
            hour = dateTemp.substring(dateTemp.length()-8, dateTemp.length()-6);
            minute = dateTemp.substring(dateTemp.length()-5, dateTemp.length()-3);
            System.out.println("date : "+date);
            System.out.println("hour : "+hour);
            System.out.println("minute : "+minute);
        } catch (Exception e) {
            System.out.println("getParameter catch error");
        }

        System.out.println("dateTemp"+dateTemp);
        date = dateTemp.substring(0,10);
        hour = dateTemp.substring(dateTemp.length()-8, dateTemp.length()-6);
        minute = dateTemp.substring(dateTemp.length()-5, dateTemp.length()-3);
        System.out.println("date slice test");
        System.out.println("date"+date);
        System.out.println("hour"+hour);
        System.out.println("minute"+minute);


        try {

            System.out.println("Reserve Update Svl setAttribute");
            request.setAttribute("rsvId", rsvId);
            request.setAttribute("person", person);
            request.setAttribute("courseId", courseId);
            request.setAttribute("tableId", tableId);
            request.setAttribute("date", date);
            request.setAttribute("hour", hour);
            request.setAttribute("minute", minute);
            request.setAttribute("courseName", courseName);
            request.setAttribute("talbeName", talbeName);

            System.out.println("setAttribute finished go to update.jsp");

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

        } catch (Exception e) {
            IdealException ie = new IdealException(
                    IdealException.ERR_NO_EXCEPTION);
            msg = ie.getMsg();
            System.out.println(msg);
            request.setAttribute("msg", msg);
            url = "/ReserveListSvl";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

    }

}
