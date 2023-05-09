function page(i) {
    const frm = $("#loginLogFrm");
    $("#page").val(i - 1);
    frm.attr("action", "/_admin/login-log");
    frm.submit();
}