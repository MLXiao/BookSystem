function login() {
    $('.login').css("display", "block");
    var fullPathPrefix = $('input[name=fullPathPrefix]').val();
    var staticUrl = $('input[name=staticUrl]').val();
    var $loginBtn = $('#loginBtn');
    var $username = $('input[name=username]');
    var $userNameWrapper = $('.username_wrapper');
    var $password = $('input[name=password]');
    var $passwordWrapper = $('.password_wrapper');
    var $verifyCode = $('input[name=verifyCode]');
    var $verifyCodeWrapper = $('.verify_code_wrapper');
    var $verifyCodeImg = $('.verify_code_wrapper img');
    var $loginErrorMessage = $('.login_error_message');

    var scroll = function() {
        return false;
    }

    $(window).bind("wheel", scroll);

    $('.login_close').on('click', function() {
        tipDialog.close();
        $('.login').css("display", "none");
        $(window).unbind("wheel", scroll);
    });

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
            url : fullPathPrefix + '/user/update_vcode',
            type : 'POST',
            dataType : 'text',
            beforeSend : function(XMLHttpRequest) {
                $verifyCodeImg.attr('src', staticUrl + '/images/verify_code_loading.gif');
            },
            success: function(data) {
                $verifyCodeImg.attr('src', data);
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

        var user = { 
            'username' : $username.val(),
            'password' : $password.val(),
            'verifyCode' : $verifyCode.val()
        };

        $.ajax({
            url : fullPathPrefix + '/user/login',
            type : 'POST',
            data : JSON.stringify(user),
            contentType : 'application/json; charset=utf-8',
            dateType : 'json',
            success: function(data) {
                $.each(data, function(key) {
                    if (key == 'verifyCode') {
                        tipDialog.content(data[key]);
                        tipDialog.show($verifyCodeWrapper[0]);
                        $verifyCode.focus();
                        return;
                    }

                    if (key == 'service') {
                        $loginErrorMessage.html(data[key]);
                        return;
                    }
                });

                if ($.isEmptyObject(data)) {
                    $loginErrorMessage.html('');
                    location.href = fullPathPrefix + '/user/homepage'
                }
            }
        });

    });

    $verifyCodeImg.click();

    $(document).on('keydown', function(e) {
        if ($('.login').css("display") == "block") {
            switch(e.keyCode) {
                case 13:
                    $loginBtn.click();
                    break;
                case 27:
                    $('.login_close').click();
                    break;
                default :
                    break;
            }
        }
    })
}