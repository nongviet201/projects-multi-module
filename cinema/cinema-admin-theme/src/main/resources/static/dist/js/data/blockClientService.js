function getBlockData(selector, isMovedBlock) {
    const blocks = document.querySelectorAll(selector);
    let data = [];

    blocks.forEach(function(block) {
        const { startColumn, positions } = calculateBlockPosition(block);

        data.push({
            blockId: isMovedBlock ? block.getAttribute('data-block-id') : undefined, // Chỉ có khi là block moved
            audId: audId,
            seatRow: block.parentElement.getAttribute('data-row'),
            startColumn: startColumn,  // Cột bắt đầu
            endColumn: startColumn + 1,  // Cột kết thúc
            positions: positions  // Số vị trí
        });
    });

    return data;
}

function calculateBlockPosition(block) {
    let startColumn;
    let positions = 1;
    let newSeatCount = 0;

    let prev = block.previousElementSibling;

    while (prev != null && prev.classList.contains('newBlock')) {
        positions++;
        prev = prev.previousElementSibling;
    }

    while (prev != null && prev.classList.contains('newSeat')) {
        newSeatCount++;
        prev = prev.previousElementSibling;
    }

    if (prev == null) {
        startColumn = 0;
    } else if (prev.classList.contains('div-seat-double')) {
        let dataColumn = prev.lastElementChild.getAttribute('data-column');
        startColumn = dataColumn !== null ? parseInt(dataColumn, 10) + newSeatCount : 0;
    } else {
        let dataColumn = prev.getAttribute('data-column');
        startColumn = dataColumn !== null ? parseInt(dataColumn, 10) + newSeatCount : 0;
    }

    return { startColumn, positions };
}

async function deleteBlock(ids) {
    try {
        await axios.delete(`/admin/api/v1/block/${ids}`);
    } catch (error) {
        toastShow(error.response.data.message, 'error');
        console.error(error);
    }
}

async function createBlock(data) {
    try {
        await axios.post(`/admin/api/v1/block/create`, data);
    } catch (error) {
        toastShow(error.response.data.message, 'error');
        console.error(error);
    }
}

async function updateMovedBlock(data) {
    try {
        await axios.put(`/admin/api/v1/block/update`, data);
    } catch (error) {
        toastShow(error.response.data.message, 'error');
        console.error(error);
    }
}