<%@page import="com.tyut.book.util.StringUtil"%>
<%@page import="com.tyut.book.model.BookCollection"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>我的收藏</title>
    <link rel="stylesheet" href="${mt:getStaticUrl() }/css/my_collect.css" />
  </head>
<body>
  <jsp:include page="common/header.jsp"></jsp:include>
  <div class = "content_wrapper">

    <div class = "table_wrapper">
        <input type="hidden" name="action" value="${mt:getFullPath('') }/user/my_collect" />
        <ul class="table_title">
          <li class="cover">封面</li>
          <li class="name">书名</li>
          <li class="time">收藏时间</li>
          <li class="operation">操作</li>
        </ul>

        <% List<BookCollection> collections = (List<BookCollection>)request.getAttribute("collections"); %>

        <% for (BookCollection collection : collections) { %>
          <ul class="table_item">
            <li class="cover"><img alt="封面" src="<%= StringUtil.ByteArrayToImgBase4String(collection.getCover()) %>" /></li>
            <li class="name"><a href="${mt:getFullPath('') }/book/<%= collection.getBookId() %>"><%= collection.getBookName() %></a></li>
            <li class="time"><%= sdf.format(collection.getCreatedTime()) %></li>
            <li class="operation"><a href="${mt:getFullPath('') }/user/cancel_collect/<%=collection.getBookId() %>">取消收藏</a></li>
          </ul>
        <% }                     %>

    </div>

    <jsp:include page="common/pagination.jsp" />

  </div>
</body>
</html>