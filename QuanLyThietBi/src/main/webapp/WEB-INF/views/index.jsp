<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
  <div class="right_col" role="main">
          <!-- top tiles -->
  <div class="row" style="display: inline-block;" >
          <div class="row" style="display: inline-block;" >
          <div class="tile_count">
            <div class="col-md-2 col-sm-4  tile_stats_count">
              <span class="count_top"><i class="fa fa-tasks"></i> Đang bảo trì</span>
               <div class="count">0</div>
              <span class="count_bottom"><i class="fa fa-clock-o"></i> Tháng 11</span>
            </div>
            <div class="col-md-2 col-sm-4  tile_stats_count">
              <span class="count_top"><i class="fa fa-tasks"></i> Đã bảo trì xong</span>
               <div class="count">0</div>
              <span class="count_bottom"><i class="fa fa-clock-o"></i> Tháng 11</span>
            </div>
            <div class="col-md-2 col-sm-4  tile_stats_count">
             <span class="count_top"><i class="fa fa-truck" aria-hidden="true"></i> Nhập hàng</span>
               <div class="count">0</div>
              <span class="count_bottom"><i class="fa fa-clock-o"></i> Tháng 11</span>
            </div>
            <div class="col-md-2 col-sm-4  tile_stats_count">
              <span class="count_top"><i class="fa fa-truck" aria-hidden="true"></i> Xuất hàng</span>
              <div class="count">0</div>
              <span class="count_bottom"><i class="fa fa-clock-o"></i> Tháng 11</span>
            </div>
            <div class="col-md-2 col-sm-4  tile_stats_count">
              <span class="count_top"><i class="fa fa-laptop" aria-hidden="true"></i> Thiết bị tồn kho</span>
              <div class="count">0</div>
              <span class="count_bottom"><i class="fa fa-clock-o"></i> Hiện tại</span>
            </div>
            <div class="col-md-2 col-sm-4  tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Tổng số tài khoản</span>
               <div class="count">0</div>
              <span class="count_bottom"><i class="fa fa-clock-o"></i> Hiện tại</span>
            </div>
          </div>
        </div>
          </div>
        </div>
         

     




          
        </div>		
<script>
$(document).ready(function(){
	$('#home').parents("li").addClass('active').siblings().removeClass("active");
	$('#home').addClass('current-page').siblings().removeClass("current-page");
	$('#home').parents().show();
});
</script>