let cityBtns;
let cityAccordion;
let movieAccordion;
let showtimeAccordion;
let divDate;
let cinema;
let dateBtns;
let timeBtns


function stageOne() {
    $.ajax({
        url: '/booking/get/stage-one', type: 'GET', success: function (htmlResponse) {
            divStage.innerHTML = htmlResponse;
            cityBtns = document.querySelectorAll(".city-btn");
            cityAccordion = document.querySelector("#collapseOne")
            movieAccordion = document.querySelector("#collapseTwo");
            showtimeAccordion = document.querySelector("#collapseThree");
            divDate = document.querySelector(".div-date");
            cinema = document.querySelector(".cinema");
            dateBtns = document.querySelectorAll(".date-btn");
            timeBtns = document.querySelectorAll(".time-btn");
        }, error: function (xhr, status, error) {
            console.error('Đã xảy ra lỗi: ' + error);
            console.error('Status:', status);
            console.error('Error:', error);
        }
    });
}

function getCityFunc(button) {
    cityValue = btnFunc(button, cityBtns)
    toggleAccordion(cityAccordion);
    toggleAccordion(movieAccordion);
}

function getMovieFunc(div) {
    movieId = div.getAttribute('value');
    const movieName = div.querySelector('h5').innerText;
    const moviePoster = div.querySelector('img').getAttribute('src');

    showtimeDetailMovieShow(moviePoster, movieName);
    showtimeDetailCinemaHide();
    getShowtime(movieId, cityValue);
    toggleAccordion(movieAccordion);
    toggleAccordion(showtimeAccordion);
}

async function getShowtime(movieId, cityId) {
    try {
        showtimeData = await axios.get('api/v1/showtime/get/' + movieId + '/' + cityId);
    } catch (e) {
        console.log(e);
    }
    if (showtimeData.data.length === 0) {
        divDate.innerHTML = "Phim bạn chọn chưa có lịch chiếu vui lòng chọn phim khác hoặc thử lại sau.";
        cinema.innerHTML = "";
    } else {
        showtimeData.data.forEach(showtime => {
            divDate.innerHTML = `  
                <button class="date-btn border-0 py-2 px-3"
                        onclick="getDateFunc(this)"
                        value="${showtime.id}">
                        ${showtime.screeningDate}
                </button>
            `;
        })
    }
}

let showtime;

function getDateFunc(button) {
    dateValue = btnFunc(button, dateBtns);
    showtime = showtimeFindById(dateValue);
    cinema.innerHTML = `
        <div class="border-bottom pb-3">
            <p><strong>${showtime.auditorium.cinema.name}</strong></p>
            <div class="row align-items-center">
                <div class="col-2">2D Phụ đề</div>
                <div class="col-10">
                    <button class="time-btn"
                        value="${showtime.id}"
                        onclick="getShowtimeFunc(this)">
                        ${showtime.startTime}
                    </button>
                </div>
            </div>
        </div>
    `
}

function showtimeFindById(id) {
    return showtimeData.data.find(showtime => showtime.id.toString() === id) || null;
}


function btnFunc(button, btnArray) {
    btnArray.forEach(btn => {
        btn.classList.remove('active');
    });
    button.classList.add('active');
    return button.value;
}

function toggleAccordion(accordion) {
    const bsAccordion = new bootstrap.Collapse(accordion);
    if (bsAccordion._isShown) {
        bsAccordion.hide();
    } else {
        bsAccordion.show();
    }
}

function showtimeDetailMovieShow(poster, name) {
    showtimeDetailMovie.innerHTML = `
        <div class="col-lg-4">
            <img src="${poster}"
                 alt="${name}">
        </div>
        <div class="col-lg-8 ps-3">
            <strong>${name}</strong>
        </div>
    `
}


function getShowtimeFunc(button) {
    const showtimeId = btnFunc(button, timeBtns);
    showtime = showtimeFindById(showtimeId.toString());
    auditoriumId = showtime.auditorium.id
    showtimeDetailCinemaShow(showtime.auditorium.cinema.name, showtime.auditorium.name, showtime.screeningDate, showtime.startTime);
    if (movieId && auditoriumId != null) {
        nextBtn.classList.remove('disabled');
    } else {
        console.log("Chưa chọn phim");
    }
}

function showtimeDetailCinemaShow(cinemaName, auditoriumName, startDate, startTime) {
    showtimeDetailCinema.innerHTML = `
         <div>
            <strong>${cinemaName}</strong>
            <span> - </span>
            <span>${auditoriumName}</span>
        </div>
        <div class="fs-14px mt-2">
            <span>Suất: </span>
            <strong>${startTime}</strong>
            <span> - </span>
            <strong>${startDate}</strong>
        </div>
    `
}

function showtimeDetailCinemaHide() {
    showtimeDetailCinema.innerHTML = "";
}




