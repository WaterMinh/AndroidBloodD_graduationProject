<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main class="app-content">
	<div class="app-title">
		<ul class="app-breadcrumb breadcrumb side">
			<li class="breadcrumb-item active"><a href="#"><b>Danh sách lịch sử hiến máu</b></a></li>
		</ul>
		<div id="clock"></div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="tile">
				<div class="tile-body">

					<div class="row element-button">
						<div class="col-sm-2">

							<a class="btn btn-add btn-sm" href="AddHistory"
								title="Thêm"><i class="fas fa-plus"></i> Đặt lịch hiến máu</a>
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
								<th>Tên</th>
								<th class="dt-center">Lần hiến thứ</th>
								<th class="dt-center">Cân nặng</th>
								<th class="dt-center">Số lượng</th>
								<th class="dt-center">Ngày hiến máu</th>
								<th>Nơi hiến máu</th>
								<th class="dt-center">Sức khoẻ</th>
								<th></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
								<th style="display:none;"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listHistory}" var="u">
								<tr>
									<td width="10"><input type="checkbox" name="check1"
										value="1"></td>
									<td class="dt-center">${u.idHis}</td>
									<td>${u.fullName}</td>
									<td class="dt-center">${u.times}</td>
									<td class="dt-center">${u.weight} kg</td>
									<td class="dt-center">${u.amountOfBlood}ml</td>
									<td class="dt-center">${u.dayDonation}</td>
									<td>${u.placeName}</td>
									<td class="dt-center">
										<button class="btn btn-primary btn-sm eye" type="button"
											title="Xem" onclick="view(this)">
											<i class="fas fa-eye"></i>
										</button>
									</td>
									<td class="dt-center">
										<button class="btn btn-primary btn-sm trash" type="button"
											title="Xóa" onclick="deleteU(this)">
											<i class="fas fa-trash-alt"></i>
										</button>
										<button class="btn btn-primary btn-sm edit" type="button"
											title="Sửa" onclick="updateU(this)">
											<i class="fas fa-edit"></i>
										</button>
									</td>
									<td style="display:none;">${u.tinhTrang}</td>
									<td style="display:none;">${u.macBenh}</td>
									<td style="display:none;">${u.sutCan}</td>
									<td style="display:none;">${u.phauThuat}</td>
									<td style="display:none;">${u.xam}</td>
									<td style="display:none;">${u.truyenMau}</td>
									<td style="display:none;">${u.maTuy}</td>
									<td style="display:none;">${u.quanHe}</td>
									<td style="display:none;">${u.quanHeCungGioi}</td>
									<td style="display:none;">${u.vaccine}</td>
									<td style="display:none;">${u.vungDich}</td>
									<td style="display:none;">${u.benh}</td>
									<td style="display:none;">${u.diKham}</td>
									<td style="display:none;">${u.tanTat}</td>
									<td style="display:none;">${u.mangThai}</td>
									<td style="display:none;">${u.thuocKS}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</main>

<!--
  MODAL
-->
  <div class="modal fade" id="ModalUP" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static"
    data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">

        <div class="modal-body">
          <div class="row">
            <div class="form-group  col-md-12">
              <span class="thong-tin-thanh-toan">
                <h5>Thông tin sức khoẻ</h5>
              </span>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-md-4">
              <label class="control-label">Đã từng hiến máu</label>
              <p id="health10"><i class="fas fa-check"></i></p>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Mắc bệnh nền</label>
              <p id="health11"><i class="fas fa-times"></i></p>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Sụt cân</label>
              <p id="health12"><i class="fas fa-check"></i></p>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Phẫu thuật</label>
              <p id="health13"><i class="fas fa-check"></i></p>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Xăm hình</label>
              <p id="health14"><i class="fas fa-check"></i></p>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Được truyền máu</label>
              <p id="health15"><i class="fas fa-check"></i></p>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Sử dụng ma tuý</label>
              <p id="health16"><i class="fas fa-check"></i></p>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Quan hệ nhiễm bệnh</label>
              <p id="health17"><i class="fas fa-check"></i></p>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Quan hệ cùng giới</label>
              <p id="health18"><i class="fas fa-check"></i></p>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Tiêm vaccine</label>
              <p id="health19"><i class="fas fa-check"></i></p>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Đến từ vùng dịch</label>
              <p id="health20"><i class="fas fa-check"></i></p>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Ho, nhức đầu, sốt</label>
              <p id="health21"><i class="fas fa-check"></i></p>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Xét nghiệm răng</label>
              <p id="health22"><i class="fas fa-check"></i></p>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Tàn tật</label>
              <p id="health23"><i class="fas fa-check"></i></p>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Mang thai</label>
              <p id="health24"><i class="fas fa-check"></i></p>
            </div>
            <div class="form-group col-md-12">
              <label class="control-label">Dùng thuốc kháng sinh</label>
              <p id="health25"><i class="fas fa-check"></i></p>
            </div>
          </div>
          <a class="btn btn-cancel" data-dismiss="modal" href="#">Đóng</a>
          <BR>
        </div>
        <div class="modal-footer">
        </div>
      </div>
    </div>
  </div>
  <!--
  MODAL
-->

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
    		window.location = "ManageHistories?id=" + data[1];
    	}
    }
    
    function deleteU(r) {
    	var oTable = $('#sampleTable').DataTable()
    	var i = r.parentNode.parentNode.rowIndex - 1;
    	var data = oTable.row(i).data();
    	if (data != null) {
    		swal({
    	          title: "Cảnh báo",
    	          text: "Bạn có chắc chắn là muốn xóa lịch sử này?",
    	          buttons: ["Hủy bỏ", "Đồng ý"],
    	        }).then((willDelete) => {
    	            if (willDelete) {
    	            	$.ajax({
    	        			url: "ManageHistories",
    	        			type: "post",
    	        			dataType: "text",
    	        			data: {
    	        				idHis: data[1],
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
    function view(r) {
    	var oTable = $('#sampleTable').DataTable()
    	var index = r.parentNode.parentNode.rowIndex - 1;
    	var data = oTable.row(index).data();
    	if (data != null) {
    		for (var i = 10; i <= 25; i++) {
    			if (data[i] == "true") {
    				document.getElementById('health' + i).innerHTML = '<i class="fas fa-check">';
    			} else {
    				document.getElementById('health' + i).innerHTML = '<i class="fas fa-times">';
    			}
    		}
      		$("#ModalUP").modal({ backdrop: false, keyboard: false })
    	}
    }
  </script>
<%@ include file="footer.jsp"%>