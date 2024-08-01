const divNewMovies = document.querySelector(".new-movies");
getNewMovie();
async function getNewMovie () {
    try {
        let res = await axios.get("/api/v1/movies/get/new");
        divNewMovies.innerHTML = res.data;
    } catch (e) {
        console.log(e)
    }
}