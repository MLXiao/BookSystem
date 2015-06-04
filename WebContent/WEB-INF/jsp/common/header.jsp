<%@page import="com.tyut.book.util.StringUtil"%>
<%@page import="com.tyut.book.util.PropertyUtil"%>
<%@page import="com.tyut.book.Constants"%>
<%@page import="com.tyut.book.model.User"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% User user = (User)session.getAttribute(Constants.USER); %>
<div class="header_wrapper">
  <div class="header">
    <span class="header_logo" onclick="javascript:location.href = '${mt:getFullPath('') }/user/homepage'"><font>图书分享平台</font></span>
    <span class="current_location">当前位置&nbsp;:&nbsp;<font class="current_page"></font></span>

    <% if (user == null) {                            %>

      <a class="login_link" href="#" onclick="javascript:login()" >登陆/注册</a>

    <% } else {                                       %>
      <div class="Header_right">
        <img class="back" onclick="history.go(-1)" alt="返回" title="返回" src="${mt:getStaticUrl() }/images/back.png" />
        <%-- <img class="avatar" alt="头像" src="${mt:getStaticUrl() }/images/default_avatar.png" /> --%>
        <img class="avatar" alt="头像" src="<%= user.getAvatar() == null ? PropertyUtil.getStaticUrl() + "/images/default_avatar.png" : StringUtil.ByteArrayToImgBase4String(user.getAvatar()) %>" />
        <a class="username_link" href="${mt:getFullPath('') }/user/show_user/<%= user.getId() %>"><%= user.getUsername() %></a>
        <span class="message_span" onclick="location.href='${mt:getFullPath('') }/user/message'">
          <img title="消息" alt="消息" src="${mt:getStaticUrl() }/images/message.png" />
          <font><%= session.getAttribute(Constants.MESSAGE_COUNT).toString().equals("0") ? "" : session.getAttribute(Constants.MESSAGE_COUNT) %></font>
        </span>
        <img title="更多操作" src="${mt:getStaticUrl() }/images/menu.png" class="operation_menu" alt="操作" onclick="javascript:headerOperation()">
        <a class="logout" href="${mt:getFullPath('') }/user/logout">注销</a>
      </div>
      <div class="user_operation_detail" style="display: none;">
        <div class="upload_book" onclick="javascript:location.href = '${mt:getFullPath('') }/book/add_book'">上架图书</div>
        <div onclick="javascript:location.href = '${mt:getFullPath('') }/book/my_book'">我的图书</div>
        <div onclick="javascript:location.href = '${mt:getFullPath('') }/user/my_collect'">我的收藏</div>
        <div onclick="javascript:location.href = '${mt:getFullPath('') }/user/my_borrow'">我的借阅</div>
      </div>
    <% }                                                %> 
  </div>
</div>
<input type="hidden" name="fullPathPrefix" value="${mt:getFullPath('') }"/>
<input type="hidden" name="staticUrl" value="${mt:getStaticUrl() }"/>

<link rel="stylesheet" href="${mt:getStaticUrl() }/css/ui-dialog.css" />
<link rel="stylesheet" href="${mt:getStaticUrl() }/css/common.css" />

<script src="${mt:getStaticUrl() }/js/jquery-2.1.4.min.js"></script>
<script src="${mt:getStaticUrl() }/js/common.js"></script>
<script src="${mt:getStaticUrl() }/js/dialog-min.js"></script>
