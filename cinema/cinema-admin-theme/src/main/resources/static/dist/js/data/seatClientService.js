let seatData;
let audId;
let bin = false;

function seatShowBtn(btn) {
    audId = btn.getAttribute('data-aud-id');
    toggleSeatView(false);
}

async function toggleSeatView(isBinView) {
    const binButton = document.getElementById('seat-bin-btn');
    const mapButton = document.getElementById('seat-map-btn');

    binButton.classList.toggle('d-none', isBinView);
    mapButton.classList.toggle('d-none', !isBinView);

    const url = isBinView ? `/admin/api/v1/seat/aud-bin/${audId}` : `/admin/api/v1/seat/aud/${audId}`;
    try {
        const res = await axios.get(url);
        seatData = res.data;
        bin = isBinView;
        renderSeat(seatData);
    } catch (error) {
        toastShow(error.response.data.message, "error");
        console.error(error);
    }
}

async function seatBin() {
    await toggleSeatView(true);
}

async function seatShow() {
    await toggleSeatView(false);
}

function renderSeat(data) {
    $('#seatModal').modal('show');

    const seatMap = document.getElementById('seat-map');
    seatMap.innerHTML = "";
    let rows = {};
    data.forEach(seat => {
        const row = seat.seatRow;
        if (!rows[row]) {
            rows[row] = [];
        }
        rows[row].push(seat);
    });


    for (const row in rows) {
        const rowDiv = document.createElement('div');
        const rowNameS = document.createElement('button');
        const rowNameE = document.createElement('button');
        const rowList = document.createElement('div');

        rowNameS.className = 'seat';
        rowNameE.className = 'seat';

        rowDiv.className = 'seat-row';
        rowNameS.innerText = row;

        if (bin) {
            rowNameE.innerHTML = `
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-rotate-ccw align-middle"><polyline points="1 4 1 10 7 10"></polyline><path d="M3.51 15a9 9 0 1 0 2.13-9.36L1 10"></path></svg>
                        `;
            rowNameE.onclick = function () {
                const seatIds = rows[row].map(seat => seat.id);
                rowBtnRestore(row, seatIds);
            };
        } else {
            rowNameE.innerHTML = `
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trash align-middle"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path></svg>
                    `;
            rowNameS.onclick = function () {
                const seatIds = rows[row].map(seat => seat.id);
                rowBtnClick(row, seatIds);
            };
            rowNameE.onclick = function () {
                const seatIds = rows[row].map(seat => seat.id);
                rowBtnDelete(row, seatIds);
            };
        }

        rowDiv.appendChild(rowNameS);

        rowList.className = 'seat-list filtered';
        let coupleDiv = null;
        let coupleCounter = 0;

        rows[row].forEach((seat) => {
            if (seat.status === true) {
                const seatBtn = document.createElement('button');

                seatBtn.className = 'seat';
                seatBtn.value = seat.id;
                seatBtn.id = `seat-${seat.id}`;
                seatBtn.innerText = `${seat.seatColumn}`;
                seatBtn.onclick = () => seatBtnFunc(seat.id);

                if (seat.type === 'COUPLE') {
                    if (coupleCounter % 2 === 0) {
                        coupleDiv = document.createElement('div');
                        coupleDiv.className = 'div-seat-double';
                        coupleDiv.onclick = function () {
                            seatCoupleBtnFunc(this);
                        };
                        rowList.appendChild(coupleDiv);
                    }
                    seatBtn.classList.remove('seat');
                    seatBtn.classList.add('seat-double');
                    seatBtn.onclick = null;
                    coupleDiv.appendChild(seatBtn);
                    coupleCounter++;
                } else {
                    rowList.appendChild(seatBtn);
                }

                if (seat.type === 'VIP') {
                    seatBtn.classList.add('seat-vip');
                }

            }
            if (seat.status === false) {
                const seatBtn = document.createElement('button');
                seatBtn.classList.add('seat');
                seatBtn.innerHTML = `<i class="fa-solid fa-xmark "></i>`;
                rowList.appendChild(seatBtn);
                seatBtn.onclick = () => seatBtnFunc(seat.id);
            }
            new Sortable(rowList, {
                group: 'shared',
                multiDrag: true,
                selectedClass: 'selected',
                fallbackTolerance: 3,
                filter: '.filtered',
                animation: 150
            });
        });

        rowDiv.appendChild(rowList);
        rowDiv.appendChild(rowNameE);
        seatMap.appendChild(rowDiv);

    }
}

