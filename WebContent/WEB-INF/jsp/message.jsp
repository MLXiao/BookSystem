<%@page import="com.tyut.book.model.MessageTypeEnum"%>
<%@page import="com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default"%>
<%@page import="java.util.List"%>
<%@page import="com.tyut.book.model.Message"%>
<%@ taglib prefix="mt" uri="/myTags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>消息中心</title>
</head>
  <body>
    <jsp:include page="common/header.jsp"></jsp:include>

    <div class = "content_wrapper">

      <% String status = request.getAttribute("status").toString(); %>

      <div class = "menu_wrapper">
        <ul>
            <li onclick="location.href='${mt:getFullPath('') }/user/message?status=new'">新消息</li>
            <li onclick="location.href='${mt:getFullPath('') }/user/message?status=old'">消息历史</li>
        </ul>
      </div>


      <div class = "message_wrapper">
      <% List<Message> messages = (List<Message>)request.getAttribute("messages"); %>

        <% if (status.equals("new")) { %>

            <% for (Message message : messages) { %>

              <div class="message">

                <form id="messageForm" action="${mt:getFullPath('') }/user/deal_message" method="post">
                  <input type="hidden"  name="messageId" value="<%=message.getId() %>" />
                  <input type="hidden"  name="result" />
                </form>

              <% if (message.getType() == MessageTypeEnum.notice) { %>
              <% }  %>

              <% if (message.getType() == MessageTypeEnum.borrow_info) { %>
                   <span><%= message.getCreatedTime() %></span>
                   <span>
                     <font>用户</font>
                     <a href="javascropt:void(0)"><%= message.getSenderName() %></a>
                     <font>请求借阅你的图书</font>
                     <a href="${mt:getFullPath('') }/book/<%= message.getBookId() %>" ><%= message.getBookName() %></a>
                   </span>
                   <span>
                     <button class="btn_yes">接受</button>
                     <button class="btn_no">拒绝</button>
                   </span>
              <% }  %>

              <% if (message.getType() == MessageTypeEnum.confirm_borrow) { %>
                   <span><%= message.getCreatedTime() %></span>
                   <span>
                     <font>用户</font>
                     <a href="javascropt:void(0)"><%= message.getSenderName() %></a>
                     <font>同意了你对图书</font>
                     <a href="${mt:getFullPath('') }/book/<%= message.getBookId() %>" ><%= message.getBookName() %></a>
                     <font>的借阅请求. 收到书籍后请</font>
                     <button class="btn_yes">确认</button>
                   </span>
              <% }  %>
              
              <% if (message.getType() == MessageTypeEnum.refuse_borrow) { %>
                   <span><%= message.getCreatedTime() %></span>
                   <span>
                     <font>用户</font>
                     <a href="javascropt:void(0)"><%= message.getSenderName() %></a>
                     <font>拒绝了你对图书</font>
                     <a href="${mt:getFullPath('') }/book/<%= message.getBookId() %>"><%= message.getBookName() %></a>
                     <font>的借阅请求</font>
                     <button class="btn_yes">确认</button>
                   </span>
              <% }  %>

              <% if (message.getType() == MessageTypeEnum.return_info) { %>
                   <span><%= message.getCreatedTime() %></span>
                   <font>用户</font>
                   <a href="javascropt:void(0)"><%= message.getSenderName() %></a>
                   <font>请求归还图书</font>
                   <a href="${mt:getFullPath('') }/book/<%= message.getBookId() %>" ><%= message.getBookName() %></a>
                   <font>收到图书后请</font>
                   <button class="btn_yes">确认</button>
              <% }  %>

              <% if (message.getType() == MessageTypeEnum.confirm_return) { %>
                   <span><%= message.getCreatedTime() %></span>
                   <font>图书</font>
                   <a href="${mt:getFullPath('') }/book/<%= message.getBookId() %>" ><%= message.getBookName() %></a>
                   <font>已经成功归还至用户</font>
                   <a href="javascropt:void(0)" ><%= message.getSenderName() %></a>
                   <button class="btn_yes">确认</button>
              <% }  %>
              </div>
          <% } %>

        <% } else {               %>

            <% for (Message message : messages) { %>
              <div class="message">
              <% if (message.getType() == MessageTypeEnum.notice) { %>
                     
              <% }  %>

              <% if (message.getType() == MessageTypeEnum.borrow_info) { %>
                   <span><%= message.getUpdatedTime() %></span>
                   <span>
                   <% if(message.getResult() == true) { %>
                     <font>你同意了用户</font>
                   <% } else { %>
                     <font>你拒绝了用户</font>
                   <% }%>
                     <a href="javascropt:void(0)"><%= message.getSenderName() %></a>
                     <font>对图书</font>
                     <a href="${mt:getFullPath('') }/book/<%= message.getBookId() %>" ><%= message.getBookName() %></a>
                     <font>的借阅请求</font>
                   </span>
              <% }  %>

              <% if (message.getType() == MessageTypeEnum.confirm_borrow) { %>
                   <span><%= message.getCreatedTime() %></span>
                   <span>
                     <font>图书</font>
                     <a href="${mt:getFullPath('') }/book/<%= message.getBookId() %>" ><%= message.getBookName() %></a>
                     <font>借阅成功</font>
                   </span>
              <% }  %>

              <% if (message.getType() == MessageTypeEnum.refuse_borrow) { %>
                   <span><%= message.getCreatedTime() %></span>
                   <span>
                     <font>用户</font>
                     <a href="javascropt:void(0)"><%= message.getSenderName() %></a>
                     <font>拒绝了你对图书</font>
                     <a href="${mt:getFullPath('') }/book/<%= message.getBookId() %>" ><%= message.getBookName() %></a>
                     <font>的借阅请求</font>
                   </span>
              <% }  %>

              <% if (message.getType() == MessageTypeEnum.return_info) { %>
                   <span><%= message.getCreatedTime() %></span>
                   <font>用户</font>
                   <a href="javascropt:void(0)"><%= message.getSenderName() %></a>
                   <font>已归还图书</font>
                   <a href="${mt:getFullPath('') }/book/<%= message.getBookId() %>" ><%= message.getBookName() %></a>
              <% }  %>

              <% if (message.getType() == MessageTypeEnum.confirm_return) { %>
                   <span><%= message.getCreatedTime() %></span>
                   <font>图书</font>
                   <a onclick="location.href='${mt:getFullPath('') }/book/<%= message.getBookId() %>'"><%= message.getBookName() %></a>
                   <font>已经成功归还至用户</font>
                   <a href="javascropt:void(0)" ><%= message.getSenderName() %></a>
              <% }  %>
              </div>
          <% } %>

        <% }                      %>

      </div>

      <script>
          $('.btn_yes, .btn_no').click(function() {
              if ($(this).attr('class') == 'btn_yes') {
                  $('input[type=hidden][name=result]').val('true');
              } else {
                  $('input[type=hidden][name=result]').val('false');
              }
              $('#messageForm').submit();
          });
      </script>

    </div>

  </body>
</html>