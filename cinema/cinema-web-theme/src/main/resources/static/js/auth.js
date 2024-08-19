let isLogin = false;

document.getElementById('form-login').addEventListener('submit', async (e) => {
    e.preventDefault();
    if (!$('#form-login').valid()) {
        return;
    }

    const data = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    }

    const btn = document.getElementById("btn-login");
    const context = btn.textContent;

    try {
        btnLoadingInClick(btn)
        const res = await axios.post("/api/v1/auth/login", data);
        btnLoadingFinish(btn, context);
        toastShowSuccess("Đăng nhập thành công!");
        setHeaderUserInfo(res.data);
        hideModal(document.getElementById('auth-login-modal'));
    } catch (e) {
        btnLoadingFinish(btn, context);
        modalErrorLog(
            document.getElementById("auth-login-modal"),
            document.querySelector(".error-login"),
            e.response.data.message
        )
    }
})

document.getElementById('form-register').addEventListener('submit', async (e) => {
    e.preventDefault();
    if (!$('#form-register').valid()) {
        return;
    }

    let gender;
    document.querySelectorAll('input[type="radio"][name="gender"]').forEach(e => {
        if (e.checked) {
            gender = e.value;
        }
    })

    const btn = document.getElementById("btn-register");
    const context = btn.textContent;

    if (document.getElementById("accept-terms-of-service").checked) {
        btnLoadingInClick(btn);
        let data = {
            fullName: document.getElementById("register-name").value,
            email: document.getElementById("register-email").value,
            phoneNumber: document.getElementById("register-phone-number").value,
            gender: gender,
            birthday: document.getElementById("register-dob").value,
            password: document.getElementById("register-password").value,
            confirmPassword: document.getElementById("register-confirm-password").value
        }
        try {
            const res = await axios.post("/api/v1/auth/register", data);
            showAuthMessageModal("register");
            btnLoadingFinish(btn, context);
            hideModal('#auth-register-modal');
            toastShowSuccess("Đăng ký thành công!");
        } catch (e) {
            btnLoadingFinish(btn, context);
            modalErrorLog(
                document.getElementById("auth-register-modal"),
                document.querySelector(".error-register"),
                e.response.data.message
            )
        }
    } else {
        modalErrorLog(
            document.getElementById("auth-register-modal"),
            document.querySelector(".error-register"),
            "Vui lòng chấp nhận điều khoản dịch vụ của chúng tôi"
        )
    }
})

document.getElementById('form-forgot-password').addEventListener('submit', async (e) => {
    e.preventDefault()
    if (!$('#form-forgot-password').valid()) {
        return;
    }

    const email = document.getElementById("forgot-password-email").value;
    const btn = document.getElementById("btn-forgot-password");
    const context = btn.textContent;
    btnLoadingInClick(btn);
    try {
        const res = await axios.post("/api/v1/auth/forgot-password?email=" + email);
        showAuthMessageModal("forgot-password");
        btnLoadingFinish(btn, context);
        hideModal('#auth-forgot-password');
        toastShowSuccess("Quên mật khẩu thành công!");
    } catch (e) {
        btnLoadingFinish(btn, context);
        modalErrorLog(
            document.getElementById("auth-forgot-password"),
            document.querySelector(".error-forgot-password"),
            e.response.data.message
        )
    }
})

function logout() {
    toastShowSuccess("Đang đăng xuất tài khoản");
    setTimeout(function () {
        window.location.href = '/logout';
    }, 2000);
}

async function checkLogin() {
    try {
        const res = await axios.get("/api/v1/auth/check-login");
        if (res.data === false) {
            $(document.getElementById('auth-login-modal')).modal('show');
            return false;
        } else {
            return true;
        }
    } catch (e) {
        console.log(e);
        return false;
    }
}

