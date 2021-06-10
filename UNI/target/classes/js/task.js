$(document).ready(function () {
    $("#task1").click(function () {
        $.ajax({
            url:"http://127.0.0.1:8080/api/project/task/all",
            type: "GET",
            dataType: "json",
            success: function (response) {
                console.log(response);

            },
            error: function (response){
                console.log("ОШИБКА ОШИБКА ОШИБКА", response);

            }
        });

    });
    $("#createTask").click(async function (){
        let varData = {
            "title": "M",
            "body": "fs",
            "developer":{
                "id":2,
                "first_name": "pasha",
                "last_name":"uiop",
                "login":"wwrr",
                "middle_name":"aaa",
                "password_hash": 190
            },
            "analyst":{
                "id":1,
                "first_name": "pasha",
                "last_name":"uiop",
                "login":"wwrr",
                "middle_name":"aaa",
                "password_hash": 190

            },
            "tester":{
                "id":3
            },
            "status":{
                "id":1

            }
        };
        let response = await fetch("http://localhost:8080/api/project/tasks", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: varData
        });
        console.log(response)
    });



})