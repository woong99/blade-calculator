$(function () {
    /* Final DEF 계산 */
    calculateProduct(DEF);

    /* 자연수 타입 블러 이벤트 */
    $(".natural-number").blur(function () {
        calculateNaturalNumber(this, DEF);
    });

    /* 퍼센트 타입 블러 이벤트 */
    $(".percent").blur(function () {
        calculatePercent(this, DEF);
    });

    /* 저장 버튼 클릭 이벤트 */
    $("#btn-save").click(() => {
        const command = $("#btn-save").data("command");
        const frm = $("#hp-frm");
        if (command === "insert") {
            frm.attr("action", "/calculator/defense/insert.do")
        } else if (command === "update") {
            frm.attr("action", "/calculator/defense/update.do")
        } else if (command === "noAuth") {
            alert("로그인 후 이용해주세요.");
            location.href = "/login";
            return false;
        } else {
            alert("잘못된 접근입니다.");
            return false;
        }
        $(".natural-number").each((index, item) => {
            if (isEmpty($(item).val())) {
                alert("값을 입력해주세요.");
                $(item).focus();
                return false;
            }
        })

        $(".percent").each((index, item) => {
            if (isEmpty($(item).val())) {
                alert("값을 입력해주세요.");
                $(item).focus();
                return false;
            }
        })
        if (confirm("저장하시겠습니까?")) {
            frm.submit();
        }
    });
});
