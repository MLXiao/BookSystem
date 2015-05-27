<%@page import="com.tyut.book.model.StatusEnum"%>
<%@page import="com.tyut.book.util.StringUtil"%>
<%@page import="com.tyut.book.model.BorrowHistory"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>我的借阅</title>
  </head>
  <body>

  <jsp:include page="common/header.jsp"></jsp:include>
  <div class = "content_wrapper">

    <div class="leftMenu">
      <ul>
        <li id="succeed" onclick="goStatus(this)">借阅中</li>
        <li id="borrowing" onclick="goStatus(this)">请求中</li>
        <li id="failed" onclick="goStatus(this)">借阅失败</li>
        <li id="returning" onclick="goStatus(this)">归还中</li>
        <li id="returned" onclick="goStatus(this)">已归还</li>
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
      <input type="hidden" name="action" value="${mt:getFullPath('') }/user/my_borrow" />
      <ul>
        <li>封面</li>
        <li>书名</li>
        <li>作者</li>
        <li>借阅时间</li>
        <li>操作</li>
      </ul>

      <% List<BorrowHistory> histories = (List<BorrowHistory>)request.getAttribute("histories"); %>
      <% for (BorrowHistory history : histories) { %>
        <ul>
          <li><img alt="封面" src="<%= StringUtil.ByteArrayToImgBase4String(history.getCover()) %>" /></li>
          <li><%= history.getBookName() %></li>
          <li><%= history.getAuthor() %></li>
          <li><%= history.getCreatedTime() %></li>
          <li>
            <% if (history.getStatus() == StatusEnum.succeed) { %>
            <%= history.getBookId() %>
            <button onclick="location.href='${mt:getFullPath('') }/user/return_book?bookId=<%= history.getBookId() %>'">归还图书</button>
            <% } %>
          </li>
        </ul>
      <% } %>
      <ul>
      </ul>

    </div>

    <jsp:include page="common/pagination.jsp" />

  </div>

  </body>
</html>