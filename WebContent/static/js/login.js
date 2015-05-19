$(function() {
    var fullPathPrefix = $('input[name=fullPathPrefix]').val();
    var staticUrl = $('input[name=staticUrl]').val();
    var $loginBtn = $('#loginBtn');
    var $username = $('input[name=username]');
    var $userNameWrapper = $('.username_wrapper');
    var $password = $('input[name=password]');
    var $passwordWrapper = $('.password_wrapper');
    var $verifyCode = $('input[name=verify]');
    var $verifyCodeWrapper = $('.verify_code_wrapper');
    var $verifyCodeImg = $('.verify_code_wrapper img');

    var tipDialog = dialog({
        align: 'right',
        content: '',
    });

    $('input[name=username], input[name=password], input[name=verifyCode]')
        .on('input propertychange', function() {
        tipDialog.close();
        $(this).focus();
        var $loginXTmp = $(this).parent().find('.login_x')
        if ($(this).val()) {
            $loginXTmp.css('display', 'inline-block');
        } else {
            $loginXTmp.css('display', 'none');
        }
    });

    $('.username_wrapper .login_x, .password_wrapper .login_x').on('click', function() {
        $(this).parent().find('input').val('').focus();
        $(this).css('display', 'none');
    });

    $verifyCodeImg.on('click', function() {
        $.ajax({
            url : fullPathPrefix + '/user/update_code',
            type : 'POST',
            dateType : 'text',
            beforeSend : function(XMLHttpRequest) {
                $verifyCodeImg.attr('src', staticUrl + '/images/verify_code_loading.gif');
            },
            success: function(data) {
                $verifyCodeImg.attr('src', staticUrl + '/images/verify_code.png?date=' + new Date().getTime());
            }
        });
    });

    $loginBtn.on('click', function() {
        if (!$.trim($username.val())) {
            tipDialog.content('请填写用户名');
            tipDialog.show($userNameWrapper[0]);
            $username.focus();
            return;
        }

        if (!$.trim($password.val())) {
            tipDialog.content('请填写密码');
            tipDialog.show($passwordWrapper[0]);
            $password.focus();
            return;
        }

        if (!$.trim($verifyCode.val())) {
            tipDialog.content('请填写验证码');
            tipDialog.show($verifyCodeWrapper[0]);
            $verifyCode.focus();
            return;
        }
    });
});