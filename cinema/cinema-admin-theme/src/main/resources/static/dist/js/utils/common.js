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
        "language" : languageTable,
        "createdRow": function(row, data, dataIndex) {
            $('td', row).eq(0).addClass('text-start');
            $('td', row).eq(5).addClass('text-end');
            $('td', row).eq(1).addClass('text-center');
            $('td', row).eq(2).addClass('text-center');
            $('td', row).eq(3).addClass('text-center');
            $('td', row).eq(4).addClass('text-center');
        },
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
    } catch (error) {
        console.log(error)
    }
}

function toastShow(message, type) {
    const notyf = new Notyf({
        duration: 1000,
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
                duration: 2000,
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