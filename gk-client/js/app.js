// session id
var token = $.cookie('JSESSIOINID');

// user id
var id = $.cookie('id');


// logout button
$("#logout").click(function (event) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8484/api/user/logout/'+localStorage.getItem("token"),
        xhrFields: {
            withCredentials: true
        },
        crossDomain:true,
        success: function (data) {
            alert("user logged out");
            window.location = "index.html";
        },
        error: function (xhr, textStatus, error) {
            console.log(xhr.statusText);
            console.log(textStatus);
            console.log(error);
            //window.location = "index.html";

        }
    });
});


// viewModel
var ViewModel = function () {

    self = this;

    self.UsersList = ko.observableArray([]);
    self.ActiveUserList = ko.observableArray([]);
    
    // active user
    window.setInterval(function () {
    // Get users_list
        self.UsersList([]);
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8484/api/user/users',
        crossDomain: true,
        xhrFields: {
            withCredentials: true
        },
        success: function (users) {
            users.forEach(function (user) {
                self.UsersList.push(user);
            });
        },
        error: function (xhr, textStatus, error) {
            console.log("error");
            console.log(xhr.statusText);
            console.log(textStatus);
            console.log(error);
        },
        complete: function (data) {
            console.log(data);
        }
    });
}, 5000);

    // active user
    window.setInterval(function () {
        // Get users_list
        self.ActiveUserList([]);
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8484/api/user/recent',
            crossDomain: true,
            xhrFields: {
                withCredentials: true
            },
            success: function (users) {
                users.forEach(function (user) {
                    self.ActiveUserList.push(user);
                });
            },
            error: function (xhr, textStatus, error) {
                console.log("error");
                console.log(xhr.statusText);
                console.log(textStatus);
                console.log(error);
            },
            complete: function (data) {
                console.log(data);
            }
        });

    }, 3000);

};

// bind user list to viewmodel
ko.applyBindings(new ViewModel());


