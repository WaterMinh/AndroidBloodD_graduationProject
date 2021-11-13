<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main class="app-content">
	<div class="app-title">
		<ul class="app-breadcrumb breadcrumb side">
			<li class="breadcrumb-item active"><a href="#"><b>Danh sách người dùng</b></a></li>
		</ul>
		<div id="clock"></div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="tile">
				<div class="tile-body">

					<div class="row element-button">
						<div class="col-sm-2">

							<a class="btn btn-add btn-sm" href="AddUser"
								title="Thêm"><i class="fas fa-plus"></i> Tạo mới người dùng</a>
						</div>

						<div class="col-sm-2">
							<a class="btn btn-delete btn-sm print-file" type="button"
								title="In" onclick="myApp.printTable()"><i
								class="fas fa-print"></i> In dữ liệu</a>
						</div>
						<div class="col-sm-2">
							<a class="btn btn-delete btn-sm print-file js-textareacopybtn"
								type="button" title="Sao chép"><i class="fas fa-copy"></i>
								Sao chép</a>
						</div>

						<div class="col-sm-2">
							<a class="btn btn-excel btn-sm" href="" title="In"><i
								class="fas fa-file-excel"></i> Xuất Excel</a>
						</div>
						<div class="col-sm-2">
							<a class="btn btn-delete btn-sm pdf-file" type="button"
								title="In" onclick="myFunction(this)"><i
								class="fas fa-file-pdf"></i> Xuất PDF</a>
						</div>
					</div>
					<table class="table table-hover table-bordered js-copytextarea"
						cellpadding="0" cellspacing="0" border="0" id="sampleTable">
						<thead>
							<tr>
								<th width="10"><input type="checkbox" id="all"></th>
								<th class="dt-center">ID</th>
								<th width="140">Họ và tên</th>
								<th width="20">Ảnh thẻ</th>
								<th>Ngày sinh</th>
								<th class="dt-center">Giới tính</th>
								<th>SĐT</th>
								<th width="250">Địa chỉ</th>
								<th>Email</th>
								<th class="dt-center">Số CMND/CCCD</th>
								<th class="dt-center">Nghề nghiệp</th>
								<th class="dt-center">Nhóm máu</th>
								<th class="dt-center">Admin</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listUser}" var="u">
								<tr>
									<td width="10"><input type="checkbox" name="check1"
										value="1"></td>
									<td class="dt-center">${u.userId}</td>
									<td>${u.fullName}</td>
									<td><img class="img-card-person" src="http://localhost:8080/BloodDonation${u.avatar}"
										alt=""></td>
									<td>${u.birthday}</td>
									<td class="dt-center">${u.gender?"Nam":"Nữ"}</td>
									<td>${u.telephone}</td>
									<td>${u.address}</td>
									<td>${u.email}</td>
									<td class="dt-center">${u.identityCard}</td>
									<td class="dt-center">${u.job}</td>
									<td class="dt-center">${u.bloodType}</td>
									<td class="dt-center"><c:if test="${u.permission == 1}"><i class="fas fa-check"></i></c:if></td>
									<td class="dt-center"><button
											class="btn btn-primary btn-sm trash" type="button"
											title="Xóa" onclick="deleteU(this)">
											<i class="fas fa-trash-alt"></i>
										</button>
										<button class="btn btn-primary btn-sm edit" type="button"
											title="Sửa" onclick="updateU(this)">
											<i class="fas fa-edit"></i>
										</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</main>

<!-- Essential javascripts for application to work-->
<script src="js/admin/jquery-3.2.1.min.js"></script>
<script src="js/admin/popper.min.js"></script>
<script src="js/admin/bootstrap.min.js"></script>
<script src="js/admin/plugins/jquery.table2excel.js"></script>
<script src="js/admin/main.js"></script>
<!-- The javascript plugin to display page loading on top-->
<script src="js/admin/plugins/pace.min.js"></script>
<!-- Page specific javascripts-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
<!-- Data table plugin-->
<script type="text/javascript"
	src="js/admin/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="js/admin/plugins/dataTables.bootstrap.min.js"></script>
<script type="text/javascript">$('#sampleTable').DataTable();</script>
<script>
    function deleteRow(r) {
      var i = r.parentNode.parentNode.rowIndex;
      document.getElementById("sampleTable").deleteRow(i);
    }
    
    function updateU(r) {
    	var oTable = $('#sampleTable').DataTable()
    	var i = r.parentNode.parentNode.rowIndex - 1;
    	var data = oTable.row(i).data();
    	if (data != null) {
    		window.location = "ManageUsers?id=" + data[1];
    	}
    }
    
    function deleteU(r) {
    	var oTable = $('#sampleTable').DataTable()
    	var i = r.parentNode.parentNode.rowIndex - 1;
    	var data = oTable.row(i).data();
    	if (data != null) {
    		swal({
    	          title: "Cảnh báo",
    	          text: "Bạn có chắc chắn là muốn xóa người dùng này?",
    	          buttons: ["Hủy bỏ", "Đồng ý"],
    	        }).then((willDelete) => {
    	            if (willDelete) {
    	            	$.ajax({
    	        			url: "ManageUsers",
    	        			type: "post",
    	        			dataType: "text",
    	        			data: {
    	        				userId: data[1],
    	        				method: "delete"
    	        			},
    	        			success: function(result) {
    	        				var json = $.parseJSON(result);
    	        				if (json.success) {
    	        					swal(json.message, {});
    	        					deleteRow(r);
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
    	    	});
    	}
        
    }
    
    $('#all').click(function (e) {
      $('#sampleTable tbody :checkbox').prop('checked', $(this).is(':checked'));
      e.stopImmediatePropagation();
    });

    //EXCEL
    // $(document).ready(function () {
    //   $('#').DataTable({

    //     dom: 'Bfrtip',
    //     "buttons": [
    //       'excel'
    //     ]
    //   });
    // });

    //In dữ liệu
    var myApp = new function () {
      this.printTable = function () {
        var tab = document.getElementById('sampleTable');
        var win = window.open('', '', 'height=700,width=700');
        win.document.write(tab.outerHTML);
        win.document.close();
        win.print();
      }
    }
    //     //Sao chép dữ liệu
    //     var copyTextareaBtn = document.querySelector('.js-textareacopybtn');

    // copyTextareaBtn.addEventListener('click', function(event) {
    //   var copyTextarea = document.querySelector('.js-copytextarea');
    //   copyTextarea.focus();
    //   copyTextarea.select();

    //   try {
    //     var successful = document.execCommand('copy');
    //     var msg = successful ? 'successful' : 'unsuccessful';
    //     console.log('Copying text command was ' + msg);
    //   } catch (err) {
    //     console.log('Oops, unable to copy');
    //   }
    // });
  </script>
<%@ include file="footer.jsp"%>