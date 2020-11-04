<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
	<div class="menu_section">
		<h3>General</h3>
			<ul class="nav side-menu" id="home">
				<li><a href='<c:url value="/index"></c:url>'><i class="fa fa-home"></i>Trang chủ  </a>					
				</li>
			</ul>		
			<ul class="nav side-menu" id="inventory">
				<li><a><i class="	fa fa-industry"></i>Kho <span class="fa fa-chevron-down"></span></a>
					<ul class="nav child_menu">
						<li id="productstocklist"><a href='<c:url value="/product-stock/list"></c:url>'>Sản phẩm</a></li>											
						<li id="goodsissuelist"><a href='<c:url value="/goods-issue/list"></c:url>'>Danh sách xuất kho</a></li>	
						<li id="goodsreceiptlist"><a href='<c:url value="/goods-receipt/list"></c:url>'>Danh sách nhập kho</a></li>							
					</ul>
				</li>
			</ul>
			<ul class="nav side-menu" id="maintenance">
				<li><a><i class="fa fa-tasks"></i>Bảo Trì <span class="fa fa-chevron-down"></span></a>
					<ul class="nav child_menu">
						<li id="maintenanceaction"><a href='<c:url value="/maintenance/add"></c:url>'>Phiếu bảo trì</a></li>
						<li id="maintenancelist"><a href='<c:url value="/maintenance/list"></c:url>'>Danh sách bảo trì</a></li>						
					</ul>
				</li>
			</ul>
			<c:if test="${userInfo.role == 1 }">
				<ul class="nav side-menu" id="manage">
					<li><a><i class="fa fa-users"></i>Quản lý<span class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu">
							<li id="inventorylist"><a href='<c:url value="/inventory/list"></c:url>'>Kho</a></li>
							<li id="userlist"><a href='<c:url value="/user/list"></c:url>'>Tài khoản</a></li>	
							<li id="productlist"><a href='<c:url value="/product/list"></c:url>'>Sản phẩm</a></li>
							<li id="categorylist"><a href='<c:url value="/category/list"></c:url>'>Danh mục</a></li>					
						</ul>
					</li>
				</ul>
			</c:if>
	</div>
</div>