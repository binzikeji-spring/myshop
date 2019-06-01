<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 用户管理</title>
    <jsp:include page="../includes/header.jsp" />
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp" />
    <jsp:include page="../includes/menu.jsp" />
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">${tbUser.id == null ? '新增':'修改'}用户</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-${baseResult.status == 200 ? 'success' : 'danger'} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <p>${baseResult.message}</p>
                        </div>
                    </c:if>
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbUser.id == null ? '新增':'修改'}用户</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <from:form id="inputForm" cssClass="form-horizontal" action="/user/save" method="post" modelAttribute="tbUser">
                            <from:hidden path="id" />
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="email" class="col-sm-4 control-label">邮箱：</label>
                                    <div class="col-sm-4">
                                        <from:input path="email" cssClass="form-control required email" placeholder="邮箱" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="col-sm-4 control-label">密码：</label>
                                    <div class="col-sm-4">
                                        <form:password path="password" cssClass="form-control required" placeholder="密码" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="username" class="col-sm-4 control-label">用户名：</label>
                                    <div class="col-sm-4">
                                        <from:input path="username" cssClass="form-control required" placeholder="用户名" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="phone" class="col-sm-4 control-label">手机号：</label>
                                    <div class="col-sm-4">
                                        <from:input path="phone" cssClass="form-control required mobile" placeholder="手机号" />
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer col-md-offset-5">
                                <button type="button" class="btn btn-default">返回</button>
                                <button type="submit" class="btn btn-info">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </from:form>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../includes/footer.jsp" />
</div>
<jsp:include page="../includes/script.jsp" />
</body>
</html>
