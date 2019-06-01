/**
 *
 * @returns {{init: init}}
 */
var App = function () {
    //iCheck
    var _masterCheckbox;
    var _checkbox;
    //用户存放id数组
    var _idArray;
    // 默认的 Dropzone 参数
    var defaultDropzoneOpts = {
        url: "", // 文件提交地址
        method: "post",  // 也可用put
        paramName: "dropzFile", // 默认为file
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传"+this.maxFiles+"个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    };
    /**
     * 私有方法，初始化 ICheck
     */
    var handlerInitCheckbox = function () {
        //激活
        $('input[type="checkbox"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue'
        });
        //获取控制端Checkbox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');
        //获取全部Checkbox集合
        _checkbox = $('input[type="checkbox"].minimal');
    };

    /**
     * Checkbox 全选功能
     */
    var handlerCheckboxAll = function () {
        _masterCheckbox.on("ifClicked",function (e) {
            //返回 true 表示未选中
            var f = e.target.checked;
            if (f){
                _checkbox.iCheck("uncheck");
            }
            //选中状态
            else {
                _checkbox.iCheck("check");
            }
        });
    };

    var handlerDeleteSingle = function (url, id, msg) {
        // 可选参数
        if (!msg) msg = null;
        // 将 ID 放入数组中，以便和批量删除通用
        _idArray = new Array();
        _idArray.push(id);
        $("#modal-massage").html(msg == null ? "您确定删除数据吗？" : msg);
        $("#modal-default").modal("show");
        // 绑定事件
        $("#btnModalOk").bind("click", function () {
            handlerDeleteData(url);
        });
    };

    /**
     * 批量删除
     */
    var handlerDeleteMulti = function (url) {
        _idArray = new Array();
        //将选中的id放入数组中
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefined" && $(this).is(":checked")){
                _idArray.push(_id);
            }
        });
        /**
         * 判断用户是否选择数据项
         */
        if (_idArray.length === 0){
            $("#modal-massage").html("您还没有选择任何数据项，请至少选择一项...");
        }
        else {
            $("#modal-massage").html("您确定删除数据吗...");
        }
        // 点击删除按钮式弹出模态框
        $("#modal-default").modal("show");
        //用户选择了数据项，则调用删除方法
        $("#btnModalOk").bind("click", function () {
            handlerDeleteData(url)
        });
    };

    /**
     * AJAX 异步删除
     * @param url
     */
    var handlerDeleteData = function (url) {
        $("#modal-default").modal("hide");
        // 判断是否选择数据
        if (_idArray.length > 0){
            // 删除操作
            setTimeout(function () {
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "data":{"ids": _idArray.toString()},
                    "dataType": "JSON",
                    "success": function (data) {
                        // 请求成功后，无论是成功还是失败都需要弹出模态框进行提示，所以这里需要先解绑原来的 click 事件
                        $("#btnModalOk").unbind("click");
                        // 请求成功
                        if (data.status === 200){
                            // 刷新页面
                            $("#btnModalOk").bind("click", function () {
                                window.location.reload();
                            });
                        }
                        // 请求失败
                        else {
                            // 确定按钮的事件改成隐藏模态框
                            $("#btnModalOk").bind("click", function () {
                                $("#modal-default").modal("hide");
                            });
                        }
                        // 无论成功还是失败都需要弹出模态框，所以这里的模态框都是必须调用的
                        $("#modal-massage").html(data.message);
                        $("#modal-default").modal("show");
                    }
                });
            },500);
        }
    };

    /**
     * 初始化DataTable
     */
    var handlerInitDataTables = function (url, columns) {
        var _dataTable = $('#dataTable').DataTable({
            "paging": true,
            "info": true,
            "lengthChange": false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ajax":{
                "url": url
            },
            "columns": columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function() {
                handlerInitCheckbox();
                handlerCheckboxAll();
            }
        });
        return _dataTable;
    };

    /**
     * 查看详情
     * @param url
     */
    var handlerShowDetail = function (url) {
        // 这里是通过 ajax 请求 html 的方式将 jsp 装载进模态框中
        $.ajax({
            "url": url,
            "type": "get",
            "dataType": "html",
            "success": function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };

    /**
     * 初始化 zTree
     * @param url
     * @param autoParam
     * @param callback
     */
    var handlerZTree = function (url, autoParam, callback) {
        var setting = {
            view: {
                // 禁止多选
                selectedMulti: false
            },
            async: {
                // 开启异步加载功能
                enable: true,
                // 远程访问地址
                url: url,
                // 选择父节点时会自动将节点中的参数传回服务器再重新取结果
                autoParam: autoParam
            }
        };
        // 初始化 zTree 控件
        $.fn.zTree.init($("#myTree"), setting);
        // 绑定事件
        $("#btnModalOk").bind("click", function () {
            // 获取 zTree 控件
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            // 获取已选中的节点
            var nodes = zTree.getSelectedNodes();
            if (nodes.length == 0) {
                alert("请先选择一个父节点");
            }
            else {
                callback(nodes);
            }
        });
    };

    /**
     * 初始化 Dropzone
     */
    var handlerInitDropzone = function (opts) {
        // 关闭 Dropzone 的自动发现功能
        Dropzone.autoDiscover = false;
        // 继承
        $.extend(defaultDropzoneOpts, opts);
        new Dropzone(defaultDropzoneOpts.id, defaultDropzoneOpts);
    };

    return{
        //初始化
        init:function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },
        //批量删除
        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        },

        /**
         * 单个删除
         */
        deleteSingle: function(url, id, msg){
            handlerDeleteSingle(url, id, msg);
        },

        // 初始化DATATables
        initDataTable: function (url, colums) {
            return handlerInitDataTables(url,colums);
        },

        //显示详情
        showDetail: function (url) {
            handlerShowDetail(url);
        },

        /**
         * 初始化 zTree
         * @param url
         * @param autoParam
         * @param callback
         */
        initZTree: function (url,autoParam, callback) {
            handlerZTree(url,autoParam, callback)
        },

        /**
         * 初始化 Dropzone
         * @param opts
         */
        initDropzone: function (opts) {
            handlerInitDropzone(opts);
        }
    }
}();

$(function () {
   App.init();
});