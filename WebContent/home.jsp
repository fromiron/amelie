<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="amelie.*" %>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Ristorante Amelie</title>

    <!-- TODO 全ページにコピペする  -->
    <meta name="viewport" content="width=device-width, user-scalable=1"/>
    <link rel="stylesheet" href="public/css/reset.css"/>
    <link rel="stylesheet" href="public/css/stylesheet.css"/>
    <link rel="stylesheet" href="public/css/slider.css"/>
    <link
            href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP:wght@300;400;500;600;700&display=swap"
            rel="stylesheet"/>
    <link rel="stylesheet"
          href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
          crossorigin="anonymous"/>
    <script src="./public/js/nav.js" defer="defer"></script>
    <script src="./public/js/bg.js" defer="defer"></script>
    <script src="./public/js/msgBox.js" defer="defer"></script>
    <script src="./public/js/slider.js" defer="defer"></script>

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
        sessionId = (String) session.getAttribute("usrId");
        sessionName = (String) session.getAttribute("usrName");
        sessionAdmName = (String) session.getAttribute("admName");
    } catch (Exception e) {
    }
%>
<!-- session ゲット -->


<nav class="nav"><a class="nav__logo" href="./home.jsp">
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
            <a href="contact.jsp">contact</a></div>
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
    String title = "GRAND OPEN!";
    String subtitle = "イタリアンレストランAMELIEが6月18日をもちまして札幌にオープンします。下のメニューボタンをCLICKして本場の味を再現したメニューをご覧ください。";

    ArrayList<Menu> other = Menu.getMenu(900);
    for (int i = 0; i < other.size(); i++) {
        Menu m = other.get(i);
        if (m.getPriority() == 2) {
            title = m.getDetail();
            subtitle = m.getMenuName() + m.getPrice() + "円";
        }
    }

%>


<div class="home_contents">
    <div class="home__msg_box">
        <div class="home__msg_box__top_wrapper">
            <div class="home_msg_box__top title"><%=title %>
            </div>
            <div class="home_msg_box__top sub_title"><%=subtitle %>
            </div>
        </div>

        <div class="home_msg_box__bottom">

            <div class="home_msg_box__bottom__btn__wrapper">
                <a href="showMenu.jsp">
                    <div class="home_msg_box__btn__btn">GO
                        TO MENU
                    </div>
                </a>
                <div class="home_msg_box__btn__btn closeBtn">CLOSE</div>
            </div>

            <div class="home_msg_box__bottom__img__wrapper">
                <div class="home_msg_box__bottom__img"></div>
            </div>
        </div>

    </div>


    <!-- slider -->
    <div id="slider_wrap" class="slider disable">

        <div class="slidercontainer">
            <div class="showSlide fade">
                <img src="public/image/course.jpg"/>
                <div class="content">COURSE</div>
            </div>
            <div class="showSlide fade">
                <img src="public/image/seafood.jpg"/>
                <div class="content">SEAFOOD</div>
            </div>

            <div class="showSlide fade">
                <img src="public/image/pasta.jpg"/>
                <div class="content">PASTA</div>
            </div>
            <div class="showSlide fade">
                <img src="public/image/wine.png"/>
                <div class="content">WINE</div>
            </div>
            <div class="showSlide fade">
                <img src="public/image/dessert.jpg"/>
                <div class="content">DESSERT</div>
            </div>
            <!-- arrows -->
            <div class="sliderBtn">
                <a class="left" onclick="nextSlide(-1)">❮</a>
                <a class="right" onclick="nextSlide(1)">❯</a></div>
        </div>

    </div>
</div>
<!-- slider -->


<!-- TODO 全ページにコピペする  -->
<div class="home__bg_cover"></div>
<div id="home__bg"></div>
<!-- TODO 全ページにコピペする  -->

</body>

</html>