function headerOperation() {
    var $userOperationDetail = $('.user_operation_detail');
    console.debug('a');
    if ($userOperationDetail.css('display') == 'none') {
        $userOperationDetail.css('display','block');
    } else {
        $userOperationDetail.css('display','none');
    }
}

$(function() {
    $('.current_page').text($('title').text());
});