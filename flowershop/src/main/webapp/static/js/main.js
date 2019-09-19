$(document).ready(function() {

    $("input[name$='login']").keyup(function () {
        login = $(this).val();

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/rest/checked/user/",
            data: login,
            contentType: false,
            cache: false,
            processData: false,
            success: function (result) {
                json = jQuery.parseJSON(result);

                if (json) {

                    $("#login").addClass("red-input");
                    $("#button-reg").prop("disabled", true);
//                    alert("Логин уже занят");
                } else {

                    $("#login").removeClass("red-input");
                    $("#button-reg").prop("disabled", false);
//                    alert("Логин свободен");
                }
            },
        });
    });

    $("input[name$='check_password']").keyup(function () {
        var passwordVal = $("input[name$='password']").val();

        if ($(this).val() != passwordVal) {

            $(this).addClass("red-input");
            $("#button-reg").prop("disabled", true);

        } else {

            $(this).removeClass("red-input");
            $("#button-reg").prop("disabled", false);
        }
    });

    $("input[name$='phone']").mask('+7 (000) 000-0000');

});