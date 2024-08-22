document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("datetimepicker-dashboard").flatpickr({
        locale: "vn",
        inline: true,
        prevArrow: "<span title=\"Tháng trước\">&laquo;</span>",
        nextArrow: "<span title=\"Tháng sau\">&raquo;</span>",
        defaultDate: Date.now()
    });

    loadSelectCinemaDataTime()
});

function initMaps() {
    $.ajax({
        url: `admin/api/v1/cinema/get/all-marker`,
        type: 'GET',
        success: function (response) {
            renderMap(response);
        },
        error: function (xhr, status, error) {
            console.log(`Đã xảy ra lỗi: ` + error);
        }
    });
}
function renderMap(marker) {
    const defaultMap = {
        zoom: 5,
        center: {
            lat: 16.854701709660723,
            lng: 106.48639963144498,
        },
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    const map = new google.maps.Map(document.getElementById("map"), defaultMap);

    marker.forEach(function (cinema) {
        const marker = new google.maps.Marker({
            position: {lat: cinema.lat, lng: cinema.lng},
            map: map,
            title: cinema.name,
            label: cinema.id.toString(),
            zIndex: 2
        });

        const infoWindow = new google.maps.InfoWindow({
            content: `
                <div>
                    <strong>${cinema.name}</strong>
                    <br>${cinema.address}<br>
                    <a class="text-primary" href="https://www.google.com/maps/search/?api=1&query=${cinema.lat},${cinema.lng}" target="_blank">Xem trên Google Maps</a>
                </div>
            `,
        });

        marker.addListener('click', function () {
            infoWindow.open(map, marker);
        });

        map.addListener('click', function () {
            infoWindow.close();
        });
    });
}

function loadSelectCinemaDataTime() {
    const selectElement = document.getElementById('index-cinema-time');
    getAllCinemaRevenue(selectElement.value);

    selectElement.addEventListener('change', function () {
        let time = selectElement.value;
        getAllCinemaRevenue(time);
    });
}

function getAllCinemaRevenue(time) {
    if ($.fn.DataTable.isDataTable('#index-cinema')) {
        $('#index-cinema').DataTable().destroy();
    }

    $('#index-cinema').DataTable({
        "ajax": {
            "url": `admin/api/v1/cinema/get/all-revenue?time=${time}`,
            "type": "GET",
            "dataSrc": ""
        },
        "columns": [
            {
                "data": "name",  // Tên rạp
                "render": function (data, type, row) {
                    return `<a href="cinema-detail?cinemaId=${row.cinemaId}">${data}</a>`;
                }
            },
            {"data": "totalTickets"},    // Tổng vé bán ra
            {
                "data": "totalRevenue", // Tổng doanh thu
                "render": function (data) {
                    return `${formatPrice(data)} đ`;
                }
            },
            {
                "data": "65",
                "render": function (data, type, row) {
                    return `
                    <div class="d-flex flex-column w-100">
                        <span class="me-2 mb-1 text-muted">65%</span>
                        <div class="progress progress-sm bg-success-light w-100">
                            <div class="progress-bar bg-success" role="progressbar"
                                 style="width: 65%;"></div>
                        </div>
                    </div>
                    `;
                }
            },
            {
                "data": "managers",  // Quản lý
                "render": function (data, type, row) {
                    return data.map(manager => {
                        // Tạo thẻ <a> cho mỗi quản lý
                        return `<a href="/admin/user/${manager.userId}">${manager.name}</a>`;
                    }).join(', '); // Nối các thẻ <a> bằng dấu phẩy
                }
            }
        ],
        "createdRow": function(row, data, dataIndex) {
            $('td', row).eq(0).addClass('text-start');
            $('td', row).eq(4).addClass('text-end');
            $('td', row).eq(1).addClass('text-center');
            $('td', row).eq(2).addClass('text-center');
            $('td', row).eq(3).addClass('text-center');
        },
        "paging": true,
        "pageLength": 5,
        "lengthMenu": [5, 10, 25, 50, 100],
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": true,
        "responsive": true,
        "language": languageTable,
    });
}