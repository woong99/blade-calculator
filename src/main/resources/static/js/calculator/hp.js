$(function () {
    /* Final HP 계산 */
    calculateProduct(HP);

    /* 자연수 타입 블러 이벤트 */
    $(".natural-number").blur(function () {
        calculateNaturalNumber(this, HP);
    });

    /* 퍼센트 타입 블러 이벤트 */
    $(".percent").blur(function () {
        calculatePercent(this, HP);
    });

    /* 저장 버튼 클릭 이벤트 */
    $("#btn-save").click(() => {
        const frm = $("#hp-frm");
        frm.attr("action", "/calculator/hp/save.do");

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
