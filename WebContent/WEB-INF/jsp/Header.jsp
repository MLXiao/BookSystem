<%@page import="com.tyut.book.Constants"%>
<%@page import="com.tyut.book.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% User user = (User)session.getAttribute(Constants.USER); %>
<div class="header_wrapper">
  <div class="header">
    <span class="header_logo"><font>图书分享平台</font></span>
    <span>当前位置:<font>XX页</font></span>
    <% if (user == null) {                            %>

      <a class="login_link" href="#" onclick="javascript:login()" >登陆/注册</a>

    <% } else {                                       %>
      <div class="Header_right">
        <img class="avatar" alt="头像" />
        <a class="username_link" href="#"><%= user.getUsername() %></a>
        <span>消息</span>
        <img class="user_operation" alt="操作">
        <a href="#">注销</a>
      </div>
      <div class="user_operation_detail">
        <div class="upload_book">上架图书</div>
        <div>我的图书</div>
        <div>我的收藏</div>
        <div>我的借阅</div>
      </div>
    <% }                                                %> 
  </div>
</div>