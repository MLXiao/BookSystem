<%@page import="com.tyut.book.model.Pagination"%>
<%@page import="com.tyut.book.model.LoanStatusEnum"%>
<%@page import="com.tyut.book.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.tyut.book.Constants"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<link rel="stylesheet" href="${mt:getStaticUrl() }/css/pagination.css" />
<div class="pagination">
  <% Pagination pagination = (Pagination)request.getAttribute("pagination");          %>
  <% int currentPage = pagination.getCurrentPage();          %>
  <% int totalPage = pagination.getTotalPage();          %>
  <% List<Integer> pageList = pagination.getPageList();            %>
  <% if (pageList.size() > 1) {                  %>

    <% if (currentPage > 5)  { %>
      <a class="first_page" onclick="goPage(this)" href="javascript:void(0)">首页</a>
    <% }                       %>

    <% if (currentPage > 1)  { %>
      <a class="pre_page" onclick="goPage(this)" href="javascript:void(0)">上一页</a>
    <% }                        %>

    <% for (int pageNum : pageList) {      %>
      <a class="page_num" onclick="goPage(this)" href="javascript:void(0)"><%= pageNum %></a>
    <% }     %>

    <% if (currentPage < totalPage)  { %>
      <a class="next_page" onclick="goPage(this)" href="javascript:void(0)">下一页</a>
    <% } %>

    <% if (currentPage <= totalPage - 5)  { %>
      <a class="last_page" onclick="goPage(this)" href="javascript:void(0)">尾页</a>
    <% } %>
    
  <% }         %>
  
  <% if (pageList.size() > 5) {                  %>
    <span>
        <input type="text" />
        <input class="go_page" type="button" onclick="goPage(this)" value = "跳转" />
    </span>
  <% }    %>

  <input type="hidden" name="currentPage" value="<%= currentPage %>" />
  <input type="hidden" name="totalPage" value="<%= pagination.getTotalPage() %>" />

</div>
<script type="text/javascript">
    function goPage(obj) {
        var $currentPageObj = $('input[type=hidden][name=currentPage]')
        var currentPage = $currentPageObj.val();
        var totalPage = $('input[type=hidden][name=totalPage]').val();
        switch($(obj).attr('class')) {
            case 'first_page' :
                $currentPageObj.val(1);
                break;
            case 'last_page' :
                $currentPageObj.val(totalPage);
                break;
            case 'pre_page' :
                $currentPageObj.val(parseInt(currentPage) - 1);
                break;
            case 'next_page' :
                $currentPageObj.val(parseInt(currentPage) + 1);
                break;
            case 'page_num' :
                $currentPageObj.val($(obj).text());
                break;
            case 'go_page' :
                $currentPageObj.val($(obj).parent().find('input').val());
                break;
            default :
                break;
        }
        fillTable();
    }
    $('.page_num').each(function() {
        if ($(this).text() == $('input[type=hidden][name=currentPage]').val()) {
            $(this).css("color", "rgb(43, 195, 109)");
        }
    });
</script>