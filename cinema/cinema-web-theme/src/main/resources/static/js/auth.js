async function login() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const data = {
        email: email, password: password
    }

    const btn = document.getElementById("btn-login");
    const context = btn.textContent;

    try {
        btnLoadingInClick(btn)
        const res = await axios.post("/api/v1/auth/login", data);
        btnLoadingFinish(btn, context);
        toastShowSuccess("Đăng nhập thành công!");
        setTimeout(function () {
            window.location.href = '/';
        }, 2000);
    } catch (e) {
        btnLoadingFinish(btn, context);
        console.log(e.response.data.message);
        toastShowFail(e.response.data.message);
    }
}

async function register() {
    const fullName = document.getElementById("register-name").value;
    const email = document.getElementById("register-email").value;
    const phoneNumber = document.getElementById("register-phone-number").value;
    let gender;
    document.getElementsByName("gender").forEach(e => {
        if (e.checked) {
            gender = e.value;
        }
    })
    const birthday = document.getElementById("register-dob").value;
    const password = document.getElementById("register-password").value;
    const confirmPassword = document.getElementById("register-confirm-password").value;

    const btn = document.getElementById("btn-register");
    const context = btn.textContent;

    if (document.getElementById("accept-terms-of-service").checked) {
        btnLoadingInClick(btn);
        let data = {
            fullName: fullName,
            email: email,
            phoneNumber: phoneNumber,
            gender: gender,
            birthday: birthday,
            password: password,
            confirmPassword: confirmPassword
        }
        try {
            const res = await axios.post("/api/v1/auth/register", data);
            showAuthMessageModal("register");
            btnLoadingFinish(btn, context);
            hideModal('#auth-register-modal');
            toastShowSuccess("Đăng ký thành công!");
        } catch (e) {
            btnLoadingFinish(btn, context);
            toastShowFail(e.response.data.message);
        }
    } else {
        toastShowFail("vui lòng đồng ý với điều khoản dịch vụ của chúng tôi")
    }
}

async function forgotPassword() {
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
        toastShowFail(e.response.data.message);
    }
}

function logout() {
    toastShowSuccess("Đang đăng xuất tài khoản");
    setTimeout(function () {
        window.location.href = '/logout';
    }, 2000);
}

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
