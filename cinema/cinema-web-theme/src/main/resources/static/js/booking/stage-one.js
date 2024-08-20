

function stageOne() {
    $.ajax({
        url: '/booking/get/stage-one', type: 'GET', success: function (htmlResponse) {
            divStage.innerHTML = htmlResponse;

        }, error: function (xhr, status, error) {
            console.error('Đã xảy ra lỗi: ' + error);
            console.error('Status:', status);
            console.error('Error:', error);
        }
    });
}
let cityId;
function getCityFunc(button) {
    cityBtns = document.querySelectorAll(".city-btn");
    cityId = btnFunc(button, cityBtns)
    document.getElementById('city-name').innerHTML = `- ${button.textContent}`;
    showtime = null;
    showtimeCheck();
    openNextAccordion(1);
}

function getMovieFunc(div) {
    const movieId = div.getAttribute('value');
    const movieName = div.querySelector('h5').innerText;
    const moviePoster = div.querySelector('img').getAttribute('src');
    const movieAgeRequirement = div.getAttribute('data-movie-age');
    document.getElementById('movie-name').innerHTML = `- ${movieName}`;

    showtimeDetailMovieShow(moviePoster, movieName, movieAgeRequirement);
    showtimeDetailCinemaHide();
    getShowtime(movieId);
    openNextAccordion(2);
}

async function getShowtime(movieId) {
    try {
        showtimeData = await axios.get('api/v1/showtime/get/' + movieId + '/' + cityId);
    } catch (e) {
        console.log(e);
        return;
    }
    cinema = document.querySelector(".cinema");
    divDate = document.querySelector(".div-date");
    if (showtimeData.data.length === 0) {
        divDate.innerHTML = "Phim bạn chọn chưa có lịch chiếu vui lòng chọn phim khác hoặc thử lại sau.";
        cinema.innerHTML = "";
    } else {
        divDate.innerHTML = "";
        let showtimeDate = new Set;
        showtimeData.data.forEach(showtime => {
            showtimeDate.add(showtime.screeningDate);
        })
        showtimeDate.forEach(date => {
            const dateBtn = document.createElement('button');
            dateBtn.classList.add('date-btn', 'cursor-pointer', 'ms-3', 'p-1');
            dateBtn.style.maxWidth = '90px';
            dateBtn.onclick =  function() { getDateFunc(dateBtn); };
            dateBtn.value = date;
            dateBtn.innerText = date;
            divDate.appendChild(dateBtn);
        })
        getDateFunc(divDate.firstElementChild);
    }
}

function getDateFunc(button) {
    const dateBtns = document.querySelectorAll(".date-btn");
    const dateValue = btnFunc(button, dateBtns);

    const cinema = document.querySelector(".cinema");
    cinema.innerHTML = "";

    let listShowtimeByDate = showtimeFindByDate(dateValue);
    // Cấu trúc dữ liệu để lưu trữ suất chiếu theo từng rạp và loại phòng chiếu
    const cinemaMap = new Map(); // Map để lưu trữ thông tin suất chiếu theo từng rạp

    listShowtimeByDate.forEach(e => {
        const cinemaName = e.cinemaName;
        const auditoriumType = e.auditoriumType;

        if (!cinemaMap.has(cinemaName)) {
            cinemaMap.set(cinemaName, new Map());
        }
        const typeMap = cinemaMap.get(cinemaName);

        if (!typeMap.has(auditoriumType)) {
            typeMap.set(auditoriumType, []);
        }
        typeMap.get(auditoriumType).push(e);
    });

    cinemaMap.forEach((typeMap, cinemaName) => {
        const divCinema = document.createElement('div');
        divCinema.classList.add('border-bottom', 'py-3');
        divCinema.innerHTML = `<p><strong>${cinemaName}</strong></p>`;

        const divShowtime = document.createElement('div');
        divShowtime.classList.add('row', 'align-items-center', 'div-showtimes');

        // Duyệt qua từng loại phòng chiếu và chỉ tạo các phần tử nếu có suất chiếu
        typeMap.forEach((showtimes, audType) => {
            if (showtimes.length === 0) return; // Nếu không có suất chiếu, bỏ qua

            const audTypeDiv = document.createElement('div');
            audTypeDiv.classList.add('col-2', 'fs-14px', 'ps-2');
            audTypeDiv.innerText = audType;
            divShowtime.appendChild(audTypeDiv);

            const timeBtnDiv = document.createElement('div');
            timeBtnDiv.classList.add('col-10');

            showtimes.forEach(e => {
                const btn = document.createElement('button');
                btn.classList.add('time-btn', 'py-2', 'px-3', 'ms-2', 'mb-2');
                btn.onclick = function() { getShowtimeFuncBtn(btn); };
                btn.value = e.id;
                if (showtime != null && e.id === showtime.id) {
                    btn.classList.add('active');
                }
                btn.innerText = e.startTime;
                timeBtnDiv.appendChild(btn);
            });

            divShowtime.appendChild(timeBtnDiv);
        });

        divCinema.appendChild(divShowtime);
        cinema.appendChild(divCinema);
    });
}

function getShowtimeFuncBtn(button) {
    timeBtns = document.querySelectorAll(".time-btn");
    const showtimeId = btnFunc(button, timeBtns);
    showtime = showtimeFindById(showtimeId.toString());

    showtimeDetailCinemaShow(
        showtime.cinemaName,
        showtime.auditoriumName,
        showtime.screeningDate,
        showtime.startTime,
        showtime.auditoriumType
    );

    showtimeCheck();
}

function showtimeFindById(id) {
    return showtimeData.data.find(
        showtime => showtime.id.toString() === id
    ) || null;
}

function showtimeFindByDate(date) {
    return showtimeData.data.filter(
        showtime => showtime.screeningDate === date
    );
}

function btnFunc(button, btnArray) {
    btnArray.forEach(btn => {
        btn.classList.remove('active');
    });
    button.classList.add('active');
    return button.value;
}

function openNextAccordion(currentIndex) {
    const nextIndex = currentIndex + 1;
    const nextAccordionButton =
        document.querySelector(`#accordionExample .accordion-item:nth-of-type(${nextIndex}) .accordion-button`);
    if (nextAccordionButton) {
        nextAccordionButton.click();
    }
}

function showtimeCheck() {
    if (showtime != null) {
        nextBtn.classList.remove('disabled');
    } else {
        nextBtn.classList.add('disabled');
    }
}