$(".select2").select2();
$('#form-movie').validate({
    rules: {
        name: { required: true },
        releaseDate: { required: true },
        description: { required: true },
        trailer: { required: true },
        genre: { required: true },
        slug: { required: true },
        producer: { required: true },
        duration: { required: true, digits: true },
        rating: { required: true, number: true, min: 0, max: 10 },
        ratingCount: { required: true, digits: true },
        ageRequirement: { required: true },
        graphicsType: { required: true },
        translationTypes: { required: true },
        country: { required: true },
    },
    messages: {
        name: { required: "Vui lòng nhập tên phim" },
        releaseDate: { required: "Vui lòng nhập ngày công chiếu" },
        description: { required: "Vui lòng nhập mô tả" },
        trailer: { required: "Vui lòng nhập trailer" },
        genre: { required: "Vui lòng chọn thể loại phim" },
        slug: { required: "Vui lòng nhập slug" },
        producer: { required: "Vui lòng nhập nhà sản xuất" },
        duration: { required: "Vui lòng nhập thời lượng phim", digits: "Thời lượng phim phải là số nguyên" },
        rating: { required: "Vui lòng nhập đánh giá", number: "Đánh giá phải là số", min: "Đánh giá không thể nhỏ hơn 0", max: "Đánh giá không thể lớn hơn 10" },
        ratingCount: { required: "Vui lòng nhập tổng số người dùng đánh giá", digits: "Số người dùng đánh giá phải là số nguyên" },
        ageRequirement: { required: "Vui lòng chọn giới hạn tuổi" },
        graphicsType: { required: "Vui lòng chọn hình thức chiếu" },
        translationTypes: { required: "Vui lòng chọn hình thức dịch" },
        country: { required: "Vui lòng chọn quốc gia" },
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

// upload poster
const inputPosterEl = document.getElementById("input-poster");
const posterEl = document.getElementById("poster")

inputPosterEl.addEventListener("change", async (event) => {
    const files = event.target.files

    const formData = new FormData()
    formData.append("file", files[0])

    await uploadFile(formData, posterEl)
})

// upload bannerImg
const inputBannerEl = document.getElementById("input-bannerImg");
const bannerEl = document.getElementById("bannerImg")

inputBannerEl.addEventListener("change", async (event) => {
    const files = event.target.files

    const formData = new FormData()
    formData.append("file", files[0])

    await uploadFile(formData, bannerEl);
})


function getMovieData() {
    return data = {
        name: $('#name').val(),
        slug: $('#slug').val(),
        description: $('#description').val(),
        poster: $('#poster').attr('src'),
        bannerImg: $('#bannerImg').attr('src'),
        trailer: $('#trailer').val(),
        ageRequirement: $('#ageRequirement').val(),
        duration: $('#duration').val(),
        producer: $('#producer').val(),
        rating: $('#rating').val(),
        ratingCount: $('#ratingCount').val(),
        releaseDate: $('#releaseDate').val(),
        status: parseInt($('#status').val(), 10) === 1,
        graphicTypes: $('#graphicsType').val(),
        translationTypes: $('#translationTypes').val(),
        countryIds: $('#country').val(),
        genreIds: $('#genre').val(),
        directorIds: $('#director').val(),
        actorIds: $('#actor').val(),
    }
}