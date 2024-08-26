function editAud(button) {
    const id = button.getAttribute('data-aud-id')
    const aud = auditoriums.filter(e => e.id.toString() === id.toString());

    document.getElementById('aud-name').value = aud[0].name;

    audType.forEach(e => {
        const option = document.createElement('option');
        option.value = e;
        option.text = e;
        if (aud[0].auditoriumType === e.toString()) {
            option.selected = true;
        }
        document.getElementById('aud-type').appendChild(option);
    })

    document.getElementById('aud-status').value = aud[0].enabled? '1' : '0';

    $('#auditoriumModal').modal('show');

    document.getElementById('aud-update').addEventListener("click", async (e) => {
        if (!$('#form-aud').valid()) return;

        const data = {
            name: $('#aud-name').val(),
            type: $('#aud-type').val(),
            enabled: parseInt($('#aud-status').val(), 10) === 1,
        }

        try {
            await axios.put(`/admin/api/v1/aud/${aud[0].id}`, data);
            toastShow("Cập nhật thông tin phòng chiếu thành công", "success");
            $('#auditoriumModal').modal('hide');
            setTimeout(() =>
                location.reload(), 2000);
        } catch (error) {
            toastShow(error.response.data.message, "error");
            console.error(error);
        }
    })
}

async function removeAud(button) {
    const id = button.getAttribute('data-aud-id')
    const aud = auditoriums.filter(e => e.id.toString() === id.toString());
    document.getElementById('aud-remove-message').innerHTML = `
                <h5>Bạn có chắc muốn xóa <strong> ${aud[0].name} </strong></h5>
            `;
    $('#removeAuditoriumModal').modal('show');

    document.getElementById('aud-remove').addEventListener('click', async (e) => {
        try {
            await axios.delete(`/admin/api/v1/aud/${id}`);
            toastShow("Xóa phòng chiếu thành công", "success");
            $('#removeAuditoriumModal').modal('hide');
            setTimeout(() =>
                location.reload(), 2000);
        } catch (error) {
            toastShow(error.response.data.message, "error");
            console.error(error);
        }
    })
}

// create
document.getElementById('aud-create')
    .addEventListener('click', async (e) => {
    if (!$('#form-create-aud').valid()) return;

    const data = {
        cinemaId: cinema.id,
        name: $('#create-aud-name').val(),
        type: $('#create-aud-type').val(),
        enabled: parseInt($('#create-aud-status').val(), 10) === 1,
        totalRowChair: $('#create-aud-total-row-chair').val(),
        totalColumnChair: $('#create-aud-total-column-chair').val(),
    }
    console.log(data);
    try {
        await axios.post('/admin/api/v1/aud', data);
        toastShow("Tạo phòng chiếu thành công", "success");
        $('#auditoriumModal').modal('hide');
        setTimeout(() =>
            location.reload(), 2000);
    } catch (error) {
        toastShow(error.response.data.message, "error");
        console.error(error);
    }
})