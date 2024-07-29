package com.nongviet201.cinema.core;

import com.nongviet201.cinema.core.model.entity.bill.Combo;
import com.nongviet201.cinema.core.model.entity.cinema.*;
import com.nongviet201.cinema.core.model.entity.movie.*;
import com.nongviet201.cinema.core.model.enums.AuditoriumType;
import com.nongviet201.cinema.core.model.enums.SeatType;
import com.nongviet201.cinema.core.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CinemaCoreApplicationTests {

    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private AuditoriumRepository auditoriumRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private ComboRepository comboRepository;

    @Test
    void createData() {
        createCityData();
        createCinemaData();
        createAuditoriumData();
        createGenresData();
        createActorData();
        crateActorsData1();
        createDirectorsData();
        createDirectorData1();
        createCountryData();
        createMovieData();
        createShowTime();
        createComboFood();
    }

    @Test
    void createCityData() {
        cityRepository.save(new City(1, "Hà Nội"));
        cityRepository.save(new City(2, "Bắc Giang"));
        cityRepository.save(new City(3, "Thái Nguyên"));
        cityRepository.save(new City(4, "Cao Bằng"));
        cityRepository.save(new City(5, "TP. HCM"));
    }

    @Test
    void createCinemaData() {
        City hanoi = cityRepository.findById(1).orElse(null);
        ;
        City bacGiang = cityRepository.findById(2).orElse(null);
        ;
        City thaiNguyen = cityRepository.findById(3).orElse(null);
        ;
        City caoBang = cityRepository.findById(4).orElse(null);
        ;


        List<Cinema> cinemas = new ArrayList<>();
        cinemas.add(new Cinema(1, "Cinema Long Biên", "Hà Nội", hanoi));
        cinemas.add(new Cinema(2, "Cinema Cầu Giấy", "Hà Nội", hanoi));
        cinemas.add(new Cinema(3, "Cinema Thanh Xuân", "Hà Nội", hanoi));
        cinemas.add(new Cinema(4, "Cinema Bắc Giang", "Bắc Giang", bacGiang));
        cinemas.add(new Cinema(5, "Cinema Thái Nguyên", "Thái Nguyên", thaiNguyen));
        cinemas.add(new Cinema(6, "Cinema Cao Bằng", "Cao Bằng", caoBang));
        cinemaRepository.saveAll(cinemas);
    }

    @Test
    void createAuditoriumData() {
        List<Auditorium> auditoriums = new ArrayList<>();
        auditoriums.add(new Auditorium(1, "Room 1", 10, 12, AuditoriumType.NORMAL, cinemaRepository.findById(1)));
        auditoriums.add(new Auditorium(2, "Room 2", 10, 12, AuditoriumType.NORMAL, cinemaRepository.findById(1)));
        auditoriums.add(new Auditorium(3, "Room 3", 10, 12, AuditoriumType.NORMAL, cinemaRepository.findById(1)));
        auditoriums.add(new Auditorium(4, "Room 1", 10, 12, AuditoriumType.IMAX, cinemaRepository.findById(2)));
        auditoriums.add(new Auditorium(5, "Room 2", 10, 12, AuditoriumType.NORMAL, cinemaRepository.findById(2)));
        auditoriums.add(new Auditorium(6, "Room 3", 10, 12, AuditoriumType.NORMAL, cinemaRepository.findById(2)));
        auditoriumRepository.saveAll(auditoriums);
        for (Auditorium auditorium : auditoriums) {
            createSeatData(auditorium.getTotalRowChair(), auditorium.getTotalColumnChair(), auditorium.getId());
        }
    }

    @Test
    void createSeatData(int row, int column, int auditoriumId) {
        Auditorium auditorium = auditoriumRepository.findById(auditoriumId);
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                Seat seat = Seat.builder()
                        .type(SeatType.GHE_THUONG)
                        .status(true)
                        .seatRow(numberToLetter(i))
                        .seatColumn(j)
                        .auditorium(auditorium)
                        .build();
                seatRepository.save(seat);
            }
        }
    }
    String numberToLetter(int number) {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};

        // Chuyển đổi số sang chữ cái tương ứng
        if (number >= 1 && number <= 26) {
            return letters[number - 1];
        } else {
            return null;
        }
    }

    @Test
    void createGenresData() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(1, "Hành Động", "hanh-dong"));
        genres.add(new Genre(2, "Giả Tưởng", "gia-tuong"));
        genres.add(new Genre(3, "Hoạt Hình", "hoat-hinh"));
        genres.add(new Genre(4, "Hài", "hai"));
        genres.add(new Genre(5, "Kinh Di", "kinh-di"));
        genreRepository.saveAll(genres);
    }

    @Test
    void createGenresData1() {
        genreRepository.save(new Genre(6, "Giật Gân", "giat-gan"));
        genreRepository.save(new Genre(7, "Tội Phạm", "toi-pham"));
    }

    @Test
    void createActorData() {
        List<Actor> actors = new ArrayList<>();
        actors.add(new Actor(1, "Song Joong Ki", "avatar-song-joong-ki.jpg", "Song Joong Ki is a South Korean actor."));
        actors.add(new Actor(2, "Tom Holland", "avatar-tom-holland.jpg", "Tom Holland is an English actor."));
        actors.add(new Actor(3, "Scarlett Johansson", "avatar-scarlett-johansson.jpg", "Scarlett Johansson is an American actress."));
        actors.add(new Actor(4, "Chris Pratt", "avatar-chris-pratt.jpg", "Chris Pratt is an American actor."));
        actors.add(new Actor(5, "Zendaya", "avatar-zendaya.jpg", "Zendaya is an American actress and singer."));
        actors.add(new Actor(6, "Robert Downey Jr.", "avatar-robert-downey-jr.jpg", "Robert Downey Jr. is an American actor."));
        actors.add(new Actor(7, "Gal Gadot", "avatar-gal-gadot.jpg", "Gal Gadot is an Israeli actress."));
        actors.add(new Actor(8, "Chris Hemsworth", "avatar-chris-hemsworth.jpg", "Chris Hemsworth is an Australian actor."));
        actors.add(new Actor(9, "Jennifer Lawrence", "avatar-jennifer-lawrence.jpg", "Jennifer Lawrence is an American actress."));
        actors.add(new Actor(10, "Ryan Reynolds", "avatar-ryan-reynolds.jpg", "Ryan Reynolds is a Canadian actor."));
        actors.add(new Actor(11, "Emma Stone", "avatar-emma-stone.jpg", "Emma Stone is an American actress."));
        actors.add(new Actor(12, "Dwayne Johnson", "avatar-dwayne-johnson.jpg", "Dwayne Johnson is an American-Canadian actor."));
        actors.add(new Actor(13, "Margot Robbie", "avatar-margot-robbie.jpg", "Margot Robbie is an Australian actress."));
        actors.add(new Actor(14, "Will Smith", "avatar-will-smith.jpg", "Will Smith is an American actor and rapper."));
        actors.add(new Actor(15, "Emily Blunt", "avatar-emily-blunt.jpg", "Emily Blunt is an English-American actress."));
        actors.add(new Actor(16, "Tom Cruise", "avatar-tom-cruise.jpg", "Tom Cruise is an American actor."));
        actors.add(new Actor(17, "Angelina Jolie", "avatar-angelina-jolie.jpg", "Angelina Jolie is an American actress."));
        actors.add(new Actor(18, "Brad Pitt", "avatar-brad-pitt.jpg", "Brad Pitt is an American actor."));
        actors.add(new Actor(19, "Natalie Portman", "avatar-natalie-portman.jpg", "Natalie Portman is an Israeli-American actress."));
        actors.add(new Actor(20, "Chris Evans", "avatar-chris-evans.jpg", "Chris Evans is an American actor."));
        actors.add(new Actor(21, "Margot Robbie", "avatar-margot-robbie-2.jpg", "Margot Robbie is an Australian actress."));
        actors.add(new Actor(22, "Benedict Cumberbatch", "avatar-benedict-cumberbatch.jpg", "Benedict Cumberbatch is an English actor."));
        actors.add(new Actor(23, "Emma Watson", "avatar-emma-watson.jpg", "Emma Watson is an English actress."));
        actors.add(new Actor(24, "John Boyega", "avatar-john-boyega.jpg", "John Boyega is an English actor."));
        actors.add(new Actor(25, "Jason Momoa", "avatar-jason-momoa.jpg", "Jason Momoa is an American actor."));
        actors.add(new Actor(26, "Lee Min Ho", "avatar-lee-min-ho.jpg", "Lee Min Ho is a South Korean actor."));
        actors.add(new Actor(27, "Kim Soo Hyun", "avatar-kim-soo-hyun.jpg", "Kim Soo Hyun is a South Korean actor."));
        actors.add(new Actor(28, "Park Shin Hye", "avatar-park-shin-hye.jpg", "Park Shin Hye is a South Korean actress."));
        actors.add(new Actor(29, "Hyun Bin", "avatar-hyun-bin.jpg", "Hyun Bin is a South Korean actor."));
        actors.add(new Actor(30, "Jun Ji Hyun", "avatar-jun-ji-hyun.jpg", "Jun Ji Hyun is a South Korean actress."));
        actors.add(new Actor(31, "Park Seo Joon", "avatar-park-seo-joon.jpg", "Park Seo Joon is a South Korean actor."));
        actors.add(new Actor(32, "Kim Tae Ri", "avatar-kim-tae-ri.jpg", "Kim Tae Ri is a South Korean actress."));
        actors.add(new Actor(33, "Lee Jong Suk", "avatar-lee-jong-suk.jpg", "Lee Jong Suk is a South Korean actor."));
        actors.add(new Actor(34, "Bae Suzy", "avatar-bae-suzy.jpg", "Bae Suzy is a South Korean actress."));
        actorRepository.saveAll(actors);
    }

    @Test
    void crateActorsData1() {
        actorRepository.save(new Actor(35, "Ha Jung Woo", "Ha Jung Woo.img", "Ha Jung Woo is a South Korean actor."));
        actorRepository.save(new Actor(36, "Yeo Jin Goo", "Yeo Jin Goo.jpg", "Yeo Jin Goo is a South Korean actor."));
        actorRepository.save(new Actor(37, "Sung Dong Il", "Sung Dong Il.jpg", "Sung Dong Il is a South Korean actor."));
    }
    @Test
    void createDirectorsData() {
        List<Director> directors = new ArrayList<>();
        directors.add(new Director(1, "Christopher Nolan", "avatar-christopher-nolan.jpg",
                "Christopher Edward Nolan là một đạo diễn, nhà sản xuất và nhà viết kịch người Anh. Ông được biết đến với những bộ phim mang tính biểu tượng như loạt phim The Dark Knight và Inception."));
        directors.add(new Director(2, "Quentin Tarantino", "avatar-quentin-tarantino.jpg",
                "Quentin Jerome Tarantino là một đạo diễn, nhà biên kịch và nhà sản xuất phim người Mỹ. Ông nổi tiếng với phong cách viết kịch bản không tuân theo trật tự thời gian và sử dụng âm nhạc sâu sắc trong các bộ phim của mình."));
        directors.add(new Director(3, "Martin Scorsese", "avatar-martin-scorsese.jpg",
                "Martin Charles Scorsese là một đạo diễn, nhà sản xuất phim và biên kịch người Mỹ. Ông là một trong những đạo diễn hàng đầu của điện ảnh thế giới với nhiều bộ phim nổi tiếng như Taxi Driver và The Departed."));
        directors.add(new Director(4, "Steven Spielberg", "avatar-steven-spielberg.jpg",
                "Steven Allan Spielberg là một đạo diễn, nhà sản xuất phim và nhà viết kịch người Mỹ. Ông được biết đến với các bộ phim nổi tiếng như Jaws, E.T. the Extra-Terrestrial và Jurassic Park."));
        directors.add(new Director(5, "James Cameron", "avatar-james-cameron.jpg",
                "James Francis Cameron là một đạo diễn, nhà sản xuất phim và nhà viết kịch người Canada. Ông là người đạo diễn của các bộ phim ăn khách như Terminator 2: Judgment Day và Titanic."));
        directors.add(new Director(6, "David Fincher", "avatar-david-fincher.jpg",
                "David Andrew Leo Fincher là một đạo diễn và nhà sản xuất phim người Mỹ. Ông nổi tiếng với các bộ phim kinh dị tâm lý như Se7en và Fight Club."));
        directors.add(new Director(7, "Spike Lee", "avatar-spike-lee.jpg",
                "Shelton Jackson \"Spike\" Lee là một đạo diễn, nhà sản xuất và diễn viên phim người Mỹ. Ông nổi tiếng với phong cách nghệ thuật đa dạng và chính trị trong các bộ phim của mình."));
        directors.add(new Director(8, "Denis Villeneuve", "avatar-denis-villeneuve.jpg",
                "Denis Villeneuve là một đạo diễn và nhà biên kịch phim người Canada. Ông làm nổi bật tên tuổi với các bộ phim như Blade Runner 2049 và Arrival."));
        directors.add(new Director(9, "Woody Allen", "avatar-woody-allen.jpg",
                "Woody Allen là một đạo diễn, nhà biên kịch và diễn viên phim người Mỹ. Ông nổi tiếng với các bộ phim hài lãng mạn như Annie Hall và Midnight in Paris."));
        directors.add(new Director(10, "Wes Anderson", "avatar-wes-anderson.jpg",
                "Wesley Wales Anderson là một đạo diễn, nhà biên kịch và nhà sản xuất phim người Mỹ. Ông nổi tiếng với phong cách quay phim độc đáo và các câu chuyện kỳ lạ trong các bộ phim của mình."));
        directors.add(new Director(11, "Alfred Hitchcock", "avatar-alfred-hitchcock.jpg",
                "Sir Alfred Joseph Hitchcock là một đạo diễn và nhà sản xuất phim người Anh. Ông được coi là một trong những đạo diễn hàng đầu trong lịch sử điện ảnh với các tác phẩm kinh điển như Psycho và Vertigo."));
        directors.add(new Director(12, "Francis Ford Coppola", "avatar-francis-ford-coppola.jpg",
                "Francis Ford Coppola là một đạo diễn, nhà sản xuất và nhà viết kịch phim người Mỹ. Ông nổi tiếng với loạt phim The Godfather và Apocalypse Now."));
        directors.add(new Director(13, "Ang Lee", "avatar-ang-lee.jpg",
                "Ang Lee là một đạo diễn, nhà sản xuất và biên kịch phim người Đài Loan. Ông đã đoạt giải Oscar cho các bộ phim như Crouching Tiger, Hidden Dragon và Brokeback Mountain."));
        directors.add(new Director(14, "Roman Polanski", "avatar-roman-polanski.jpg",
                "Roman Polanski là một đạo diễn, nhà sản xuất và nhà viết kịch phim người Ba Lan-Francis. Ông nổi tiếng với các bộ phim như Rosemary's Baby và The Pianist."));
        directors.add(new Director(15, "Akira Kurosawa", "avatar-akira-kurosawa.jpg",
                "Akira Kurosawa là một đạo diễn và nhà viết kịch phim người Nhật Bản. Ông là một trong những đạo diễn có ảnh hưởng lớn nhất đối với điện ảnh thế giới với các tác phẩm như Seven Samurai và Rashomon."));
        directorRepository.saveAll(directors);
    }

    @Test
    void createDirectorData1() {
        directorRepository.save(new Director(16, "Kim Sung Han", "avatar-ch.img", "update..."));
    }

    @Test
    void createCountryData() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country(1, "Mỹ", "my"));
        countries.add(new Country(2, "Nhật Bản", "nhat-ban"));
        countries.add(new Country(3, "Hàn Quốc", "han-quoc"));
        countries.add(new Country(4, "Thái Lan", "thai-lan"));
        countries.add(new Country(5, "Việt Nam", "viet-nam"));
        countryRepository.saveAll(countries);
    }

    @Test
    void createMovieData() {
        Country hanQuoc = countryRepository.findById(3).orElse(null);
        List<Genre> genres = new ArrayList<>();
        genres.add(genreRepository.findById(1).orElse(null));

        List<Director> directors = new ArrayList<>();
        directors.add(directorRepository.findById(16).orElse(null));
        List<Actor> actors = new ArrayList<>();
        actors.add(actorRepository.findById(35).orElse(null));
        actors.add(actorRepository.findById(36).orElse(null));
        actors.add(actorRepository.findById(37).orElse(null));

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(
                1,
                "Vây Hãm Trên Không",
                "vay-ham-tren-khong",
                "Bộ phim hành động ly kỳ dựa trên sự kiện có thật với sự tham gia của Ha Jung Woo, Yeo Jin Goo và Sung Dong Il được dựa trên một sự kiện có thật năm 1971, khi một thanh niên Hàn Quốc định cướp một chiếc máy bay chở khách khởi hành từ thành phố cảnh phía đông Sokcho bay tới Seoul. Mọi người trên chuyến bay này đều đang đặt cược mạng sống của mình!",
                "https://cdn.galaxycine.vn/media/2024/7/19/hijack-1971-1_1721360469683.jpg",
                120,
                8.5,
                true,
                "https://youtu.be/1Umr4h5dn5I",
                LocalDate.of(2024, 7, 19),
                LocalDate.of(2024, 7, 19),
                LocalDate.of(2024, 8, 19),
                LocalDate.now(),
                LocalDate.now(),
                hanQuoc,
                genres,
                directors,
                actors
        ));
        movieRepository.saveAll(movies);
    }

    @Test
    void createShowTime() {
        Movie movie = movieRepository.findById(1).orElse(null);
        Auditorium auditorium = auditoriumRepository.findById(1);
        List<Showtime> showtimes = new ArrayList<>();
        showtimes.add(new Showtime(1, LocalDate.of(2024, 7, 30), LocalTime.of(12, 0), LocalTime.of(14,0), movie, auditorium));
        showtimes.add(new Showtime(2, LocalDate.of(2024, 7, 30), LocalTime.of(14, 0), LocalTime.of(16,0), movie, auditorium));
        showtimes.add(new Showtime(3, LocalDate.of(2024, 7, 29  ), LocalTime.of(14, 0), LocalTime.of(16,0), movie, auditorium));
        showtimeRepository.saveAll(showtimes);
    }

    @Test
    void createComboFood() {
        List<Combo> combo = new ArrayList<>();
        combo.add(new Combo(1, "Combo Couple", " 02 Ly nước ngọt size L + 01 Hộp bắp + 1 Snack", "https://cdn.galaxycine.vn/media/2024/3/29/menuboard-coonline-2024-combo2-min_1711699866349.jpg", true, 190000));
        combo.add(new Combo(2, "Combo FA", " 01 Ly nước ngọt size L + 01 Hộp bắp", "https://cdn.galaxycine.vn/media/2024/3/29/menuboard-coonline-2024-combo1-copy-min_1711699814762.jpg", true, 190000));
        comboRepository.saveAll(combo);
    }

}
