$(function() {
    var $loginBtn = $('#loginBtn');
    var $username = $('input[name=username]');
    var $userNameWrapper = $('.username_wrapper');
    var $password = $('input[name=password]');
    var $passwordWrapper = $('.password_wrapper');
    var $verificationCode = $('input[name=verificationCode]');
    var $verificationCodeWrapper = $('.verification_code_wrapper');

    var tipDialog = dialog({
        align: 'right',
        content: '',
    });

    $('input[name=username], input[name=password], input[name=verificationCode]')
        .on('input propertychange', function() {
            tipDialog.close();
            $(this).focus();
        }
    );

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

        if (!$.trim($verificationCode.val())) {
            tipDialog.content('请填写验证码');
            tipDialog.show($verificationCodeWrapper[0]);
            $verificationCode.focus();
            return;
        }
    });
});