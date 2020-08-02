package amelie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (urlPatterns = "/AdminUserListSvl")
public class AdminUserListSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminUserListSvl() {
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
        HttpSession session = request.getSession(false);

        System.out.println("AdminUserListSvl");

        if (session.getAttribute("admName") == null) {
            System.out.println("session null");
            url = "/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

        try{
        System.out.println("adminUserList forward");
        url = "/adminUserList.jsp";
        System.out.println(url);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
        }catch(Exception e){
        	e.printStackTrace();
            System.out.println("forward error");

        }

    }

}
