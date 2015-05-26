<%@page import="com.tyut.book.model.Pagination"%>
<%@page import="com.tyut.book.model.LoanStatusEnum"%>
<%@page import="com.tyut.book.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.tyut.book.Constants"%>
<%@page import="java.util.List"%>
<%@page import="com.tyut.book.model.Category"%>
<%@page import="com.tyut.book.model.Book"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>我的图书</title>
    <link rel="stylesheet" href="${mt:getStaticUrl() }/css/my_book.css" />
  </head>
  <body>

    <jsp:include page="common/header.jsp"></jsp:include>

    <div class = "content_wrapper">

      <jsp:include page="common/top_bar.jsp" />

      <div class="left_menu">
        <ul>
          <li id="all" onclick="goStatus(this)">全部</li>
          <li id="loaned" onclick="goStatus(this)">已借出</li>
          <li id="not_loaned" onclick="goStatus(this)">未借出</li>
          <li id="loaning" onclick="goStatus(this)">借阅处理中</li>
        </ul>
        <input type="hidden" name="loanStatus" value="<%= request.getAttribute("loanStatus") %>" />
      </div>
      <script type="text/javascript">
          function goStatus(obj) {
              $('input[type=hidden][name=currentPage]').val(1);
              $('input[type=hidden][name=loanStatus]').val($(obj).attr('id'));
              fillTable();
          }
      </script>

      <div class="table_wrapper">
        <input type="hidden" name="action" value="${mt:getFullPath('') }/book/my_book" />
        <ul>
          <li>封面</li>
          <li>书名</li>
          <li>作者</li>
          <li>上架时间</li>
          <li>当前持有者</li>
          <li>操作</li>
        </ul>

        <% List<Book> bookList = (List<Book>) request.getAttribute("bookList"); %>
        <% for (Book book : bookList) {                                        %>
        <ul onclick="location.href='${mt:getFullPath('') }/book/<%= book.getId() %>'">
          <li><img alt="封面" src="<%= StringUtil.ByteArrayToImgBase4String(book.getCover()) %>" /></li>
          <li><%= book.getName() %></li>
          <li><%= book.getAuthor() %></li>
          <li><%= book.getCreatedTime() %></li>
          <li><%= book.getCurrentOwnerName() %></li>
          <li>
            <%  if (book.getLoanStatus() == LoanStatusEnum.not_loaned) {           %>
                    <a href="#">更新</a>
                    <a href="#">下架</a>
            <%  }                                                              %>
          </li>
        </ul>
        <% }                                                                    %>

      </div>

      <jsp:include page="common/pagination.jsp" />

    </div>

  <script src="${mt:getStaticUrl() }/js/my_book.js"></script>
  </body>
</html>