<%@page import="com.tyut.book.model.StatusEnum"%>
<%@page import="com.tyut.book.util.StringUtil"%>
<%@page import="com.tyut.book.model.BorrowHistory"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<%@page import="java.text.SimpleDateFormat"%>
<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>我的借阅</title>
    <link rel="stylesheet" href="${mt:getStaticUrl() }/css/my_borrow.css" />
  </head>
  <body>

  <jsp:include page="common/header.jsp"></jsp:include>
  <div class = "content_wrapper">

    <div class="left_menu">
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
          $('.left_menu li').each(function() {
              if ($(this).attr('id') == $('input[type=hidden][name=loanStatus]').val()) {
                  $(this).css('background-color','rgb(133, 195, 130)');
                  $(this).css('color','#FFF');
              }
          });
    </script>

    <div class="table_wrapper">
      <input type="hidden" name="action" value="${mt:getFullPath('') }/user/my_borrow" />
      <ul class="table_title">
        <li class="cover">封面</li>
        <li class="name">书名</li>
        <li class="author">作者</li>
        <li class="time">借阅时间</li>
        <li class="operation">操作</li>
      </ul>

      <% List<BorrowHistory> histories = (List<BorrowHistory>)request.getAttribute("histories"); %>
      <% for (BorrowHistory history : histories) { %>
        <ul class="table_item">
          <li class="cover"><img alt="封面" src="<%= StringUtil.ByteArrayToImgBase4String(history.getCover()) %>" /></li>
          <li class="name"><a href="${mt:getFullPath('') }/book/<%= history.getBookId() %>"><%= history.getBookName() %></a></li>
          <li class="author"><%= history.getAuthor() %></li>
          <li class="time"><%= sdf.format(history.getCreatedTime()) %></li>
          <li class="operation">
            <% if (history.getStatus() == StatusEnum.succeed) { %>
            <a href="${mt:getFullPath('') }/user/return_book?bookId=<%= history.getBookId() %>">归还图书</a>
            <% } %>
          </li>
        </ul>
      <% } %>

    </div>

    <jsp:include page="common/pagination.jsp" />

  </div>

  </body>
</html>