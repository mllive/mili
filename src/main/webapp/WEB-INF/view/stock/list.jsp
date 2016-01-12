<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../inc/meta.jsp"%>
<%@include file="../inc/common.jsp"%>
<%@include file="../inc/jquerymobile.jsp"%>
<html>
<head>
<title><spring:message code="root.title" /></title>
<script>
	$(function() {
		init();
		//setInterval("init()", 1000 * 5);
	});
	function init() {
		$.ajax({
			url : 'json',
			data : {
				codes : '${codes}',
				timeid : Math.random()
			},
			cache : false,
			dataType : "json",
			success : function(result) {
				if (result.data) {
					var bodylist = $("#bodylist");
					bodylist.html("");
					var data = result.data;
					reload(data);
				} else {
					alert('数据加载失败！');
				}
			},
			error : function(result) {
				alert('数据加载失败！');
			}
		});
	}
	function reload(data) {
		for (var i = 0; i < data.length; i++) {
			var tdnamecode = $("<td>" + data[i].name + data[i].code + "</td>");
			var nowprice = $("<td>" + data[i].nowprice + "</td>");
			var upv = data[i].nowprice - data[i].closeprice;
			var upvtd = $("<td>" + upv.toFixed(2) + "</td>");
			var up = (data[i].nowprice - data[i].closeprice)
					/ data[i].closeprice * 100;
			var uptd = $("<td>" + up.toFixed(2) + "%</td>");
			var maxprice = $("<td>" + data[i].maxprice + "</td>");
			var minprice = $("<td>" + data[i].minprice + "</td>");
			var openprice = $("<td>" + data[i].openprice + "</td>");
			var closeprice = $("<td>" + data[i].closeprice + "</td>");
			var datetime = $("<td>" + data[i].date + " " + data[i].time
					+ "</td>");
			var tr;
			if (upv > 0) {
				tr = $("<tr style='color: red'></tr>");
			} else if (upv < 0) {
				tr = $("<tr style='color: green'></tr>");
			} else {
				tr = $("<tr></tr>");
			}
			tdnamecode.appendTo(tr);
			nowprice.appendTo(tr);
			upvtd.appendTo(tr);
			uptd.appendTo(tr);
			maxprice.appendTo(tr);
			minprice.appendTo(tr);
			openprice.appendTo(tr);
			closeprice.appendTo(tr);
			datetime.appendTo(tr);
			tr.appendTo(bodylist);
		}
	}
	function addcode() {
		var code = $("#newcode").val();
		if (code.length != 8) {
			alert("请输入正确的股票代码");
			return;
		}
		$.ajax({
			url : '/addcode',
			data : {
				newcode : $("#newcode").val(),
				timeid : Math.random()
			},
			cache : false,
			dataType : "json",
			success : function(result) {
				if (result.success) {
					alert();
				} else {
					alert('数据加载失败！');
				}
			},
			error : function(result) {
				alert('数据加载失败！');
			}
		});
	}
</script>
</head>
<body>
	<div data-role="page" id="pageone">
		<div data-role="header" data-theme="b" data-position="fixed">
			<a href="#" style="display: none;"
				class="ui-btn ui-corner-all ui-shadow ui-icon-home ui-btn-icon-left">主页</a>
			<h1>stock列表</h1>
			<a href="javascript:void(0)" onclick="window.location.reload();"
				class="ui-btn ui-corner-all ui-shadow ui-icon-refresh ui-btn-icon-left">刷新</a>
		</div>
		<div data-role="main" class="ui-content">
			<form role="form" action="">
				<input type="text" class="form-control" id="newcode" name="newcode"
					placeholder="请输入股票代码">
				<button type="button" class="btn btn-default" onclick="addcode();">添加</button>
			</form>
			<table data-role="table" data-mode="columntoggle"
				class="ui-responsive" data-column-btn-text="选择列" id="myTable">
				<thead>
					<tr>
						<th>名称代码</th>
						<th>当前价</th>
						<th data-priority="1">涨跌额</th>
						<th data-priority="2">涨跌幅</th>
						<th data-priority="3">最高</th>
						<th data-priority="4">最低</th>
						<th data-priority="5">今开</th>
						<th data-priority="6">昨收</th>
						<th data-priority="6">更新时间</th>
					</tr>
				</thead>
				<tbody id="bodylist">
					
				</tbody>
			</table>
		</div>

		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</div>
</body>
</html>
