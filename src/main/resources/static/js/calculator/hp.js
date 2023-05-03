$(function () {
    /* Final HP 계산 */
    calculateProduct();

    /* 자연수 타입 블러 이벤트 */
    $(".natural-number").blur(function () {
        calculateNaturalNumber(this);
    });

    /* 퍼센트 타입 블러 이벤트 */
    $(".percent").blur(function () {
        calculatePercent(this);
    });

    /* 저장 버튼 클릭 이벤트 */
    $("#btn-save").click(() => {
        const command = $("#btn-save").data("command");
        const frm = $("#hp-frm");
        if (command === "insert") {
            frm.attr("action", "/calculator/hp/insert.do")
        } else if (command === "update") {
            frm.attr("action", "/calculator/hp/update.do")
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

/* 자연수 타입 계산 */
function calculateNaturalNumber(e) {
    const firstInput = $("#" + $(e).attr("id").split("_")[0] + "_1");
    const secondInput = $("#" + $(e).attr("id").split("_")[0] + "_2");
    const result = $("#" + $(e).attr("id").split("_")[0] + "_3");
    const firstInputVal = firstInput.val().replaceAll(",", "");
    const secondInputVal = secondInput.val().replaceAll(",", "");
    if (!numberRegex.test(firstInputVal)) {
        result.text("NaN");
        return false;
    }
    if (!numberRegex.test(secondInputVal)) {
        result.text("NaN");
        return false;
    }
    const intFirstInputVal = parseInt(firstInputVal);
    const intSecondInputVal = parseInt(secondInputVal);
    result.text((intFirstInputVal + intSecondInputVal).toLocaleString());
    firstInput.val(intFirstInputVal.toLocaleString());
    secondInput.val(intSecondInputVal.toLocaleString());

    calculateProduct();
}

/* 퍼센트 타입 계산 */
function calculatePercent(e) {
    const firstInput = $("#" + $(e).attr("id").split("_")[0] + "_1");
    const secondInput = $("#" + $(e).attr("id").split("_")[0] + "_2");
    const result = $("#" + $(e).attr("id").split("_")[0] + "_3");
    const firstInputVal = firstInput.val().replaceAll("%", "");
    const secondInputVal = secondInput.val().replaceAll("%", "");
    if (!numberRegex.test(firstInputVal)) {
        result.text("NaN");
        firstInput.val(firstInputVal);
        return false;
    }
    if (!numberRegex.test(secondInputVal)) {
        result.text("NaN");
        secondInput.val(secondInputVal);
        return false;
    }
    result.text(parseInt(firstInputVal) + parseInt(secondInputVal) + "%");
    firstInput.val(parseInt(firstInputVal) + "%");
    secondInput.val(parseInt(secondInputVal) + "%");

    calculateProduct();
}

/* 결과 곱 계산 */
function calculateProduct() {
    let firstProduct = 1;
    let resultProduct = 1;
    $(".natural-number").each((index, item) => {
        if (index % 2 === 0) {
            firstProduct *= parseInt($(item).val().replaceAll(",", ""));
        } else {
            resultProduct *= parseInt($("#" + $(item).attr("id").split("_")[0] + "_3").text().replaceAll(",", "").replaceAll("%", ""));
        }
    })
    $(".percent").each((index, item) => {
        if (index % 2 === 0) {
            firstProduct *= parseInt($(item).val().replaceAll("%", "")) / 100;
        } else {
            resultProduct *= parseInt($("#" + $(item).attr("id").split("_")[0] + "_3").text().replaceAll(",", "").replaceAll("%", "")) / 100;
        }
    })

    const exchangedFirstProduct = exchangeStat(firstProduct);
    const exchangedResultProduct = exchangeStat(resultProduct);
    $("#hp-data").text(exchangedFirstProduct);
    $("#hp-change-rate").text(((new BigNumber(resultProduct).dividedBy(new BigNumber(firstProduct)) - 1) * 100).toFixed(2) + '%');
    $("#hp-result").text(exchangedResultProduct);
}

/* 스탯 변환 */
function exchangeStat(data) {
    data = new BigNumber(data);
    if (data < 1000) {
        return data;
    } else if (data < 1E+11) {
        return parseInt(data / new BigNumber(1000)).toLocaleString() + "K";
    } else if (data < 1E+14) {
        return parseInt(data / new BigNumber(1000000)).toLocaleString() + "M";
    } else if (data < 1E+17) {
        return parseInt(data / new BigNumber(1E+9)).toLocaleString() + "G";
    } else if (data < 1E+20) {
        return parseInt(data / new BigNumber(1E+12)).toLocaleString() + "T";
    } else if (data < 1E+23) {
        return parseInt(data / new BigNumber(1E+15)).toLocaleString() + "P";
    } else if (data < 1E+26) {
        return parseInt(data / new BigNumber(1E+18)).toLocaleString() + "E";
    } else if (data < 1E+29) {
        return parseInt(data / new BigNumber(1E+21)).toLocaleString() + "Z";
    } else if (data < 1E+32) {
        return parseInt(data / new BigNumber(1E+24)).toLocaleString() + "Y";
    } else if (data < 1E+35) {
        return parseInt(data / new BigNumber(1E+27)).toLocaleString() + "V";
    } else if (data < 1E+38) {
        return parseInt(data / new BigNumber(1E+30)).toLocaleString() + "R";
    } else if (data < 1E+41) {
        return parseInt(data / new BigNumber(1E+33)).toLocaleString() + "O";
    } else if (data < 1E+44) {
        return parseInt(data / new BigNumber(1E+36)).toLocaleString() + "Q";
    } else if (data < 1E+47) {
        return parseInt(data / new BigNumber(1E+39)).toLocaleString() + "X";
    } else if (data < 1E+50) {
        return parseInt(data / new BigNumber(1E+42)).toLocaleString() + "KK";
    } else if (data < 1E+53) {
        return parseInt(data / new BigNumber(1E+45)).toLocaleString() + "KM";
    } else if (data < 1E+56) {
        return parseInt(data / new BigNumber(1E+48)).toLocaleString() + "KG";
    } else if (data < 1E+59) {
        return parseInt(data / new BigNumber(1E+51)).toLocaleString() + "KT";
    } else {
        return "NaN";
    }
}
