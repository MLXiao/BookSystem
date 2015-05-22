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
<link rel="stylesheet" href="${mt:getStaticUrl() }/css/add_book.css" />
</head>
<body>

  <jsp:include page="common/Header.jsp"></jsp:include>

  <div class = "content_wrapper">
    <form action="${mt:getFullPath('') }/book/add_book" method="post">
      <div class= "basic_info">
        <ul>
          <li>书名:</li>
          <li><input type="text" name="name" /></li>
        </ul>
        <ul>
          <li>作者:</li>
          <li><input type="text" name="author" /></li>
        </ul>
        <ul>
          <li>图书分类:</li>
          <li>
            <% List<Category> allCategory = (List<Category>)session.getAttribute(Constants.ALL_CATEGORY);                        %>
            <select name="categoryId">
              <option>请选择...</option>
            <% for (Category category : allCategory) {                         %>
              <option value="<%= category.getId() %>"><%=category.getName() %></option>
            <% }                                                              %>
            </select>
          </li>
        </ul>
        <ul>
          <li>押金:</li>
          <li><input type="text" name="deposit" /></li>
        </ul>
        <ul>
          <li>简介:</li>
          <li><textarea name="description"></textarea></li>
        </ul>
      <input type="hidden" name="coverBase64" />
      <input type="submit" value="提交" />
      </div>
    </form>

    <div class="cover_wrapper">
      <img class="cover_img" alt="选择封面" style="display: inline-block; width: 160px; height: 220px"/>
      <input type="file" onchange="previewFile()" />
    </div>

  </div>
  <script src="${mt:getStaticUrl() }/js/add_book.js"></script>
</body>
</html>