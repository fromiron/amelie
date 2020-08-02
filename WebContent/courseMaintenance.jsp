<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="amelie.*"%>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Ristorante Amelie</title>

<!-- TODO 全ページにコピペする  -->
<meta name="viewport" content="width=device-width, user-scalable=1" />
<link rel="stylesheet" href="public/css/reset.css" />
<link rel="stylesheet" href="public/css/stylesheet.css" />
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP:wght@300;400;500;600;700&display=swap" rel="stylesheet" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous" />
<script src="./public/js/nav.js" defer="defer"></script>
<script src="./public/js/bg.js" defer="defer"></script>
<!-- TODO 全ページにコピペする  -->

</head>

<body>

 <!-- 全ページにコピペする  -->
 <!-- session ゲット -->

 <%
     request.setCharacterEncoding("UTF-8");
     String sessionId = null;
     String sessionName = null;
     String sessionAdmName = null;
     try {
         sessionAdmName = (String) session.getAttribute("admName");
         sessionId = (String) session.getAttribute("usrId");
         sessionName = (String) session.getAttribute("usrName");
     } catch (Exception e) {
     }
 %>
 <!-- session ゲット -->



 <nav class="nav"> <a class="nav__logo" href="home.jsp">
  <div class="nav__logo_wrapper">
   <i class="fab fa-accusoft"></i>
   <div>Amelie</div>
  </div>
 </a>
 <div class="nav__list">
  <div class="nav_list_btn">
   <a href="./ShowMenuSvl">menu</a>
  </div>

  <%
      if (sessionId == null && sessionAdmName == null) {
  %>

  <div class="nav_list_btn">
   <a href="userLogin.jsp">login</a>
  </div>
  <div class="nav_list_btn">
   <a href="userInsert.jsp">join</a>
  </div>

  <%
      } else if (sessionId != null) {
  %>
  <div class="nav_list_btn">
   <a href="userIndex.jsp">my page</a>
  </div>
  <%
      } else if (sessionAdmName != null) {
  %>
  <div class="nav_list_btn">
   <a href="adminIndex.jsp">admin page</a>
  </div>

  <%
      }
  %>


  <div class="nav_list_btn">
   <a href="contact.jsp">contact</a>
  </div>
 </div>

 <div class="nav__admin">

  <%
      if (sessionId == null && sessionAdmName == null) {
  %>
  <a href="adminLogin.jsp"><i class="fas fa-user-cog icon_hover"></i></a>
  <%
      } else {
  %>
  <a href="./UserLogoffSvl"><span class="logout">logout</span></a>
  <%
      }
  %>
 </div>
 </nav>



 <div class="menuDropBtn">
  <i class="fas fa-chevron-down"></i>
 </div>
 <msg class="user_msg" display="hidden"></msg>
 <!-- TODO 全ページにコピペする  -->


 <%
     ArrayList<Course> course = Course.getOneCourseList();
 %>



 <!-- menuMintenance -->
 <div class="menuMaintenance_contents">
  <div class="menuMaintenance_wrapper">
   <div class="menu_show_table">
    <table style="border-collapse: collapse;" border="1">
     <tr>
      <td colspan="8" class="menu_show_table__title top"><h2>コースのマスターメンテナンス</h2></td>
     </tr>
     <tr>
      <td colspan="8" class="menu_show_table__title"></td>
     </tr>

     <tr class="menu_show_table__title">

      <td>ID</td>
      <td>商品名</td>
      <td>価格</td>
      <td>説明</td>
      <td>注文</td>
      <td>優先</td>

      <td></td>

     </tr>

     <%
         for (int i = 0; i < course.size(); i++) {
             Course c = course.get(i);
     %>


     <tr class="menu_show_table__row">
      <td class="menu_show_table__cell"><%=c.getCourseId()%></td>
      <td class="menu_show_table__cell"><%=c.getCourseName()%></td>
      <td class="menu_show_table__cell"><%=c.getDetail()%></td>
      <td class="menu_show_table__cell"><%=c.getPrice()%></td>
      <td class="menu_show_table__cell">
       <%
           if (c.getOrderFlg() == 1) {
       %>可<%
           } else {
       %>不可<%
           }
       %>
      </td>
      <td class="menu_show_table__cell">
       <%
           if (c.getPriority() == 1) {
       %>普通<%
           } else {
       %>おすすめ<%
           }
       %>
      </td>


      <td>
       <form method="post" action="MenuUpdateSvl">
        <input type="hidden" name="menuID" value="<%=c.getCourseId()%>"></input> <input type="hidden" name="typeID" value="100" /> <input type="submit" value="変更" class="small_button" />
       </form>

       <form method="post" action="MenuDeleteSvl">
        <input type="hidden" name="menuID" value="<%=c.getCourseId()%>"></input> <input type="hidden" name="courseID" value="<%=c.getCourseId()%>"></input> <input type="hidden" name="typeID"
         value="100" /> <input type="submit" value="削除" class="small_button" name="delete" />
       </form>
      </td>
     </tr>

     <tr>
      <td colspan="8" style="background-color: rgba(255,255,255,0.3)" >
       <%
           ArrayList<Menu> cmenu = Menu.getCourseInMenu(c.getCourseId());

               for (int j = 0; j < cmenu.size(); j++) {
                   Menu cm = cmenu.get(j);
       %>
       <div class="menu_list__menu_detail course_in_menu">
        ・<%=cm.getMenuName()%></div> <%
     }
 %>

      </td>
     </tr>

     <%
         }
     %>
     <tr>
      <td colspan="8">
       <form method="post" action="MenuInsertSvl">
        <input type="hidden" name="typeID" value="100" /> <input type="submit" value="新しいコース" class="big_button" />
       </form>
      </td>
     </tr>
     <tr>
      <td colspan="8">
       <form method="post" action="MenuMaintenanceSvl">
        <input type="hidden" name="typeID" value="200" /> <input type="submit" class="big_button" value="メニューメンテナンス" />
       </form>
      </td>
     </tr>



    </table>
   </div>









  </div>
 </div>





 <!-- TODO 全ページにコピペする  -->
 <div class="home__bg_cover"></div>
 <div id="home__bg"></div>
 <!-- TODO 全ページにコピペする  -->

</body>

</html>