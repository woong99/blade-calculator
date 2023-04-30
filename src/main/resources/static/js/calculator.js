$(function () {
    $(".natural-number").blur(function () {
        const firstInput = $("#" + $(this).attr("id").split("-")[0] + "-1");
        const secondInput = $("#" + $(this).attr("id").split("-")[0] + "-2");
        const result = $("#" + $(this).attr("id").split("-")[0] + "-3");
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

        let firstProduct = intFirstInputVal;
        let secondProduct = intSecondInputVal;
        let resultProduct = parseInt(result.text().replaceAll(",", ""));
        $(".percent").each(function (index, item) {
            if (index % 2 === 0) {
                firstProduct *= parseInt($(item).val().replaceAll("%", "")) / 100;
            } else {
                resultProduct *= parseInt($("#" + $(item).attr("id").split("-")[0] + "-3").text().replaceAll(",", "").replaceAll("%", "")) / 100;
            }
        })

        console.log(firstProduct);
        console.log(resultProduct);
    });

    $(".percent").blur(function () {
        const firstInput = $("#" + $(this).attr("id").split("-")[0] + "-1");
        const secondInput = $("#" + $(this).attr("id").split("-")[0] + "-2");
        const result = $("#" + $(this).attr("id").split("-")[0] + "-3");
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

        let firstProduct = parseInt($("#hp-1").val().replaceAll(",", ""));
        let resultProduct = parseInt($("#hp-3").text().replaceAll(",", ""));
        $(".percent").each(function (index, item) {
            if (index % 2 === 0) {
                firstProduct *= parseInt($(item).val().replaceAll("%", "")) / 100;
            } else {
                resultProduct *= parseInt($("#" + $(item).attr("id").split("-")[0] + "-3").text().replaceAll(",", "").replaceAll("%", "")) / 100;
            }
        })

        console.log(firstProduct);
        console.log(resultProduct);
    });
});
