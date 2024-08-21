document.addEventListener("DOMContentLoaded", function () {
    const liItems = document.querySelectorAll(".sidebar-item");

    function setActiveItem(index) {
        localStorage.setItem("activeMenuItem", index);
    }

    function getActiveItem() {
        return localStorage.getItem("activeMenuItem");
    }

    liItems.forEach(function (li, index) {
        li.addEventListener("click", function () {
            liItems.forEach(function (item) {
                item.classList.remove("active");
            });
            li.classList.add("active");
            setActiveItem(index);
        });
    });
    const activeIndex = getActiveItem();
    if (activeIndex !== null) {
        liItems.forEach(function (item) {
            item.classList.remove("active");
        });
        liItems[activeIndex].classList.add("active");
    }
});