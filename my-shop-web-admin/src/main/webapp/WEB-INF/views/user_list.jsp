<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 用户管理</title>
    <jsp:include page="../includes/header.jsp" />
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
                用户管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">用户管理</li>
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
                    <div class="box box-info box-info-search" style="display: none;">
                        <div class="box-header">
                            <h3 class="box-title">高级搜索</h3>
                        </div>
                        <div class="box-body">
                            <div class="row form-horizontal">
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="username" class="col-sm-4 control-label">姓名</label>
                                        <div class="col-sm-8">
                                            <input id="username" class="form-control" pattern="姓名">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="phone" class="col-sm-4 control-label">手机</label>
                                        <div class="col-sm-8">
                                            <input id="phone" class="form-control" pattern="手机">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="email" class="col-sm-4 control-label">邮箱</label>
                                        <div class="col-sm-8">
                                            <input id="email" class="form-control" pattern="邮箱">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="box-footer">
                            <button type="button" onclick="search();" class="btn btn-info pull-right">提交</button>
                        </div>
                    </div>
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>
                        </div>
                        <div class="box-body">
                            <a href="/user/form" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 添加</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" onclick="App.deleteMulti('/user/delete');" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> 批量删除</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" class="btn btn-default btn-sm"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" class="btn btn-default btn-sm"><i class="fa fa-upload"></i> 导出</a>&nbsp;&nbsp;&nbsp;
                            <button class="btn btn-primary btn-sm" onclick="$('.box-info-search').css('display') == 'none' ? $('.box-info-search').show('fast') : $('.box-info-search').hide('fast')"><i class="fa fa-search"></i> 展开高级搜索</button>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox" class="minimal icheck_master"></th>
                                        <th>ID</th>
                                        <th>用户名</th>
                                        <th>手机号</th>
                                        <th>邮箱</th>
                                        <th>更新时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody></tbody>
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
<script src="/static/assets/app/DataTime.js"></script>
<script>
    var _dataTable;
    $(function () {
        var _colums = [
            {"data": function ( row, type, val, meta ) {
                    return '<td><input id="'+row.id+'" type="checkbox" class="minimal"></td>';
                }
            },
            {"data": "id"},
            {"data": "username"},
            {"data": "phone"},
            {"data": "email"},
            {"data":
                    function ( row, type, val, meta ) {
                        return DateTime.format(row.updated, "yyyy-MM-dd HH:mm:ss");
                    }
            },
            {"data": function ( row, type, val, meta ) {
                    var detailUrl = "/user/detail?id="+row.id;
                    var deleteUrl = "/user/delete";
                    return '<button class="btn btn-default btn-sm" onclick="App.showDetail(\''+detailUrl+'\');"><i class="fa fa-search"></i> 查看</button>&nbsp;&nbsp;&nbsp;'+
                        '<a href="/user/form?id='+row.id+'" class="btn btn-info btn-sm"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;'+
                        '<button onclick="App.deleteSingle(\''+deleteUrl+'\', '+row.id+');" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-danger"><i class="fa fa-trash-o"></i> 删除</button>';
                }
            }
        ]
        _dataTable = App.initDataTable("/user/page", _colums);
    });
    
    function search() {
        var username = $("#username").val();
        var phone = $("#phone").val();
        var email = $("#email").val();
        var param = {
            "username": username,
            "phone": phone,
            "email": email
        };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }
</script>
<!-- 模态框 -->
<sys:modal />
</body>
</html>
