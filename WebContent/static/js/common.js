function headerOperation() {
    var $userOperationDetail = $('.user_operation_detail');
    if ($userOperationDetail.css('display') == 'none') {
        $userOperationDetail.css('display','block');
    } else {
        $userOperationDetail.css('display','none');
    }
}

function fillTable() {
    var action = $('input[type=hidden][name=action]').val();
    var currentPage = $('input[type=hidden][name=currentPage]').val();
    var categoryId = $('input[type=hidden][name=categoryId]').val();
    var keyWord = $('input[type=hidden][name=keyWord]').val();
    var loanStatus = $('input[type=hidden][name=loanStatus]').val();

    var requestUrl = action;

    requestUrl = requestUrl + '?currentPage=' + currentPage;

    if (categoryId != '0' && categoryId != null && categoryId != undefined && keyWord != '') {
        requestUrl = requestUrl + '&categoryId=' + categoryId;
    }

    if (keyWord != null && keyWord != undefined && keyWord != '') {
        requestUrl = requestUrl + '&keyWord=' + keyWord;
    }

    if (loanStatus != 'all') {
        requestUrl = requestUrl + '&loanStatus=' + loanStatus;
    }

    location.href = requestUrl;

}

$(function() {
    $('.current_page').text($('title').text());
})
