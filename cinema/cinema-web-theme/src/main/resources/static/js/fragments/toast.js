// Hàm để hiển thị toast
function toastShowSuccess(message) {
    const toastSuccess = document.querySelector('.toast-success');
    toastSuccess.querySelector(".success-message").textContent = message;
    toastShow(toastSuccess);
}

function toastShowFail(message) {
    const toastFail = document.querySelector('.toast-fail');
    toastFail.querySelector(".fail-message").textContent = message;
    toastShow(toastFail);
}


// Hàm để hiển thị bất kỳ toast nào
function toastShow(toastEl) {
    document.body.appendChild(toastEl);
    const toast = new bootstrap.Toast(toastEl);
    toast.show();
}
