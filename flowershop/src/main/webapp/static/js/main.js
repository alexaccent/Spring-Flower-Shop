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
});