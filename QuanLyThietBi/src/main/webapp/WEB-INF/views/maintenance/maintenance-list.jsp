<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="right_col" role="main">
	<div class="">
			<div class="page-title">
				<div class="title_left">
					<h4>Bảo trì</h4>
				</div>
				<div class="clearfix"></div>
			</div>
				<div class="clearfix"></div>
			<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_content">
									<br />
									<c:url value="/maintenance/list/1" var="searchUrl"></c:url>
									<form:form servletRelativeAction="${searchUrl}" method="POST" modelAttribute="searchForm" cssClass="form-horizontal form-label-left">
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="idProduct">Tên sản phẩm <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:select path="idProduct" cssClass="form-control">
													<form:option value="0">Chọn sản phẩm</form:option>
													<c:forEach items="${listProduct}" var="item">
														<form:option value="${item.id}">${item.name}  - ${item.code}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Tên người phụ trách <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="name" cssClass="form-control"/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="status">Trạng thái <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:select path="status" cssClass="form-control">
													<form:option value="0">Chọn trạng thái</form:option>
													<form:option value="1">Đang xử lý</form:option>
													<form:option value="2">Đã hoàn thành</form:option>
												</form:select>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Từ :  <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="dateTo" type="date" cssClass="form-control"/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Đến <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="dateFrom"	type="date" cssClass="form-control"/>
											</div>
										</div>
										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-search"></i> Search</button>												
												<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>												
											</div>
										</div>

									</form:form>
								</div>
							</div>
						</div>
					</div>

		<div id="alert">
			
		</div>	
	<div class="table-responsive">
		<a href='<c:url value="/maintenance/add"></c:url>'><button class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i>Thêm</button></a>
		<!-- 
			<a data-toggle="modal" data-target="#excel-modal"	href="javascript:void(0)" ><button class="btn btn-success" title="import sản phẩm"><i class="glyphicon glyphicon-import"></i> Import</button></a>
		<a href='<c:url value="/maintenance/excel-file"></c:url>'><button class="btn btn-default" title="lấy mẫu import"><i class="glyphicon glyphicon-file"></i> Document</button></a>
		 -->
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">#</th>
                            <th class="column-title">Id</th>
                            <th class="column-title">Tên người phụ trách</th>  
                            <th class="column-title">Tên sản phẩm</th>
                            <th class="column-title">Trạng thái</th>                        
                            <th class="column-title no-link last text-center" colspan="3" ><span class="nobr">Action</span>
                            </th>
                          </tr>
                        </thead>

                        <tbody>
                          <c:forEach items="${list}" var="item" varStatus="i"> 
                          	<tr>
                            <td>${pageInfo.offSet + i.index + 1} </td>
                            <td>${item.id}</td>                        
                        	<td>${item.name}</td>
                        	<td>${item.productDTO.name}</td>
                        	<td>
                        		<c:choose>
                        			<c:when test="${item.status == 1}">
                        				Đang xử lý
                        			</c:when>
                        			<c:otherwise>
                        				Đã hoàn hành
                        			</c:otherwise>
                        		</c:choose>
                        	</td>
                            <td colspan="3" class="last text-center">                           
                            	<a href='<c:url value="/history/list/${item.id}"></c:url>' title="lịch sử" class="btn btn-info" ><i class="glyphicon glyphicon-list"></i></a>
	                            <a href='<c:url value="/maintenance/view/${item.id}"></c:url>' class="btn btn-primary"><i class="glyphicon glyphicon-eye-open"></i></a> 
	                            <a href='<c:url value="/maintenance/edit/${item.id}"></c:url>' class="btn btn-warning"><i class="glyphicon glyphicon-edit"></i></a> 
	                           <!-- <a href="javascript:void(0)" onclick="deleteItem(${item.id})" class="btn btn-danger btn-delete"><i class="glyphicon glyphicon-trash"></i></a>  -->
	                           
                            </td>                  
                          	</tr>
                          </c:forEach>
							
                        </tbody>
                      </table>
     <jsp:include page="/WEB-INF/views/layout/paging.jsp"/>                      


      </div>

		<div id="excel-modal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
				  <form action='<c:url value="/manage/maintenance/import-excel"></c:url>' method="post" enctype="multipart/form-data">
					<div class="modal-header">
						<p class="modal-title">Import sản phẩm</p>
						<button class="close" data-dismiss="modal" >&times;</button>
					</div>
					<div class="modal-body">
						<input type="file" name="file">
					</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-success" >Có</button>
							<button  class="btn btn-danger" data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function gotoPage(page){
		$("#searchForm").attr('action',"<c:url value='/manage/maintenance/list/'/>"+page);
		$("#searchForm").submit();
	}
	function deleteItem(id){
		if(confirm("Bạn có chắc chắn xóa nó không ?")){
			$.ajax({
				type : 'delete',
				url : "<c:url value='/categories/'/>"+id,
				success : function(result){
					var str =  '<div class="alert alert-success" role="alert">'
							+  ' Xóa thành công !'
							+  '<button type="button" class="close" data-dismiss="alert" aria-label="Close">'
							+	'<span aria-hidden="true">&times;</span>'
							+	'</button>'
					$('#alert').html(str);
				},
				error : function(result){
					var str =  '<div class="alert alert-danger" role="alert">'
						+  ' Xóa thất bại !'
						+  '<button type="button" class="close" data-dismiss="alert" aria-label="Close">'
						+	'<span aria-hidden="true">&times;</span>'
						+	'</button>'
					$('#alert').html(str);
				}
			});
		}
	}
	
	$(document).ready(function(){
		var msgError = '${msgError}';
		var msgSuccess ='${msgSuccess}';
		if(msgError){
			new PNotify({
		        title: 'Thông Báo',
		        text: msgError,
		        type: 'error',
		        styling: 'bootstrap3'
		        
		    });	
		}
		if(msgSuccess){
			new PNotify({
		        title: 'Thông Báo',
		        text: msgSuccess,
		        type: 'success',
		        styling: 'bootstrap3'
		    });	
		}
	});
	
	$(document).ready(function(){
		$('#maintenancelist').parents("li").addClass('active').siblings().removeClass("active");
		$('#maintenancelist').addClass('current-page').siblings().removeClass("current-page");
		$('#maintenancelist').parents().show();
	});
	

	
</script>



