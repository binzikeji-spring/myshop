<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容分类</title>
    <jsp:include page="../includes/header.jsp" />
    <link rel="stylesheet" href="/static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css" />
</head>
<style>
    .example-modal .modal {
        position: relative;
        top: auto;
        bottom: auto;
        right: auto;
        left: auto;
        display: block;
        z-index: 1;
    }

    .example-modal .modal {
        background: transparent !important;
    }
</style>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp" />
    <jsp:include page="../includes/menu.jsp" />
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容分类
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">内容分类</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <!-- /.row -->
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-${baseResult.status == 200 ? 'success' : 'danger'} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <p>${baseResult.message}</p>
                        </div>
                    </c:if>
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容分类</h3>
                        </div>
                        <div class="box-body">
                            <a href="/content/category/form" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 添加</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> 批量删除</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" class="btn btn-default btn-sm"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" class="btn btn-default btn-sm"><i class="fa fa-upload"></i> 导出</a>&nbsp;&nbsp;&nbsp;
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="treeTable" class="table table-hover">
                                <thead>
                                    <tr>
                                        <td>ID</td>
                                        <td>名称</td>
                                        <td>排序</td>
                                        <td>操作</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${tbContentCategories}" var="tbContentCategory">
                                        <tr id="${tbContentCategory.id}" pId="${tbContentCategory.parent.id}">
                                            <td>${tbContentCategory.id}</td>
                                            <td>${tbContentCategory.name}</td>
                                            <td>${tbContentCategory.sortOrder}</td>
                                            <td>
                                                <a href="/content/category/form?id=${tbContentCategory.id}" class="btn btn-info btn-sm"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;
                                                <button onclick="App.deleteSingle('/content/category/delete', '${tbContentCategory.id}');" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> 删除</button>&nbsp;&nbsp;&nbsp;
                                                <a href="/content/category/form?parent.id=${tbContentCategory.id}&parent.name=${tbContentCategory.name}" class="btn btn-default btn-sm"><i class="fa fa-plus"></i> 新增下级菜单</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../includes/footer.jsp" />
</div>
<jsp:include page="../includes/script.jsp" />
<script src="/static/assets/plugins/treeTable/jquery.treeTable.min.js"></script>
<!-- 模态框 -->
<sys:modal />
<script>
    $(function () {
        $("#treeTable").treeTable({
            expandLevel: 2,
            column: 1
        });
    });
</script>
</body>
</html>
