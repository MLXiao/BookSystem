<%@page import="com.tyut.book.util.StringUtil"%>
<%@page import="com.tyut.book.model.Book"%>
<%@page import="com.tyut.book.Constants"%>
<%@page import="java.util.List"%>
<%@page import="com.tyut.book.model.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上架图书</title>
<link rel="stylesheet" href="${mt:getStaticUrl() }/css/save_book.css" />
<script src="${mt:getStaticUrl() }/js/jquery-2.1.4.min.js"></script>
</head>
<body>

  <% Book book = (Book)request.getAttribute("book"); %>

  <% if (book != null) { %>
    <script>
      $('title').text('更新图书');
    </script>
  <% }       %>

  <input id="cateIdHid" type="hidden" value="<%= book == null ? "" : book.getCategoryId() %>" />

  <jsp:include page="common/header.jsp"></jsp:include>

  <div class = "content_wrapper">
    <form action="${mt:getFullPath('') }/book/save_book" method="post">
      <div class= "basic_info">
        <input type="hidden" name="id" value="<%= book == null ? 0 : book.getId() %>" />
        <ul>
          <li class="info_title">书名:</li>
          <li><input type="text" name="name" value="<%= book == null ? "" : book.getName() %>" /></li>
        </ul>
        <ul>
          <li class="info_title">作者:</li>
          <li><input type="text" name="author" value="<%= book == null ? "" : book.getAuthor() %>" /></li>
        </ul>
        <ul>
          <li class="info_title">分类:</li>
          <li>
            <% List<Category> allCategory = (List<Category>)session.getAttribute(Constants.ALL_CATEGORY);                        %>
            <select name="categoryId">
              <option value="0">请选择...</option>
            <% for (Category category : allCategory) {                         %>
              <option value="<%= category.getId() %>"><%=category.getName() %></option>
            <% }                                                              %>
            </select>
          </li>
        </ul>
        <ul>
          <li class="info_title">押金:</li>
          <li><input type="text" name="deposit" value="<%= book == null ? "" : book.getDeposit() %>" /></li>
        </ul>
        <ul>
          <li class="info_title">简介:</li>
          <li><textarea name="description"><%= book == null ? "" : book.getDescription() %></textarea></li>
        </ul>
      <input type="hidden" name="coverBase64" value="<%= book == null ? "" : StringUtil.ByteArrayToImgBase4String(book.getCover()) %>" />
      <div class="op_btn">
        <input onclick="verifySubmit()" type="button" value="提交" />
        <input onclick="history.go(-1)" type="button" value="取消" />
      </div>
      </div>
    </form>

    <div class="cover_wrapper">
      <img class="cover_img" alt="选择封面" src="<%= book == null ? "" : StringUtil.ByteArrayToImgBase4String(book.getCover()) %>"/>
      <input style="display: none;" type="file" onchange="previewFile()" />
    </div>

  </div>
  <script>
      function previewFile() {
          var $coverImg = $('.cover_img');
          var imgFile = document.querySelector('input[type=file]').files[0];
          var reader = new FileReader();
          reader.onloadend = function () {
              $coverImg.attr('src',reader.result);
              $('input[name = coverBase64]').val(reader.result);
          }
          if (imgFile) {
              reader.readAsDataURL(imgFile);
          } else {
              coverImg.src = "";
          }
      }

      function verifySubmit() {
          var name = $('input[name=name]').val();
          var author = $('input[name=author]').val();
          var category = $('select[name=categoryId]').val();
          var deposit = $('input[name=deposit]').val();
          var description = $('textarea[name=description]').val();
          var coverBase64 = $('input[name=coverBase64]').val();

          var flag = true;

          if ($.trim(name) == '') {
              dName = dialog({
                  align: 'right',
                  content: '请填写书名！',
              });
              dName.show($('input[name=name]')[0]);
              flag = false;
              setTimeout(function () {
                  dName.close().remove();
              }, 2000);
          }
          if ($.trim(author) == '') {
              dAuthor = dialog({
                  align: 'right',
                  content: '请填写作者！',
              });
              dAuthor.show($('input[name=author]')[0]);
              flag = false;
              setTimeout(function () {
                  dAuthor.close().remove();
              }, 2000);
          }
          if ($.trim(category) == '0') {
              dCategory = dialog({
                  align: 'right',
                  content: '请选图书分类！',
              });
              dCategory.show($('select[name=categoryId]')[0]);
              flag = false;
              setTimeout(function () {
                  dCategory.close().remove();
              }, 2000);
          }
          if ($.trim(deposit) == '') {
              dDeposit1 = dialog({
                  align: 'right',
                  content: '请输入押金！',
              });
              dDeposit1.show($('input[name=deposit]')[0]);
              flag = false;
              setTimeout(function () {
                  dDeposit1.close().remove();
              }, 2000);
          }
          if ($.trim(deposit) != '' && (isNaN(deposit) || parseInt(deposit) < 0 || parseInt(deposit) > 1000)) {
              dDeposit2 = dialog({
                  align: 'right',
                  content: '请输入合法数字！',
              });
              dDeposit2.show($('input[name=deposit]')[0]);
              flag = false;
              setTimeout(function () {
                  dDeposit2.close().remove();
              }, 2000);
          }
          if ($.trim(description) == '') {
              dDescription = dialog({
                  align: 'right',
                  content: '请输入图书简介！',
              });
              dDescription.show($('textarea[name=description]')[0]);
              flag = false;
              setTimeout(function () {
                  dDescription.close().remove();
              }, 2000);
          }
          if ($.trim(coverBase64) == '') {
              dCover = dialog({
                  align: 'right',
                  content: '请上传封面！',
              });
              dCover.show($('.cover_img')[0]);
              flag = false;
              setTimeout(function () {
                  dCover.close().remove();
              }, 2000);
          }

          if (!flag) {
             return;
          }

          $('form').submit();
      }

      $('.cover_img').click(function() {
          $('input[type=file]').click();
      });

      if ($('#cateIdHid').val() != '') {
          $('select[name=categoryId]').val($('#cateIdHid').val());
      }

  </script>
</body>
</html>