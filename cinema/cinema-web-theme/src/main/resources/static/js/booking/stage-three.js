
function stageThree() {
    $.ajax({
        url: '/booking/get/stage-three', type: 'GET', success: function (htmlResponse) {
            divStage.innerHTML = htmlResponse;
            ticketDetailComboHide();
            getCombo()
        }, error: function (xhr) {
            console.error('Đã xảy ra lỗi: ' + xhr);
        }
    });
}

const comboData = new Map();
const combosName = new Map();
const ticketCombo = document.getElementById('ticket-combo');

function getCombo() {
    const combos = document.querySelectorAll('.combo');

    combos.forEach(combo => {
        // Lấy các nút và số lượng trong combo
        const minusBtn = combo.querySelector('.minus');
        const plusBtn = combo.querySelector('.plus');
        const quantityDisplay = combo.querySelector('.combo-quantity');
        const comboId = quantityDisplay.getAttribute('value');

        let quantity = parseInt(quantityDisplay.innerText, 10);

        comboData.set(comboId, quantity);

        minusBtn.addEventListener('click', function () {
            if (quantity > 0) {
                quantity -= 1;
                quantityDisplay.innerText = quantity;
                comboData.set(comboId, quantity);
                updateTicketCombo(); // Cập nhật tổng giá
            }
        });

        plusBtn.addEventListener('click', function () {
            quantity += 1;
            quantityDisplay.innerText = quantity;
            comboData.set(comboId, quantity);
            updateTicketCombo(); // Cập nhật tổng giá
        });
    });
}


function updateTicketCombo() {
    const comboDetails = document.querySelector('.combo-detail');
    comboDetails.innerHTML = ''; // Xóa nội dung hiện tại của ticketCombo

    totalComboPrice = 0; // Đặt lại tổng giá của combo
    let isAnyComboSelected = false;

    comboData.forEach((quantity, comboId) => {
        if (quantity > 0) {
            isAnyComboSelected = true;
            const combo = document.querySelector(`.combo [value="${comboId}"]`).closest('.combo');
            const comboName = combo.querySelector('p.fs-18px').innerText;
            const comboPrice = parseFloat(combo.querySelector('p.fs-16px').innerText.split(':')[1].trim()); // Lấy giá từ thông tin và chuyển đổi sang số thực
            combosName.set(comboId, comboName);
            const totalComboItemPrice = comboPrice * quantity;
            totalComboPrice += totalComboItemPrice;
            let totalComboPriceFormat = formatPrice(totalComboPrice);

            let detail = document.createElement('div');
            detail.classList.add('d-flex', 'justify-content-between', 'align-items-center');
            detail.innerHTML = `
                <div>
                    <strong class="fs-14px">${quantity} <span> x </span></strong>
                    <span class="fs-14px text-black-50">${comboName}</span>
                </div>
                <strong class="fs-14px">${totalComboPriceFormat}<span>đ</span></strong> <!-- Hiển thị tổng giá với 2 chữ số thập phân -->
            `;
            comboDetails.appendChild(detail);
        }
    });

    totalPrice = totalComboPrice + totalTicketPrice;
    ticketTotalPriceShow();

    if (isAnyComboSelected) {
        ticketCombo.classList.remove('d-none');
    } else {
        ticketCombo.classList.add('d-none');
    }
}
