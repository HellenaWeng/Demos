<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/8/16
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    request.setAttribute("basePath", basePath);
%>
