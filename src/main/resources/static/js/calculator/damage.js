$(function () {
    /* Final 데미지 계산 */
    calculateProduct();

    /* 성장 계산 */
    $(".growth").each((index, item) => {
        const p = $(item).attr("p");
        console.log(p)
        const type = $(item).attr("type");
        const level = parseInt($(item).val().replaceAll(",", ""));
        const upgradeCost = $("#" + $(item).attr("id").split("_")[0] + "_2");
        if (type === 'ATK') {
            if (level >= 30000) {
                maxCost(upgradeCost, "#g-atk_", 30000);
            } else {
                calculateGrowth(p, type);
            }
        } else if (type === 'CRITICAL_D') {
            if (level >= 3500) {
                maxCost(upgradeCost, "#g-critical-d_", 3500);
            } else {
                calculateGrowth(p, type);
            }
        } else if (type === 'CRITICAL_P') {
            if (level >= 400) {
                maxCost(upgradeCost, "#g-critical-p_", 400);
            } else {
                calculateGrowth(p, type);
            }
        } else if (type === 'SUPER_CRITICAL_D') {
            if (level >= 3500) {
                maxCost(upgradeCost, "#g-super-critical-d_", 3500);
            } else {
                calculateGrowth(p, type);
            }
        } else if (type === 'SUPER_CRITICAL_P') {
            if (level >= 400) {
                maxCost(upgradeCost, "#g-super-critical-p_", 400);
            } else {
                calculateGrowth(p, type);
            }
        } else if (type === 'HYPER_CRITICAL_D') {
            if (level >= 3500) {
                maxCost(upgradeCost, "#g-hyper-critical-d_", 3500);
            } else {
                calculateGrowth(p, type);
            }
        } else if (type === 'HYPER_CRITICAL_P') {
            if (level >= 400) {
                maxCost(upgradeCost, "#g-hyper-critical-p_", 400);
            } else {
                calculateGrowth(p, type);
            }
        } else if (type === 'SUPER_SKILL_ATK') {
            if (level >= 2000) {
                maxCost(upgradeCost, "#g-super-skill-atk_", 2000);
            } else {
                calculateGrowth(p, type);
            }
        } else if (type === 'SPECIAL_ATK') {
            if (level >= 2000) {
                maxCost(upgradeCost, "#g-special-atk_", 2000);
            } else {
                calculateGrowth(p, type);
            }
        } else if (type === 'SUPER_ATK') {
            if (level >= 2000) {
                maxCost(upgradeCost, "#g-super-atk_", 2000);
            } else {
                calculateGrowth(p, type);
            }
        } else {
            alert("잘못된 접근입니다.");
            return false;
        }

    })

    /* 성장 계산 후 순서 계산 */
    calculateGrowthOrder();

    /* 자연수 타입 블러 이벤트 */
    $(".natural-number").blur(function () {
        calculateNaturalNumber(this);
    });

    /* 퍼센트 타입 블러 이벤트 */
    $(".percent").blur(function () {
        calculatePercent(this);
    });

    /* 치명타 타입 블러 이벤트 */
    $(".critical").blur(function () {
        calculateCritical(this);
    });

    /* 데미지 저장 버튼 클릭 이벤트 */
    $("#btn-save").click(() => {
        const frm = $("#damage-frm");

        frm.attr("action", "/calculator/damage/atk/save.do");
        $(".natural-number").each((index, item) => {
            if (isEmpty($(item).val())) {
                alert("값을 입력해주세요.");
                $(item).focus();
                return false;
            }
        });

        $(".percent").each((index, item) => {
            if (isEmpty($(item).val())) {
                alert("값을 입력해주세요.");
                $(item).focus();
                return false;
            }
        });

        $(".critical").each((index, item) => {
            if (isEmpty($(item).val())) {
                alert("값을 입력해주세요.");
                $(item).focus();
                return false;
            }
        });
        if (confirm("저장하시겠습니까?")) {
            frm.submit();
        }
    });

    $("#btn-growth-save").click(() => {
        const frm = $("#growth-frm");
        frm.attr("action", "/calculator/damage/growth/save.do");
        $(".growth").each((index, item) => {
            if (isEmpty($(item).val())) {
                alert("값을 입력해주세요.");
                $(item).focus();
                return false;
            }
        })
        if (confirm("저장하시겠습니까?")) {
            frm.submit();
        }
    })

    let status = true;
    $(".growth").blur(function () {
        const firstInput = $("#" + $(this).attr("id").split("_")[0] + "_1");
        const upgradeCost = $("#" + $(this).attr("id").split("_")[0] + "_2");
        const firstInputVal = parseInt(firstInput.val().replaceAll(",", ""));
        const type = $(this).attr("type")
        const point = (firstInputVal + 1).toLocaleString();

        if (type === 'ATK') {
            if (firstInputVal >= 30000) {
                maxCost(upgradeCost, "#g-atk_", 30000);
                return false;
            }
        } else if (type === 'CRITICAL_D') {
            if (firstInputVal >= 3500) {
                maxCost(upgradeCost, "#g-critical-d_", 3500);
                return false;
            }
        } else if (type === 'CRITICAL_P') {
            if (firstInputVal >= 400) {
                maxCost(upgradeCost, "#g-critical-p_", 400);
                return false;
            }
        } else if (type === 'SUPER_CRITICAL_D') {
            if (firstInputVal >= 3500) {
                maxCost(upgradeCost, "#g-super-critical-d_", 3500);
                return false;
            }
        } else if (type === 'SUPER_CRITICAL_P') {
            if (firstInputVal >= 400) {
                maxCost(upgradeCost, "#g-super-critical-p_", 400);
                return false;
            }
        } else if (type === 'HYPER_CRITICAL_D') {
            if (firstInputVal >= 3500) {
                maxCost(upgradeCost, "#g-hyper-critical-d_", 3500);
                return false;
            }
        } else if (type === 'HYPER_CRITICAL_P') {
            if (firstInputVal >= 400) {
                maxCost(upgradeCost, "#g-hyper-critical-p_", 400);
                return false;
            }
        } else if (type === 'SUPER_SKILL_ATK') {
            if (firstInputVal >= 2000) {
                maxCost(upgradeCost, "#g-super-skill-atk_", 2000);
                return false;
            }
        } else if (type === 'SPECIAL_ATK') {
            if (firstInputVal >= 2000) {
                maxCost(upgradeCost, "#g-special-atk_", 2000);
                return false;
            }
        } else if (type === 'SUPER_ATK') {
            if (firstInputVal >= 2000) {
                maxCost(upgradeCost, "#g-super-atk_", 2000);
                return false;
            }
        } else {
            alert("잘못된 접근입니다.");
            return false;
        }

        if (status) {
            if (point <= 0) {
                alert("0 이상의 레벨을 입력해주세요.");
                firstInput.val("0");
                upgradeCost.text("0");
                return false;
            }
            status = false;
            $.ajax({
                url: "/calculator/damage/growth/cost",
                data: {point, type}
            }).done(res => {
                let p = res.valueIncrease;
                upgradeCost.text(res.cost.toLocaleString());
                status = true;
                calculateGrowth(p, type);
                calculateGrowthOrder();
            }).fail(res => {
                if (res.status === 404) {
                    alert("존재하지 않는 레벨입니다.");
                } else {
                    alert("알 수 없는 오류입니다. 개발자에게 문의해주세요.");
                }
                firstInput.val("0");
                upgradeCost.text("0");
                status = true;
            });
        } else {
            alert("계산이 진행중입니다.");
        }
        firstInput.val(parseInt(firstInputVal).toLocaleString());
    })
});

