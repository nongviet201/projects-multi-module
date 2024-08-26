$('#form-cinema').validate({
    rules: {
        name: { required: true },
        address: { required: true},
        lng: { required: true},
        lat: { required: true},
    },
    messages: {
        name: { required: "Vui lòng nhập tên phim" },
        address: { required: "Vui lòng nhập địa chỉ"},
        lng: { required: "Vui lòng nhập vị trí theo kinh độ" },
        lat: { required: "Vui lòng nhập vị trí theo vĩ độ" },
    },
    errorElement: 'span',
    errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-group').append(error);
    },
    highlight: function (element) {
        $(element).addClass('is-invalid');
    },
    unhighlight: function (element) {
        $(element).removeClass('is-invalid');
    }
});

$('#form-aud').validate({
    rules: {
        name: { required: true },
    },
    messages: {
        name: { required: "Vui lòng nhập tên phim" },
    },
    errorElement: 'span',
    errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-group').append(error);
    },
    highlight: function (element) {
        $(element).addClass('is-invalid');
    },
    unhighlight: function (element) {
        $(element).removeClass('is-invalid');
    }
});