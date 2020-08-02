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
<link rel="stylesheet" href="./public/css/bg.css" />
<link
 href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP:wght@300;400;500;600;700&display=swap"
 rel="stylesheet" />
<link rel="stylesheet"
 href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
 integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
 crossorigin="anonymous" />
<link
 href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@400;700&display=swap"
 rel="stylesheet" />
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



 <div class="menu_contents">

  <div class="menu_list_wrapper">
   <div class="menu_list">
    <div class="menu_list__content">
     <div class="menu_list__content__innerWrapper">
      <div class="menu_list__content__img"

       style="background-image: url('public/image/course.jpg');"><div class="menu_list__content__text__innerText">コース料理</div></div>

     </div>
     <div class="menu_list__content__show_menu">
      <%
          ArrayList<Course> course = Course.getOneCourseList();

          for (int i = 0; i < course.size(); i++) {
              Course c = course.get(i);
              if (c.getPriority() == 2) {
      %>
      <div class="menu_list__menu_title priority">
       <i class="fas fa-award"></i><%=c.getCourseName()%><br /> <span
        class="menu_list__menu_detail"><%=c.getDetail()%></span><br />￥<%=c.getPrice()%></div>
                    <%
            ArrayList<Menu> cmenu = Menu.getCourseInMenu(c.getCourseId());

            for(int j = 0; j < cmenu.size(); j++){
                Menu cm = cmenu.get(j);
                %>
                <div class="menu_list__menu_detail course_in_menu">・<%=cm.getMenuName() %></div>
                <%

            }

          } else {
      %>
      <div class="menu_list__menu_title"><%=c.getCourseName()%><br />
       <span class="menu_list__menu_detail"><%=c.getDetail()%></span><br />￥<%=c.getPrice()%></div>
            <%
            ArrayList<Menu> cmenu = Menu.getCourseInMenu(c.getCourseId());

            for(int j = 0; j < cmenu.size(); j++){
                Menu cm = cmenu.get(j);
                %>
                <div class="menu_list__menu_detail course_in_menu">・<%=cm.getMenuName() %></div>
                <%

            }
            %>


      <%
          }
          }
      %>
     </div>
    </div>



    <div class="menu_list__content">
     <div class="menu_list__content__innerWrapper">
      <div class="menu_list__content__img"
       style="background-image: url('public/image/zensai.jpg');">       <div class="menu_list__content__text__innerText">前菜</div>
       </div>

     </div>
     <div class="menu_list__content__show_menu">

      <%
          ArrayList<Menu> zensai = Menu.getMenu(200);
          if (zensai.size() <= 0) {
      %>
      <div class="menu_list__menu_empty">メニュー準備中です。</div>
      <%
          } else {
              for (int i = 0; i < zensai.size(); i++) {
                  Menu m = zensai.get(i);
                  if (m.getPriority() == 2) {
      %>
      <div class="menu_list__menu_title priority">
       <i class="fas fa-award"></i><%=m.getMenuName()%><br /> <span
        class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>
      <%
          } else {
      %>
      <div class="menu_list__menu_title"><%=m.getMenuName()%><br />
       <span class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>

      <%
          }
              }
          }
      %>
     </div>
    </div>


    <div class="menu_list__content">
     <div class="menu_list__content__innerWrapper">
      <div class="menu_list__content__img"
       style="background-image: url('public/image/soup.jpg');">       <div class="menu_list__content__text__innerText">スープ</div>
       </div>

     </div>
     <div class="menu_list__content__show_menu">

      <%
          ArrayList<Menu> soup = Menu.getMenu(210);
          if (soup.size() <= 0) {
      %>
      <div class="menu_list__menu_empty">メニュー準備中です。</div>
      <%
          } else {

              for (int i = 0; i < soup.size(); i++) {
                  Menu m = soup.get(i);
                  if (m.getPriority() == 2) {
      %>
      <div class="menu_list__menu_title priority">
       <i class="fas fa-award"></i><%=m.getMenuName()%><br /> <span
        class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>
      <%
          } else {
      %>
      <div class="menu_list__menu_title"><%=m.getMenuName()%><br />
       <span class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>

      <%
          }
              }
          }
      %>
     </div>
    </div>




    <div class="menu_list__content">
     <div class="menu_list__content__innerWrapper">
      <div class="menu_list__content__img"
       style="background-image: url('public/image/pasta.jpg');">       <div class="menu_list__content__text__innerText">パスタ</div>
       </div>

     </div>
     <div class="menu_list__content__show_menu">

      <%
          ArrayList<Menu> pasta = Menu.getMenu(220);
          if (pasta.size() <= 0) {
      %>
      <div class="menu_list__menu_empty">メニュー準備中です。</div>
      <%
          } else {

              for (int i = 0; i < pasta.size(); i++) {
                  Menu m = pasta.get(i);
                  if (m.getPriority() == 2) {
      %>
      <div class="menu_list__menu_title priority">
       <i class="fas fa-award"></i><%=m.getMenuName()%><br /> <span
        class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>
      <%
          } else {
      %>
      <div class="menu_list__menu_title"><%=m.getMenuName()%><br />
       <span class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>

      <%
          }
              }
          }
      %>
     </div>
    </div>



    <div class="menu_list__content">
     <div class="menu_list__content__innerWrapper">
      <div class="menu_list__content__img"
       style="background-image: url('public/image/meat.jpg');"><div class="menu_list__content__text__innerText">肉料理</div>
       </div>

     </div>
     <div class="menu_list__content__show_menu">
      <%
          ArrayList<Menu> meat = Menu.getMenu(300);
          if (meat.size() <= 0) {
      %>
      <div class="menu_list__menu_empty">メニュー準備中です。</div>
      <%
          } else {

              for (int i = 0; i < meat.size(); i++) {
                  Menu m = meat.get(i);
                  if (m.getPriority() == 2) {
      %>
      <div class="menu_list__menu_title priority">
       <i class="fas fa-award"></i><%=m.getMenuName()%><br /> <span
        class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>
      <%
          } else {
      %>
      <div class="menu_list__menu_title"><%=m.getMenuName()%><br />
       <span class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>

      <%
          }
              }
          }
      %>
     </div>
    </div>


    <div class="menu_list__content">
     <div class="menu_list__content__innerWrapper">
      <div class="menu_list__content__img"
       style="background-image: url('public/image/seafood.jpg');">       <div class="menu_list__content__text__innerText">魚料理</div>
       </div>

     </div>
     <div class="menu_list__content__show_menu">
      <%
          ArrayList<Menu> seafood = Menu.getMenu(310);
          if (seafood.size() <= 0) {
      %>
      <div class="menu_list__menu_empty">メニュー準備中です。</div>
      <%
          } else {

              for (int i = 0; i < seafood.size(); i++) {
                  Menu m = seafood.get(i);
                  if (m.getPriority() == 2) {
      %>
      <div class="menu_list__menu_title priority">
       <i class="fas fa-award"></i><%=m.getMenuName()%><br /> <span
        class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>
      <%
          } else {
      %>
      <div class="menu_list__menu_title"><%=m.getMenuName()%><br />
       <span class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>

      <%
          }
              }
          }
      %>
     </div>
    </div>



   </div>






   <!-- list 2 -->

   <div class="menu_list">

    <div class="menu_list__content">
     <div class="menu_list__content__innerWrapper">
      <div class="menu_list__content__img"
       style="background-image: url('public/image/dessert.jpg');">       <div class="menu_list__content__text__innerText">デザート</div>
       </div>

     </div>
     <div class="menu_list__content__show_menu">
      <%
          ArrayList<Menu> dessert = Menu.getMenu(400);
          if (dessert.size() <= 0) {
      %>
      <div class="menu_list__menu_empty">メニュー準備中です。</div>
      <%
          } else {

              for (int i = 0; i < dessert.size(); i++) {
                  Menu m = dessert.get(i);
                  if (m.getPriority() == 2) {
      %>
      <div class="menu_list__menu_title priority">
       <i class="fas fa-award"></i><%=m.getMenuName()%><br /> <span
        class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>
      <%
          } else {
      %>
      <div class="menu_list__menu_title"><%=m.getMenuName()%><br />
       <span class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>

      <%
          }
              }
          }
      %>
     </div>
    </div>



    <div class="menu_list__content">
     <div class="menu_list__content__innerWrapper">
      <div class="menu_list__content__img"
       style="background-image: url('public/image/wine.png');">       <div class="menu_list__content__text__innerText">ワイン</div>
       </div>

     </div>
     <div class="menu_list__content__show_menu">

      <%
          ArrayList<Menu> wine = Menu.getMenu(700);
          if (wine.size() <= 0) {
      %>
      <div class="menu_list__menu_empty">メニュー準備中です。</div>
      <%
          } else {

              for (int i = 0; i < wine.size(); i++) {
                  Menu m = wine.get(i);
                  if (m.getPriority() == 2) {
      %>
      <div class="menu_list__menu_title priority">
       <i class="fas fa-award"></i><%=m.getMenuName()%><br /> <span
        class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>
      <%
          } else {
      %>
      <div class="menu_list__menu_title"><%=m.getMenuName()%><br />
       <span class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>

      <%
          }
              }
          }
      %>
     </div>
    </div>



    <div class="menu_list__content">
     <div class="menu_list__content__innerWrapper">
      <div class="menu_list__content__img"
       style="background-image: url('public/image/alcohol.jpg');">       <div class="menu_list__content__text__innerText">アルコール類</div>
       </div>

     </div>
     <div class="menu_list__content__show_menu">

      <%
          ArrayList<Menu> alcohol = Menu.getMenu(710);
          if (alcohol.size() <= 0) {
      %>
      <div class="menu_list__menu_empty">メニュー準備中です。</div>
      <%
          } else {

              for (int i = 0; i < alcohol.size(); i++) {
                  Menu m = alcohol.get(i);
                  if (m.getPriority() == 2) {
      %>
      <div class="menu_list__menu_title priority">
       <i class="fas fa-award"></i><%=m.getMenuName()%><br /> <span
        class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>
      <%
          } else {
      %>
      <div class="menu_list__menu_title"><%=m.getMenuName()%><br />
       <span class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>

      <%
          }
              }
          }
      %>
     </div>
    </div>


    <div class="menu_list__content">
     <div class="menu_list__content__innerWrapper">
      <div class="menu_list__content__img"
       style="background-image: url('public/image/softdrink.jpg');">       <div class="menu_list__content__text__innerText">ソフトドリンク類</div>
       </div>

     </div>
     <div class="menu_list__content__show_menu">
      <%
          ArrayList<Menu> softdrink = Menu.getMenu(720);
          if (softdrink.size() <= 0) {
      %>
      <div class="menu_list__menu_empty">メニュー準備中です。</div>
      <%
          } else {

              for (int i = 0; i < softdrink.size(); i++) {
                  Menu m = softdrink.get(i);
                  if (m.getPriority() == 2) {
      %>
      <div class="menu_list__menu_title priority">
       <i class="fas fa-award"></i><%=m.getMenuName()%><br /> <span
        class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>
      <%
          } else {
      %>
      <div class="menu_list__menu_title"><%=m.getMenuName()%><br />
       <span class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>

      <%
          }
              }
          }
      %>
     </div>
    </div>


    <div class="menu_list__content">
     <div class="menu_list__content__innerWrapper">
      <div class="menu_list__content__img"
       style="background-image: url('public/image/other.jpg');">       <div class="menu_list__content__text__innerText">その他</div>
       </div>

     </div>

     <div class="menu_list__content__show_menu">
      <%
          ArrayList<Menu> other = Menu.getMenu(900);
          if (other.size() <= 0) {
      %>
      <div class="menu_list__menu_empty">メニュー準備中です。</div>
      <%
          } else {

              for (int i = 0; i < other.size(); i++) {
                  Menu m = other.get(i);
                  if (m.getPriority() == 2) {
      %>
      <div class="menu_list__menu_title priority">
       <i class="fas fa-award"></i><%=m.getMenuName()%><br /> <span
        class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>
      <%
          } else {
      %>
      <div class="menu_list__menu_title"><%=m.getMenuName()%><br />
       <span class="menu_list__menu_detail"><%=m.getDetail()%></span><br />￥<%=m.getPrice()%></div>

      <%
          }
              }
          }
      %>
     </div>
    </div>




   </div>

  </div>
 </div>










 <!-- TODO 全ページにコピペする  -->
 <div class="home__bg_cover"></div>
 <div id="home__bg"></div>
 <!-- TODO 全ページにコピペする  -->

</body>

</html>