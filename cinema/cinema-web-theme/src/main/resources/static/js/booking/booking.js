const divStage = document.getElementById("stage");
let currentStage = 1;
const nextBtn = document.getElementById("next-page");
const prevBtn = document.getElementById("prev-page");

let totalPrice = 0;
let totalComboPrice = 0;
let totalTicketPrice = 0;

if (code !== null) {
    currentStage = 5;
    stageChange();
}

if (showtime === null) {
    stageOne();
} else {
    currentStage = 2;
    stageChange();
}

nextBtn.addEventListener('click', async () => {
    if (currentStage.toString() !== "4") {
        if (currentStage.toString() === "1") {
            const loggedIn = await checkLogin();
            if (loggedIn) {
                nextBtn.classList.remove("disabled");
                nextFunc();
            }
        } else {
            nextFunc();
        }
    }
});

function nextFunc() {
    currentStage++;
    stageChange();
}

prevBtn.addEventListener('click', () => {
    if (currentStage === 3) {
        divStage.innerHTML = "";
        showStageTwo();
        currentStage--;
        updateBarStage();
        ticketDetailComboHide();
    } else {
        currentStage--;
        stageChange();
    }
})

function stageChange() {
    divStage.innerHTML = "";
    updateBarStage();
    stageFunction()
}


function stageFunction() {
    switch (currentStage) {
        case 0:
            window.location.href = "/";
            break;
        case 1:
            window.location.href = "/booking";
            stageOne()
            break;
        case 2:
            stageTwo()
            break;
        case 3:
            hideStageTwo()
            stageThree()
            break
        case 4:
            stageFour()
            break
        case 5:
            stageFive()
            break
    }
}