<%@page import="com.tyut.book.util.PropertyUtil"%>
<%@page import="com.tyut.book.util.PathUtil"%>
<%@page import="com.tyut.book.Constants"%>
<%@page import="com.tyut.book.model.LoanStatusEnum"%>
<%@page import="com.tyut.book.model.Book"%>
<%@page import="com.tyut.book.model.User"%>
<%@page import="com.tyut.book.util.StringUtil"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>${book.name }</title>
  <link rel="stylesheet" href="${mt:getStaticUrl() }/css/book_detail.css" />
</head>
  <body>

    <jsp:include page="common/header.jsp"></jsp:include>

    <div class="content_wrapper">
      <% Book book = (Book)request.getAttribute("book"); %>
      <div class="book_info_wrapper">

        <input type="hidden" name="bookId" value="<%= book.getId() %>" />

        <ul class="basic_info">
          <li class="title">书名:</li>
          <li><%= book.getName() %></li>
        </ul>
        <ul class="basic_info">
          <li class="title">作者:</li>
          <li><%= book.getAuthor() %></li>
        </ul>
        <ul class="basic_info">
          <li class="title">上传者:</li>
          <li><a href="${mt:getFullPath('') }/user/show_user/<%= book.getOwnerId() %>"><%= book.getOwnerName() %></a></li>
        </ul>
        <ul class="basic_info">
          <li class="title">所需押金：</li>
          <li><%= book.getDeposit() %></li>
        </ul>
        <ul class="brief_info">
          <li class="title">简介:</li>
          <li><%= book.getDescription() %></li>
        </ul>

        <img class="cover" alt="封面" src="<%= StringUtil.ByteArrayToImgBase4String(book.getCover()) %>" />
      </div>

      <% Object user = session.getAttribute(Constants.USER);  %>
      <% if (user != null && ((User)user).getId() != book.getOwnerId()) { %>

        <div class="collect">
          <img src="<%= request.getAttribute("isBookCollected").toString().equals("true") ? PropertyUtil.getStaticUrl() + "/images/collected.png" : PropertyUtil.getStaticUrl() + "/images/uncollected.png" %>" onclick="collect(this)" alt="<%= request.getAttribute("isBookCollected").toString().equals("true") ? "已收藏" : "收藏"  %>" />
          <br>
          <font><%= request.getAttribute("isBookCollected").toString().equals("true") ? "已收藏" : "收藏"  %></font>
        </div>
        <input class="borrow" type="button" onclick="sendBorrowRequest(this)" value="<%= book.getLoanStatus() == LoanStatusEnum.not_loaned ? "请求借阅" : "借阅中" %>" />
      
      <% } %>

    </div>

    <script src="${mt:getStaticUrl() }/js/book_detail.js"></script>

  </body>
</html>