/* 성장 계산 */
function calculateGrowth(p, type) {
    const d8 = $("#avg-data-before").val();
    const d10 = $("#final-data-before").val();
    const d11 = $("#final-c-data-before").val();
    const d12 = $("#final-s-c-data-before").val();
    const d13 = $("#final-h-c-data-before").val();
    const d17 = parseInt($("#atk_1").val().replaceAll(",", ""));  // D17
    const d18 = $("#critical-p_1").val().replaceAll("%", "") / 100; // D18
    const d19 = $("#critical-d_1").val().replaceAll("%", "") / 100;  // D19
    const d20 = $("#super-critical-p_1").val().replaceAll("%", "") / 100;  // D20
    const d21 = $("#super-critical-d_1").val().replaceAll("%", "") / 100;   // D21
    const d22 = $("#hyper-critical-p_1").val().replaceAll("%", "") / 100;   // D22
    const d23 = $("#hyper-critical-d_1").val().replaceAll("%", "") / 100;   // D23
    const d9 = ((1 - d18) * d10 + d18 * (1 - d20) * d11 + d18 * d20 * (1 - d22) * d12 + d18 * d20 * d22 * d13);
    const d33 = $("#super-skill-atk_1").val().replaceAll("%", "") / 100;
    const d40 = $("#special-atk_1").val().replaceAll("%", "") / 100;
    const d44 = $("#super-atk_1").val().replaceAll("%", "") / 100;
    const l17 = $("#g-atk_2").text().replaceAll(",", "");
    const l18 = $("#g-critical-p_2").text().replaceAll(",", "");
    const l19 = parseInt($("#g-critical-d_2").text().replaceAll(",", ""));
    const l20 = $("#g-super-critical-p_2").text().replaceAll(",", "");
    const l21 = $("#g-super-critical-d_2").text().replaceAll(",", "");
    const l22 = $("#g-hyper-critical-p_2").text().replaceAll(",", "");
    const l23 = $("#g-hyper-critical-d_2").text().replaceAll(",", "");
    const l24 = $("#g-super-skill-atk_2").text().replaceAll(",", "");
    const l25 = $("#g-special-atk_2").text().replaceAll(",", "");
    const l26 = $("#g-super-atk_2").text().replaceAll(",", "");
    if (p.includes('%')) {  // P
        p = parseInt(p.replaceAll("%", "").replaceAll(",", "")) / 100;
    } else {
        p = parseInt(p.replaceAll(",", ""));
    }
    let damageProduct = 1;  // PRODUCT(D24:D49)
    $(".percent").each((index, item) => {
        if (index % 2 === 0) {
            damageProduct *= parseInt($(item).val().replaceAll("%", "")) / 100;
        }
    })
    let damagePerGold;
    if (type === "ATK") {
        damagePerGold = ((1 - d18) * ((d17 + p) * damageProduct) + d18 * (1 - d20) * ((d17 + p) * damageProduct) * d19 + d18 * d20 * (1 - d22) * ((d17 + p) * damageProduct) * d19 * d21 + d18 * d20 * d22 * ((d17 + p) * damageProduct) * d19 * d21 * d23 - d8) / l17;
        $("#g-atk_3").text(damagePerGold.toExponential(1));
        $("#g-atk_4").val(damagePerGold);
    } else if (type === "CRITICAL_P") {
        damagePerGold = (1 - d18) * d10 + d18 * (1 - d20) * d11 + d18 * d20 * (1 - d22) * d12 + d18 * d20 * d22 * d13 / l18;
        $("#g-critical-p_3").text(damagePerGold.toExponential(1));
        $("#g-critical-p_4").val(damagePerGold);
    } else if (type === "CRITICAL_D") {
        damagePerGold = (((1 - d18) * (d17 * damageProduct) + d18 * (1 - d20) * ((d17 * damageProduct) * (d19 + 0.005)) + d18 * d20 * (1 - d22) * (((d17 * damageProduct) * (d19 + 0.005)) * d21) + d18 * d20 * d22 * ((((d17 * damageProduct) * (d19 + 0.005)) * d21) * d23)) - d8) / l19;
        $("#g-critical-d_3").text(damagePerGold.toExponential(1));
        $("#g-critical-d_4").val(damagePerGold);
    } else if (type === "SUPER_CRITICAL_P") {
        damagePerGold = (((1 - d18) * d10 + d18 * (1 - d20) * d11 + d18 * d20 * (1 - d22) * d12 + d18 * d20 * d22 * d13 / l20))
        $("#g-super-critical-p_3").text(damagePerGold.toExponential(1));
        $("#g-super-critical-p_4").val(damagePerGold);
    } else if (type === "SUPER_CRITICAL_D") {
        damagePerGold = (((1 - d18) * (d17 * damageProduct) + d18 * (1 - d20) * ((d17 * damageProduct) * (d19)) + d18 * d20 * (1 - d22) * (((d17 * damageProduct) * (d19)) * (d21 + 0.005)) + d18 * d20 * d22 * ((((d17 * damageProduct) * (d19)) * (d21 + 0.005)) * d23)) - d8) / l21
        $("#g-super-critical-d_3").text(damagePerGold.toExponential(1));
        $("#g-super-critical-d_4").val(damagePerGold);
    } else if (type === "HYPER_CRITICAL_P") {
        if (d22 === 0) {
            $("#g-hyper-critical-p_3").text("0% H.Crit");
            $("#g-hyper-critical-p_4").val(0);
        } else {
            damagePerGold = (((1 - d18) * d10 + d18 * (1 - d20) * d11 + d18 * d20 * (1 - d22) * d12 + d18 * d20 * d22 * d13 / l22))
            $("#g-hyper-critical-p_3").text(damagePerGold.toExponential(1));
            $("#g-hyper-critical-p_4").val(damagePerGold);
        }
    } else if (type === "HYPER_CRITICAL_D") {
        damagePerGold = (((1 - d18) * (d17 * damageProduct) + d18 * (1 - d20) * ((d17 * damageProduct) * (d19)) + d18 * d20 * (1 - d22) * (((d17 * damageProduct) * (d19)) * (d21)) + d18 * d20 * d22 * ((((d17 * damageProduct) * (d19)) * (d21)) * (d23 + 0.005))) - d8) / l23
        $("#g-hyper-critical-d_3").text(damagePerGold.toExponential(1));
        $("#g-hyper-critical-d_4").val(damagePerGold);
    } else if (type === "SUPER_SKILL_ATK") {
        damagePerGold = (d9 * (1 + 0.001 / d33) - d9) / l24;
        $("#g-super-skill-atk_3").text(damagePerGold.toExponential(1));
        $("#g-super-skill-atk_4").val(damagePerGold);
    } else if (type === "SPECIAL_ATK") {
        damagePerGold = (d9 * (1 + 0.001 / d40) - d9) / l25;
        $("#g-special-atk_3").text(damagePerGold.toExponential(1));
        $("#g-special-atk_4").val(damagePerGold);
    } else if (type === "SUPER_ATK") {
        damagePerGold = ((d9 * (1 + 0.00015 / d44)) - d9) / l26;
        $("#g-super-atk_3").text(damagePerGold.toExponential(1));
        $("#g-super-atk_4").val(damagePerGold);
    } else {
        alert("잘못된 접근입니다.");
        location.reload();
    }
}

