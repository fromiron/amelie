package amelie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (urlPatterns = "/MenuOperationSvl")
public class MenuOperationSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final int   INSERT           = 11;
    public static final int   UPDATE           = 12;
    public static final int   DELETE           = 13;

    public MenuOperationSvl() {
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

        System.out.println("MenuOperationSvl");


        String url = "";
        int mode = 0;
        String menuName = "";
        String detail = "";
        int orderFlg = 0;
        int price = 0;
        int priority = 0;
        int typeID = 0;
        int menuId = 0;
        String msg;
        HttpSession session = request.getSession(false);

        String modeStr = (String) request.getParameter("mode");
        mode = Integer.parseInt(modeStr);
        String menuIdStr = (String) request.getParameter("menuID");
        try{
        menuId = Integer.parseInt(menuIdStr);}
        catch(Exception e){}


        System.out.println("mode id : " + mode);

        if (session == null) {
            url = "/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

        menuName = request.getParameter("menuName");
        detail = request.getParameter("detail");
        try {
            mode = Integer.parseInt(request.getParameter("mode"));
            orderFlg = Integer.parseInt(request.getParameter("orderFlg"));
            typeID = Integer.parseInt(request.getParameter("typeID"));
            price = Integer.parseInt(request.getParameter("price"));
            priority = Integer.parseInt(request.getParameter("priority"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mode == INSERT) {
            System.out.println("Menu Ope Insert");

            Menu m = new Menu();
            m.setMenuName(menuName);
            m.setDetail(detail);
            m.setOrderFlg(orderFlg);
            m.setTypeID(typeID);
            m.setPrice(price);
            m.setPriority(priority);

            try {
                Menu.updateMenu(m, mode);
                request.setAttribute("typeID", typeID);
                url = "MenuMaintenanceSvl";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);

            } catch (IdealException e) {
                e.printStackTrace();
                IdealException ie = new IdealException(
                        IdealException.ERR_NO_DB_EXCEPTION);
                msg = ie.getMsg();
                System.out.println(msg);
                request.setAttribute("msg", msg);
                url = "/menuInsert.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }

        } else if (mode == UPDATE) {
            System.out.println("Menu Ope Update");

            Menu m = new Menu();
            m.setMenuName(menuName);
            m.setDetail(detail);
            m.setOrderFlg(orderFlg);
            m.setTypeID(typeID);
            m.setMenuID(menuId);
            m.setPrice(price);
            m.setPriority(priority);

            System.out.println("menuName " + menuName);
            System.out.println("detail " + detail);
            System.out.println("orderFlg " + orderFlg);
            System.out.println("typeID " + typeID);
            System.out.println("price " + price);
            System.out.println("priority " + priority);
            System.out.println("update set ok");
            try {
                Menu.updateMenu(m, mode);
                request.setAttribute("msg", "メニュ－変更成功");
                request.setAttribute("menuID", menuId);
                url = "MenuUpdateSvl";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);


            } catch (IdealException e) {
                e.printStackTrace();
                IdealException ie = new IdealException(
                        IdealException.ERR_NO_DB_EXCEPTION);
                msg = ie.getMsg();
                System.out.println(msg);
                request.setAttribute("msg", msg);
                url = "/menuUpdate.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }

        } else if (mode == DELETE) {
            System.out.println("Menu Ope Delete");

            menuId = Integer.parseInt(request.getParameter("menuID"));
            Menu m = new Menu();
                        m.setMenuName(menuName);
            m.setDetail(detail);
            m.setOrderFlg(orderFlg);
            m.setTypeID(typeID);
            m.setMenuID(menuId);
            m.setPrice(price);
            m.setPriority(priority);

            try {
                Menu.updateMenu(m, mode);
                request.setAttribute("typeID", typeID);
                url = "MenuMaintenanceSvl";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);

            } catch (IdealException e) {
                e.printStackTrace();
                IdealException ie = new IdealException(
                        IdealException.ERR_NO_DB_EXCEPTION);
                msg = ie.getMsg();
                System.out.println(msg);
                request.setAttribute("msg", msg);
                url = "/menuDelete.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);

            }

        }


    }

}
