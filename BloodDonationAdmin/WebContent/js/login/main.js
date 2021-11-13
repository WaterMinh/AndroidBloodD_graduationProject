/* =========================================== */
/* =========================================== */
function loginEnter(event) {
	var key = event.keyCode || event.which;
	if (key === 13) {
		login();
	}
}

function login() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    if (email == null || email == "" || password == null || password == "") {
        swal({
            title: "",
            text: "Bạn chưa điền đầy đủ thông tin đăng nhập",
            icon: "error",
            close: true,
            button: "Thử lại",
          });
    } else if (!RegexEmail(email)) {
		swal({
            title: "",
            text: "Email không đúng định dạng",
            icon: "error",
            close: true,
            button: "Thử lại",
          });
	} else {
		$.ajax({
			url: "Login",
			type: "post",
			dataType: "text",
			data: {
				email: email,
				password: password
			},
			success: function(result) {
				var json = $.parseJSON(result);
				if (json.success) {
					window.location = "Admin";
				} else {
					swal({
						title: "",
						text: json.message,
						icon: "error",
						close: true,
						button: "Thử lại",
					});
				}
			},
			error: function(xhr, status, error) {
				swal({
					title: "",
					text: "Đã xảy ra lỗi",
					icon: "error",
					close: true,
					button: "Thử lại",
				});
			}
		});
	}
}


function RegexEmail(emailInput) {
    var emailRegexStr = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    var isvalid = emailRegexStr.test(emailInput);
    if (!isvalid) {
        return false;
    } else {
		return true;
	}
}


