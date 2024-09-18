function formatPrice(number) {
    return number.toLocaleString('vi-VN');
}

function handleError(xhr, status, error) {
    console.log(error, xhr.responseText, status);
}

function jTableSetting(Element) {
    $(Element).DataTable({
        "paging": true,
        "lengthChange": true,
        "lengthMenu": [10, 25, 50, 100],
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
        "language" : languageTable
    });
}
const languageTable = {
    "sProcessing": "Đang xử lý...",
    "sLengthMenu": "Hiển thị _MENU_ mục",
    "sZeroRecords": "Không tìm thấy kết quả",
    "sInfo": "Đang hiển thị _START_ đến _END_ trong tổng số _TOTAL_ mục",
    "sInfoEmpty": "Đang hiển thị 0 đến 0 trong tổng số 0 mục",
    "sInfoFiltered": "(lọc từ _MAX_ mục)",
    "sInfoPostFix": "",
    "sSearch": "Tìm kiếm:",
    "sUrl": "",
    "sInfoThousands": ",",
    "sLoadingRecords": "Đang tải...",
    "oAria": {
    "sSortAscending": ": kích hoạt để sắp xếp cột theo thứ tự tăng dần",
        "sSortDescending": ": kích hoạt để sắp xếp cột theo thứ tự giảm dần"
    }
}

async function uploadFile(file, el) {
    try {
        const res = await axios.post(`/api/v1/files/uploadFile`, file);
        el.src = res.data;
        toastShow("Cập nhật ảnh mới thành công!", "success")
    } catch (error) {
        toastShow("Đã sảy ra lỗi khi cập nhật ảnh!", "error")
        console.log(error)
    }
}

function formatDate(dateString) {
    const date = new Date(dateString);

    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Tháng bắt đầu từ 0
    const year = date.getFullYear();

    return `${day}/${month}/${year}`;
}

function parseTimeString(timeStr) {
    const [hours, minutes] = timeStr.split(':').map(Number);
    const date = new Date();
    date.setHours(hours, minutes, 0, 0);
    return date;
}

function formatEnumText () {
    const enumElement = document.querySelectorAll('.enum');
    if (enumElement) {
        enumElement.forEach((element) => {
            const enumText = element.textContent || element.innerText;
            element.textContent = enumText.replace(/_/g, ' ');
        });
    }
}

function toastShow(message, type) {
    const notyf = new Notyf({
        duration: 3000,
        position: {
            x: 'right',
            y: 'top',
        },
        types: [
            {
                type: 'warning',
                background: 'orange',
                icon: {
                    className: 'material-icons',
                    tagName: 'i',
                    text: 'warning'
                }
            },
            {
                type: 'error',
                background: 'indianred',
                duration: 3000,
                dismissible: false
            }
        ]
    });
    switch (type) {
        case "success":
            notyf.success(message);
            break;
        case "error":
            notyf.error(message);
            break;
        case "warning":
            notyf.warning(message);
            break;
        default:
            notyf.info(message);
    }
}