$(document).ready(function () {
    $("#createuser").click(async function (){
        let varData = {
            "first_name": $("#first_name").val(),
            "last_name": $("#last_name").val(),
            "login": $("#login").val(),
            "middle_name": $("#middle_name").val(),
            "password_hash": $("#password_hash").val()
        };
        let response = await fetch("http://localhost:8080/api/project/users", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: varData
        });
        console.log(response)
    });

})