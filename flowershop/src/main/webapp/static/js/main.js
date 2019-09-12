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
                    console.log("Логин уже занят");
                } else {

                    $("#login").removeClass("red-input");
                    $("#button-reg").prop("disabled", false);
                    console.log("Логин свободен");
                }
            },
        });
    });

    function ajaxForRest(login) {
        var json;
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/rest/checkeduser/",
            data: login,
            contentType: false,
            cache: false,
            processData: false,
            success: function (result) {
                json = jQuery.parseJSON(result);

                if (json) {

                    $("#fieldset").attr("disabled");
                    $("#login").css('box-shadow', '0 0 0 0.2rem rgba(232, 4, 36, 0.25) !important');
                    $("#login").css("background-color", "aqua");

                    console.log("Пользователь уже существет");
                } else {
                    console.log("Пользователь не найден");
                }
            },
        });
    }

});