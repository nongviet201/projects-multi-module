

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
    showtime = null;
    showtimeCheck();
    openNextAccordion(1);
}

function getMovieFunc(div) {
    const movieId = div.getAttribute('value');
    const movieName = div.querySelector('h5').innerText;
    const moviePoster = div.querySelector('img').getAttribute('src');

    showtimeDetailMovieShow(moviePoster, movieName);
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
            dateBtn.classList.add('date-btn', 'cursor-pointer', 'ms-3', 'py-2', 'px-3');
            dateBtn.onclick =  function() { getDateFunc(dateBtn); };
            dateBtn.value = date;
            dateBtn.innerText = date;
            divDate.appendChild(dateBtn);
        })
    }
}

function getDateFunc(button) {
    const dateBtns = document.querySelectorAll(".date-btn");
    const dateValue = btnFunc(button, dateBtns);

    const cinema = document.querySelector(".cinema");
    cinema.innerHTML = "";

    listShowtimeByDate = showtimeFindByDate(dateValue);
    const cinemaListName = new Set;
    listShowtimeByDate.forEach( e => {
        if (e.auditorium.cinema.city.id.toString() === cityId) {
            cinemaListName.add(e.auditorium.cinema.name);
        }
    })

    cinemaListName.forEach(cinemaName => {
        const divCinema = document.createElement('div');
        divCinema.classList.add('border-bottom', 'pb-3');
        divCinema.innerHTML = `<p><strong>${cinemaName}</strong></p>`;
        const divShowtime = document.createElement('div');
        divShowtime.classList.add('row', 'align-items-center');
        const movieType = document.createElement('div');
        movieType.classList.add('col-2');
        movieType.innerText = "2D Phụ đề";
        divShowtime.appendChild(movieType)
        const timeBtn = document.createElement('div');
        timeBtn.classList.add('col-10');
        listShowtimeByDate.forEach(showtime => {
            if (showtime.auditorium.cinema.name === cinemaName) {
                const btn = document.createElement('button');
                btn.classList.add('time-btn', 'py-2', 'px-3', 'ms-2');
                btn.onclick = function() {getShowtimeFuncBtn(btn)};
                btn.value = showtime.id;
                btn.innerText = showtime.startTime;
                timeBtn.appendChild(btn);
            }
        })
        divShowtime.appendChild(timeBtn)
        divCinema.appendChild(divShowtime);
        cinema.appendChild(divCinema);
    })
}

function getShowtimeFuncBtn(button) {
    timeBtns = document.querySelectorAll(".time-btn");
    const showtimeId = btnFunc(button, timeBtns);
    showtime = showtimeFindById(showtimeId.toString());
    auditoriumId = showtime.auditorium.id;

    showtimeDetailCinemaShow(
        showtime.auditorium.cinema.name,
        showtime.auditorium.name,
        showtime.screeningDate,
        showtime.startTime
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