$('#form-login').validate({
    rules: {
        email: {
            required: true,
            email: true,
        },
        password: {
            required: true,
        }
    },
    messages: {
        email: {
            required: "Không được để trống email",
            email: "Email không đúng định dạng "
        },
        password: {
            required: "Không được để trống password"
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

$('#form-register').validate({
    rules: {
        name: {
            required: true,
        },
        email: {
            required: true,
            email: true,
        },
        phoneNumber: {
            required: true,
            minlength: 10,
        },
        gender: {
            required: true,
        },
        birthday: {
            required: true,
        },
        password: {
            required: true,
            minlength: 8,
        },
        confirmPassword: {
            required: true,
            minlength: 8,
            equalTo: "#register-password"
        }
    },
    messages: {
        name: {
            required: "Không được để trống Họ tên",
        },
        email: {
            required: "Không được để trống email",
            email: "Email không đúng định dạng "
        },
        phoneNumber: {
            required: "Không được để trống số điện thoại",
            minlength: "Số điện thoại phải 10 chữ số"
        },
        gender : {
            required: "Vui lòng chọn giới tính",
        },
        birthday: {
            required: "Vui lòng chọn ngày sinh nhật",
        },
        password: {
            required: "Không được để trống mật khẩu",
            minlength: "Mật khẩu phải có ít nhất 8 kí tự",
        },
        confirmPassword: {
            required: "Vui lòng nhập lại mật khẩu",
            minlength: "Mật khẩu phải có ít nhất 8 kí tự",
            equalTo: "Mật khẩu không khớp với mật khẩu trên"
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

$('#form-forgot-password').validate({
    rules: {
        email: {
            required: true,
            email: true,
        }
    },
    messages: {
        email: {
            required: "Không được để trống email",
            email: "Email không đúng định dạng "
        }
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

function showAuthMessageModal(status) {
    const modal = new bootstrap.Modal(document.getElementById('auth-message-modal'))
    const title = document.getElementById("modal-message-title");
    const content = document.getElementById("modal-message-content");
    if (status === "register") {
        title.textContent = "Đăng ký Thành công";
        content.innerHTML = `
        <strong>Bạn đã đăng ký tài khoản thành công!</strong>
        <p>Chúng tôi đã gửi link xác thực tới email của bạn, vui lòng kiểm tra email và xác thực tài khoản</p>
        `;
    } else if (status === "forgot-password") {
        title.textContent = "Quên mật khẩu";
        content.innerHTML = `
        <strong>Dựa trên thông tin của bạn chúng tôi đã gửi link đăng nhập tới địa chỉ email của bạn</strong>
        <p>Vui lòng kiểm tra email của bạn</p>
        `;
    }
    modal.show();
}

function btnLoadingInClick(button) {
    button.innerHTML = `<div class="loader"></div>`;
}

function btnLoadingFinish(button, context) {
    button.innerHTML = `${context}`;
}

function hideModal(modalEl) {
    $(modalEl).modal('hide');
}

function modalErrorLog(modalEl, errorEl, message) {
    errorEl.textContent = message;
    modalEl.addEventListener('hidden.bs.modal', event => {
        errorEl.textContent = "";
    })
}

function setHeaderUserInfo(user) {
    document.querySelector('.login-btn').innerHTML = `
    <div class="dropdown">
        <div class="text-center" data-bs-toggle="dropdown" aria-expanded="false">
            <img style="width: 50px; height: 50px" src="${user.avatar}">
            <p class="my-2 fw-700">
                <span>${user.fullName}</span>
            </p>
        </div>
        <ul class="dropdown-menu mt-2">
            <li><a class="dropdown-item fs-14px" href="/user"><i class="me-3 fa-regular fa-id-card"></i>Tài khoản</a></li>
            <li><a class="dropdown-item fs-14px" href="/user?stage=1"><i class="me-3 fa-solid fa-list-ul"></i>Lịch sử</a></li>
            <li><a class="dropdown-item fs-14px" onclick="logout()"><i class="me-3 fa-solid fa-arrow-right-from-bracket fa-flip-horizontal"></i>Đăng xuất</a></li>
        </ul>
    </div>
    `;
}
