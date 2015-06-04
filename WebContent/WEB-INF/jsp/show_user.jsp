<%@page import="com.tyut.book.Constants"%>
<%@page import="com.tyut.book.model.GenderEnum"%>
<%@page import="com.tyut.book.model.User"%>
<%@page import="com.tyut.book.util.StringUtil"%>
<%@page import="com.tyut.book.util.PropertyUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<link rel="stylesheet" href="${mt:getStaticUrl() }/css/show_user.css" />
</head>
<body>
    <jsp:include page="common/header.jsp"></jsp:include>

    <div class = "content_wrapper">

        <% User user = (User)request.getAttribute("user"); %>

        <div class="basic_info">
          <ul class="username">
            <li class="title">用户名:</li>
            <li class="content"><%= user.getUsername() %></li>
          </ul>
          <ul class="gender">
            <li class="title">性别:</li>
            <li class="content"><%= user.getGender() == GenderEnum.male ? "男" : "女" %></li>
          </ul>
          <ul class="phome_num">
            <li class="title">手机号:</li>
            <li class="content"><%= user.getPhomeNum() %></li>
          </ul>
          <ul class="email">
            <li class="title">邮箱:</li>
            <li class="content"><%= user.getEmail() %></li>
          </ul>
        </div>

        <img class="user_avatar" alt="头像" src="<%= user.getAvatar() == null ? PropertyUtil.getStaticUrl() + "/images/default_avatar.png" : StringUtil.ByteArrayToImgBase4String(user.getAvatar()) %>" />

        <% User currentUser = (User)session.getAttribute(Constants.USER); %>
        <% if (currentUser.getId() == user.getId()) { %>
            
        <% } %>

    </div>

</body>
</html>