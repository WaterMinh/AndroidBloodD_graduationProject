<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<style>
.Choicefile {
	display: block;
	background: #14142B;
	border: 1px solid #fff;
	color: #fff;
	width: 150px;
	text-align: center;
	text-decoration: none;
	cursor: pointer;
	padding: 5px 0px;
	border-radius: 5px;
	font-weight: 500;
	align-items: center;
	justify-content: center;
}

.Choicefile:hover {
	text-decoration: none;
	color: white;
}

#uploadfile, .removeimg {
	display: none;
}

#thumbbox {
	position: relative;
	width: 100%;
	margin-bottom: 20px;
}

.removeimg {
	height: 25px;
	position: absolute;
	background-repeat: no-repeat;
	top: 5px;
	left: 5px;
	background-size: 25px;
	width: 25px;
	/* border: 3px solid red; */
	border-radius: 50%;
}

.removeimg::before {
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
	content: '';
	border: 1px solid red;
	background: red;
	text-align: center;
	display: block;
	margin-top: 11px;
	transform: rotate(45deg);
}

.removeimg::after {
	/* color: #FFF; */
	/* background-color: #DC403B; */
	content: '';
	background: red;
	border: 1px solid red;
	text-align: center;
	display: block;
	transform: rotate(-45deg);
	margin-top: -2px;
}
</style>
<main class="app-content">
	<div class="app-title">
		<ul class="app-breadcrumb breadcrumb">
			<li class="breadcrumb-item">Danh sách tin tức</li>
			<li class="breadcrumb-item"><a href="#">Thêm tin tức</a></li>
		</ul>
		<div id="clock"></div>
	</div>
	<div class="row">
		<div class="col-md-12">

			<div class="tile">

				<h3 class="tile-title">Tạo mới tin tức</h3>
				<div class="tile-body">
					<form class="row">
						<input type="hidden" name="idNews" id="idNews" value="${u.idNews}" />
						<div class="form-group col-md-8">
							<label class="control-label">Tiêu đề</label> <input
								class="form-control" type="text" name="title" id="title" value="${u.title}" required>
						</div>
						<div class="form-group col-md-4">
							<label class="control-label">Ngày đăng</label> <input
								class="form-control" type="datetime-local" name="publicDate" id="publicDate" value="<fmt:formatDate value="${u.publicDate}" pattern="yyyy-MM-dd'T'HH:mm" />">
						</div>
						<div class="form-group col-md-8">
							<label class="control-label">Bài viết</label> <textarea
								class="form-control" name="info" id="info" required>${u.info}</textarea>
						</div>
						<div class="form-group col-md-4">
							<label class="control-label">Ảnh</label>
							<div id="myfileupload">
								<input type="file" id="uploadfile" name="ImageUpload"
									onchange="readURL(this);" />
							</div>
							<div id="thumbbox">
								<img height="300" width="300" alt="Thumb image" id="thumbimage"
									style="display: none" /> <a class="removeimg"
									href="javascript:"></a>
							</div>
							<div id="boxchoice">
								<a href="javascript:" class="Choicefile"><i
									class='bx bx-upload'></i></a>
								<p style="clear: both"></p>
							</div>

						</div>
					</form>
				</div>
				<button class="btn btn-save" type="button" onclick="save()">Lưu lại</button>
				<a class="btn btn-cancel" href="ManageNews">Hủy bỏ</a>
			</div>
		</div>
	</div>
</main>


<!-- Essential javascripts for application to work-->
<script src="js/admin/jquery-3.2.1.min.js"></script>
<script src="js/admin/popper.min.js"></script>
<script src="js/admin/bootstrap.min.js"></script>
<script src="js/admin/main.js"></script>
<!-- The javascript plugin to display page loading on top-->
<script src="js/admin/plugins/pace.min.js"></script>
<script>
	function save() {
		var idNews = document.getElementById("idNews").value;
		var title = document.getElementById("title").value;
		var publicDate = document.getElementById("publicDate").value;
		var info = document.getElementById("info").value;
		var image = document.getElementById("uploadfile").files[0];
		
		if (title == "" || publicDate == "" || info == "") {
	        swal({
	            title: "",
	            text: "Bạn chưa điền đầy đủ thông tin",
	            icon: "error",
	            close: true,
	            button: "Thử lại",
	          });
	    } else {
	    	if (image != null) {
	    		var formData = new FormData();
				formData.append("image", image);
				$.ajax({
					url : "http://192.168.0.110:8080/BloodDonation/api/bloodDonate2/upload",
					type : "post",
					contentType : false,
					processData : false,
					data : formData,
					success : function(result) {
						var json = $.parseJSON(result);
						if (json != null && json != "") {
							$.ajax({
								url: "AddNews",
								type: "post",
								dataType: "text",
								data: {
									idNews: idNews,
									title: title,
									publicDate: publicDate,
									info: info,
									image: json
								},
								success: function(result) {
									var json = $.parseJSON(result);
									if (json.success) {
										window.location = "ManageNews";
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
						} else {
							swal({
								title : "",
								text : "Upload ảnh thất bại",
								icon : "error",
								close : true,
								button : "Thử lại",
							});
						}
					},
					error : function(xhr, status, error) {
						swal({
							title : "",
							text : "Không kết nối được tới server",
							icon : "error",
							close : true,
							button : "Thử lại",
						});
					}
				});
	    	} else {
		    	$.ajax({
					url: "AddNews",
					type: "post",
					dataType: "text",
					data: {
						idNews: idNews,
						title: title,
						publicDate: publicDate,
						info: info
					},
					success: function(result) {
						var json = $.parseJSON(result);
						if (json.success) {
							window.location = "ManageNews";
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
	}
	
	function readURL(input, thumbimage) {
		if (input.files && input.files[0]) { //Sử dụng  cho Firefox - chrome
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#thumbimage").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		} else { // Sử dụng cho IE
			$("#thumbimage").attr('src', input.value);

		}
		$("#thumbimage").show();
		$('.filename').text($("#uploadfile").val());
		$('.Choicefile').css('background', '#14142B');
		$('.Choicefile').css('cursor', 'default');
		$(".removeimg").show();
		$(".Choicefile").unbind('click');

	}
	$(document).ready(function() {
		$(".Choicefile").bind('click', function() {
			$("#uploadfile").click();
		});
		$(".removeimg").click(function() {
			$("#thumbimage").attr('src', '')
					.hide();
			$("#myfileupload").html('<input type="file" id="uploadfile"  onchange="readURL(this);" />');
			$(".removeimg").hide();
			$(".Choicefile").bind('click', function() {
						$("#uploadfile").click();
					});
			$('.Choicefile').css('background',
					'#14142B');
			$('.Choicefile').css('cursor',
					'pointer');
			$(".filename").text("");
		});
	})
</script>
<%@ include file="footer.jsp"%>