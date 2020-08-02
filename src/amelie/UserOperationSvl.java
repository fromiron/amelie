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

@WebServlet (urlPatterns = "/UserOperationSvl")
public class UserOperationSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final int   INSERT           = 11;
    public static final int   UPDATE           = 12;
    public static final int   DELETE           = 13;


    public UserOperationSvl() {
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
        String msg;

        System.out.println("UserOperationSvl");


        HttpSession session = request.getSession(false);

        int mode = 0;
        try {
            mode = Integer.parseInt(request.getParameter("mode"));
        } catch (NumberFormatException e) {

        }

        if (session == null && mode != 11) {
            url = "/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

        if (mode == INSERT) {
            User gu = null;
            try {
                gu = User.getUser(request.getParameter("usrId"));
            } catch (IdealException e1) {
                e1.printStackTrace();
            }
            if (gu != null) {
                try {
                    throw new IdealException(IdealException.ERR_ID_ZYUUHUKU);
                } catch (IdealException e) {
                    IdealException ie = new IdealException(
                            IdealException.ERR_ID_ZYUUHUKU);
                    msg = ie.getMsg();
                    System.out.println(msg);
                    request.setAttribute("msg", msg);
                    url = "/userInsert.jsp";
                }
            } else {
                User u = new User();
                u.setUsrId(request.getParameter("usrId"));
                u.setUsrName(request.getParameter("usrName"));
                u.setPassword(request.getParameter("password"));
                u.setAddress(request.getParameter("address"));
                u.setPhone(request.getParameter("phone"));
                ;
                u.setMail(request.getParameter("mail"));

                try {
                    User.insert(u);
                } catch (SQLException e) {
                    e.printStackTrace();
                    IdealException ie = new IdealException(
                            IdealException.ERR_NO_DB_EXCEPTION);
                    msg = ie.getMsg();
                    System.out.println(msg);
                    request.setAttribute("msg", msg);
                    url = "/userInsert.jsp";
                }

                session = request.getSession(true);
                session.setAttribute("usrIndex", u.getUsrIndex());
                session.setAttribute("usrId", u.getUsrId());
                session.setAttribute("usrName", u.getUsrName());
                session.setAttribute("password", u.getPassword());
                session.setAttribute("address", u.getAddress());
                session.setAttribute("phone", u.getPhone());
                session.setAttribute("mail", u.getMail());
                url = "/userInsertCompletion.jsp";
            }

        }
        if (mode == UPDATE) {
            User u = new User();

            u.setUsrId(request.getParameter("usrId"));
            u.setUsrName(request.getParameter("usrName"));
            u.setPassword(request.getParameter("password"));
            u.setAddress(request.getParameter("address"));
            u.setPhone(request.getParameter("phone"));
            u.setMail(request.getParameter("mail"));

            try {
                User.update(u);
            } catch (SQLException | IdealException e) {
                e.printStackTrace();
                IdealException ie = new IdealException(
                        IdealException.ERR_NO_DB_EXCEPTION);
                msg = ie.getMsg();
                System.out.println(msg);
                request.setAttribute("msg", msg);
                url = "/UserUpdateSlv";
            }

            session.setAttribute("usrIndex", u.getUsrIndex());
            session.setAttribute("usrIndex", u.getUsrIndex());
            session.setAttribute("usrId", u.getUsrId());
            session.setAttribute("usrName", u.getUsrName());
            session.setAttribute("password", u.getPassword());
            session.setAttribute("address", u.getAddress());
            session.setAttribute("phone", u.getPhone());
            session.setAttribute("mail", u.getMail());
            url = "/userIndex.jsp";
        }

        if (mode == DELETE) {
            User u = new User();

            u.setUsrId(request.getParameter("usrId"));
            u.setUsrName(request.getParameter("usrName"));
            u.setPassword(request.getParameter("password"));
            u.setAddress(request.getParameter("address"));
            u.setPhone(request.getParameter("phone"));
            u.setMail(request.getParameter("mail"));

            try {
                User.delete(u);
            } catch (SQLException | IdealException e) {
                e.printStackTrace();
                IdealException ie = new IdealException(
                        IdealException.ERR_NO_USER_DELETE);
                msg = ie.getMsg();
                System.out.println(msg);
                request.setAttribute("msg", msg);
                url = "/userDelete.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }

            session.invalidate();
            url = "/home.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);

    }

}