/* 성장 순서 계산 */
function calculateGrowthOrder() {
    let arr = [];
    $(".growth").each((index, item) => {
        const data = $("#" + $(item).attr("id").split("_")[0] + "_4").val();
        if (data !== "MAX") {
            arr.push({
                type: $(item).attr("id").split("_")[0] + "_3",
                item: parseFloat(data)
            });
        }
    });
    arr.sort((a, b) => {
        if (a.item > b.item) {
            return -1;
        } else if (a.item < b.item) {
            return 1;
        } else {
            return 0;
        }
    });

    const length = arr.length;
    for (let i = 0; i < length; i++) {
        console.log(arr[i].type)
        if (i < length / 3) {
            $("#" + arr[i].type).css("background-color", "#75fb4c");
        } else if (i < (length / 3) * 2) {
            $("#" + arr[i].type).css("background-color", "#f8d878");
        } else {
            $("#" + arr[i].type).css("background-color", "#d85140");
        }
    }

    if (length > 0) {
        const firstUpgrade = $("#" + arr[0].type.split("_")[0] + "_5").text();
        const firstUpgradeImg = $("#" + arr[0].type.split("_")[0] + "_5").children().attr("src");
        console.log(firstUpgrade)
        $("#growth-upgrade-first").text(firstUpgrade);
        $("#growth-upgrade-first").append(`<img alt='스킬' id='growth-upgrade-first-img' src='${firstUpgradeImg}'/>`)
    } else {
        $("#growth-upgrade-first").text("완료");
    }
}

