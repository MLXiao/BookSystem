<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Book System</title>

    <link rel="stylesheet" href="${mt:getStaticUrl() }/css/ui-dialog.css" />
    <link rel="stylesheet" href="${mt:getStaticUrl() }/css/common.css" />
    <link rel="stylesheet" href="${mt:getStaticUrl() }/css/login.css" />
  </head>
  <body style="background: url('${mt:getStaticUrl() }/images/bg_homepage.png') no-repeat;">

    <div class="login">
      <div class="login_mask"></div>
      <div class="login_main">
        <div class="login_header">
          <span class="login_logo">用户登录</span>
          <span class="login_close login_x">X</span>
        </div>
        <div class="login_error_message"></div>
        <div class="error_message"></div>
        <div class="username_wrapper">
          <input type="text" name="username"  placeholder="用户名" autofocus />
          <span class="username_clearer login_x">X</span>
        </div>
        <br>
        <div class="password_wrapper">
          <input type="password" name="password" placeholder="密码" />
          <span class="password_clearer login_x">X</span>
        </div>
        <br>
        <div class="verify_code_wrapper">
          <input type="text" name="verifyCode" placeholder="验证码">
          <span class="verify_code_clearer login_x">X</span>
          <img title="点击更换图片" alt="验证码" src="${mt:getStaticUrl() }/images/verify_code.png" />
        </div>
        <div class="remeber_pass_wrapper">
          <input type="checkbox" name="remeberPass" /><label>记住密码</label>
          <a class="forgot_pass" href="#">忘记密码？</a>
        </div>
        <input type="button" id="loginBtn" value="登录">
        <a class="register" href="#">立即注册</a>
      </div>
    </div>

    <input type="hidden" name="fullPathPrefix" value="${mt:getFullPath('') }"/>
    <input type="hidden" name="staticUrl" value="${mt:getStaticUrl() }"/>

    <script src="${mt:getStaticUrl() }/js/jquery-2.1.4.min.js"></script>
    <script src="${mt:getStaticUrl() }/js/dialog-min.js"></script>
    <script src="${mt:getStaticUrl() }/js/login.js"></script>
  </body>
</html>