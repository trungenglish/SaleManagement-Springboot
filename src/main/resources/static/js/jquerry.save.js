$("#form-add-user").submit(function(e) {
    e.preventDefault();

    var user = {
        username: $("#InputUsername").val(),
        fullname: $("#InputFullname").val(),
        email: $("#InputEmail1").val(),
        phone: $("#InputPhone").val(),
        password: $("#InputPassword1").val()
    };

    $.ajax({
        url: '/users/save',
        type: 'POST',
        data: JSON.stringify(user),
        success: function(response, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("User saved successfully");
            } else if (xhr.status === 409) {
                alert(response);
            }
        },
        error: function() {
            alert("An error occurred");
        }
    });
});