function stageThree() {
    $.ajax({
        url: '/booking/get/stage-three', type: 'GET', success: function (htmlResponse) {
            divStage.innerHTML = htmlResponse;
            getCombo()
        }, error: function (xhr, status, error) {
            console.error('Đã xảy ra lỗi: ' + xhr);
        }
    });
}

const comboData = new Map();
let totalComboPrice = 0;
const ticketCombo = document.getElementById('ticket-combo');

function getCombo() {
    const combos = document.querySelectorAll('.combo');

    combos.forEach(combo => {
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

let combosName = new Map();

function updateTicketCombo() {
    const comboDetails = document.querySelector('.combo-detail');
    comboDetails.innerHTML = '';

    totalComboPrice = 0;
    let isAnyComboSelected = false;

    comboData.forEach((quantity, comboId) => {
        if (quantity > 0) {
            isAnyComboSelected = true;
            const combo = document.querySelector(`.combo [value="${comboId}"]`).closest('.combo');
            const comboName = combo.querySelector('p.fs-18px').innerText;
            const comboPrice = parseFloat(combo.querySelector('p.fs-16px').innerText.split(':')[1].trim());

            combosName.set(comboId, comboName);

            const totalComboItemPrice = comboPrice * quantity;
            totalComboPrice += totalComboItemPrice;

            let detail = document.createElement('div');
            detail.classList.add('d-flex', 'justify-content-between', 'align-items-center');
            detail.innerHTML = `
                <div>
                    <strong class="fs-14px">${quantity} <span> x </span></strong>
                    <span class="fs-14px text-black-50">${comboName}</span>
                </div>
                <strong class="fs-14px">${totalComboItemPrice}</strong>
            `;
            comboDetails.appendChild(detail);
        }
    });

    ticketTotalPriceShow();

    if (isAnyComboSelected) {
        ticketCombo.classList.remove('d-none');
    } else {
        ticketCombo.classList.add('d-none');
    }
}
