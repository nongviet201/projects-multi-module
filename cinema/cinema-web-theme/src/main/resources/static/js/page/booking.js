const cityBtns = document.querySelectorAll(".city-btn");
const cityAccordion = document.querySelector("#collapseOne")
const movieAccordion = document.querySelector("#collapseTwo");
const showtimeAccordion = document.querySelector("#collapseThree");
let cityValue;
let movieValue;

function btnFunc(button, btnArray) {
    btnArray.forEach(btn => {
        btn.classList.remove('active');
    });
    button.classList.add('active');
    return button.value;
}

function getCityFunc(button) {
    cityValue = btnFunc(button, cityBtns)

    toggleAccordion(cityAccordion);
    toggleAccordion(movieAccordion);
}

function getMovieFunc(div) {
    movieValue = div.getAttribute('value');
    getShowtime(movieValue, cityValue);
    toggleAccordion(movieAccordion);
    toggleAccordion(showtimeAccordion);
}

const divDate = document.querySelector(".div-date");
const cinema = document.querySelector(".cinema");
const dateBtns = document.querySelectorAll(".date-btn");
const timeBtns = document.querySelectorAll(".time-btn");
let showtimeData;
let html = "";

async function getShowtime(movieId, cityId) {
    try {
        showtimeData = await axios.get('api/v1/showtime/get/' + movieId + '/' + cityId);
        console.log(showtimeData.data);
    } catch (e) {
        console.log(e);
    }
    showtimeData.data.forEach(showtime => {
        html += `  
        <button class="date-btn border-0 py-2 px-3"
                onclick="getDateFunc(this)"
                value="${showtime.id}">
                ${showtime.screeningDate}
        </button>
        `;
        divDate.innerHTML = html;
        html = "";
    })
}


function getDateFunc(button) {
    dateValue = btnFunc(button, dateBtns);
    showtimeData.data.forEach(showtime => {
        console.log("showtime.id:", showtime.id, typeof showtime.id);
        console.log("dateValue:", dateValue, typeof dateValue);
        if (showtime.id.toString() === dateValue) {
            html += `
                <div class="border-bottom pb-3">
                    <p><strong>${showtime.auditorium.cinema.name}</strong></p>
                    <div class="row align-items-center">
                        <div class="col-2">2D Phụ đề</div>
                        <div class="col-10">
                            <button class="time-btn">
                                ${showtime.startTime}
                            </button>
                        </div>
                    </div>
                </div>
            `;
            cinema.innerHTML = html;
            console.log(cinema)
            html = "";
        }
    })
}

function toggleAccordion(accordion) {
    const bsAccordion = new bootstrap.Collapse(accordion);
    if (bsAccordion._isShown) {
        bsAccordion.hide();
    } else {
        bsAccordion.show();
    }
}
