<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/all.css"/>
		<link rel="stylesheet" type="text/css" href="css/pop.css"/>
		<link rel="stylesheet" type="text/css" href="css/main.css"/>
		<script type="text/javascript">
			function bodyInit(){
				if('${result.msg}'){
					alert('${result.msg}');
				}
			}
		</script>
	</head>
	<body style="background: #e1e9eb;" onload="bodyInit();">
		<form action="" id="mainForm" method="post">
			<div class="right">
				<div class="current">
					当前位置：<a href="#">导入/导出</a> &gt; 导入结果
				</div>
				<div class="rightCont">
					<p class="g_title fix">导入结果展示</p>
					<table class="tab1">
						<tbody>
							<tr>
								<td align="right" width="80">标题：</td>
								<td>${result.title}</td>
<%--								<td>EXCEL文档</td>--%>
							</tr>
						</tbody>
					</table>
					<div class="zixun fix">
						<table class="tab2" width="100%">
							<tbody>
								<tr>
									<th>序号</th>
									<th>标题</th>
									<th>内容</th>
<%--									<th>出生日期</th>--%>
								</tr>
								<c:forEach items="${result.prescriptionList}" var="item" varStatus="s">
									<tr>
<%--										<td>${s.count}</td>--%>
<%--										<td>${item.name}</td>--%>
<%--										<td>${item.age}</td>--%>
<%--&lt;%&ndash;										<td>${item.time}</td>&ndash;%&gt;--%>
<%--										<td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd"/></td>--%>
                                        <td width="5%">${s.count}</td>
                                        <td width="10%">${item.title}</td>
                                        <td>${item.content}</td>
									</tr>

								</c:forEach>
<%--								<tr>--%>
<%--									<td>1</td>--%>
<%--									<td>张三</td>--%>
<%--									<td>25</td>--%>
<%--									<td>1990-09-01</td>--%>
<%--								</tr>--%>
<%--								<tr>--%>
<%--									<td>2</td>--%>
<%--									<td>李四</td>--%>
<%--									<td>25</td>--%>
<%--									<td>1990-09-01</td>--%>
<%--								</tr>--%>
<%--								<tr>--%>
<%--									<td>3</td>--%>
<%--									<td>王五</td>--%>
<%--									<td>25</td>--%>
<%--									<td>1990-09-01</td>--%>
<%--								</tr>--%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>