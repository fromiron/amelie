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

<script type="text/javascript" defer="defer">
	window.onload = function() {
		const
		typeId = document.querySelector(".dbMenuTypeId").innerHTML.trim();
		const
		typeName = document.querySelector(".dbMenuTypeName").innerHTML.trim();
		let
		menuPriority = document.querySelector(".dbMenuPriority").innerHTML
				.trim();
		menuPriority = menuPriority - 1;

		const
		selectOption = document.querySelector("#selectOption");
		const
		optionList = selectOption.children;
		for (var i = 0; i < optionList.length; i++) {

			if (optionList[i].value != typeId) {
				selectOption.options[i].disabled = true;
			} else {
				selectOption.options[i].selected = true;
			}
		}

		const
		selectPriority = document.querySelector("#priority");

		if (menuPriority == 0) {
			selectPriority.options[0].selected = true;
			selectPriority.options[1].disabled = true;
		} else {
			selectPriority.options[1].selected = true;
			selectPriority.options[0].disabled = true;
		}

	}
</script>


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
     int menuID = 1;
     int typeID = 200;

     try {

         String menuIdStr = (String) request.getParameter("menuID");
         String typeIdStr = (String) request.getParameter("typeID");
         menuID = Integer.parseInt(menuIdStr);
         typeID = Integer.parseInt(typeIdStr);
     } catch (Exception e) {
     }

     Menu menu = Menu.getOneMenu(menuID, typeID);
 %>


 <div class="dbMenuTypeId" style="display: none"><%=menu.getTypeID()%></div>
 <div class="dbMenuTypeName" style="display: none"><%=menu.getTypeName()%></div>
 <div class="dbMenuPriority" style="display: none"><%=menu.getPriority()%></div>
 <div class="dbMenuMenuId" style="display: none"><%=menu.getMenuID()%></div>




 <div class="menu_panel_container">

  <div class="menu_panel">
   <form method="post" action="MenuOperationSvl">
    <table class="readonly">
     <tr>
      <th colspan="2" class="menu_panel__title change">メニューの削除</th>
     </tr>
     <tr>
      <th class="menu_panel__sub_title">メニュー名</th>
      <td><input type="text" name="menuName" class="menu_panel__input readonly" value="<%=menu.getMenuName()%>" readonly /></td>
     </tr>
     <tr>
      <th class="menu_panel__sub_title">価格</th>
      <td><input type="number" name="price" class="menu_panel__input readonly" value="<%=menu.getPrice()%>" readonly /></td>
     </tr>
     <tr>
      <th class="menu_panel__sub_title">オーダー可</th>
      <td>
       <%
           if (menu.getOrderFlg() == 1) {
       %> <input type="radio" name="orderFlg" value="1" checked="checked" onclick="return(false);">可</input> <input type="radio" name="orderFlg" value="0" onclick="return(false);">不可</input> <%
     } else {
 %> <input type="radio" name="orderFlg" value="1" onclick="return(false);">可</input> <input type="radio" name="orderFlg" value="0" checked="checked" onclick="return(false);">不可</input> <%
     }
 %>



      </td>
     </tr>
     <tr>
      <th class="menu_panel__sub_title">分類</th>
      <td><select id="selectOption" name="typeID" readonly>
        <option value="">選択</option>
        <%
            ArrayList<Menu> category = Menu.getMenuCategory();
            for (int i = 1; i < category.size(); i++) {
                Menu m = category.get(i);
        %>
        <option value=<%=m.getTypeID()%>><%=m.getTypeName()%></option>
        <%
            }
        %>
      </select></td>
     </tr>

     <th class="menu_panel__sub_title">優先順位</th>
     <td><select name="priority" id="priority" readonly>
       <option value="1">普通</option>
       <option value="2">おすすめ</option>

     </select></td>
     </tr>




     <tr>
      <th class="menu_panel__sub_title">コメント</th>
      <td class="menu_panel__input comment readonly"><textarea name="detail" class="textAreaReadOnly" rows="10" cols="30" readonly><%=menu.getDetail()%></textarea></td>
     </tr>


     <tr>
      <td colspan="2"><input type="hidden" name="menuID" value="<%=menu.getMenuID()%>"></input> <input type="hidden" name="mode" value="13"></input> <input type="submit" value="メニューを削除"
       class="big_button" /></td>
     </tr>



     <tr>
      <td colspan="2"><a href="javascript:history.back();" class="big_button gray">一覧表示画面に戻る</a></td>
     </tr>

     <%
         String insertReturn = (String) request.getAttribute("msg");
         if (insertReturn == null) {
         }
         if (insertReturn != null) {
     %>
     <tr>
      <td colspan="2">
       <div class="menu_panel__result">
        <%
            out.print(insertReturn);
        %>
       </div>
      </td>
     </tr>
     <%
         }
     %>

    </table>
   </form>



  </div>
 </div>



 <!-- TODO 全ページにコピペする  -->
 <div class="home__bg_cover"></div>
 <div id="home__bg"></div>
 <!-- TODO 全ページにコピペする  -->

</body>

</html>