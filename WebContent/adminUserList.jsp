<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
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
<link
 href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP:wght@300;400;500;600;700&display=swap"
 rel="stylesheet" />
<link rel="stylesheet"
 href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
 integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
 crossorigin="anonymous" />
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
 <a href="contact.jsp">contact</a>  </div>
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

     ArrayList<User> ul = User.getUserList();
 %>


<%= ul.size() %>

 <!-- menuMintenance -->
 <div class="menuMaintenance_contents">
  <div class="menuMaintenance_wrapper">
   <div class="menu_show_table">
    <table style="border-collapse: collapse;" border="1">
     <tr>
      <td colspan="8" class="menu_show_table__title top"><h2>お客様リスト</h2></td>
      <tr class="menu_show_table__title">


       <td>index</td>
       <td>名前</td>
       <td>ID</td>
       <td>pass</td>
       <td>住所</td>
       <td>電話</td>
       <td>メール</td>
       <td>その他</td>
       <td></td>

      </tr>



      <%
          for (int i = 0; i < ul.size(); i++) {
              User u = ul.get(i);
      %>
      <tr class="menu_show_table__row">
       <td class="menu_show_table__cell"><%=u.getUsrIndex()%></td>
       <td class="menu_show_table__cell"><%=u.getUsrName()%></td>
       <td class="menu_show_table__cell"><%=u.getUsrId()%></td>
       <td class="menu_show_table__cell"><%=u.getPassword()%></td>
       <td class="menu_show_table__cell"><%=u.getAddress()%></td>
       <td class="menu_show_table__cell"><%=u.getPhone()%></td>
       <td class="menu_show_table__cell"><%=u.getMail()%></td>
       <td class="menu_show_table__cell"><%=u.getExp()%></td>

          </tr>
      <%
          }
      %>
      <tr>
       <td colspan="8">

        <button class="big_button" onclick="history.back()">戻る</button>
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