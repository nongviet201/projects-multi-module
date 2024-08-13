function stageFive() {
    showStatus(bill.status)
}

function showStatus(status) {
    const bookingBody = document.querySelector(".booking-body");
    const amountFormat = formatPrice(bill.amount);
    bookingBody.innerHTML = ``;
    if (status === true) {
        bookingBody.innerHTML = `
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-5">
                    <div class="message-box _success">
                        <i class="fa-regular fa-circle-check"
                           style="width: 4rem; height: 4rem; color: #28a745"></i>
                        <h1 class="fs-24px fw-700 mt-3"> Thanh toán thành công </h1>
                        <p class="my-2 fs-16px text-black-50">chúc bạn xem phim vui vẻ</p>
                        <div class="my-3 border-1 p-2">
                            <div class="mt-6 border p-3" style="border-radius: 5px">
                                <div class="d-flex justify-content-between text-sm">
                                    <span>Tổng thanh toán:</span>
                                    <span class="fs-14px">
                                        <strong class="fs-16px">${amountFormat} <span>đ</span></strong>
                                    </span>
                                </div>
                                <div class="d-flex justify-content-between text-sm mt-2">
                                    <span>Thời gian:</span>
                                    <span class="fs-14px">
                                        <p class="fs-14px fw-600">${bill.updatedAt}</p>
                                    </span>
                                </div>
                                <div class="d-flex justify-content-between text-sm mt-2">
                                    <span>Số hóa đơn:</span>
                                    <strong class="fs-14px">${bill.id}</strong>
                                </div>
                            </div>
                        </div>
                        <button style="background-color: rgb(37 99 235)">
                            <a class="text-white fs-16px" href="/">Trang Chủ</a>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        `;
    } else {
        bookingBody.innerHTML = `
        <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="message-box _success _failed">
                    <i class="fa-regular fa-circle-xmark"
                       style="width: 4rem; height: 4rem; color: #ff0000"></i>
                    <h1 class="fs-24px fw-700 mt-3"> Thanh toán thất bại</h1>
                    <p class="my-2 text-black-50 fs-16px">${bill.statusMessage}</p>
                    <div class="my-3 border-1 p-2">
                        <div class="mt-6 border p-3" style="border-radius: 5px">
                                <div class="d-flex justify-content-between text-sm">
                                    <span>Tổng thanh toán:</span>
                                    <span class="fs-14px">
                                        <strong class="fs-16px">${amountFormat} <span> đ</span></strong>
                                    </span>
                                </div>
                                <div class="d-flex justify-content-between text-sm mt-2">
                                    <span>Thời gian:</span>
                                    <span class="fs-14px">
                                        <p class="fs-14px fw-600">${bill.updatedAt}</p>
                                    </span>
                                </div>
                                <div class="d-flex justify-content-between text-sm mt-2">
                                    <span>Số hóa đơn:</span>
                                    <strong class="fs-14px">${bill.id}</strong>
                                </div>
                            </div>
                    </div>
                    <button style="background-color: rgb(37 99 235)">
                        <a class="text-white fs-16px" href="/">Trang Chủ</a>
                    </button>
                </div>
            </div>
        </div>
        </div>
        `;
    }
}