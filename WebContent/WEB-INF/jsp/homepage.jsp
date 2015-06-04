<%@page import="com.tyut.book.util.StringUtil"%>
<%@page import="com.tyut.book.model.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.tyut.book.Constants"%>
<%@page import="com.tyut.book.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图书分享平台</title>
    <link rel="stylesheet" href="${mt:getStaticUrl() }/css/homepage.css" />
  </head>
  <body>

    <jsp:include page="common/header.jsp"></jsp:include>

    <jsp:include page="common/login.jsp"></jsp:include>

    <div class = "content_wrapper">

      <jsp:include page="common/top_bar.jsp"></jsp:include>

      <div class="table_wrapper">
        <input type="hidden" name="action" value="${mt:getFullPath('') }/user/homepage" />
        <% List<Book> bookList = (List<Book>)request.getAttribute("bookList"); %>
        <% int size = bookList.size(); %>
        <% int rowCount = (size - 1) / 4 + 1; %>
        <% for (int i = 0; i < rowCount; i++) { %>

               <ul>

        <%     for (int j = 0; j < 4; j++) { %>
        <%         int index = i * 4 + j;      %>
        <%         if (index < size) { %>

        <%             Book book = bookList.get(index); %>

                       <li>
                         <img title="<%= book.getDescription()%>" onclick="location.href='${mt:getFullPath('') }/book/<%= book.getId() %>'" alt="封面" src="<%= StringUtil.ByteArrayToImgBase4String(book.getCover()) %>" />
                         <br>
                         <font>书名: <%= book.getName() %></font>
                         <br>
                         <font>押金: <%= book.getDeposit() %></font>
                       </li>

        <%         } %>

        <%     } %>

               </ul>

        <% }  %>
      </div>

    <jsp:include page="common/pagination.jsp" />

    </div>


    

    <script type="text/javascript">
      if (location.hash == "#login") {
          login();
      }
    </script>

  </body>
</html>