async function seatBtnHandler(btn, isCouple = false) {
    const seatIds = isCouple
        ? Array.from(btn.querySelectorAll('.seat-double')).map(e => parseInt(e.value, 10))
        : [btn];

    const seats = seatData.filter(seat => seatIds.includes(seat.id));

    if (seats[0].deleted) {
        $('#seat-single-delete').addClass('d-none');
        $('#seat-single-restore').removeClass('d-none');
    } else {
        $('#seat-single-delete').removeClass('d-none');
        $('#seat-single-restore').addClass('d-none');
    }

    $('#edit-seat-close').addClass('d-none');

    if (seats.length) {
        const seatInfo = seats.map(seat => `<strong class="ms-1">${seat.seatRow} ${seat.seatColumn}</strong>`).join(' ');
        document.getElementById('edit-seat-title').innerHTML = `Chỉnh sửa thông tin ghế ${seatInfo}`;

        $('#seatModal').modal('hide');
        $('#seat-status').val(seats[0].status ? '1' : '0');
        $('#seat-type').val(seats[0].type.toString());
        $('#seat-block').val(seats[0].block ? '1' : '0');

        $('#editSeatModal').modal('show');

        document.getElementById('seat-update').onclick = async () => {
            const data = {
                seatIds: seatIds,
                status: parseInt($('#seat-status').val(), 10) === 1,
                block: parseInt($('#seat-block').val(), 10) === 1,
                type: $('#seat-type').val(),
            };
            await updateSeatAction(data);
        };
        document.getElementById('seat-single-delete').onclick = async () => {
            await handleSeatAction('/admin/api/v1/seat/delete', seatIds, 'Xóa thông tin ghế thành công!');
        }
        document.getElementById('seat-single-restore').onclick = async () => {
            await handleSeatAction('/admin/api/v1/seat/restore', seatIds, 'Khôi phục thông tin ghế thành công!');
        }
    } else {
        toastShow('Ghế không được tìm thấy!', 'error');
    }
}

async function seatCoupleBtnFunc(btn) {
    await seatBtnHandler(btn, true);
}

async function seatBtnFunc(btn) {
    await seatBtnHandler(btn);
}

function rowBtnClick(row, seatIds) {
    document.getElementById('edit-seat-title').innerHTML = `Chỉnh sửa thông tin hàng ghế <strong class="ms-1">${row}</strong> `;
    $('#seatModal').modal('hide');


    $('#edit-seat-close').removeClass('d-none');
    $('#seat-single-delete').addClass('d-none');

    const seats = seatData.filter(seat => seatIds.includes(seat.id));

    $('#seat-status').val(seats[0].status ? '1' : '0');
    $('#seat-type').val(seats[0].type.toString());
    $('#seat-block').val(seats[0].block ? '1' : '0');

    $('#editSeatModal').modal('show');

    document.getElementById('seat-update').onclick = async () => {
        const data = {
            seatIds: seatIds,
            status: parseInt($('#seat-status').val(), 10) === 1,
            block: parseInt($('#seat-block').val(), 10) === 1,
            type: $('#seat-type').val(),
        };
        await updateSeatAction(data);
    }
}

function updateModal(title, message, actionText, actionClass, showDelete) {
    document.getElementById('remove-seat-title').innerHTML = title;
    document.getElementById('remove-seat-message').innerHTML = message;

    toggleVisibility('seat-delete', showDelete);
    toggleVisibility('seat-restore', !showDelete);

    $('#seatModal').modal('hide');
    $('#deleteSeatModal').modal('show');
}

function rowBtnDelete(row, seatIds) {
    const title = `Xóa tất cả ghế thuộc hàng <strong class="ms-1">${row}</strong>`;
    const message = `Bạn có xác nhận <strong class="text-danger">Xóa</strong> thông tin tất cả ghế?`;
    const actionText = 'Xóa';
    const actionClass = 'seat-delete';
    updateModal(title, message, actionText, actionClass, true);

    document.getElementById('seat-delete').onclick = () => {
        handleSeatAction('/admin/api/v1/seat/delete', seatIds, 'Xóa thông tin ghế thành công!');
    };
}

function rowBtnRestore(row, seatIds) {
    const title = `Khôi phục tất cả ghế thuộc hàng <strong class="ms-1">${row}</strong>`;
    const message = `Bạn có xác nhận <strong class="text-primary">Khôi phục</strong> thông tin tất cả ghế?`;
    const actionText = 'Khôi phục';
    const actionClass = 'seat-restore';
    updateModal(title, message, actionText, actionClass, false);

    document.getElementById('seat-restore').onclick = () => {
        handleSeatAction('/admin/api/v1/seat/restore', seatIds, 'Khôi phục thông tin ghế thành công!');
    };
}

function toggleVisibility(elementId, show) {
    const element = document.getElementById(elementId);
    if (element) {
        element.classList.toggle('d-none', !show);
    }
}

async function handleSeatAction(url, seatIds, successMessage) {
    try {
        await axios.put(url, seatIds);
        toastShow(successMessage, 'success');
        $('#deleteSeatModal').modal('hide');
        $('#editSeatModal').modal('hide');
        await seatShow();
    } catch (error) {
        toastShow(error.response.data.message, 'error');
        console.error(error);
    }
}

async function updateSeatAction(data) {
    try {
        await axios.put('/admin/api/v1/seat', data);
        toastShow('Cập nhật thông tin ghế thành công!', 'success');
        $('#editSeatModal').modal('hide');
        await seatShow();
    } catch (error) {
        toastShow(error.response.data.message, 'error');
        console.error(error);
    }
}