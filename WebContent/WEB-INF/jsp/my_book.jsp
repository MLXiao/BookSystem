<%@page import="com.tyut.book.model.Pagination"%>
<%@page import="com.tyut.book.model.LoanStatusEnum"%>
<%@page import="com.tyut.book.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.tyut.book.Constants"%>
<%@page import="java.util.List"%>
<%@page import="com.tyut.book.model.Category"%>
<%@page import="com.tyut.book.model.Book"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<%@page import="java.text.SimpleDateFormat"%>
<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>我的图书</title>
    <link rel="stylesheet" href="${mt:getStaticUrl() }/css/my_book.css" />
  </head>
  <body>

    <jsp:include page="common/header.jsp"></jsp:include>

    <div class="content_wrapper">

      <div class="flash_message"><%= session.getAttribute(Constants.FLASH_MESSAGE) %></div>
      <% Object flashMessage = session.getAttribute(Constants.FLASH_MESSAGE); %>
      <% if (flashMessage != null) { %>
      <%     session.removeAttribute(Constants.FLASH_MESSAGE);  %>
             <script>
                 $('.flash_message').show("slow");
                 setTimeout(function () {
                     $('.flash_message').hide("slow");
                 }, 2500); 
            </script>
      <% }    %>

      <jsp:include page="common/top_bar.jsp" />

      <div class="left_menu">
        <ul>
          <li id="all" onclick="goStatus(this)">全部</li>
          <li id="loaned" onclick="goStatus(this)">已借出</li>
          <li id="not_loaned" onclick="goStatus(this)">未借出</li>
          <li id="loaning" onclick="goStatus(this)">借阅处理中</li>
        </ul>
        <input type="hidden" name="loanStatus" value="<%= request.getAttribute("loanStatus") %>" />
      </div>
      <script type="text/javascript">
          function goStatus(obj) {
              $('input[type=hidden][name=currentPage]').val(1);
              $('input[type=hidden][name=loanStatus]').val($(obj).attr('id'));
              fillTable();
          }
          $('.left_menu li').each(function() {
              if ($(this).attr('id') == $('input[type=hidden][name=loanStatus]').val()) {
                  $(this).css('background-color','rgb(133, 195, 130)');
                  $(this).css('color','#FFF');
              }
          });
      </script>

      <div class="table_wrapper">
        <input type="hidden" name="action" value="${mt:getFullPath('') }/book/my_book" />
        <ul class="table_title">
          <li class="cover">封面</li>
          <li class="name">书名</li>
          <li class="author">作者</li>
          <li class="time">上架时间</li>
          <li class="owner">当前持有者</li>
          <li class="operation">操作</li>
        </ul>

        <% List<Book> bookList = (List<Book>) request.getAttribute("bookList"); %>
        <% for (Book book : bookList) {                                        %>
        <ul class="table_item" >
          <li class="cover"><img alt="封面" src="<%= StringUtil.ByteArrayToImgBase4String(book.getCover()) %>" /></li>
          <li class="name"><a title="<%= book.getName() %>" href="${mt:getFullPath('') }/book/<%= book.getId() %>"><%= book.getName() %></a></li>
          <li class="author" title="<%= book.getAuthor() %>"><%= book.getAuthor() %></li>
          <li class="time"><%= sdf.format(book.getCreatedTime()) %></li>
          <li class="owner"><%= book.getCurrentOwnerName() %></li>
          <li class="operation">
            <%  if (book.getLoanStatus() == LoanStatusEnum.not_loaned) {           %>
                    <a href="${mt:getFullPath('') }/book/edit_book/<%= book.getId() %>">更新</a>
                    <a onclick="deleteBook(<%= book.getId() %>)" href="javascript:void(0)">下架</a>
            <%  }                                                              %>
          </li>
        </ul>
        <% }                                                                    %>

      </div>

      <form id="deleteBookForm" action="${mt:getFullPath('') }/book/delete_book" method="post">
        <input id="idHide" type="hidden" name="id" />
      </form>

      <script>
          function deleteBook(id) {
              $('#idHide').val(id);
              if(confirm("确定要下架该图书吗？")) {
                  $('#deleteBookForm').submit();
              }
          }
      </script>

      <jsp:include page="common/pagination.jsp" />

    </div>

  <script src="${mt:getStaticUrl() }/js/my_book.js"></script>
  </body>
</html>