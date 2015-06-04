<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List"%>
<%@page import="com.tyut.book.model.Category"%>
<%@page import="com.tyut.book.Constants"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<div class="top_bar">
  <% List<Category> allCategory = (List<Category>)session.getAttribute(Constants.ALL_CATEGORY);                        %>
  <select name="categoryId" onchange="goSearch(this)">
    <option value="0">请选择图书分类</option>
    <% for (Category category : allCategory) {                         %>
        <option value="<%= category.getId() %>"><%=category.getName() %></option>
    <% }                                                              %>
  </select>
  <input type="text" id="keyWord" name="keyWord" placeholder="请输入关键字" />
  <img src="${mt:getStaticUrl() }/images/search.png" alt="搜索" onclick="goSearch(this)" />
  <input type="hidden" name="categoryId" value="<%= request.getAttribute("categoryId") %>" />
  <input type="hidden" name="keyWord" value="<%= request.getAttribute("keyWord") %>" />
</div>
<script type="text/javascript">
    function goSearch(obj) {
      $('input[type=hidden][name=currentPage]').val(1);
      $('input[type=hidden][name=categoryId]').val($('select').val());
      $('input[type=hidden][name=keyWord]').val($('input[name=keyWord]').val());
      fillTable();
    }
    $('select[name=categoryId]').val($('input[type=hidden][name=categoryId]').val());
    $('#keyWord').val($('input[type=hidden][name=keyWord]').val())
</script>