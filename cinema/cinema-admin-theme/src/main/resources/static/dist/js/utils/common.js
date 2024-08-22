function formatPrice(number) {
    return number.toLocaleString('vi-VN');
}

function handleError(xhr, status, error) {
    console.log(error, xhr.responseText, status);
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