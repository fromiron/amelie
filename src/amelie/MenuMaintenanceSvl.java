package amelie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (urlPatterns = "/MenuMaintenanceSvl")
public class MenuMaintenanceSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MenuMaintenanceSvl() {
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
        int typeID=200;
        String msg;
        HttpSession session = request.getSession(false);


        System.out.println("MenuMaintenanceSvl");


        if (session == null) {
            url = "/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
        try {
            typeID = Integer.parseInt(request.getParameter("typeID"));
            System.out.println("get parameter typeID : "+typeID);
            request.setAttribute("typeId", typeID);
            System.out.println("set attrbute typeID : "+typeID);
        } catch (Exception e) {
           e.printStackTrace();
        }
        try {
            String typeIDStr =(String) request.getAttribute("typeID");
            System.out.println("typeID : "+typeID);
            typeID=Integer.parseInt(typeIDStr);
            System.out.println("get parameter typeID : "+typeID);
            request.setAttribute("typeId", typeID);
            System.out.println("set attrbute typeID : "+typeID);
        } catch (Exception e) {
           e.printStackTrace();
        }

        try {
            request.setAttribute("mType", MenuType.getAllType());
        } catch (IdealException e) {
            e.printStackTrace();
            IdealException ie = new IdealException(
                    IdealException.ERR_NO_DB_EXCEPTION);
            msg = ie.getMsg();
            System.out.println(msg);
            request.setAttribute("msg", msg);
            url = "/adminIndex.jsp";
        }

        try {
            request.setAttribute("menu", Menu.getMenu(typeID));
        } catch (IdealException e) {
            e.printStackTrace();
            IdealException ie = new IdealException(
                    IdealException.ERR_NO_DB_EXCEPTION);
            msg = ie.getMsg();
            System.out.println(msg);
            request.setAttribute("msg", msg);
            url = "/adminIndex.jsp";
        }

        if(typeID == 100){
            url = "/courseMaintenance.jsp";
            System.out.println("typeID 100 go to courseMaintenance");
        }else{
            url = "/menuMaintenance.jsp";
            System.out.println("typeID menu go to menuMaintenance");

        }


        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);

    }

}
