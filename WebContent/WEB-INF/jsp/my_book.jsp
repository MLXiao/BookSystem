<%@page import="com.tyut.book.model.LoanStatusEnum"%>
<%@page import="com.tyut.book.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.tyut.book.Constants"%>
<%@page import="java.util.List"%>
<%@page import="com.tyut.book.model.Category"%>
<%@page import="com.tyut.book.model.Book"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>我的图书</title>
    <link rel="stylesheet" href="${mt:getStaticUrl() }/css/my_book.css" />
  </head>
  <body>

    <jsp:include page="common/Header.jsp"></jsp:include>

    <div class = "content_wrapper">

      <div class="top_bar">
          <% List<Category> allCategory = (List<Category>)session.getAttribute(Constants.ALL_CATEGORY);                        %>
          <select name="categoryId">
              <option value="0">请选择图书分类</option>
          <% for (Category category : allCategory) {                         %>
              <option value="<%= category.getId() %>"><%=category.getName() %></option>
          <% }                                                              %>
        </select>
        <input type="text" name="keyword" placeholder="请输入关键字" />
        <img alt="搜索" />
      </div>

      <div class="left_menu">
        <ul>
          <li value="all">全部</li>
          <li value="loaned">已借出</li>
          <li value="not_loaned">未借出</li>
          <li value="loaning" >借阅处理中</li>
        </ul>
      </div>

      <div class="table_wrapper">
        <ul>
          <li>封面</li>
          <li>书名</li>
          <li>作者</li>
          <li>上架时间</li>
          <li>当前持有者</li>
          <li>操作</li>
        </ul>

        <% List<Book> bookList = (List<Book>) request.getAttribute("bookList"); %>
        <% for (Book book : bookList) {                                        %>
        <ul>
          <li><img alt="封面" src="<%= StringUtil.ByteArrayToImgBase4String(book.getCover()) %>" /></li>
          <li><%= book.getName() %></li>
          <li><%= book.getAuthor() %></li>
          <li><%= book.getCreatedTime() %></li>
          <li><%= book.getCurrentOwnerName() %></li>
          <li>
            <%  if (book.getLoanStatus() == LoanStatusEnum.not_loaned) {           %>
                    <a href="#">更新</a>
                    <a href="#">下架</a>
            <%  }                                                              %>
          </li>
        </ul>
        <% }                                                                    %>

      </div>

      <div class="pagination">

        <% int currentPage = (int)request.getAttribute("currentPage");          %>
        <% int totalPage = (int)request.getAttribute("totalPage");          %>
        <% List<Integer> pageList = (List<Integer>)request.getAttribute("pageList");            %>
        <% if (pageList.size() > 1) {                  %>

          <% if (currentPage > 5)  { %>
            <a href="#">首页</a>
          <% }                       %>

          <% if (currentPage > 1)  { %>
            <a href="#">上一页</a>
          <% }                        %>

          <% for (int pageNum : pageList) {      %>
            <a href="#"><%= pageNum %></a>
          <% }     %>

          <% if (currentPage < totalPage)  { %>
            <a href="#">下一页</a>
          <% } %>

          <% if (currentPage <= totalPage - 5)  { %>
            <a href="#">尾页</a>
          <% } %>
          
        <% }         %>
      </div>

    </div>

  <script src="${mt:getStaticUrl() }/js/add_book.js"></script>
  </body>
</html>