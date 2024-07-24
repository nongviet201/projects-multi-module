const updateBlogBtn = document.getElementById("updateBlog");
const detailBlogForm = document.getElementById("form-detail-blog");
const title = document.getElementById("title");
const content = document.getElementById("content");
const status = document.getElementById("status");


updateBlogBtn.addEventListener("click", async (e) => {
    e.preventDefault();
    let data = {
        title: title.value,
        content: content.value,
        status: status.value,
    }

    const blogId = e.target.getAttribute("data-id");
    console.log(blogId);

    try {
        let reponse = await axios.put(`/api/blogs/${blogId}` + data)
        let res = reponse.data;
        toastr.success("da luu thay doi");
    } catch (e) {
        console.log(e)
        toastr.error("bạn không phải là tác giả");
    }
})




// thông báo
$('#form-detail-blog').validate({
    rules: {
        title: {
            required: true,
        },
        content: {
            required: true,
        },
        status: {
            required: true,
        }
    },
    messages: {
        title: {
            required: "không được để trống"
        },
        content: {
            required: "không được để trống"
        },
        status: {
            required: "không được để trống"
        },
    },
    errorElement: 'span',
    errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-group').append(error);
    },
    highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
    },
    unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
    }
});