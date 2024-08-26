// delete
document.getElementById("delete-cinema").addEventListener("click", async () => {
    try {
        const res = await axios.delete(`/admin/api/v1/cinema/${cinema.id}`)
        toastShow(`Đã xóa phim ${cinema.name}`, "success")
        setTimeout(() => {
            window.location.href = "/admin/cinema";
        }, 1500)
    } catch (error) {
        console.log(error)
        toastShow(error.response.data.message, "error");
    }
})

document.getElementById('btn-update').addEventListener("click", async (e) => {
    if (!$('#form-cinema').valid()) return;
    try {
        await axios.put(`/admin/api/v1/cinema/${cinema.id}`, getData());
        toastShow("Cập nhật thông tin rạp thành công", "success");
    } catch (error) {
        toastShow(error.response.data.message, "error");
        console.error(error);
    }
});

// restore
document.getElementById("restore-cinema").addEventListener("click", async () => {
    try {
        const res = await axios.put(`/admin/api/v1/cinema/restore/${cinema.id}`)
        toastShow(`Đã khôi phục phim ${cinema.name}`, "success")
        setTimeout(() => {
            window.location.href = "/admin/cinema?data=delete";
        }, 1000)
    } catch (error) {
        console.log(error)
        toastShow(error.response.data.message, "error");
    }
})

function getData() {
    return data = {
        name: $('#name').val(),
        address: $('#address').val(),
        enabled: parseInt($('#status').val(), 10) === 1,
        city: $('#city').val(),
        lng: parseFloat($('#lng').val()),
        lat: parseFloat($('#lat').val()),
    }
}