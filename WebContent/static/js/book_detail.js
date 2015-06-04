function sendBorrowRequest(obj) {
    var bookId = $('input[type=hidden][name=bookId]').val();
    
     if(!confirm("确认借阅?")) {
        return;
     }

     var fullPathPrefix = $('input[name=fullPathPrefix]').val();
     var bookId = $('input[type=hidden][name=bookId]').val();

     $.ajax({
         url : fullPathPrefix + '/user/require_borrow',
         type : 'POST',
         data : bookId,
         contentType : 'application/text; charset=utf-8',
         dateType : 'text',
         success: function(data) {
             if (data == "ok") {
                 $(obj).val("已被借阅");
                 $(obj).attr("disabled", "false");
             } else {
                 alert("借阅失败!");
             }
         }
     });

}

function collect(obj) {
    if ($(obj).attr("alt") == "已收藏") {
        return;
    }

    var fullPathPrefix = $('input[name=fullPathPrefix]').val();
    var staticUrl =  $('input[name=staticUrl]').val();
    var bookId = $('input[type=hidden][name=bookId]').val();

    $.ajax({
        url : fullPathPrefix + '/user/collect_book',
        type : 'POST',
        data : bookId,
        contentType : 'application/text; charset=utf-8',
        dateType : 'text',
        success: function(data) {
            if (data == "ok") {
                $(obj).attr("alt", "已收藏")
                $('.collect font').text("已收藏")
                $(obj).attr("src", staticUrl + "/images/collected.png");
            } else {
                alert("收藏失败!");
            }
        }
    });

}