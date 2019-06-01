/**
 * 函数对象
 */
var Validate = function () {

    /**
     * 初始化校验规则
     */
    var handlerInit = function () {
        $("#inputForm").validate({
            errorElement: 'span',
            errorClass: 'text-red',
            errorPlacement: function (error, element) {
                element.parent().parent().attr("class", "form-group has-error");
                error.insertAfter(element);
            }
        });
    };

    /**
     * 增加自动以验证规则
     */
    var handlerInitCustomValidate = function () {
        $.validator.addMethod("mobile", function (value, element) {
            var length = value.length;
            var mobile = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");
    };

    return{
        /**
         * 初始化校验规则
         */
        init: function () {
            handlerInitCustomValidate();
            handlerInit();
        }
    }
}();

$(function () {
    Validate.init();
});