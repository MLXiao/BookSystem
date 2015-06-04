function headerOperation() {
    var $userOperationDetail = $('.user_operation_detail');
    if ($userOperationDetail.css('display') == 'none') {
        $userOperationDetail.css('display','block');
    } else {
        $userOperationDetail.css('display','none');
    }
}

function encodeSearchKey(keyWord) {
    keyWord = keyWord.replace(/%/g, '%25');
    keyWord = keyWord.replace(/;/g, '%3B');
    keyWord = keyWord.replace(/\//g, '%2F');
    keyWord = keyWord.replace(/\\/g, '%5C');
    keyWord = keyWord.replace(/,/g, '%2C');
    keyWord = keyWord.replace(/=/g, '%3D');
    keyWord = keyWord.replace(/'/g, '%27');
    keyWord = keyWord.replace(/`/g, '%60');
    keyWord = keyWord.replace(/&/g, '%26');
    return keyWord;
}

function fillTable() {
    var action = $('input[type=hidden][name=action]').val();
    var currentPage = $('input[type=hidden][name=currentPage]').val();
    var categoryId = $('input[type=hidden][name=categoryId]').val();
    var keyWord = $('input[type=hidden][name=keyWord]').val();
    var loanStatus = $('input[type=hidden][name=loanStatus]').val();

    var requestUrl = action;

    requestUrl = requestUrl + '?currentPage=' + currentPage;

    if (categoryId != '0' && categoryId != null && categoryId != undefined && categoryId != '') {
        requestUrl = requestUrl + '&categoryId=' + categoryId;
    }

    if (keyWord != null && keyWord != undefined && keyWord != '') {
        keyWord = encodeSearchKey(keyWord);
        requestUrl = requestUrl + '&keyWord=' + keyWord;
    }

    if (loanStatus != 'all' && loanStatus != undefined && loanStatus != null && loanStatus != '') {
        requestUrl = requestUrl + '&loanStatus=' + loanStatus;
    }

    location.href = requestUrl;

}

$(function() {
    $('.current_page').text($('title').text());
    if ($('.current_page').text() == '图书分享平台') {
        $('.current_page').text('首页');
    }
    $('.content_wrapper').css("min-height", $(window).height() - 40);

    if ($('title').text() == '图书分享平台') {
        $('.back').hide();
    }
})
