const numberRegex = /^[0-9]*$/;
const maxCol = 5;
const maxRow = 20;

function isEmpty(data) {
    return data === undefined || data === '';
}

function onlyNumber(e) {
    e.value = e.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
}

function onlyNumberAndComma(e) {
    e.value = e.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
    e.value = e.value.replace(/\B(?=(\d{3})+(?!\d))/g, ',')
}