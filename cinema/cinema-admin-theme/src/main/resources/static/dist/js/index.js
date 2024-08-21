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
        console.log(cinema)
        const marker = new google.maps.Marker({
            position: {lat: cinema.lat, lng: cinema.lng},
            map: map,
            title: cinema.name,
            label: cinema.id,
            zIndex: 2
        });

        const infoWindow = new google.maps.InfoWindow({
            content: `
                            <div>
                                <strong>${cinema.name}</strong>
                                <br>${cinema.address}<br>
                            <a href="https://www.google.com/maps/search/?api=1&query=${cinema.lat},${cinema.lng}" target="_blank">Xem trên Google Maps</a>
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



document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("datetimepicker-dashboard").flatpickr({
        locale: "vn",
        inline: true,
        prevArrow: "<span title=\"Tháng trước\">&laquo;</span>",
        nextArrow: "<span title=\"Tháng sau\">&raquo;</span>",
        defaultDate: Date.now()
    });
});