function maxCost(upgradeCost, text, maxCost) {
    $(upgradeCost).text("MAX");
    $(text + "1").val(maxCost);
    $(text + "3").text("MAX");
    $(text + "3").css("background-color", "#ffffff");
    $(text + "4").val("MAX");
    calculateGrowthOrder();
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

    /* ================================================ 데미지 데이터 계산 S ================================================ */
    /* 치명타 데미지 데이터 */
    const criticalProduct = ($("#critical-d_1").val().replaceAll(",", "").replaceAll("%", "") / 100) * firstProduct;
    /* 슈퍼 치명타 데미지 데이터 */
    const superCriticalProduct = ($("#super-critical-d_1").val().replaceAll(",", "").replaceAll("%", "") / 100) * criticalProduct;
    /* 하이퍼 치명타 데미지 데이터 */
    const hyperCriticalProduct = ($("#hyper-critical-d_1").val().replaceAll(",", "").replaceAll("%", "") / 100) * superCriticalProduct;

    /* 치명타 데미지 최종 */
    const resultCriticalProduct = ($("#critical-d_3").text().replaceAll(",", "").replaceAll("%", "") / 100) * resultProduct;
    /* 슈퍼 치명타 데미지 최종 */
    const resultSuperCriticalProduct = ($("#super-critical-d_3").text().replaceAll(",", "").replaceAll("%", "") / 100) * resultCriticalProduct;
    /* 하이퍼 치명타 데미지 최종 */
    const resultHyperCriticalProduct = ($("#hyper-critical-d_3").text().replaceAll(",", "").replaceAll("%", "") / 100) * resultSuperCriticalProduct;

    /* 치명타 확률 */
    const criticalProbability = $("#critical-p_1").val().replaceAll(",", "").replaceAll("%", "") / 100;
    /* 슈퍼 치명타 확률 */
    const superCriticalProbability = $("#super-critical-p_1").val().replaceAll(",", "").replaceAll("%", "") / 100;
    /* 하이퍼 치명타 확률 */
    const hyperCriticalProbability = $("#hyper-critical-p_1").val().replaceAll(",", "").replaceAll("%", "") / 100;
    /* 평균 자동공격피해 */
    const avgDamage = (1 - criticalProbability) * firstProduct
        + criticalProbability * (1 - superCriticalProbability) * criticalProduct
        + criticalProbability * superCriticalProbability * (1 - hyperCriticalProbability) * superCriticalProduct
        + criticalProbability * superCriticalProbability * hyperCriticalProbability * hyperCriticalProduct;

    /* 치명타 확률 최종 */
    const resultCriticalProbability = $("#critical-p_3").text().replaceAll(",", "").replaceAll("%", "") / 100;
    /* 슈퍼 치명타 확률 최종 */
    const resultSuperCriticalProbability = $("#super-critical-p_3").text().replaceAll(",", "").replaceAll("%", "") / 100;
    /* 하이퍼 치명타 확률 최종 */
    const resultHyperCriticalProbability = $("#hyper-critical-p_3").text().replaceAll(",", "").replaceAll("%", "") / 100;
    /* 평균 자동공격피해 최종 */
    const resultAvgDamage = (1 - resultCriticalProbability) * resultProduct
        + resultCriticalProbability * (1 - resultSuperCriticalProbability) * resultCriticalProduct
        + resultCriticalProbability * resultSuperCriticalProbability * (1 - resultHyperCriticalProbability) * resultSuperCriticalProduct
        + resultCriticalProbability * resultSuperCriticalProbability * resultHyperCriticalProbability * resultHyperCriticalProduct;

    /* ================================================ 데미지 데이터 계산 E ================================================ */

    $("#avg-data-before").val(avgDamage);
    $("#final-data-before").val(firstProduct);
    $("#final-c-data-before").val(criticalProduct);
    $("#final-s-c-data-before").val(superCriticalProduct);
    $("#final-h-c-data-before").val(hyperCriticalProduct);

    /* ================================================ 데미지 데이터 변환 S ================================================ */
    const exchangedAvgProduct = exchangeStat(avgDamage);
    const exchangedFinalProduct = exchangeStat(firstProduct);
    const exchangedCriticalProduct = exchangeStat(criticalProduct);
    const exchangedSuperCriticalProduct = exchangeStat(superCriticalProduct);
    const exchangedHyperCriticalProduct = exchangeStat(hyperCriticalProduct);

    const exchangedResultAvgProduct = exchangeStat(resultAvgDamage);
    const exchangedResultProduct = exchangeStat(resultProduct);
    const exchangedResultCriticalProduct = exchangeStat(resultCriticalProduct);
    const exchangedResultSuperCriticalProduct = exchangeStat(resultSuperCriticalProduct);
    const exchangedResultHyperCriticalProduct = exchangeStat(resultHyperCriticalProduct);
    /* ================================================ 데미지 데이터 변환 E ================================================ */

    /* ================================================ 데미지 데이터 적용 S ================================================ */
    $("#avg-data").text(exchangedAvgProduct);
    $("#final-data").text(exchangedFinalProduct);
    $("#final-c-data").text(exchangedCriticalProduct);
    $("#final-s-c-data").text(exchangedSuperCriticalProduct);
    $("#final-h-c-data").text(exchangedHyperCriticalProduct);

    $("#avg-change-rate").text(((new BigNumber(resultAvgDamage).dividedBy(new BigNumber(avgDamage)) - 1) * 100).toFixed(2) + '%');
    $("#final-change-rate").text(((new BigNumber(resultProduct).dividedBy(new BigNumber(firstProduct)) - 1) * 100).toFixed(2) + '%');
    $("#final-c-change-rate").text(((new BigNumber(resultCriticalProduct).dividedBy(new BigNumber(criticalProduct)) - 1) * 100).toFixed(2) + '%');
    $("#final-s-c-change-rate").text(((new BigNumber(resultSuperCriticalProduct).dividedBy(new BigNumber(superCriticalProduct)) - 1) * 100).toFixed(2) + '%');
    $("#final-h-c-change-rate").text(((new BigNumber(resultHyperCriticalProduct).dividedBy(new BigNumber(hyperCriticalProduct)) - 1) * 100).toFixed(2) + '%');

    $("#avg-result").text(exchangedResultAvgProduct);
    $("#final-result").text(exchangedResultProduct);
    $("#final-c-result").text(exchangedResultCriticalProduct);
    $("#final-s-c-result").text(exchangedResultSuperCriticalProduct);
    $("#final-h-c-result").text(exchangedResultHyperCriticalProduct);
    /* ================================================ 데미지 데이터 적용 E ================================================ */
}

/* 자연수 타입 계산 */
function calculateNaturalNumber(e, stat) {
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

/* 치명타 타입 계산 */
function calculateCritical(e) {
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
