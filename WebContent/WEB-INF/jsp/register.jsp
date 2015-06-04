<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<link rel="stylesheet" href="${mt:getStaticUrl() }/css/register.css" />
</head>
<body>
    <div class="content_wrapper">
      <form action="${mt:getFullPath('') }/user/register" method="post">
        <ul class="username">
          <li class="title">用户名:</li>
          <li class="content"><input type="text" name="username" /></li>
          <li class="error_message"></li>
        </ul>
        <ul class="password">
          <li class="title">密码:</li>
          <li class="content"><input type="text" name="password" /></li>
          <li class="error_message"></li>
        </ul>
        <ul class="nickname">
          <li class="title">昵称:</li>
          <li class="content"><input type="text" name="nickname" /></li>
          <li class="error_message"></li>
        </ul>
        <ul class="gender">
          <li class="title">性别:</li>
          <li class="content">
            <select name="gender">
              <option value="0">请选择...</option>
              <option value="male">男</option>
              <option value="female">女</option>
            </select>
          </li>
          <li class="error_message"></li>
        </ul>
        <ul class="phome_num">
          <li class="title">手机号:</li>
          <li class="content"><input type="text" name="phomeNum" /></li>
          <li class="error_message"></li>
        </ul>
        <ul class="email">
          <li class="title">邮箱:</li>
          <li class="content"><input type="text" name="email" /></li>
          <li class="error_message"></li>
        </ul>

        <input type="hidden" name="avatarBase64" />
        
      </form>
        <button class="register" onclick="register()">注册</button>
        <button class="cancel" onclick="location.href='${mt:getFullPath('') }/user/homepage'">取消</button>
      <div class="avatar_wrapper">
        <img alt="上传头像" />
        <input style="display: none;" type="file" onchange="previewFile()" />
        <br>
        <font class="error_message"></font>
      </div>
    </div>

    <script src="${mt:getStaticUrl() }/js/jquery-2.1.4.min.js"></script>
    <script>
    function previewFile() {
        var $avatarImg = $('.avatar_wrapper img');
        var imgFile = document.querySelector('input[type=file]').files[0];
        var reader = new FileReader();
        reader.onloadend = function () {
            $avatarImg.attr('src',reader.result);
            $('input[name=avatarBase64]').val(reader.result);
        }
        if (imgFile) {
            reader.readAsDataURL(imgFile);
        } else {
            coverImg.src = "";
        }
    }
    
    $('.avatar_wrapper img').click(function() {
        $('input[type=file]').click();
        $('.avatar_wrapper .error_message').text('')
    });

    function register() {
        var flag = true;
        $('input[type=text]').each(function() {
            if ($.trim($(this).val()) == '') {
                var $errorMessage = $(this).parent().parent().find('.error_message')
                $errorMessage.text('请填写此字段！');
                flag = false;
            }
        });

        if ($('select').val() == '0') {
            var $errorMessage = $('.gender .error_message');
            $errorMessage.text('请选择性别！');
            flag = false;
        }

        if ($('input[name=avatarBase64]').val() == '') {
            $('.avatar_wrapper .error_message').text('请上传头像！')
            flag = false;
        }

        if (flag) {
            $('form').submit();
        }

    }

    $('input[type=text]').focus(function() {
        $(this).parent().parent().find('.error_message').text('');
    });
    $('select').click(function() {
        $('.gender .error_message').text('');
    });

    </script>
</body>
</html>