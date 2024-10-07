package com.nongviet201.cinema.core;

import com.github.javafaker.Faker;
import com.nongviet201.cinema.core.entity.Post;
import com.nongviet201.cinema.core.entity.bill.*;
import com.nongviet201.cinema.core.entity.bill.coupon.Coupon;
import com.nongviet201.cinema.core.entity.bill.coupon.CouponRoles;
import com.nongviet201.cinema.core.entity.bill.coupon.OneTimeCoupon;
import com.nongviet201.cinema.core.entity.cinema.*;
import com.nongviet201.cinema.core.entity.movie.*;
import com.nongviet201.cinema.core.entity.user.Employee;
import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.entity.user.UserStatistic;
import com.nongviet201.cinema.core.model.enums.PostType;
import com.nongviet201.cinema.core.model.enums.bill.BillStatus;
import com.nongviet201.cinema.core.model.enums.bill.PaymentMethod;
import com.nongviet201.cinema.core.model.enums.cinema.AuditoriumType;
import com.nongviet201.cinema.core.model.enums.cinema.SeatType;
import com.nongviet201.cinema.core.model.enums.coupon.DiscountCouponType;
import com.nongviet201.cinema.core.model.enums.coupon.UserType;
import com.nongviet201.cinema.core.model.enums.movie.AgeRequirement;
import com.nongviet201.cinema.core.model.enums.movie.GraphicsType;
import com.nongviet201.cinema.core.model.enums.movie.MovieScheduleStatus;
import com.nongviet201.cinema.core.model.enums.movie.TranslationType;
import com.nongviet201.cinema.core.model.enums.showtime.DayType;
import com.nongviet201.cinema.core.model.enums.showtime.ScreeningTimeType;
import com.nongviet201.cinema.core.model.enums.user.EmployeePosition;
import com.nongviet201.cinema.core.model.enums.user.EmployeeStatus;
import com.nongviet201.cinema.core.model.enums.user.UserRank;
import com.nongviet201.cinema.core.model.enums.user.UserRole;
import com.nongviet201.cinema.core.payment.vnpay.code.ResponseCodeVNPAY;
import com.nongviet201.cinema.core.repository.*;
import com.nongviet201.cinema.core.service.BaseTicketPriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.time.LocalDate.now;

@SpringBootTest
class CinemaCoreApplicationTests {

    Random random = new Random();

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private OneTimeCouponRepository oneTimeCouponRepository;
    @Autowired
    private CouponRolesRepository couponRolesRepository;
    @Autowired
    private BaseTicketPriceRepository baseTicketPriceRepository;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillSeatRepository billSeatRepository;
    @Autowired
    private BillComboRepository billComboRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private MovieScheduleRepository movieScheduleRepository;
    @Autowired
    private UserStatisticRepository userStatisticRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    void createData() {
        createCityData();
        createCinemaData();
        createAuditoriumData();
        createGenresData();
        createGenresData1();
        createActorData();
        crateActorsData1();
        createDirectorsData();
        createDirectorData1();
        createCountryData();
        createMovieData();
        createComboFood();
        createPost();
        createBaseOneTimeCoupon();
        createCoupon();
        generateRandomBills();
        employeeCreateData();
    }

    void createCityData() {
        cityRepository.save(new City(1, "Hà Nội"));
        cityRepository.save(new City(2, "Bắc Giang"));
        cityRepository.save(new City(3, "Thái Nguyên"));
        cityRepository.save(new City(4, "Cao Bằng"));
        cityRepository.save(new City(5, "TP. HCM"));
    }

    public void createCinemaData() {
        // Lấy dữ liệu các thành phố từ cơ sở dữ liệu
        City hanoi = cityRepository.findById(1).orElse(null);
        City bacGiang = cityRepository.findById(2).orElse(null);
        City thaiNguyen = cityRepository.findById(3).orElse(null);
        City caoBang = cityRepository.findById(4).orElse(null);
        City tpHCM = cityRepository.findById(5).orElse(null);

        // Tạo danh sách các rạp chiếu phim
        List<Cinema> cinemas = new ArrayList<>();
        List<User> users = generateUsers(16);

        cinemas.add(new Cinema(1, "VCinema Long Biên", "Long Biên, Hà Nội, Việt Nam", 21.026393010780403, 105.89992863856092, true, false, now(), now(), hanoi, Collections.singletonList(users.get(0))));
        cinemas.add(new Cinema(2, "VCinema Cầu Giấy", "Ngh. 29/11, Dịch Vọng, Cầu Giấy, Hà Nội, Việt Nam", 21.03351528834814, 105.79411818273913, true, false, now(), now(), hanoi, Collections.singletonList(users.get(1))));
        cinemas.add(new Cinema(3, "VCinema Thanh Xuân", "Khương Trung, Thanh Xuân, Hà Nội, Việt Nam", 20.997492275942655, 105.8199491662796, true, false, now(), now(), hanoi, Collections.singletonList(users.get(2))));

        cinemas.add(new Cinema(4, "VCinema Bắc Giang I", "Tp. Bắc Giang, Phường Ngô Quyền, Bắc Giang, Việt Nam", 21.27912091519057, 106.19574872580445, true, false, now(), now(), bacGiang, Collections.singletonList(users.get(3))));
        cinemas.add(new Cinema(5, "VCinema Việt Yên", "Hoàng Ninh, Việt Yên, Bắc Giang, Việt Nam", 21.252419263627424, 106.13967108182585, true, false, now(), now(), bacGiang, Collections.singletonList(users.get(4))));
        cinemas.add(new Cinema(6, "VCinema Quang Châu", "Quang Châu, Việt Yên, Bắc Giang, Việt Nam", 21.21971954371105, 106.1117665271365, true, false, now(), now(), bacGiang, Collections.singletonList(users.get(5))));

        cinemas.add(new Cinema(7, "VCinema Thái Nguyên", "Tân Lập, Tp. Thái Nguyên, Thái Nguyên, Việt Nam", 21.56276548570201, 105.83117308322893, true, false, now(), now(), thaiNguyen, Collections.singletonList(users.get(6))));
        cinemas.add(new Cinema(8, "VCinema Sông Công", "FRHV+42, Lương Châu, Tp. Sông Công, Thái Nguyên, Việt Nam", 21.477988250508425, 105.84253036503291, true, false, now(), now(), thaiNguyen, Collections.singletonList(users.get(7))));
        cinemas.add(new Cinema(9, "VCinema Phổ Yên", "Ba Hàng, Phổ Yên, Thái Nguyên, Việt Nam", 21.418647331024115, 105.87165006225278, true, false, now(), now(), thaiNguyen, Collections.singletonList(users.get(8))));

        cinemas.add(new Cinema(10, "VCinema Cao Bằng", "M785+8GC, P. Hợp giang, Cao Bằng, Việt Nam", 22.666036484334104, 106.25877162623944, true, false, now(), now(), caoBang, Collections.singletonList(users.get(9))));
        cinemas.add(new Cinema(11, "VCinema Trùng Khánh", "Sông Quy Xuân, Đàm Thuỷ, Trùng Khánh, Cao Bằng, Việt Nam", 22.8549771376638, 106.72388721431751, true, false, now(), now(), caoBang, Collections.singletonList(users.get(10))));

        cinemas.add(new Cinema(12, "VCinema Bình Thạnh", "Bình Thạnh, Hồ Chí Minh, Việt Nam", 10.806659429276372, 106.70238077660515, true, false, now(), now(), tpHCM, Collections.singletonList(users.get(11))));
        cinemas.add(new Cinema(13, "VCinema Gò Vấp", "191 Đ. Lê Văn Thọ, Phường 8, Gò Vấp, Hồ Chí Minh, Việt Nam", 10.842124688214556, 106.65608276289838, true, false, now(), now(), tpHCM, Collections.singletonList(users.get(12))));
        cinemas.add(new Cinema(14, "VCinema Tân Bình", "2 Nguyễn Thế Lộc, Phường 12, Tân Bình, Hồ Chí Minh, Việt Nam", 10.798908749930709, 106.65026783102783, true, false, now(), now(), tpHCM, Collections.singletonList(users.get(13))));
        cinemas.add(new Cinema(15, "VCinema Tân Phú", "162-186 Tây Sơn, Tân Quý, Tân Phú, Hồ Chí Minh, Việt Nam", 10.79569107360507, 106.62383067986916, true, false, now(), now(), tpHCM, Collections.singletonList(users.get(14))));
        cinemas.add(new Cinema(16, "VCinema Phú Nhuận", "Phường 15, Phú Nhuận, Hồ Chí Minh, Việt Nam", 10.796318344416628, 106.68024211641091, true, false, now(), now(), tpHCM, Collections.singletonList(users.get(15))));

        // Lưu danh sách các rạp chiếu phim vào cơ sở dữ liệu
        cinemaRepository.saveAll(cinemas);

        // Tạo giá vé cơ bản cho từng rạp chiếu phim
        for (Cinema cinema : cinemas) {
            createBaseTicketPrices(cinema);
        }
    }
    void createAuditoriumData() {
        List<Auditorium> auditoriums = new ArrayList<>();
        List<Cinema> cinemas = cinemaRepository.findAll();
        int[] numbers = {3, 4, 5};
        int[] rowChair = {10, 12, 13};
        AuditoriumType[] types = AuditoriumType.values();

        for (Cinema cinema : cinemas) {
            for (int i = 1; i <= numbers[random.nextInt(numbers.length)]; i++) {
                Auditorium auditorium = Auditorium.builder().name("Room " + i).totalRowChair(rowChair[random.nextInt(rowChair.length)]).totalColumnChair(rowChair[random.nextInt(rowChair.length)] + numbers[random.nextInt(numbers.length)]).auditoriumType(types[random.nextInt(types.length)]).cinema(cinema).build();
                auditoriumRepository.save(auditorium);
                createSeatData(auditorium.getTotalRowChair(), auditorium.getTotalColumnChair(), auditorium.getId());
            }
        }
    }

    void createSeatData(int row, int column, int auditoriumId) {
        Optional<Auditorium> auditorium = auditoriumRepository.findById(auditoriumId);
        SeatType seatType;

        for (int i = 1; i <= row; i++) {
            if (i == 1) {
                seatType = SeatType.COUPLE;
            } else if (i > 1 && i <= (row / 2) + 1) {
                seatType = SeatType.VIP;
            } else {
                seatType = SeatType.NORMAL;
            }
            for (int j = 1; j <= column; j++) {
                if (seatType == SeatType.COUPLE) {
                    if (j == column && column % 2 != 0) {
                        continue;
                    }
                }
                    Seat seat = Seat.builder()
                        .type(seatType)
                        .status(true)
                        .seatRow(numberToLetter(i))
                        .seatColumn(j)
                        .auditorium(auditorium.orElse(null)) // Use orElse(null) to handle Optional
                        .build();
                seatRepository.save(seat);
            }
        }
    }

    String numberToLetter(int number) {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        // Chuyển đổi số sang chữ cái tương ứng
        if (number >= 1 && number <= 26) {
            return letters[number - 1];
        } else {
            return null;
        }
    }

    void createGenresData() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(1, "Hành Động", "hanh-dong"));
        genres.add(new Genre(2, "Giả Tưởng", "gia-tuong"));
        genres.add(new Genre(3, "Hoạt Hình", "hoat-hinh"));
        genres.add(new Genre(4, "Hài", "hai"));
        genres.add(new Genre(5, "Kinh Di", "kinh-di"));
        genreRepository.saveAll(genres);
    }

    void createGenresData1() {
        genreRepository.save(new Genre(6, "Giật Gân", "giat-gan"));
        genreRepository.save(new Genre(7, "Tội Phạm", "toi-pham"));
    }

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

    void crateActorsData1() {
        actorRepository.save(new Actor(35, "Ha Jung Woo", "Ha Jung Woo.img", "Ha Jung Woo is a South Korean actor."));
        actorRepository.save(new Actor(36, "Yeo Jin Goo", "Yeo Jin Goo.jpg", "Yeo Jin Goo is a South Korean actor."));
        actorRepository.save(new Actor(37, "Sung Dong Il", "Sung Dong Il.jpg", "Sung Dong Il is a South Korean actor."));
    }

    void createDirectorsData() {
        List<Director> directors = new ArrayList<>();
        directors.add(new Director(1, "Christopher Nolan", "avatar-christopher-nolan.jpg", "Christopher Edward Nolan là một đạo diễn, nhà sản xuất và nhà viết kịch người Anh. Ông được biết đến với những bộ phim mang tính biểu tượng như loạt phim The Dark Knight và Inception."));
        directors.add(new Director(2, "Quentin Tarantino", "avatar-quentin-tarantino.jpg", "Quentin Jerome Tarantino là một đạo diễn, nhà biên kịch và nhà sản xuất phim người Mỹ. Ông nổi tiếng với phong cách viết kịch bản không tuân theo trật tự thời gian và sử dụng âm nhạc sâu sắc trong các bộ phim của mình."));
        directors.add(new Director(3, "Martin Scorsese", "avatar-martin-scorsese.jpg", "Martin Charles Scorsese là một đạo diễn, nhà sản xuất phim và biên kịch người Mỹ. Ông là một trong những đạo diễn hàng đầu của điện ảnh thế giới với nhiều bộ phim nổi tiếng như Taxi Driver và The Departed."));
        directors.add(new Director(4, "Steven Spielberg", "avatar-steven-spielberg.jpg", "Steven Allan Spielberg là một đạo diễn, nhà sản xuất phim và nhà viết kịch người Mỹ. Ông được biết đến với các bộ phim nổi tiếng như Jaws, E.T. the Extra-Terrestrial và Jurassic Park."));
        directors.add(new Director(5, "James Cameron", "avatar-james-cameron.jpg", "James Francis Cameron là một đạo diễn, nhà sản xuất phim và nhà viết kịch người Canada. Ông là người đạo diễn của các bộ phim ăn khách như Terminator 2: Judgment Day và Titanic."));
        directors.add(new Director(6, "David Fincher", "avatar-david-fincher.jpg", "David Andrew Leo Fincher là một đạo diễn và nhà sản xuất phim người Mỹ. Ông nổi tiếng với các bộ phim kinh dị tâm lý như Se7en và Fight Club."));
        directors.add(new Director(7, "Spike Lee", "avatar-spike-lee.jpg", "Shelton Jackson \"Spike\" Lee là một đạo diễn, nhà sản xuất và diễn viên phim người Mỹ. Ông nổi tiếng với phong cách nghệ thuật đa dạng và chính trị trong các bộ phim của mình."));
        directors.add(new Director(8, "Denis Villeneuve", "avatar-denis-villeneuve.jpg", "Denis Villeneuve là một đạo diễn và nhà biên kịch phim người Canada. Ông làm nổi bật tên tuổi với các bộ phim như Blade Runner 2049 và Arrival."));
        directors.add(new Director(9, "Woody Allen", "avatar-woody-allen.jpg", "Woody Allen là một đạo diễn, nhà biên kịch và diễn viên phim người Mỹ. Ông nổi tiếng với các bộ phim hài lãng mạn như Annie Hall và Midnight in Paris."));
        directors.add(new Director(10, "Wes Anderson", "avatar-wes-anderson.jpg", "Wesley Wales Anderson là một đạo diễn, nhà biên kịch và nhà sản xuất phim người Mỹ. Ông nổi tiếng với phong cách quay phim độc đáo và các câu chuyện kỳ lạ trong các bộ phim của mình."));
        directors.add(new Director(11, "Alfred Hitchcock", "avatar-alfred-hitchcock.jpg", "Sir Alfred Joseph Hitchcock là một đạo diễn và nhà sản xuất phim người Anh. Ông được coi là một trong những đạo diễn hàng đầu trong lịch sử điện ảnh với các tác phẩm kinh điển như Psycho và Vertigo."));
        directors.add(new Director(12, "Francis Ford Coppola", "avatar-francis-ford-coppola.jpg", "Francis Ford Coppola là một đạo diễn, nhà sản xuất và nhà viết kịch phim người Mỹ. Ông nổi tiếng với loạt phim The Godfather và Apocalypse Now."));
        directors.add(new Director(13, "Ang Lee", "avatar-ang-lee.jpg", "Ang Lee là một đạo diễn, nhà sản xuất và biên kịch phim người Đài Loan. Ông đã đoạt giải Oscar cho các bộ phim như Crouching Tiger, Hidden Dragon và Brokeback Mountain."));
        directors.add(new Director(14, "Roman Polanski", "avatar-roman-polanski.jpg", "Roman Polanski là một đạo diễn, nhà sản xuất và nhà viết kịch phim người Ba Lan-Francis. Ông nổi tiếng với các bộ phim như Rosemary's Baby và The Pianist."));
        directors.add(new Director(15, "Akira Kurosawa", "avatar-akira-kurosawa.jpg", "Akira Kurosawa là một đạo diễn và nhà viết kịch phim người Nhật Bản. Ông là một trong những đạo diễn có ảnh hưởng lớn nhất đối với điện ảnh thế giới với các tác phẩm như Seven Samurai và Rashomon."));
        directorRepository.saveAll(directors);
    }

    void createDirectorData1() {
        directorRepository.save(new Director(16, "Kim Sung Han", "avatar-ch.img", "update..."));
    }

    void createCountryData() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country(1, "Mỹ", "my"));
        countries.add(new Country(2, "Nhật Bản", "nhat-ban"));
        countries.add(new Country(3, "Hàn Quốc", "han-quoc"));
        countries.add(new Country(4, "Thái Lan", "thai-lan"));
        countries.add(new Country(5, "Việt Nam", "viet-nam"));
        countryRepository.saveAll(countries);
    }

    void createMovieData() {
        Country my = countryRepository.findById(1).orElse(null);
        Country nhatBan = countryRepository.findById(2).orElse(null);
        Country hanQuoc = countryRepository.findById(3).orElse(null);
        Country thaiLan = countryRepository.findById(4).orElse(null);
        Country vietNam = countryRepository.findById(5).orElse(null);
        Country indonesia = countryRepository.save(new Country(6, "Indonesia", "indonesia"));

        List<Genre> hanhDongGiaTuong = new ArrayList<>();
        hanhDongGiaTuong.add(genreRepository.findById(1).orElse(null));
        hanhDongGiaTuong.add(genreRepository.findById(2).orElse(null));

        List<Genre> kinhDi = new ArrayList<>();
        kinhDi.add(genreRepository.findById(5).orElse(null));

        List<Genre> hoatHinh = new ArrayList<>();
        hoatHinh.add(genreRepository.findById(3).orElse(null));
        List<Genre> hanhdong = new ArrayList<>();
        hanhdong.add(genreRepository.findById(1).orElse(null));

        List<Director> directors = new ArrayList<>();
        directors.add(directorRepository.findById(16).orElse(null));
        List<Actor> actors = new ArrayList<>();
        actors.add(actorRepository.findById(35).orElse(null));
        actors.add(actorRepository.findById(36).orElse(null));
        actors.add(actorRepository.findById(37).orElse(null));

        Director nagaokaTomoka = directorRepository.save(Director.builder().name("Nagaoka Tomoka").bio("đang cập nhật...").avatar("avatar.png").build());
        Director ShawnLevy = directorRepository.save(Director.builder().name("Shawn Levy").bio("đang cập nhật...").avatar("avatar.png").build());
        Director JokoAnwar = directorRepository.save(Director.builder().name("Joko Anwar").bio("đang cập nhật...").avatar("avatar.png").build());


        Actor TakayamaMinami = actorRepository.save(Actor.builder().name("Takayama Minami").bio("đang cập nhật...").avatar("avatar.png").build());
        Actor YamazakiWakana = actorRepository.save(Actor.builder().name("Yamazaki Wakana").bio("đang cập nhật...").avatar("avatar.png").build());
        Actor RyanReynolds = actorRepository.save(Actor.builder().name("Ryan Reynolds").bio("đang cập nhật...").avatar("avatar.png").build());
        Actor HughJackman = actorRepository.save(Actor.builder().name("Hugh Jackman").bio("đang cập nhật...").avatar("avatar.png").build());
        Actor PatrickStewart = actorRepository.save(Actor.builder().name("Patrick Stewart").bio("đang cập nhật...").avatar("avatar.png").build());
        Actor ChristineHakim = actorRepository.save(Actor.builder().name("Christine Hakim").bio("đang cập nhật...").avatar("avatar.png").build());
        Actor RezaRahadian = actorRepository.save(Actor.builder().name("Reza Rahadian").bio("đang cập nhật...").avatar("avatar.png").build());
        Actor FaradinaMufti = actorRepository.save(Actor.builder().name("Faradina Mufti").bio("đang cập nhật...").avatar("avatar.png").build());

        movieRepository.save(Movie.builder().name("Vây Hãm Trên Không").slug("vay-ham-tren-khong").description("Bộ phim hành động ly kỳ dựa trên sự kiện có thật với sự tham gia của Ha Jung Woo, Yeo Jin Goo và Sung Dong Il được dựa trên một sự kiện có thật năm 1971, khi một thanh niên Hàn Quốc định cướp một chiếc máy bay chở khách khởi hành từ thành phố cảnh phía đông Sokcho bay tới Seoul. Mọi người trên chuyến bay này đều đang đặt cược mạng sống của mình!").poster("https://cdn.galaxycine.vn/media/2024/7/19/hijack-1971-1_1721360469683.jpg").bannerImg("https://cdn.galaxycine.vn/media/2024/7/19/hijack-1971-2_1721360477277.jpg").trailer("https://www.youtube.com/embed/f9OeK-ruVVo?si=A2n-HyqQtcz4veZP").ageRequirement(AgeRequirement.T16).duration(120).rating(8.5).status(true).releaseDate(LocalDate.of(2024, 7, 19)).createdAt(now()).updatedAt(now()).producer("Perfect Storm Film").graphicsTypes(List.of(GraphicsType._2D)).translationTypes(List.of(TranslationType.PHỤ_ĐỀ)).country(List.of(hanQuoc)).genres(hanhdong).directors(directors).actors(actors).build());

        movieRepository.save(Movie.builder().name("Deadpool & Wolverine").slug("Deadpool-&-Wolverine").description("<div class=\"block__wysiwyg text-black-10 text-sm font-normal not-italic content-text content__data__full\"><p style=\"text-align:justify; margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:150%\">Sau một số tác phẩm chưa đạt thành công như kì vọng gần đây, Marvel Studios ngày càng thận trọng khi ra mắt các dự án mới. Deadpool &amp; Wolverine chính là bộ phim Marvel duy nhất ra mắt năm 2024. Bộ phim là tác phẩm mà công chúng kì vọng sẽ cứu rỗi vũ trụ điện ảnh Marvel khỏi cơn thoái trào. Chính vì vậy, chẳng có gì ngạc nhiên khi Deadpool &amp; Wolverine được đầu tư, chăm chút hết sức kĩ lưỡng.</span></span></span></p>\n" + "\n" + "<p style=\"text-align:justify; margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:150%\">Sau teaser và trailer đầu tiên, cốt truyện Deadpool &amp; Wolverine dần dần hé lộ. Đặc sản “trứng phục sinh” bùng nổ ở trailer, khiến khán giả đồn đoán liên tục, gợi nhớ đến loạt tác phẩm quen thuộc như <i>Ant-Man</i>, <i>X-Men United</i>, <i>X-Men: First Class</i>, <i>Loki</i>…</span></span></span></p>\n" + "\n" + "<p style=\"text-align:justify; margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:150%\">Phản diện phần này là Cassandra Nova – em gái song sinh độc ác của giáo sư X. Ả sở hữu khả năng ngoại cảm cùng hàng tá kĩ năng dễ dàng đo ván Wolverine và Deadpool. Vai diễn nặng kí này Emma Corrin đảm nhận. Cô được biết đến khi trở thành vương phi Diana thời trẻ trong series truyền hình nổi tiếng The Crown. </span></span></span></p>\n" + "\n" + "<p style=\"text-align:justify; margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:150%\">Sau Tim Miller (Deadpool) và David Leitch (Deadpool 2), đạo diễn Shawn Levy của Real Steel và Free Guy là cái tên tiếp theo cầm trịch tác phẩm về gã phản anh hùng nói nhiều. Ryan Reynolds tiếp tục quay lại vai diễn mang tính biểu tượng trong sự nghiệp. Anh tham gia luôn khâu biên kịch cùng Rhett Reese, Paul Wernick, Zeb Wells và Shawn Levy. Hugh Jackman cũng tái xuất vai diễn dường như chẳng ai thay thế nổi – Wolverine. &nbsp;</span></span></span></p>\n" + "\n" + "<p><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><a href=\"https://www.galaxycine.vn/phim-dang-chieu\">Phim mới</a> <strong>Deadpool &amp; Wolverine</strong>&nbsp;ra mắt tại các <em><a href=\"https://www.galaxycine.vn/\">rạp chiếu phim</a></em> toàn quốc từ 27.07.2024.</span></span></p>\n" + "</div>").poster("https://cdn.galaxycine.vn/media/2024/7/22/deadpool--wolverine-500_1721640472363.jpg").bannerImg("https://preview.redd.it/deadpool-and-wolverine-textless-banner-wallpaper-v0-vs0sop0uel1d1.png?width=1080&crop=smart&auto=webp&s=4a91a3c67ea7fd049df61eb525ae81a60c14aec2").trailer("https://www.youtube.com/embed/inIVdZSFfc0?si=_hGwWAt56i15OYmJ").ageRequirement(AgeRequirement.T16).duration(127).rating(8.8).status(true).releaseDate(LocalDate.of(2024, 7, 27)).createdAt(now()).updatedAt(now()).producer("Marvel Studios, 20th Century Studios").graphicsTypes(List.of(GraphicsType._2D, GraphicsType._3D)).translationTypes(List.of(TranslationType.PHỤ_ĐỀ)).country(List.of(my)).genres(hanhDongGiaTuong).directors(List.of(ShawnLevy)).actors(List.of(RyanReynolds, HughJackman, PatrickStewart)).build());

        movieRepository.save(Movie.builder().name("Thám Tử Lừng Danh Conan: Ngôi Sao 5 Cánh 1 Triệu Đô").slug("tham-tu-lung-danh-conan-ngoi-sao-5-canh-trieu-do").description("<div class=\"block__wysiwyg text-black-10 text-sm font-normal not-italic content-text content__data__full\"><p><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\">Siêu trộm Kaito Kid và thám tử miền Tây Hattori Heiji cùng đối đầu trong cuộc tranh giành thanh kiếm thuộc về Hijikata Toushizou - phó chỉ huy của Shinsengumi! Thù mới hận cũ, Heiji sẽ xử trí Kid thế nào đây? </span></span></p>\n" + "\n" + "<p><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\">Ngoài ra, một bí mật kinh khủng về Kaito Kid sắp được tiếp lộ...</span></span><span style=\"font-family:Arial,Helvetica,sans-serif;\"><!--| --></span></p>\n" + "\n" + "<p><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><a href=\"https://www.galaxycine.vn/phim-dang-chieu/\">Phim mới</a>&nbsp;<strong>Thám Tử Lừng Danh Conan: Ngôi Sao 5 Cánh 1 Triệu Đô</strong>&nbsp;</span>suất chiếu sớm 27.07 (Không áp dụng Movie Voucher),<span style=\"font-family:Arial,Helvetica,sans-serif;\"> ra mắt tại các <em><a href=\"https://www.galaxycine.vn/\">rạp chiếu phim</a></em> toàn quốc từ 27.07.2024</span></span></p>\n" + "</div>").poster("https://cdn.galaxycine.vn/media/2024/8/2/detective-conan-the-million-dollar-pentagram-2_1722570544258.jpg").bannerImg("https://cdn.galaxycine.vn/media/2024/8/2/detective-conan-the-million-dollar-pentagram-1_1722570550126.jpg").ageRequirement(AgeRequirement.T13).duration(111).rating(9.8).status(true).trailer("https://www.youtube.com/embed/x_gGMJOppAo?si=b0BHAajHaXbTWolM").releaseDate(LocalDate.of(2024, 8, 2)).createdAt(now()).updatedAt(now()).producer("TMS Entertainment").country(List.of(nhatBan)).graphicsTypes(List.of(GraphicsType._2D)).translationTypes(List.of(TranslationType.LỒNG_TIẾNG)).genres(hoatHinh).directors(List.of(nagaokaTomoka)).actors(List.of(TakayamaMinami, YamazakiWakana)).build());

        movieRepository.save(Movie.builder().name("Mồ Tra Tấn").slug("mo-tra-tan").description("<div class=\"block__wysiwyg text-black-10 text-sm font-normal not-italic content-text content__data__full\"><p><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-variant: normal; white-space: pre-wrap;\"><span style=\"font-weight:400\"><span style=\"font-style:normal\"><span style=\"text-decoration:none\">Sau khi cha mẹ Sita thiệt mạng bất ngờ trong một vụ đánh bom liều chết, chỉ vì câu chuyện truyền miệng về Mồ Tra Tấn. Đau đớn và phẫn nộ, Sita quyết dành cả đời mình để chứng minh rằng Mồ Tra Tấn không tồn tại. Cho đến khi Sita quyết định ngủ trong ngôi mộ của một xác chết, sự thật kinh hoàng hé lộ, tất cả vượt xa sự hiểu biết của loài người...</span></span></span></span></span></span></p>\n" + "\n" + "<p><span style=\"font-size:14px;\"><a href=\"https://www.galaxycine.vn/phim-dang-chieu\">Phim mới</a>&nbsp;<strong>Grave Torture /&nbsp;Mồ Tra Tấn</strong>&nbsp;suất chiếu sớm 31.07 (Không áp dụng Movie Voucher), dự kiến ra mắt tại các <em><a href=\"https://www.galaxycine.vn/\">rạp chiếu phim</a></em> toàn quốc từ 02.08.2024.</span></p>\n" + "\n" + "<p><br>\n" + "<!--| --></p>\n" + "</div>").poster("https://cdn.galaxycine.vn/media/2024/7/25/grave-torture-1_1721895911608.jpg").bannerImg("https://cdn.galaxycine.vn/media/2024/7/25/grave-torture-2_1721895914944.jpg").ageRequirement(AgeRequirement.T18).duration(81).rating(7.4).status(true).trailer("https://www.youtube.com/embed/KKKK8qptIJc?si=ZxuCGYVguo1jDfNo").releaseDate(LocalDate.of(2024, 8, 2)).createdAt(now()).updatedAt(now()).producer("Come and See Pictures").country(List.of(indonesia)).graphicsTypes(List.of(GraphicsType._2D)).translationTypes(List.of(TranslationType.PHỤ_ĐỀ)).genres(kinhDi).directors(List.of(JokoAnwar)).actors(List.of(FaradinaMufti, RezaRahadian, ChristineHakim)).build());

        createMovieSchedule();
    }

    void createMovieSchedule() {
        List<Movie> movies = movieRepository.findAll();

        // Duyệt từng phim
        for (Movie movie : movies) {
            movieScheduleRepository.save(
                MovieSchedule.builder()
                    .startAt(now())
                    .endAt(now().plusMonths(1))
                    .status(MovieScheduleStatus.ĐANG_CHIẾU)
                    .createdAt(now())
                    .updatedAt(now())
                    .movie(movie)
                    .build()
            );
        }
        createShowTime();
    }

    void createShowTime() {

        // Mảng các số giờ có thể dùng để nhân với giờ bắt đầu
        int[] numbers = {2, 3};


        int[] numShowtime = {1, 2};

        // Lấy danh sách phim và rạp
        List<MovieSchedule> movieScheduleList = movieScheduleRepository.findAll();
        List<Cinema> cinemas = cinemaRepository.findAll();

        // Duyệt từng phim
        for (MovieSchedule movieSchedule : movieScheduleList) {
            // Tạo lịch chiếu cho từng ngày trong 4 ngày tới
            List<GraphicsType> types = new ArrayList<>(movieSchedule.getMovie().getGraphicsTypes());

            for (int i = 0; i <= 3; i++) {
                LocalDate date = now().plusDays(i);

                // Duyệt từng rạp
                for (Cinema cinema : cinemas) {
                    List<Auditorium> auditoriums = auditoriumRepository.findAllByCinema_IdIn(Collections.singletonList(cinema.getId()));
                    int hour = 8;
                    // Duyệt từng phòng chiếu trong rạp
                    for (Auditorium aud : auditoriums) {
                        // Tạo các suất chiếu trong ngày
                        for (int j = 0; j <= numShowtime[random.nextInt(numShowtime.length)]; j++) {
                            if (hour >= 24) {
                                break; // Nếu giờ >= 24, thoát khỏi vòng lặp chiếu
                            }
                            LocalTime startTime = LocalTime.of(hour, 0);

                            // Tính thời gian kết thúc chiếu phim
                            LocalTime endTime = startTime.plusMinutes(movieSchedule.getMovie().getDuration() + 10);

                            GraphicsType graphicsType;
                            TranslationType translationType;

                            if (movieSchedule.getMovie().getGraphicsTypes().size() > 1) {
                                graphicsType = movieSchedule.getMovie().getGraphicsTypes().get(numShowtime[numShowtime[random.nextInt(numShowtime.length - 1)] - 1]);
                            } else {
                                graphicsType = movieSchedule.getMovie().getGraphicsTypes().get(0);
                            }

                            if (movieSchedule.getMovie().getTranslationTypes().size() > 1) {
                                translationType = movieSchedule.getMovie().getTranslationTypes().get(numShowtime[numShowtime[random.nextInt(numShowtime.length - 1)] - 1]);
                            } else {
                                translationType = movieSchedule.getMovie().getTranslationTypes().get(0);
                            }
                            // Lưu suất chiếu
                            showtimeRepository.save(Showtime.builder().screeningDate(date).startTime(startTime).endTime(endTime).movieSchedule(movieSchedule).screeningTimeType(ScreeningTimeType.SUẤT_CHIẾU_THEO_LỊCH).graphicsType(graphicsType).auditoriumType(aud.getAuditoriumType()).translationType(translationType).auditorium(aud).build());

                            // Cập nhật giờ bắt đầu cho suất chiếu tiếp theo
                            hour += numbers[random.nextInt(numbers.length)];
                        }
                    }
                }
            }
        }
    }

    void createComboFood() {
        List<Combo> combo = new ArrayList<>();
        combo.add(new Combo(1, "Combo Couple", " 02 Ly nước ngọt size L + 01 Hộp bắp + 1 Snack", "https://cdn.galaxycine.vn/media/2024/3/29/menuboard-coonline-2024-combo2-min_1711699866349.jpg", true, 180000));
        combo.add(new Combo(2, "Combo FA", " 01 Ly nước ngọt size L + 01 Hộp bắp", "https://cdn.galaxycine.vn/media/2024/3/29/menuboard-coonline-2024-combo1-copy-min_1711699814762.jpg", true, 90000));
        comboRepository.saveAll(combo);
    }

    void createPost() {
        UserStatistic userStatistic = UserStatistic.builder().totalSpending(1324686L).points(13334).build();
        userStatisticRepository.save(userStatistic);
        User admin = userRepository.save(new User(1, "ADMIN", "admin@gmail.com", "$2a$10$dJg7xn89m1n4t2CBpm3FHexXQffvA2KLmcm0eBkJTkNTdX0whlwOG", "https://icons.veryicon.com/png/o/commerce-shopping/wangdianbao-icon-monochrome/administrators-6.png", "0123456789", "Nam", LocalDate.of(1990, 1, 1), UserRole.ADMIN, true, now(), now(), userStatistic));
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "Deadpool & Wolverine: Tri Ân 20 Năm X-Men Theo Phong Cách Bẩn Bựa", "deadpool-and-wolverine-tri-an-20-nam-x-men", "<div class=\"wysiwyg text-sm font-normal not-italic mt-3 binh-luan-phim-id content__data__full\"><p style=\"margin-bottom:11px\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Sự trồi sụt của vũ trụ điện ảnh Marvel từ sau <em>Avengers: Engame</em> đã làm không ít khán giả hoài nghi về những tác phẩm tiếp theo được nhà M cho ra mắt. Dẫu vậy, hiệu ứng trở lại của Hugh Jackman với vai Wolverine trong <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><strong>Deadpool &amp; Wolverine</strong></a> đang nhận được sự yêu thích rất lớn. Liệu tác phẩm trở lại lần này của gã phản anh hùng lắm mồm có khiến vị thế của vụ trụ điện ảnh bậc nhất trở lại với ánh hào quang?</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"338\" src=\"/media/2024/7/25/deadpool--wolverine-600-1_1721898401034.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\"><a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><strong>Deadpool &amp; Wolverine</strong></a> diễn ra tiếp theo các sự kiện của <em>Deadpool 2</em>, sau khi quậy banh các dòng thời gian rồi “nộp đơn” gia nhập Avengers nhưng bị từ chối thẳng thừng, Wade Wilson quyết định gỡ bỏ bộ đồ Deadpool để tìm kiếm con đường đích thực của mình. Bỗng một ngày, Wade được TVA (Time Variance Authority) đề nghị tham gia vào dòng thời gian thiêng liêng vì thế giới của anh sắp sụp đổ khi ‘nhân vật đinh’ đã ra đi trong <em>Logan (2017)</em>. Không muốn mất đi những người mà mình yêu thương, Wade khoác lên mình bộ đồ Deadpool, đi cầu cứu một Wolverine khác để giải cứu vũ trụ của mình. Liệu hành trình của gã lắm mồm có thành công, hay anh sẽ trở thành một nhân vật của dòng thời gian thiêng liêng của Marvel? Hãy ra <a href=\"https://www.galaxycine.vn/\">rạp chiếu phim</a> để biết câu trả lời nhe.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Khách quan mà nói, 2 phần phim trước của <em>Deadpool</em> không có gì quá nổi bật, dù đạt được doanh thu đáng mong đợi nhưng đó là vì khán giả đã quá yêu thích nhân vật này trong comics, đôi khi ra rạp chỉ để nghe gã phản anh hùng quậy phá, chửi bậy trên màn ảnh rộng. Tuy nhiên, với <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><strong>Deadpool &amp; Wolverine</strong></a>, bộ phim có thể được xếp vào hàng ngũ những bộ phim chất lượng nhất từng được Marvel sản xuất.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"338\" src=\"/media/2024/7/25/deadpool--wolverine-600-2_1721898639163.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Năm 2019, Disney thấu tóm Fox để mang các dị nhân trở về với Marvel, khán giả đã mang trong mình sự lo lắng nhất định vì có thể Deadpool sẽ trở thành bộ phim siêu anh hùng cho gia đình. Mọi suy nghĩ đó nhanh chóng bị đập tan khi <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><strong>Deadpool &amp; Wolverine</strong></a> được nhà Chuột công bố sẽ là bộ phim siêu anh hùng đầu tiên dán nhãn R. Những màn hành động máu me, những câu chửi bậy làm nên thương hiệu của Deadpool được giữ nguyên khiến khán giả vô cùng thích thú.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Cùng với một cốt truyện chỉn chu, sự lôi cuốn của <a href=\"http://www.galaxycine.vn/phim-dang-chieu/\">phim mới</a> còn đến từ những màn chửi bậy vô cùng mượt mà đến từ các nhân vật. Gã lắm mồm chửi tất cả mọi thứ trong tầm mắt, khinh miệt Fox ngay những giây phút đầu tiên của phim, chửi Disney quá thảo mai, quảng cáo nhưng phải chửi sản phẩm trước. Nổi tiếng với “chiếc mồm hư” bậc nhất giới siêu anh hùng trước khi có sự xuất hiện của Deadpool, anh Chồn của Hugh Jackman vẫn có thấy được phong độ khi vừa có thể solo đánh đấm lại vừa có thể đấu võ mồm không thua kém gã áo đỏ. Những nhân vật cameo cũng không ngần ngại thốt ra những câu nói “lost teach”, thậm chí một nhân vật được xem là cộm cán phải bỏ mạng vì nghiệp tụ vành môi.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Là bộ <a href=\"https://www.galaxycine.vn/phim-dang-chieu/\">phim chiếu rạp</a> đầu tiên chính thức đưa dị nhân vào một phần của vũ trụ Marvel, <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><strong>Deadpool &amp; Wolverine</strong></a> có thể xem là một màn tri ân vô cùng trọn vẹn với tất cả những gì mà Fox xây dựng cho X-men. Không chỉ là những màn fanservice đỉnh cao, sự góp mặt của các tác phẩm cũ còn là mảnh ghép cho câu chuyện vô cùng chất lượng mà <a href=\"https://www.galaxycine.vn/phim-dang-chieu/\">phim hay</a> mang tới. Sở hữu năng lực phá vỡ bức tường thứ tư để giao tiếp với khán giả, Deadpool còn mang tới nhưng màn bắt trend vô cùng hài hước, để những non-fan dù không biết quá nhiều về những tác phẩm trước đây của Fox hay Marvel thì vẫn có cảm giác thích thú.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"248\" src=\"/media/2024/7/25/deadpool--wolverine-600-3_1721898857097.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Diễn xuất của dàn diễn viên chất lượng là điểm vô cùng nổi bật của tác phẩm. Ryan Reynolds cho người hâm mộ thấy rõ nếu Deadpool bước ra từ trang tuyện thì sẽ trông như thế nào. Anh chàng dường như được sinh ra để đóng Deadpool, hay nói đúng hơn Pool là Ryan và Ryan chính là Pool. Chàng diễn viên sinh năm 1976 còn có một phiên bản của chính mình trong <a href=\"https://www.galaxycine.vn/phim-dang-chieu/\">phim hay</a> với cái tên Nicepool. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Cùng với Ryan, Hugh Jackman cũng được người hâm mộ hết mực yêu mến với vai diễn Wolverine. Dù nét tính cách của Hugh ngoài đời hoàn toàn trái ngược với Logan, chàng diễn viên vẫn phác họa nên được một nhân vật đậm chất comics nhất vũ trụ X-men của Fox. Thành công chống chọi với căn bệnh ung thư, Hugh một lần nữa bị anh chàng bạn thân lôi kéo để khoác lên mình bộ đồ Người Sói cho Marvel “vắt sữa đến năm 90 tuổi”.&nbsp;</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"338\" src=\"/media/2024/7/25/deadpool--wolverine-600-4_1721898900522.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Matthew Macfadyen trong vai Paradox - một nhân viên cấp cao của TVA, thành công khiến cả người xem lẫn nhân vật trong phim ghét vì sự khốn nạn của mình. Emma Corrin cũng phác họa một phản diện dị nhân cấp Omega với tư duy cực kì nhạy bén, out trình mọi đối thủ. Đôi lúc, nhân vật Cassandra Nova mà cô nàng đảm nhận còn tạo nên một nỗi sợ vô hình cho người xem tại <a href=\"https://www.galaxycine.vn/\">rạp chiếu phim</a>. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Các nhân vật cũ 2 phần Deadpool trước không có nhiều điểm nhấn, nhưng vẫn bổ trợ rất nhiều vào mục tiêu của Deadpool. Ngoại trừ một nhân vật đặc biệt, dù không có siêu năng lực vẫn đủ sức kiểm soát thế trận cho 2 nhân vật chính. Cùng với sự liên kết với MCU, các nhân vật của vũ trụ điện ảnh cũng xuất hiện để tạo nên sự thú vị cho câu chuyện.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Được dán nhãn R nên những hình ảnh máu me được đan xen và chăm chút vô cùng kĩ lưỡng. Đoạn intro của <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><strong>Deadpool &amp; Wolverine</strong></a> có thể xem là đoạn intro thú vị nhất của dòng phim siêu anh hùng. Khán giả được chiêu đãi một bữa tiệc máu me đúng nghĩa được kết hợp cùng bản nhạc huyền thoại “Bye Bye Bye” của NSYNC. Phần nghe cũng rất có đầu tư, cảnh hành động được bổ trợ rất nhiều bằng những âm thanh đã tai đi kèm những bản nhạc huyền thoại tạo cảm giác vô cùng phấn khích.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"338\" src=\"/media/2024/7/25/deadpool--wolverine-600-5_1721899084847.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Được trông đợi là bộ phim vực dậy vũ trụ điện ảnh Marvel, tuy nhiên bộ <a href=\"https://www.galaxycine.vn/phim-dang-chieu/\">phim mới</a> lại không có quá nhiều điểm liên kết với MCU hiện tại, câu nói quen thuộc “hay nhất từ sau Endgame” cũng là thứ được nhiều người nhắc đến. Để vực dậy một vũ trụ đang bên kia sườn dốc không thể chỉ bằng một bộ phim, và tác phẩm cũng khá khó hiểu với một số khán giả không biết nhiều về X-men hay không theo dõi toàn bộ MCU. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Dẫu vậy, <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><strong>Deadpool &amp; Wolverine</strong></a> là tác phẩm hay nhất của trilogy Deadpool, cũng là tác phẩm hay nhất cho người hâm mộ X-men nói riêng. Đây là bộ phim siêu anh hùng đáng xem nhất trong những năm trở lại đây, một bước chuyển mình tích cực của MCU sau hàng loạt lùm xùm không đáng có trong thời gian vừa qua.</span></span></span></p>\n" + "\n" + "<p><!--| --></p>\n" + "</div>", "", "https://www.galaxycine.vn/media/2024/7/25/deadpool--wolverine-rv-750_1721899268523.jpg", PostType.REVIEW, 230,  true,now(), now(), now(), admin));
        posts.add(new Post(2, "Thám Tử Lừng Danh Conan Ngôi Sao 5 Cánh 1 Triệu Đô: Bí Ẩn Lớn Nhất Về Kaito Kid", "tham-tu-lung-danh-conan-ngoi-sao-5-canh-1-trieu-do-bi-an-lon-nhat-ve-kaito-kid", "<div class=\"wysiwyg text-sm font-normal not-italic mt-3 binh-luan-phim-id content__data__full\"><p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Năm nay, <i>Thám Tử Lừng Danh Conan</i> kỉ niệm tuổi 30 với hàng loạt thành tích khủng. Bộ <a href=\"https://www.galaxycine.vn/phim-dang-chieu/\"><i>phim chiếu rạp</i></a> thứ 27 -&nbsp;<a href=\"https://www.galaxycine.vn/dat-ve/detective-conan-the-million-dollar-pentagram/\"><b>Ngôi Sao 5 Cánh 1 Triệu Đô</b></a> cũng lập kỉ lục doanh thu cao nhất từ trước đến nay tại quê nhà Nhật Bản.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Điều gì đã giúp thương hiệu về chàng thám tử trung học teo nhỏ giữ được thành công lâu bền đó?</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Ra đời năm 1994, <i>Detective Conan</i> nhanh chóng tạo tiếng vang bởi cốt truyện độc đáo. Trong một lần đi chơi cùng cô bạn thanh mai trúc mã Mori Ran, cậu thám tử học trò Kudo Shinichi để mắt đến hai gã mặc đồ đen có hành vi quái lạ. Khi theo dõi chúng, cậu sơ suất bị phát hiện. Trớ trêu thay, viên thuốc thủ tiêu không giết chết mà khiến Shinichi teo nhỏ thành đứa trẻ 6 tuổi. Với thân phận mới là Edogawa Conan, cậu đến sống tại nhà Mori nhằm thuận tiện việc điều tra về tổ chức áo đen. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"338\" src=\"/media/2024/8/2/tham-tu-lung-danh-conan-ngoi-sao-5-canh-1-trieu-do-bi-an-lon-ve-kaito-kid-4_1722611979207.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Suốt ba thập kỉ, <i>Detective Conan</i> đã làm mưa làm gió khắp Nhật Bản. Đâu chỉ Conan hay Shinichi, những nhân vật phụ cũng được công chúng yêu mến. Điển hình là siêu trộm Kaito Kid. Dù ít xuất hiện, Kid 1412 vẫn rất nổi tiếng. Thậm chí, còn hơn cả chính anh chàng trong truyện tranh <i>Magic Kaito</i> chung tác giả.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Cùng với Kaito, thám tử miền Tây - Hattori Heiji đối thủ kiêm bạn thân của Shinichi sở hữu lượng fan lớn. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"338\" src=\"/media/2024/8/2/tham-tu-lung-danh-conan-ngoi-sao-5-canh-1-trieu-do-bi-an-lon-ve-kaito-kid-1_1722611993392.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Trong phần <i><a href=\"https://www.galaxycine.vn/phim-dang-chieu/\">phim mới</a> </i><a href=\"https://www.galaxycine.vn/dat-ve/detective-conan-the-million-dollar-pentagram/\"><b>Detective Conan: The Million-Dollar Pentagram</b></a> / <a href=\"https://www.galaxycine.vn/dat-ve/detective-conan-the-million-dollar-pentagram/\"><b>Thám Tử Lừng Danh Conan: Ngôi Sao 5 Cánh 1 Triệu Đô</b></a>, ba nhân vật nam có lượng người hâm mộ đông đảo này tụ hội, phá giải bí ẩn quanh thanh gươm cổ do vĩ nhân Hijikata Toshizou sở hữu. Ngoài ra, cuộc tình “gà bông” giữa Hattori Heiji và cô bạn từ thuở nhỏ Toyama Kazuha cũng là điểm cộng hấp dẫn cho phần này. Liệu Heiji có tìm được địa điểm tỏ tình ưng ý, vượt qua cảnh tháp đồng hồ Big Ben của cặp đôi Shin – Ran và thành công bày tỏ tình cảm? Dù Ran và Conan hết lòng giúp đỡ, chàng thám tử da ngăm sẽ gặp phải sự phá đám không khoan nhượng của cô tiểu thư Ooka Momiji đang yêu mến mình. &nbsp;</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Sau màn hụt chân đáng tiếc vì đại dịch Covid-19, khiến doanh thu phim điện ảnh thứ 24 – <i><a href=\"https://www.galaxycine.vn/phim/detective-conan-the-scarlet-bullet/\">The Scarlet Bullet</a> </i>/ <a href=\"https://www.galaxycine.vn/phim/detective-conan-the-scarlet-bullet/\"><i>Viên Đạn Đỏ</i></a> sụt giảm ít nhiều, nhà sản xuất các phần phim điện ảnh Thám Tử Lừng Danh đã tìm tòi nhiều công thức “hút khách” mới. Phim điện ảnh thứ 25 - <a href=\"https://www.galaxycine.vn/phim/detective-conan-movie-25-the-bride-of-halloween/\"><i>The Bride of Halloween</i></a> / <a href=\"https://www.galaxycine.vn/phim/detective-conan-movie-25-the-bride-of-halloween/\"><i>Nàng Dâu Halloween</i></a> kể về nhóm F5 Học viện cảnh sát. Nhân vật chủ chốt là thành viên cục công an – Furuya Rei nổi tiếng với câu nói “Người tôi yêu là đất nước này.”, thương hiệu Detective Conan nhanh chóng lấy lại phong độ. Phim điện ảnh 26 - <a href=\"https://www.galaxycine.vn/phim/detective-conan-black-iron-submarine/\"><i>Black Iron Submarine</i></a> / <a href=\"https://www.galaxycine.vn/phim/detective-conan-black-iron-submarine/\"><i>Tàu Ngầm Sắt Màu Đen</i></a> “câu fan” bằng câu chuyện xoay quanh tổ chức áo đen và nữ khoa học gia bị teo nhỏ Haibara Ai. Năm nay, cuộc hội ngộ giữa Heiji và Kid sau nụ hôn oan nghiệt ở phần trước cùng cốt truyện xen lẫn nhiều kiến thức lịch sử và văn hóa Nhật Bản giúp <a href=\"https://www.galaxycine.vn/dat-ve/detective-conan-the-million-dollar-pentagram/\"><b>Detective Conan: The Million-dollar Pentagram</b></a> lập hàng loạt kỉ lục khủng!</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"337\" src=\"/media/2024/8/2/tham-tu-lung-danh-conan-ngoi-sao-5-canh-1-trieu-do-bi-an-lon-ve-kaito-kid-3_1722612011709.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Vài năm gần đây, truyện tranh lẫn phim hoạt hình dài kì <i>Thám Tử Lừng Danh Conan</i> đều gặp cảnh người hâm mộ than phiền rằng nét vẽ đã “xuống tay” hẳn. May mắn thay, với đứa con cưng sinh lời bạc tỉ mỗi năm một phần, nhà sản xuất trau chuốt tỉ mỉ để thành phẩm đạt chất lượng cao nhất. Đặc sản các <i><a href=\"https://www.galaxycine.vn/phim-dang-chieu/\">phim chiếu rạp</a> Detective Conan</i> – những cảnh hành động “đánh bay mọi định luật vật lý” tiếp tục làm khán giả mắt tròn mắt dẹt phấn khích.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Nếu từng thích trận đánh tay đôi giữa Kyogoku Makoto và Kaito Kid trong phim thứ 23 - <i>T<a href=\"https://www.galaxycine.vn/phim/detective-conan-movie-23-the-fist-of-blue-sapphire/\">he Fist of Blue Sapphire</a></i>, <a href=\"https://www.galaxycine.vn/dat-ve/detective-conan-the-million-dollar-pentagram/\"><b>Detective Conan: The Million-dollar Pentagram</b></a> “chiều fan” bằng màn giao đấu mãn nhãn giữa Heiji và Kid. Ở <i>The Darkest Nightmare</i>, Akai và Amuro đánh trên vòng quay cực kì ấn tượng thì <a href=\"https://www.galaxycine.vn/dat-ve/detective-conan-the-million-dollar-pentagram/\"><b>Detective Conan: The Million-dollar Pentagram</b></a> thể hiện cuộc đấu trên trực thăng cực kì gay cấn.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">&nbsp;<a href=\"https://www.galaxycine.vn/dat-ve/detective-conan-the-million-dollar-pentagram/\"><b>Detective Conan: The Million-Dollar Pentagram</b></a> vẫn có sự góp mặt của các nhân vật quen thuộc như Mori Ran, Mori Kogoro, Kazuha, Haibara, tiến sĩ Agasa, nhóm thám tử nhí… Cô tiểu thư đỏng đảnh Ooka Momiji và anh quản gia thân phận bí ẩn Iori góp mặt với những phân cảnh ấn tượng. Đặc biệt, nữ chính Magic Kaito – Aoko lần đầu xuất hiện trong phim điện ảnh <i>Thám Tử Lừng Danh Conan</i>.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Ngoài ra, ai từng mê mẩn bộ truyện đề tài kiếm đạo Yaiba cùng tác giả Aoyama Gosho càng hạnh phúc khi Okita Soshi và \"đại ma vương\" Onimaru cameo quan trọng.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">&nbsp; <a href=\"https://www.galaxycine.vn/dat-ve/detective-conan-the-million-dollar-pentagram/\"><b>Detective Conan: The Million-Dollar Pentagram</b></a> vắng bóng tổ chức áo đen, cũng chẳng hé lộ thêm chi tiết gì giúp cuộc truy tìm có chút tiến triển. Tuy nhiên, đoạn cuối hé lộ một tình tiết quan trọng chưa hề xuất hiện trong phiên bản truyện tranh. Tác giả Aoyama Gosho sẽ mạnh dạn để tình tiết này thành chính thức? </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Theo các chi tiết đã được hé lộ, phần phim sau chọn bộ ba Nagano – Yamato Kansuke, Uehara Yui và “Khổng Minh” Morofushi Takaaki làm nhân vật chính. Mong rằng <a href=\"https://www.galaxycine.vn/phim-dang-chieu/\"><i>phim chiếu rạp</i></a> thứ 28 hé lộ thêm về tổ chức áo đen – bí ẩn lớn nhất mà công chúng quan tâm lúc này!</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Dĩ nhiên, đừng quên rằng, credit của <i>Thám Tử Lừng Danh Conan</i> là đoạn phim cực kì quan trọng. Hãy kiên nhẫn ngồi lại <a href=\"https://www.galaxycine.vn/\"><em>rạp chiếu phim</em></a> và chờ đón cú shock lớn mà ekip làm phim mang lại!</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\">&nbsp;</p>\n" + "\n" + "<p><!--| --></p>\n" + "</div>", "", "https://www.galaxycine.vn/media/2024/8/2/tham-tu-l…ieu-do-bi-an-lon-ve-kaito-kid-5_1722611870756.jpg", PostType.REVIEW, 456,  true,now(), now(), now(), admin));
        posts.add(new Post(3, "Đếm 500 Cameo Từ Deadpool & Wolverine", "dem-500-cameo-tu-deadpool-wolverine", "<div class=\"wysiwyg text-sm font-normal not-italic mt-3 binh-luan-phim-id content__data__full\"><p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>Bài viết tiết lộ nhiều nội dung phim.</b></span></span></span></p>" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Là canh bạc lớn Marvel Studios đối phó cơn thoái trào dòng phim siêu anh hùng, <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><b>Deadpool &amp; Wolverine</b></a> tung ra hàng loạt chiêu bài lôi kéo khán giả. Dĩ nhiên, những quả trứng Phục Sinh liên quan đến các dự án siêu anh hùng cũ và mới là yếu tố cực kì quan trọng để nhà sản xuất “câu khách”!</span></span></span></p>" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"338\" src=\"/media/2024/8/3/boc-trung-phuc-sinh-cua-deadpool--wolverine-9_1722692800501.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Stars nắm được bao nhiêu chi tiết ẩn khi xem bộ phim chiếu rạp này? </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>Happy Hogan</b></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Từ vai trò tài xế cho Tony Stark, Happy thăng tiến tốc độ chóng mặt và nay đã ngồi vào ghế phỏng vấn tuyển dụng ở&nbsp; tập đoàn Stark. Không may, Happy thẳng thừng từ chối Wade Wilson dù gã phản anh hùng có màn phỏng vấn xin việc cực kì ấn tượng. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>Henry Cavill</b></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Chỉ xuất hiện đúng một cảnh quay, <a href=\"https://www.galaxycine.vn/dien-vien/henry-cavill/\">Henry Cavill</a> và bắp tay khổng lồ gây ấn tượng cực kì sâu sắc. Dù rất nhiều lời đồn đoán rằng “Harry Potter” <a href=\"https://www.galaxycine.vn/dien-vien/daniel-radcliffe/\">Daniel Radcliffe</a> sẽ vào vai Wolverine bản mới, có lẽ, <a href=\"https://www.galaxycine.vn/dien-vien/henry-cavill/\">Henry Cavill</a> mới là lựa chọn thích hợp hơn. Deadpool còn khẳng định Marvel trả lương cao hơn DC!</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>Johnny Thiên Thần</b></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Chắc chắn, khoảnh khắc <a href=\"https://www.galaxycine.vn/dien-vien/chris-evans/\">Chris Evans</a> thốt ra “Flame on!” là một trong những cảnh phim đáng nhớ nhất <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><strong>Deadpool &amp; Wolverine</strong></a>.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"393\" src=\"/media/2024/8/3/boc-trung-phuc-sinh-cua-deadpool--wolverine-6_1722692816492.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Những khán giả xem phim siêu anh hùng từ những năm đầu thế kỉ chắc hẳn khó quên Johnny Storm háo thắng bốc đồng ở <i>Fantastic Four</i> (2005) và <i>Fantastic Four: Rise of the Silver Surfer</i> (2007). Những năm gần đây, <a href=\"https://www.galaxycine.vn/dien-vien/chris-evans/\">Chris Evans</a> ghi dấu ấn toàn cầu nhờ hình ảnh “thanh niên nghiêm túc” Captain America.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Steve đĩnh đạc thích nhắc nhở các đồng đội “language” trong các bộ phim thuộc vũ trụ điện ảnh Marvel và Human Torch trẻ trâu khác nhau quá đỗi. Công chúng còn đùa vui rằng, xét bản tính tếu táo ngoài đời, Johnny Storm gần với <a href=\"https://www.galaxycine.vn/dien-vien/chris-evans/\">Chris Evans</a> hơn Steve Rogers nhiều.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>&nbsp;Gambit</b></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Khoảng năm 2014, Fox muốn sản xuất <i>Gambit </i>và chọn <a href=\"https://www.galaxycine.vn/dien-vien/channing-tatum/\">Channing Tatum</a> làm nam chính. Thế nhưng, dự án này đã chết yểu trước khi ra mắt. Cáo về tay nhà Chuột, bộ phim riêng cho “thần bài” trở thành điệp vụ bất khả thi. Chính bản thân <a href=\"https://www.galaxycine.vn/dien-vien/channing-tatum/\">Channing Tatum</a> cũng chẳng ít lần bày tỏ nỗi thất vọng vì không thể diễn nhân vật này trên màn ảnh. May mắn thay, <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><strong>Deadpool &amp; Wolverine</strong></a> tặng anh cơ hội được khoác lên người tạo hình ngày nhớ đêm mong ấy. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"402\" src=\"/media/2024/8/3/boc-trung-phuc-sinh-cua-deadpool--wolverine-7_1722692833671.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">“Gambit” <a href=\"https://www.galaxycine.vn/dien-vien/channing-tatum/\">Channing Tatum</a> rất thích nói những câu đạo lí, thi thoảng chèn vào lời tự sự về thân phận chẳng biết có tồn tại hay không của bản thân. Dẫu sao thì, <a href=\"https://www.galaxycine.vn/dien-vien/channing-tatum/\">Channing Tatum</a> lỡ mất Gambit biết đâu lại tốt, bởi hầu hết công chúng đều bày tỏ Taylor Kitsch (Gambit trong <i>X-Men Origins: Wolverine</i>) hợp vai hơn hẳn. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>Elektra và Blade</b></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Ở tuổi 52, Jennifer Garner vẫn quá đẹp ở tạo hình Elektra bản 2024. Nữ chiến binh từng làm mưa làm gió một thời bởi thân hình quá quyến rũ dù tác phẩm lỗ chổng vó tại các rạp chiếu phim toàn cầu. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Khác với Elektra, Blade do Wesley Snipes thủ vai thành công hơn hẳn, sản xuất tận 3 phần. Đặc biệt, <i>Blade: Trinity</i> còn <a href=\"https://www.galaxycine.vn/dien-vien/ryan-reynolds/\">Ryan Reynolds</a> góp mặt. Trong <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><b>Deadpool &amp; Wolverine</b></a>, Deadpool nói rằng anh là Blade duy nhất. Đây có phải là dấu hiệu cho thấy Blade phiên bản mới của Mahershala Ali sẽ chìm vào quên lãng mãi mãi? </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Suốt 5 năm qua, Blade đã đổi biên kịch nhiều lần, vẫn chưa đi vào quá trình sản xuất và Mahershala Ali cũng vắng bóng tại Comic-Con San Diego vừa qua. Xưa nay, không ít lần Marvel Studios ra tay “xử trảm” những dự án từng được kì vọng nếu cảm thấy chúng lỗi thời. Inhumans là ví dụ điển hình. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Tại kì Comic-Con lần này, Marvel Studios đã công bố hàng loạt dự án cho các năm 2025-2026:</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"337\" src=\"/media/2024/8/3/boc-trung-phuc-sinh-cua-deadpool--wolverine-2_1722692851100.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">- Captain America: Brave New World (Dự kiến khởi chiếu 14.02.2025)</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">- Thunderbolts (Dự kiến khởi chiếu 05.2025)</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">- The Fantastic Four: First Steps (Dự kiến khởi chiếu 07.2025)</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">- Avengers: Doomsday (Dự kiến khởi chiếu 05.2026)</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">- Avengers: Secret Wars (Dự kiến khởi chiếu 05.2027)</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Cơ hội dành cho Blade dường như chẳng còn! </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>X-23</b></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Năm 2017, cuộc phiêu lưu đầy máu của Logan và cô bé X-23 Laura đã lấy đi hàng lít nước mắt&nbsp; khán giả. Thật tuyệt vời khi lại thấy Hugh Jackman và Dafne Keen xuất hiện chung khung hình ở <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><strong>Deadpool &amp; Wolverine</strong></a>. Dù không nhiều đất diễn, Laura chứng minh rõ ràng rằng cô bé xứng đáng với sự hi sinh của Logan!&nbsp; </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"400\" src=\"/media/2024/8/3/boc-trung-phuc-sinh-cua-deadpool--wolverine-8_1722692871109.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>Avengers</b></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Iron Man, Captian America và Thor – những Avengers kì cựu cũng được nhắc đến trong <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><b>Deadpool &amp; Wolverine</b></a>. Điều này phần nào chứng minh Trái Đất 10005 mà Wade Wilson đang sống vẫn là vũ trụ gốc – cốt lõi MCU. Dẫu vậy, với một tác phẩm nhiều lần phá vỡ “bức tường thứ 4” như <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><b>Deadpool &amp; Wolverine</b></a>, không gì là chắc chắn cả.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>Spiderman</b></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">SpideyPool là bộ đôi được yêu thích hàng đầu dàn comic Marvel. Trong <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><strong>Deadpool &amp; Wolverine</strong></a>, khoảnh khắc trên xe, Deadpool dùng cử chỉ phóng tơ của Người Nhện. Thêm vào đó, câu thoại “Mọi Deadpool đều có một Peter.” tiếp tục ẩn ý về Peter Parker lừng danh. Liệu người hâm mộ SpideyPool có cơ hội chứng kiến màn song kiếm hợp bích trên màn ảnh rộng?</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Và nếu điều tuyệt vời ấy xảy ra thì Tobey Maguire, <a href=\"https://www.galaxycine.vn/dien-vien/andrew-garfield/\">Andrew Garfield</a> hay <a href=\"https://www.galaxycine.vn/dien-vien/tom-holland/\">Tom Holland</a> sẽ là Người Nhện sánh vai cùng “Deadpool” <a href=\"https://www.galaxycine.vn/dien-vien/ryan-reynolds/\">Ryan Reynolds</a>?</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>X-Men</b></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><strong>Deadpool &amp; Wolverine</strong></a> dành một đoạn phim tri ân vũ trụ điện ảnh Marvel thuộc Fox với những dị nhân quen thuộc như Charlie Xavier (Patrick Stewart / James McAvoy), Magneto (Ian McKellen / Michael Fassbender), Mystique, Jean Grey, Cyclops, Storm, Quicksilver… Hiện vẫn chưa có thông tin nào việc Marvel sẽ sử dụng dàn diễn viên cũ hay tuyển diễn viên mới cho các phim tiếp theo về Dị Nhân.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>Credit suýt có Doctor Doom</b></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b><img alt=\"\" height=\"676\" src=\"/media/2024/8/3/boc-trung-phuc-sinh-cua-deadpool--wolverine-10_1722692894413.jpg\" width=\"600\"></b></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Cùng với việc <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><b>Deadpool &amp; Wolverine</b></a> làm mưa làm gió tại các <i>rạp chiếu phim</i> toàn cầu, Marvel Studios àm dậy sóng tại Comic-Con vì hé lộ <a href=\"https://www.galaxycine.vn/dien-vien/robert-downey-jr/\">Robert Downey Jr.</a> tiếp tục đeo lên chiếc mặt nạ mới và trở thành phản diện Doctor Doom. Được biết, sau khi diễn viên thủ vai Kang - Jonathan Majors vướng vòng lao lí, hãng từng liên lạc với <a href=\"https://www.galaxycine.vn/dien-vien/robert-downey-jr/\">Robert Downey Jr</a>. về việc quay lại MCU. Marvel cũng lên kế hoạch cho Doctor Doom xuất hiện ở credit <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><b>Deadpool &amp; Wolverine</b></a>. Tuy nhiên, việc này không thành vì lúc đó <a href=\"https://www.galaxycine.vn/dien-vien/robert-downey-jr/\">Robert</a> từ chối. Thời gian sau, ngôi sao đoạt tượng vàng Oscar năm nay mới gật đầu. Dẫu sao, thay Doctor Doom bùng nổ, <a href=\"https://www.galaxycine.vn/dat-ve/deadpool--wolverine/\"><b>Deadpool &amp; Wolverine</b></a> bù lại bằng một after credit cực hài, chắc chắn sẽ khiến khán giả cười ngất ngay cả lúc đã ra khỏi <i>rạp chiếu phim</i>.</span></span></span></p>\n" + "\n" + "<p align=\"right\" style=\"text-align:right; margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>Tổng hợp và lược dịch</b></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\">&nbsp;</p>\n" + "\n" + "<p><!--| --></p>\n" + "</div>", "", "https://www.galaxycine.vn/media/2024/8/3/boc-trung-phuc-sinh-cua-deadpool--wolverine-1_1722692769418.jpg", PostType.BLOG, 600,  true,now(), now(), now(), admin));
        posts.add(new Post(4, "Despicable Me 4: Chúng Ta Biết Được Bao Nhiêu Về Minions?", "despicable-me-4-chung-ta-biet-duoc-bao-nhieu-ve-minions", "<div class=\"wysiwyg text-sm font-normal not-italic mt-3 binh-luan-phim-id content__data__full\"><p style=\"margin-bottom: 11px;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Có thể bạn thừa biết: Cho đến nay, trong số phim thuộc franchise <i>Despicable Me</i>, <i>Minions</i> vẫn đang giữ kỉ lục thành công nhất với doanh thu 1,159 tỷ đô thu về tại các <i>rạp chiếu phim</i> toàn cầu. </span></span></span></p>" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"325\" src=\"/media/2024/6/24/despicable-me-4-chung-ta-biet-duoc-bao-nhieu-ve-minions-2_1719219049689.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Ra mắt bằng vai trò nhân vật phụ <i>Despicable Me</i> (2010), những quả chuối vàng Minions chẳng những chiếm spotlight nhân vật chính - siêu ác nhân Gru mà còn trở thành biểu tượng mới của Illumination, cameo nhiều phim hoạt hình sau này. Những chú Minions mang lại giá trị kinh tế khổng lồ, hàng triệu sản phẩm ăn theo bán ra toàn cầu. <i>Despicable Me 2</i> (2013) tạo ra cơn địa chấn, thu về hơn 970 triệu $. Năm 2015, Illumination đưa <i>Minions</i> lên làm nhân vật chính, ra mắt tiền truyện về chuối vàng và thành công phá mốc tỉ đô. Đây là tác phẩm hoạt hình đầu tiên không phải của Disney hay Pixar đạt kỳ tích này. <a href=\"https://www.galaxycine.vn/phim/ke-trom-mat-trang-3/\"><i>Despicable Me 3</i></a> (2017) kéo dài chuỗi thành công với 1,034 tỷ đô. Gần đây nhất, <a href=\"https://www.galaxycine.vn/phim/minions-the-rise-of-gru/\"><i>Minions: The Rise Of Gru</i></a> (2022) tuy thua sút nhưng vẫn thu về 940 triệu đô – con số trong mơ tại bất cứ xưởng phim nào.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Giờ đây, tiếp tục “ăn theo” thành tích khủng đó, xưởng Illumination sắp tung ra <a href=\"https://www.galaxycine.vn/dat-ve/despicable-me-4/\"><b>Despicable Me 4</b></a> vào 05.07.2024. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"450\" src=\"/media/2024/6/24/despicable-me-4-chung-ta-biet-duoc-bao-nhieu-ve-minions-2_1719219186762.jfif\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Tác phẩm có kéo dài chuỗi thành công của thương hiệu Minions? </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Có thể nói, đây là điều gần như chắc chắn. Bởi Illumination đã nắm chắc công thức thành công từ những trái chuối vàng.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Còn bạn, đã thực sự hiểu về những chú Minions? Dưới đây là 13 sự thật về Minions để “ôn bài” trước giờ ra rạp!</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"373\" src=\"/media/2024/6/24/despicable-me-4-chung-ta-biet-duoc-bao-nhieu-ve-minions-10_1719219480819.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<ol style=\"margin-bottom:11px\">\n" + "\t<li style=\"margin-bottom:11px; margin-left:8px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Chỉ có một giọng lồng tiếng cho các Minions</span></span></span></li>\n" + "</ol>\n" + "\n" + "<p style=\"margin-bottom: 11px; margin-left: 8px; text-align: center;\"><img alt=\"\" height=\"450\" src=\"/media/2024/6/24/despicable-me-4-chung-ta-biet-duoc-bao-nhieu-ve-minions-7_1719219444025.jpg\" width=\"600\"></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Dù có đến gần ngàn Minions nhưng tất cả đều được lồng tiếng bởi nhà chế tác hoạt hình người Pháp - Pierre Coffin.</span></span></span></p>\n" + "\n" + "<ol start=\"2\" style=\"margin-bottom:11px\">\n" + "\t<li style=\"margin-bottom:11px; margin-left:8px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Có hẳn ngôn ngữ Minionese </span></span></span></li>\n" + "</ol>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Nghe có vẻ vô nghĩa nhưng tiếng của đám Minions có nghĩa thiệt nha. Chúng được tạo ra từ cách kết hợp những ngôn ngữ khác nhau như tiếng Anh, tiếng Pháp, tiếng Ý, tiếng Tây Ban Nha cùng một chút Hàn Quốc và Nga. Tuy nhiên, món Minions cực kì yêu thích – “banana” giữ nguyên.</span></span></span></p>\n" + "\n" + "<ol start=\"3\" style=\"margin-bottom:11px\">\n" + "\t<li style=\"margin-bottom:11px; margin-left:8px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Tất cả Minions đều là nam</span></span></span></li>\n" + "</ol>\n" + "\n" + "<p style=\"margin-bottom: 11px; margin-left: 8px; text-align: center;\"><img alt=\"\" height=\"324\" src=\"/media/2024/6/24/despicable-me-4-chung-ta-biet-duoc-bao-nhieu-ve-minions-11_1719219505322.jpg\" width=\"600\"></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Ta sẽ dễ nhầm Minions có cả nam lẫn nữ do cách ăn mặc. Thế nhưng, nhà sáng tạo xác nhận chúng đều là nam. Minions nổi loạn nên thời trang cũng loạn tùng phèo là chuyện hiển nhiên.</span></span></span></p>\n" + "\n" + "<ol start=\"4\" style=\"margin-bottom:11px\">\n" + "\t<li style=\"margin-bottom:11px; margin-left:8px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Minions có thể sống sót ngoài không gian</span></span></span></li>\n" + "</ol>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Là sinh vật có từ thời nguyên thủy, từng chọn khủng long làm chủ nhân, sống sót qua kỉ băng hà và bao nhiêu “kiếp nạn” của Trái Đất… Chẳng có gì ngạc nhiên khi khẳng định lũ chuối vàng có thể sống sót ngoài vũ trụ. Điểm này thì chúng giống loài gián ra phết!</span></span></span></p>\n" + "\n" + "<ol start=\"5\" style=\"margin-bottom:11px\">\n" + "\t<li style=\"margin-bottom:11px; margin-left:8px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Minions Trở Thành Biểu Tượng</span></span></span></li>\n" + "</ol>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Đoán xem, ai đang là gương mặt đại diện cho hãng Illumination. Chính là lũ chuối vàng chứ ai. Sau thành công rực rỡ của Despicable Me, chúng ngay lập tức được hãng Illumination chọn mặt gửi vàng. Thậm chí, Minions còn trở thành meme đình đám, bùng nổ các mạng xã hội.</span></span></span></p>\n" + "\n" + "<ol start=\"6\" style=\"margin-bottom:11px\">\n" + "\t<li style=\"margin-bottom:11px; margin-left:8px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Minions mê trái cây nào</span></span></span></li>\n" + "</ol>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Top 1 mọi thời đại dĩ nhiên là chuối. Chúng mê chuối đến mức đó là từ duy nhất chúng nói cho khán giả hiểu. Thậm chí, Minions còn sáng tác bài hát về chuối. Ngoài ra, Minions còn mê táo và đu đủ. </span></span></span></p>\n" + "\n" + "<ol start=\"7\" style=\"margin-bottom:11px\">\n" + "\t<li style=\"margin-bottom:11px; margin-left:8px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">&nbsp;Không trả lời Minions? Mơ đi!</span></span></span></li>\n" + "</ol>\n" + "\n" + "<p style=\"margin-bottom: 11px; margin-left: 8px; text-align: center;\"><img alt=\"\" height=\"339\" src=\"/media/2024/6/24/despicable-me-4-chung-ta-biet-duoc-bao-nhieu-ve-minions-12_1719219720372.jpg\" width=\"600\"></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Minions cực kì bướng bỉnh. Vì thế, nếu làm lơ không trả lời chúng, Minions sẽ nhìn bạn bằng cặp mắt ngây thơ cho đến khi nào bạn mở miệng mới thôi. </span></span></span></p>\n" + "\n" + "<ol start=\"8\" style=\"margin-bottom:11px\">\n" + "\t<li style=\"margin-bottom:11px; margin-left:8px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Minions chỉ có 3 ngón tay</span></span></span></li>\n" + "</ol>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Đừng bắt Minions tính tay vì chúng chẳng đếm được hơn 6 đâu.</span></span></span></p>\n" + "\n" + "<ol start=\"9\" style=\"margin-bottom:11px\">\n" + "\t<li style=\"margin-bottom:11px; margin-left:8px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Có bao nhiêu Minions?</span></span></span></li>\n" + "</ol>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Có tổng cộng 899 Minions. Chúng có 48 nhân dạng, thay đổi chiều cao, cân nặng, tóc và mắt.</span></span></span></p>\n" + "\n" + "<ol start=\"10\" style=\"margin-bottom:11px\">\n" + "\t<li style=\"margin-bottom:11px; margin-left:8px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Minions từ đâu mà ra?</span></span></span></li>\n" + "</ol>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Theo đạo diễn Despicable Me, Minions được tạo nên để làm hình ảnh Gru dễ thương hơn. Ban đầu, chúng có tạo hình to lớn và quái dị. Dần dần, các nhà sáng tạo thu nhỏ chúng lại với kích cỡ hiện giờ. Minions lấy cảm hứng từ Jawas trong Star Wars và Oompa Loompas trong Willy Wonka.</span></span></span></p>\n" + "\n" + "<ol start=\"11\" style=\"margin-bottom:11px\">\n" + "\t<li style=\"margin-bottom:11px; margin-left:8px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Minions già lắm rồi</span></span></span></li>\n" + "</ol>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Dù có ngoại hình và tính cách như đám nhóc tì nhưng thực tế là lũ Minions đã có mặt trên Trái Đất từ thuở xa xửa xa xưa. </span></span></span></p>\n" + "\n" + "<ol start=\"12\" style=\"margin-bottom:11px\">\n" + "\t<li style=\"margin-bottom:11px; margin-left:8px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Bob là trái chuối đặc biệt</span></span></span></li>\n" + "</ol>\n" + "\n" + "<p style=\"margin-bottom: 11px; margin-left: 8px; text-align: center;\"><img alt=\"\" height=\"427\" src=\"/media/2024/6/24/despicable-me-4-chung-ta-biet-duoc-ba-o-nhieu-ve-minions-1_1719219741607.jpg\" width=\"600\"></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Chú nhóc bị hội chứng loạn sắc tố mống mắt, đôi mắt có hai màu. </span></span></span></p>\n" + "\n" + "<ol start=\"13\" style=\"margin-bottom:11px\">\n" + "\t<li style=\"margin-bottom:11px; margin-left:8px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Minions một mắt</span></span></span></li>\n" + "</ol>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Minions có nhiều hình dáng và kích cỡ, một hoặc hai mắt. Tuy nhiên, Minions một mắt hiếm khi cao. Đa số chúng có cỡ thấp hoặc trung bình. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Sau khi ôn luyện sơ sơ, Stars đã sẵn sàng đón chào cơn bão Minions mới sẽ đổ bộ ở <a href=\"https://www.galaxycine.vn/dat-ve/despicable-me-4/\"><strong>Despicable Me 4</strong></a>. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><a href=\"https://www.galaxycine.vn/phim-dang-chieu/\"><em>Phim mới</em></a> <a href=\"https://www.galaxycine.vn/dat-ve/despicable-me-4/\"><strong>Despicable Me 4</strong></a>/ <a href=\"https://www.galaxycine.vn/dat-ve/despicable-me-4/\"><strong>Kẻ Trộm Mặt Trăng 4</strong></a> ra mắt 05.07.2024 tại các <a href=\"https://www.galaxycine.vn/\"><em>rạp chiếu phim</em></a> toàn quốc. Phim cũng được trình chiếu với định dạng <strong>IMAX Laser </strong>sắc nét tại <a href=\"https://www.galaxycine.vn/rap-gia-ve/galaxy-sala/\"><strong>Galaxy Sala</strong></a>.&nbsp;</span></span></span></p>\n" + "\n" + "<p align=\"right\" style=\"text-align:right; margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>Lược dịch từ ohfact</b></span></span></span></p>\n" + "\n" + "<p><!--| --></p>\n" + "</div>", "", "https://www.galaxycine.vn/media/2024/6/24/despicable-me-4-chung-ta-biet-duoc-bao-nhieu-ve-minions-3_1719218662477.jpg", PostType.BLOG, 30,  true,now(), now(), now(), admin));
        posts.add(new Post(5, "Venom 3: Venom Sẽ Chết?", "venom-3-venom-se-chet", "<div class=\"wysiwyg text-sm font-normal not-italic mt-3 binh-luan-phim-id content__data__full\"><p style=\"margin-bottom: 11px;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Venom và Eddie Brock lâm nguy ở <a href=\"http://www.galaxycine.vn/dat-ve/venom-the-last-dance/\"><b>The Last Dance</b></a>!</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Sau khi đến MCU chớp nhoáng, Venom và Eddie Brock quay về, tiếp tục tung hoành ngang dọc khắp San Francisco. Dẫu rằng Venom thỏa mãn vì được Eddie “dẫn đến toàn chỗ đỉnh nhất” còn Eddie thì “cần thời gian nghỉ ngơi” nhưng cả hai vẫn là một đôi hạnh phúc. Tuy nhiên, việc để Venom “ăn uống” vô tội vạ khiến Eddie lãnh hậu quả. Chính phủ phát hiện ra, quân đội vào cuộc truy đuổi, bộ đôi trở thành kẻ đào tẩu bị săn khắp nơi. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"338\" src=\"/media/2024/6/6/venom-3-venom-se-chet-4_1717646137831.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Thế nhưng, đó chưa phải là điều tệ nhất! Chủng tộc Symbiote phát hiện ra Trái Đất và chuẩn bị cho cuộc xâm lăng tổng lực! </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Quê nhà Venom có tên là Klyntar, do ác thần Knull xây dựng với âm mưu phá hủy toàn vũ trụ. Gã cũng là chủ nhân thanh gươm sát thần Necrosword từng xuất hiện trong <i>Thor: Love And Thunder</i> (2022). Nhiều lời đồn đoán cho rằng Knull chính là phản diện <a href=\"https://www.galaxycine.vn/phim-dang-chieu/\"><i>phim mới</i></a> <a href=\"http://www.galaxycine.vn/dat-ve/venom-the-last-dance/\"><b>Venom: The Last Dance</b></a>.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Ngoài ra, Patrick Mulligan (Stephen Graham) trở thành vật chủ của Toxin sau các sự kiện <a href=\"https://www.galaxycine.vn/phim/venom-let-there-be-carnage/\"><i>Venom: Let There Be Carnage</i></a> (2021). Trên lý thuyết, Toxin là con Carnage và cháu Venom. Hắn sở hữu sức mạnh còn lớn hơn cả hai. Đây có thể là vũ khí bí mật giúp Venom đối đầu hành tinh nhà mình để bảo vệ nhân loại.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Tuy nhiên, hàng loạt câu thoại mang tính ám chỉ như: “Nếu Venom vẫn còn sống, tất cả sẽ kết thúc!”, “Có thể mình sẽ không còn sống trở ra đâu, anh bạn.”, “Eddie, thời điểm đã đến!”… Liệu rằng, cái chết có chia lìa bộ đôi Symbrock?</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Kelly Marcel là đạo diễn kiêm biên kịch <a href=\"https://www.galaxycine.vn/dat-ve/venom-the-last-dance/\"><b>Venom: The Last Dance</b></a>. Bà là biên kịch <a href=\"https://www.galaxycine.vn/phim/venom-let-there-be-carnage/\"><i>Venom: Let There Be Carnage</i></a>. Đây là phim đầu tiên bà ngồi ghế chỉ đạo. Ngoài ra, đồng biên kịch Marcel ở <a href=\"https://www.galaxycine.vn/dat-ve/venom-the-last-dance/\"><b>Venom: The Last Dance</b></a> là nam chính Tom Hardy. Anh cũng từng tham gia viết kịch bản <a href=\"https://www.galaxycine.vn/phim/venom-let-there-be-carnage/\"><i>Let There Be Carnage</i></a>. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Dù trailer đầu tiên dài đến hơn 3 phút nhưng chủ yếu vẫn là Tom Hardy đối thoại cùng Tom Hardy, Eddie Brock tương tác với Venom. Người xem chưa biết nhân vật do Juno Temple và Chiwetel Ejiofor thủ vai là ai, thật sự đóng vai trò gì. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"375\" src=\"/media/2024/6/6/venom-3-venom-se-chet-3_1717646170760.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Đặc biệt, Chiwetel Ejiofor từng thể hiện vai Mordo trong <i>Doctor Strange</i> (2016) và <i>Doctor Strange In The Multiverse Of Madness</i> (2022). Mordo là pháp sư đã nghiêng về hắc ám, kẻ thù không đội trời chung của Stephen Strange. Rốt cuộc, Chiwetel Ejiofor đang diễn hai nhân vật hay Mordo ở MCU và nhân vật bí ẩn ở <a href=\"https://www.galaxycine.vn/dat-ve/venom-the-last-dance/\"><b>Venom: The Last Dance</b></a> có liên quan gì.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><i>Venom</i> (2018) không được kì vọng cao nhưng thắng đậm phòng vé. Kinh phí sản xuất 116 triệu $, thắng đậm phòng vé với 856 triệu $. <a href=\"https://www.galaxycine.vn/phim/venom-let-there-be-carnage/\"><i>Venom: Let There Be Carnage</i></a> (2021) chỉ tiêu tốn 110 triệu $ sản xuất và công chiếu giữa thời điểm đại dịch Covid-19 diễn ra tiếp tục thu hơn 506 triệu $. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"338\" src=\"/media/2024/6/6/venom-3-venom-se-chet-1_1717646204273.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Thương hiệu phản anh hùng này không hề được giới phê bình đánh giá cao nhưng khán giả hết sức yêu mến. Công chúng thích thú trước những màn đấu võ mồm giữa Eddie Brock và Symbiote. Thông qua trailer đầu tiên, ta có thể yên tâm là <a href=\"https://www.galaxycine.vn/dat-ve/venom-the-last-dance/\"><b>Venom: The Last Dance</b></a> vẫn biết phát huy điểm mạnh của mình.</span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom: 11px;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Mong rằng, tác phẩm sẽ không đi vào vết xe đổ thất bại thảm hại mang tên <a href=\"https://www.galaxycine.vn/phim/morbius/\"><i>Morbius</i></a> và <a href=\"https://www.galaxycine.vn/phim/madame-web/\"><i>Madame Web</i></a>. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><a href=\"https://www.galaxycine.vn/dat-ve/venom-the-last-dance/\"><b>Venom: The Last Dance</b></a> / <a href=\"https://www.galaxycine.vn/dat-ve/venom-the-last-dance/\"><b>Venom: Kèo Cuối</b></a> dự kiến khởi chiếu 25.10.2024 tại các <a href=\"https://www.galaxycine.vn/\"><i>rạp chiếu phim</i></a> toàn quốc. </span></span></span></p>\n" + "\n" + "<p style=\"margin-bottom:11px\">&nbsp;</p>\n" + "\n" + "<p><!--| --></p>\n" + "</div>", " ", "https://www.galaxycine.vn/media/2024/6/6/venom-3-venom-se-chet-7_1717645169505.jpg", PostType.BLOG, 10,  true,now(), now(), now(), admin));
        posts.add(new Post(5, "Mưa Quà Tặng Cho Thành Viên Galaxy Cinema 2024", "mua-qua-tang-cho-thanh-vien-galaxy-cinema-2024", "<div class=\"wysiwyg text-sm font-normal not-italic mt-3 content__data__full\"><p style=\"margin: 0in 0in 8pt;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\"><i>Chỉ cần là thành viên <b>Galaxy Cinema</b>, nhận ngay 1 bắp 2 nước!</i></span></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Năm 2024, Galaxy Cinema tiếp tục duy trì chính sách tích lũy điểm thưởng và sử dụng thanh toán vé/ bắp nước. </span></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">1. Điểm tích lũy còn lại của năm 2023 (tính tới 31.12.2023) sẽ không còn hiệu lực sử dụng từ 01.02.2024.&nbsp;<br>\n" + "2. Điểm tích lũy từ 01.01.2024 tới hết 31.01.2024 sẽ được bảo lưu và khách hàng có thể sử dụng tiếp tục trong năm 2024.<br>\n" + "3. Vé thăng hạng thành viên VIP 2023 sẽ không còn hiệu lực sử dụng từ 01.02.2024.<br>\n" + "4. Thành viên được xét lại hạng theo điều kiện chương trình từ 01.02.2024.</span></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Khách hàng thành viên có sinh nhật trong tháng sẽ được<strong> TẶNG COMBO 2</strong>: 01 bắp&nbsp;02 nước. </span></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Đặc biệt hơn nữa, G-Star và X-Star vừa nhận combo vừa có thêm vé mừng sinh nhật. </span></span></span></p>\n" + "\n" + "<p style=\"margin: 0in 0in 8pt; text-align: center;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><img alt=\"\" height=\"349\" src=\"/media/2024/1/19/1135x660_1705629079869.jpg\" width=\"600\"></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\"><b>Lưu ý: </b></span></span></span></p>\n" + "\n" + "<ul>\n" + "\t<li style=\"margin:0in 0in 0.0001pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Vé sinh nhật có thể sử dụng trong tháng tương ứng. </span></span></span></li>\n" + "\t<li style=\"margin:0in 0in 0.0001pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Quà tặng sinh nhật (combo 2, vé xem phim 2D dành cho thành viên hạng G-Star, X-Star) được thả vào tài khoản thành viên &amp; có giá trị sử dụng hiệu lực trong tháng sinh nhật thành viên. </span></span></span></li>\n" + "\t<li style=\"margin:0in 0in 0.0001pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Thành viên phải có ít nhất 1 giao dịch (vé/ bắp nước) với chi tiêu &gt; 0 trong vòng 12 tháng trở lại. </span></span></span></li>\n" + "\t<li style=\"margin:0in 0in 0.0001pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Thành viên tiếp tục được tặng vé khi nâng hạng thành viên VIP G-Star và X-Star. </span></span></span></li>\n" + "\t<li style=\"margin:0in 0in 0.0001pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Số vé tặng hạng thành viên G-Star 02 vé/năm. Khi thành viên G-Star thoả điều kiện nâng hạng thành X-Star, số vé tặng thêm là 02 vé. Tổng số vé tặng hạng thành viên X-Star là 04 vé/năm. </span></span></span></li>\n" + "\t<li style=\"margin:0in 0in 0.0001pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Khi đổi quà tặng sinh nhật (vé/ combo bắp nước), quý khách vui lòng mang giấy tờ tùy thân (CCCD/ CMND...) </span></span></span></li>\n" + "\t<li style=\"margin:0in 0in 8pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Trong mọi trường hợp, quyết định của Galaxy Cinema là quyết định cuối cùng. </span></span></span></li>\n" + "</ul>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Link QR Code tải app đăng ký thành viên:</span></span></span></p>\n" + "\n" + "<p style=\"margin: 0in 0in 8pt; text-align: center;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><img alt=\"\" height=\"424\" src=\"/media/2023/1/17/qr-code_1673943859302.jpg\" width=\"422\"></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\">&nbsp;</p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\"><b>Điều kiện sử dụng điểm tích lũy thanh toán: </b></span></span></span></p>\n" + "\n" + "<ul>\n" + "\t<li style=\"margin:0in 0in 0.0001pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Khách hàng thành viên tích lũy điểm dựa trên giá trị chi tiêu tại Galaxy Cinema (qua ứng dụng Galaxy Cinema &amp; tại rạp), cho cả chi tiêu vé &amp; bắp nước tùy theo hạng thành viên. </span></span></span></li>\n" + "\t<li style=\"margin:0in 0in 0.0001pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Thành viên có thể sử dụng điểm tích lũy để thanh toán vé, bắp nước cho các lần giao dịch kế tiếp. </span></span></span></li>\n" + "\t<li style=\"margin:0in 0in 0.0001pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Điểm tích lũy được gọi là Star. 1 điểm đổi được 1.000 đồng – áp dụng được cho việc thanh toán vé, bắp nước tại Galaxy Cinema. </span></span></span></li>\n" + "\t<li style=\"margin:0in 0in 0.0001pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Điểm tích lũy sẽ được cộng vào tài khoản vào ngay sau khi giao dịch vé, bắp nước xảy ra. </span></span></span></li>\n" + "\t<li style=\"margin:0in 0in 0.0001pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Thành viên có thể kiểm tra &amp; đổi điểm để thực hiện vào việc thanh toán vé, bắp nước trên website/ ứng dụng Galaxy Cinema, hoặc trực tiếp ngay tại rạp tại quầy vé, bắp nước. </span></span></span></li>\n" + "\t<li style=\"margin:0in 0in 0.0001pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Mỗi giao dịch có thể áp dụng đổi điểm tối thiểu 20 điểm và tối đa 100 điểm. </span></span></span></li>\n" + "\t<li style=\"margin:0in 0in 0.0001pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Điểm tích lũy sẽ bị trừ ngay sau khi đổi điểm thành công. </span></span></span></li>\n" + "\t<li style=\"margin:0in 0in 8pt 0.5in\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"font-size:14px;\"><span style=\"line-height:107%\">Điểm tích lũy có hạn sử dụng trong suốt năm 2024 (tới hết 31.12.2024).</span></span></span></li>\n" + "</ul>\n" + "\n" + "<p><!--| --></p>\n" + "</div>", "", "https://cdn.galaxycine.vn/media/2024/1/19/1200x1800_1705628936074.jpg", PostType.EVENT, 23000,  true,now(), now(), now(), admin));
        posts.add(new Post(6, "Xem Phim Hay – Ngất Ngây Cùng Bánh Phồng Dế Rec Rec", "xem-phim-hay-ngat-ngay-cung-banh-phong-de-rec-rec", "<div class=\"wysiwyg text-sm font-normal not-italic mt-3 content__data__full\"><p style=\"margin:0in 0in 8pt\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Bánh Phồng Dế Rec Rec – Snack Dế giàu đạm nhiều dinh dưỡng.</span></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Đi xem phim nhâm nhi bắp rang từ lâu đã là một trải nghiệm quá quen thuộc, nếu vẫn còn phân vân giữa các vị bắp khác nhau thì <b>Galaxy Cinema</b> mời Stars “đổi gió” với snack Rec Rec <i>ngon mà không nhàm chán!</i></span></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Bánh Phồng Dế Rec Rec mang nhiều hương vị đa dạng để Stars có cơ hội thưởng thức trọn vị thơm ngon đậm đà.</span></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>Vị Mala</b>: mang đến tinh túy từ Tứ Xuyên với vị cay kết hợp từ hạt tiêu và ớt, phù hợp với những ai muốn trải nghiệm sự kích thích vị giác một cách tuyệt đối.</span></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>Vị Gochujang</b>: Kết hợp các loại nguyên liệu bột đỏ, đậu ớt và đậu nành lên men để cho ra mùi vị ngọt ấm pha chút cay nhẹ. Dễ ăn dễ nghiện.</span></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><b>Vị Tom Yum</b>: Chất chua cay trong chanh, sả, ớt và riềng biến từng miếng snack thật đậm vị hấp dẫn mang dấu ấn rõ nét từ ẩm thực Thái Lan.</span></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Đến <b>Galaxy Cinema</b> thử ngay <i>Bánh Phồng Dế Rec Rec – Snack Hảo Hạng Đồng Hành Cho Sức Khỏe Không Giới Hạn</i>.</span></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Thời gian: từ ngày 05.04.2024.</span></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Giá bán: 35.000vnđ/bịch (extra 25.000vnđ/bịch).</span></span></span></p>\n" + "\n" + "<p style=\"margin:0in 0in 8pt\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Địa điểm: Các cụm rạp <b>Galaxy Cinema</b> tại TP.HCM.</span></span></span></p>\n" + "\n" + "<p style=\"margin: 0in 0in 8pt; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><img alt=\"\" height=\"349\" src=\"/media/2024/4/2/banhrecrec-digital-1135x660_1712051399084.jpg\" width=\"600\"></span></span></span></p>\n" + "\n" + "<p><!--| --></p>\n" + "</div>", "", "https://cdn.galaxycine.vn/media/2024/4/2/500_1712051408911.jpg", PostType.EVENT, 21200, true ,now(), now(), now(), admin));
        posts.add(new Post(7, "Tiêu Chí Phân Loại Phim Theo Lứa Tuổi", "tieu-chi-phan-loai-phim-theo-lua-tuoi-", "<div class=\"post__detail\"><h1 class=\"text-xxl text-black-10 font-bold not-italic mt-4\">Tiêu Chí Phân Loại Phim Theo Lứa Tuổi</h1><div><button type=\"button\" class=\"text-white mr-2 bg-[#4080ff] hover:bg-blue-800 focus:ring-1 focus:ring-blue-300 rounded text-xs py-1 px-[10px] md:px-5\"><svg aria-hidden=\"true\" focusable=\"false\" data-prefix=\"fas\" data-icon=\"thumbs-up\" class=\"svg-inline--fa fa-thumbs-up \" role=\"img\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 512 512\"><path fill=\"currentColor\" d=\"M313.4 32.9c26 5.2 42.9 30.5 37.7 56.5l-2.3 11.4c-5.3 26.7-15.1 52.1-28.8 75.2H464c26.5 0 48 21.5 48 48c0 18.5-10.5 34.6-25.9 42.6C497 275.4 504 288.9 504 304c0 23.4-16.8 42.9-38.9 47.1c4.4 7.3 6.9 15.8 6.9 24.9c0 21.3-13.9 39.4-33.1 45.6c.7 3.3 1.1 6.8 1.1 10.4c0 26.5-21.5 48-48 48H294.5c-19 0-37.5-5.6-53.3-16.1l-38.5-25.7C176 420.4 160 390.4 160 358.3V320 272 247.1c0-29.2 13.3-56.7 36-75l7.4-5.9c26.5-21.2 44.6-51 51.2-84.2l2.3-11.4c5.2-26 30.5-42.9 56.5-37.7zM32 192H96c17.7 0 32 14.3 32 32V448c0 17.7-14.3 32-32 32H32c-17.7 0-32-14.3-32-32V224c0-17.7 14.3-32 32-32z\"></path></svg> Thích 0</button><button type=\"button\" class=\"text-white mr-2 bg-[#4080ff] hover:bg-blue-800 focus:ring-1 focus:ring-blue-300 rounded text-xs py-1 px-[10px] md:px-5\">Chia sẻ</button><button class=\"text-xs bg-[#e9edf1] rounded mr-2 py-1 px-5\"><svg aria-hidden=\"true\" focusable=\"false\" data-prefix=\"fas\" data-icon=\"eye\" class=\"svg-inline--fa fa-eye \" role=\"img\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 576 512\"><path fill=\"currentColor\" d=\"M288 32c-80.8 0-145.5 36.8-192.6 80.6C48.6 156 17.3 208 2.5 243.7c-3.3 7.9-3.3 16.7 0 24.6C17.3 304 48.6 356 95.4 399.4C142.5 443.2 207.2 480 288 480s145.5-36.8 192.6-80.6c46.8-43.5 78.1-95.4 93-131.1c3.3-7.9 3.3-16.7 0-24.6c-14.9-35.7-46.2-87.7-93-131.1C433.5 68.8 368.8 32 288 32zM144 256a144 144 0 1 1 288 0 144 144 0 1 1 -288 0zm144-64c0 35.3-28.7 64-64 64c-7.1 0-13.9-1.2-20.3-3.3c-5.5-1.8-11.9 1.6-11.7 7.4c.3 6.9 1.3 13.8 3.2 20.7c13.7 51.2 66.4 81.6 117.6 67.9s81.6-66.4 67.9-117.6c-11.1-41.5-47.8-69.4-88.6-71.1c-5.8-.2-9.2 6.1-7.4 11.7c2.1 6.4 3.3 13.2 3.3 20.3z\"></path></svg> 80410</button></div><div class=\"wysiwyg text-sm font-normal not-italic mt-3 content__data__full\"><p><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">Theo thông tư số 05/2022/QH15, áp dụng từ: 01/01/2023,&nbsp;</span>Bảng tiêu chí phân loại phổ biến phim theo độ tuổi của Cục Điện Ảnh như sau:&nbsp;</span></span></p>\n" + "\n" + "<ul>\n" + "\t<li style=\"margin: 0in 0in 8pt;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">P: Phim phổ biến đến người xem ở mọi độ tuổi</span></span></span></li>\n" + "\t<li style=\"margin: 0in 0in 8pt;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">K: Phim phổ biến đến người xem dưới 13 tuổi với điều kiện xem cùng cha, mẹ hoặc người giám hộ</span></span></span></li>\n" + "\t<li style=\"margin: 0in 0in 8pt;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">T13: Cấm khán giả dưới 13 tuổi</span></span></span></li>\n" + "\t<li style=\"margin: 0in 0in 8pt;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">T16: Cấm khán giả dưới 16 tuổi</span></span></span></li>\n" + "\t<li style=\"margin: 0in 0in 8pt;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\">T18: Cấm khán giả dưới 18 tuổi</span></span></span></li>\n" + "</ul>\n" + "\n" + "<p style=\"margin: 0in 0in 8pt; text-align: center;\"><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><span style=\"line-height:107%\"><span aria-label=\" image widget\" contenteditable=\"false\" role=\"region\" tabindex=\"-1\"><img alt=\"\" data-widget=\"image\" height=\"349\" src=\"/media/2023/5/23/quy-dinh-do-tuoi-digital-1135x660_1684835353460.jpg\" width=\"600\"><span title=\"Click and drag to resize\">\u200B</span></span></span></span></span></p>\n" + "\n" + "<p><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><strong>Lưu ý:</strong></span></span></p>\n" + "\n" + "<ul>\n" + "\t<li><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\">Với các khách hàng mua vé online qua app <strong>Galaxy Cinema</strong>, vui lòng update app mới của Galaxy Cinema tại <strong><a href=\"https://www.galaxycine.vn/download\">ĐÂY</a></strong></span></span></li>\n" + "\t<li><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><strong>Đ</strong>ối với các phim T13, T16, T18, khách hàng vui lòng xuất trình giấy tờ tuỳ thân có ảnh (thẻ học sinh, CMND,...) trước khi mua vé.</span></span></li>\n" + "\t<li><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\">Đối với phim K, khách hàng dưới 13 tuổi vui lòng có cha, mẹ, người giám hộ đi kèm.</span></span></li>\n" + "</ul>\n" + "\n" + "<ul>\n" + "\t<li><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\">Khách hàng vui lòng chứng thực được độ tuổi phù hợp với phim được phân loại như trên.</span></span></li>\n" + "\t<li><span style=\"font-size:14px;\"><span style=\"font-family:Arial,Helvetica,sans-serif;\"><strong>Galaxy Cinema</strong> có quyền từ chối việc bán vé hoặc vào phòng chiếu nếu khách hàng không tuân thủ đúng theo quy định.\u200B</span></span></li>\n" + "</ul>\n" + "\n" + "<p style=\"text-align:center\">&nbsp;</p>\n" + "</div></div>", "", "https://cdn.galaxycine.vn/media/2023/7/25/quy-dinh…digital-1200x1800-1684835366729_1690277000962.jpg", PostType.EVENT,  15200, true,now(), now(), now(), admin));
        postRepository.saveAll(posts);
    }

    void createBaseTicketPrices(Cinema cinema) {
       /* Tạo ra các loại vé dựa trên các tiêu chí sau:
            các ngày trong tuần, _____> của mỗi loại suất chiếu ____>  của mỗi loại phim 2D, 3D _____> của mỗi loại phòng chiếu ____> của mỗi loại ghế _____> dành cho Rạp hiện tại
            cuối tuần,
*/
        for (DayType dayType : DayType.values()) {
            for (ScreeningTimeType screeningTimeType : ScreeningTimeType.values()) {
                for (GraphicsType graphicsType : GraphicsType.values()) {
                    for (AuditoriumType auditoriumType : AuditoriumType.values()) {
                        for (SeatType seatType : SeatType.values()) {
                            long price = 0;

                            if (cinema.getCity().getName().equals("TP. HCM") || cinema.getCity().getName().equals("Hà Nội")) {
                                price += 10000;
                            }

                            if (dayType == DayType.WEEKDAY) {
                                price += 10000;
                            } else if (dayType == DayType.WEEKEND) {
                                price += 15000;
                            }

                            if (screeningTimeType == ScreeningTimeType.SUẤT_CHIẾU_SỚM) {
                                price += 20000;
                            } else {
                                price += 10000;
                            }

                            if (graphicsType == GraphicsType._3D) {
                                price += 15000;
                            } else {
                                price += 10000;
                            }

                            if (auditoriumType == AuditoriumType.IMAX) {
                                price += 20000;
                            } else {
                                price += 10000;
                            }

                            if (seatType == SeatType.VIP) {
                                price += 15000;
                            } else if (seatType == SeatType.COUPLE) {
                                price += 15000;
                            } else {
                                price += 5000;
                            }

                            baseTicketPriceRepository.save(BaseTicketPrice.builder().seatType(seatType).graphicsType(graphicsType).screeningTimeType(screeningTimeType).dayType(dayType).auditoriumType(auditoriumType).cinema(cinema).price(price).build());
                        }
                    }
                }
            }
        }
    }


    void createBaseOneTimeCoupon() {
        CouponRoles newUser = CouponRoles.builder().ruleName("NEW USER").description("Khi voucher sử dụng Role này sẽ chỉ áp dụng cho người dùng đăng ký tài khoản mới").userType(UserType.NEW).build();
        couponRolesRepository.save(newUser);

        oneTimeCouponRepository.save(OneTimeCoupon.builder().name("Bạn mới giảm 25%").description("Voucher dành riêng cho các bạn mới đăng ký tài khoản, áp dụng trừ thẳng vào tổng giá trị hóa đơn. Voucher có hiệu lực 30 ngày. Lưu ý: khi voucher bị sử dụng sẽ không thể hoàn lại vì bất kì lý do gì").code("BANMOI25").couponRoles(List.of(newUser)).discountCouponType(DiscountCouponType.PERCENTAGE).discount(25).effectiveDate(30).startEnableDate(now()).isBase(true).createdAt(now()).updatedAt(now()).build());

        CouponRoles userUpRankVip = CouponRoles.builder().ruleName("RANK UP VIP").description("Khi voucher sử dụng Role này sẽ chỉ áp dụng cho người dùng lần đầu thăng hạng VIP trong năm").userType(UserType.RANK_UP_VIP).build();
        couponRolesRepository.save(userUpRankVip);

        oneTimeCouponRepository.save(OneTimeCoupon.builder().name("RANK UP!!! WELCOME TO VIP RANK").description("Chúc mừng bạn vừa mới thăng hạng tiêu dùng, chúng tôi xin gửi tặng bạn voucher giảm 35% áp dụng trừ thẳng vào tổng giá trị hóa đơn. Voucher có hiệu lực 15 ngày. Lưu ý: khi voucher bị sử dụng sẽ không thể hoàn lại vì bất kì lý do gì").code("HELLOVIP").couponRoles(List.of(userUpRankVip)).discountCouponType(DiscountCouponType.PERCENTAGE).discount(35).effectiveDate(15).startEnableDate(now()).isBase(true).createdAt(now()).updatedAt(now()).build());

        CouponRoles userUpRankPlatinum = CouponRoles.builder().ruleName("RANK UP PLATINUM").description("Khi voucher sử dụng Role này sẽ chỉ áp dụng cho người dùng lần đầu thăng hạng PLATINUM trong năm").userType(UserType.RANK_UP_PLATINUM).build();
        couponRolesRepository.save(userUpRankPlatinum);

        oneTimeCouponRepository.save(OneTimeCoupon.builder().name("RANK UP!!! WELCOME TO PLATINUM RANK").description("Chúc mừng bạn vừa mới thăng hạng tiêu dùng, chúng tôi xin gửi tặng bạn voucher giảm 50% áp dụng trừ thẳng vào tổng giá trị hóa đơn. Voucher có hiệu lực 15 ngày. Lưu ý: khi voucher bị sử dụng sẽ không thể hoàn lại vì bất kì lý do gì").code("HELLOPLATINUM").couponRoles(List.of(userUpRankPlatinum)).discountCouponType(DiscountCouponType.PERCENTAGE).discount(50).effectiveDate(15).startEnableDate(now()).isBase(true).createdAt(now()).updatedAt(now()).build());

        CouponRoles userBirthday = CouponRoles.builder().ruleName("USER BIRTHDAY").description("Khi voucher sử dụng Role này sẽ chỉ áp dụng cho người dùng đến ngày sinh nhật").userType(UserType.BIRTHDAY).build();
        couponRolesRepository.save(userBirthday);

        oneTimeCouponRepository.save(OneTimeCoupon.builder().name("CHÚC MỪNG SINH NHẬT").description("Thay mặt toàn thể đội ngũ VCINEMA, chúng tôi xin gửi lời chúc mừng sinh nhật ấm áp nhất đến bạn. Để kỷ niệm dịp đặc biệt này, chúng tôi rất vui được tặng bạn một voucher giảm giá 50% áp dụng trực tiếp vào tổng giá trị hóa đơn của bạn. Voucher có hiệu lực trong vòng 15 ngày kể từ ngày nhận được.\n" + "\n" + "Xin lưu ý: Voucher không thể hoàn lại hoặc đổi trả vì bất kỳ lý do gì sau khi đã được sử dụng.\n" + "\n" + "Chúc bạn có một sinh nhật vui vẻ và ý nghĩa!").code("HAPPYBIRTHDAY").couponRoles(List.of(userBirthday)).discountCouponType(DiscountCouponType.PERCENTAGE).discount(50).effectiveDate(15).startEnableDate(now()).isBase(true).createdAt(now()).updatedAt(now()).build());

    }

    void createCoupon() {
        CouponRoles couponRuleWeekend = CouponRoles.builder().ruleName("chỉ áp dụng cho ngày cuối tuần").dayType(DayType.WEEKEND).build();
        couponRolesRepository.save(couponRuleWeekend);

        couponRepository.save(Coupon.builder().name("Sự kiện giảm giá cuối tuần đặc biệt tại VCINEMA!").description("Chúng tôi hân hạnh thông báo chương trình giảm giá hấp dẫn vào cuối tuần này. Nhận ngay voucher với số lượng có hạn. Giảm ngay 50.000đ áp dụng trực tiếp vào tổng giá trị hóa đơn của bạn. Voucher có hiệu lực từ thứ Sáu đến Chủ Nhật và không thể hoàn lại hoặc đổi trả sau khi đã sử dụng. Đừng bỏ lỡ cơ hội tuyệt vời này để thưởng thức những bộ phim yêu thích với mức giá ưu đãi!").code("HAPPYWEEKEND").discountCouponType(DiscountCouponType.AMOUNT).discount(50000).limitAmount(50).startDate(now()).endDate(now().plusDays(7)).couponRoles(List.of(couponRuleWeekend)).status(true).createdAt(now()).updatedAt(now()).build());

        CouponRoles onlyMovieDeadpoolAndWolverineFreeCOMBOCOUPLEOnWeekEnd = CouponRoles.builder().ruleName("FREE COMBO COUPLE khi xem phim Deadpool & Wolverine vào cuối tuần").movie(movieRepository.findById(2).orElse(null)).combo(comboRepository.findById(1).orElse(null)).dayType(DayType.WEEKEND).build();
        couponRolesRepository.save(onlyMovieDeadpoolAndWolverineFreeCOMBOCOUPLEOnWeekEnd);


        couponRepository.save(Coupon.builder().name("Miễn Phí COMBO COUPLE khi xem phim Deadpool & Wolverine vào cuối tuần").description("Chúng tôi hân hạnh thông báo chương trình giảm giá hấp dẫn vào cuối tuần này. Nhận ngay voucher với số lượng có hạn. Miễn phí 100% giá COMBO COUPLE tại VCINEMA khi xem Deadpool & Wolverine vào các ngày cuối tuần. Voucher có hiệu lực từ thứ Sáu đến Chủ Nhật và không thể hoàn lại hoặc đổi trả sau khi đã sử dụng. Đừng bỏ lỡ cơ hội tuyệt vời này để thưởng thức những bộ phim yêu thích với mức giá ưu đãi!").code("COUPLEDEADPOOLANDWOLVERINE").discountCouponType(DiscountCouponType.PERCENTAGE).discount(100).limitAmount(50).startDate(now()).endDate(now().plusDays(7)).couponRoles(List.of(onlyMovieDeadpoolAndWolverineFreeCOMBOCOUPLEOnWeekEnd)).status(true).createdAt(now()).updatedAt(now()).build());

    }

    Faker faker = new Faker();

    public List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setFullName(faker.name().fullName()); // Tên đầy đủ
            user.setEmail(faker.internet().emailAddress()); // Email ngẫu nhiên
            user.setPassword(faker.internet().password()); // Mật khẩu ngẫu nhiên
            user.setAvatar("https://cdn-icons-png.flaticon.com/512/6386/6386976.png"); // Avatar ngẫu nhiên
            user.setPhoneNumber(faker.phoneNumber().phoneNumber()); // Số điện thoại ngẫu nhiên
            user.setGender(random.nextBoolean() ? "Nam" : "Nữ"); // Giới tính ngẫu nhiên
            user.setBirthday(LocalDate.ofInstant(faker.date().birthday(18, 60).toInstant(), ZoneId.systemDefault())); // Ngày sinh

            // Tạo thời gian ngẫu nhiên giữa đầu năm ngoái và năm nay
            LocalDateTime createdAt = getRandomDateTimeBetween(
                LocalDateTime.of(LocalDate.now().minusYears(1).getYear(), 1, 1, 0, 0, 0),
                LocalDateTime.now().withHour(23).withMinute(59).withSecond(59)
            );
            user.setCreatedAt(LocalDate.from(createdAt));
            user.setUpdatedAt(LocalDate.from(createdAt)); // Có thể sử dụng cùng thời điểm tạo hoặc đặt giá trị khác

            // Tạo UserStatistic tương ứng
            UserStatistic userStatistic = new UserStatistic();
            userStatistic.setPoints(random.nextInt(1000)); // Điểm ngẫu nhiên
            userStatistic.setTotalSpending((random.nextLong() * 1000000)); // Tổng chi tiêu ngẫu nhiên
            userStatistic.setUserRank(UserRank.values()[random.nextInt(UserRank.values().length)]); // Xếp hạng ngẫu nhiên

            userStatisticRepository.save(userStatistic);
            user.setUserStatistic(userStatistic);
            users.add(user);
        }
        userRepository.saveAll(users);
        return users;
    }

    private LocalDateTime getRandomDateTimeBetween(LocalDateTime start, LocalDateTime end) {
        long startEpoch = start.atZone(ZoneId.systemDefault()).toEpochSecond();
        long endEpoch = end.atZone(ZoneId.systemDefault()).toEpochSecond();
        long randomEpoch = ThreadLocalRandom.current().nextLong(startEpoch, endEpoch);
        return LocalDateTime.ofEpochSecond(randomEpoch, 0, ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
    }

    @Autowired
    BaseTicketPriceService baseTicketPriceService;

    void generateRandomBills() {
        List<User> users = generateUsers(20);
        List<Combo> allCombos = comboRepository.findAll();  // Lấy tất cả combo
        List<Showtime> showtimes = showtimeRepository.findAll();  // Lấy tất cả suất chiếu

        Random random = new Random();

        // Lấy thời gian hiện tại
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime baseTime = now.minusYears(1).withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);

        // Lặp qua các năm và tháng, dừng khi đến thời gian hiện tại
        while (baseTime.isBefore(now)) {
            for (User user : users) {
                for (int i = 0; i < (random.nextInt(20) + 5); i++) {
                    // Bước 1: Chọn ngẫu nhiên một suất chiếu
                    Showtime randomShowtime = showtimes.get(random.nextInt(showtimes.size()));
                    Auditorium auditorium = randomShowtime.getAuditorium();
                    Cinema cinema = auditorium.getCinema();

                    // Lấy tất cả ghế của phòng chiếu từ Showtime
                    List<Seat> seatsInAuditorium = seatRepository.findAllByAuditoriumIdAndDeletedOrderBySeatRowAscSeatColumnAsc(auditorium.getId(), false);

                    // Chọn ngẫu nhiên một vài ghế
                    int numberOfSeats = random.nextInt(4) + 1;  // Chọn từ 1 đến 4 ghế
                    List<Seat> selectedSeats = new ArrayList<>();
                    for (int k = 0; k < numberOfSeats; k++) {
                        Seat seat = seatsInAuditorium.get(random.nextInt(seatsInAuditorium.size()));
                        selectedSeats.add(seat);
                    }

                    // Bước 2: Chọn ngẫu nhiên combo
                    int numberOfCombos = random.nextInt(3) + 1;  // Chọn từ 1 đến 3 combo
                    List<Combo> selectedCombos = new ArrayList<>();
                    for (int j = 0; j < numberOfCombos; j++) {
                        Combo combo = allCombos.get(random.nextInt(allCombos.size()));
                        selectedCombos.add(combo);
                    }

                    // Bước 3: Tạo thời gian ngẫu nhiên trong cùng tháng đã chọn
                    LocalDateTime randomPaymentTime = baseTime
                        .withDayOfMonth(random.nextInt(baseTime.toLocalDate().lengthOfMonth()) + 1)  // Random ngày trong tháng
                        .withHour(random.nextInt(24))  // Random giờ trong ngày
                        .withMinute(random.nextInt(60))  // Random phút
                        .withSecond(random.nextInt(60));  // Random giây

                    // Bước 4: Tạo hóa đơn
                    Bill bill = new Bill();
                    bill.setStatus(BillStatus.PAID);  // Đặt trạng thái ngẫu nhiên
                    bill.setDiscount(random.nextInt(10000, 50000));  // Giảm giá ngẫu nhiên
                    bill.setTotalPrice(0);  // Tính sau khi thêm ghế và combo
                    bill.setPoints(random.nextInt(500));
                    bill.setPaymentAt(randomPaymentTime);  // Áp dụng thời gian thanh toán ngẫu nhiên đã chọn
                    bill.setCreatedAt(randomPaymentTime);
                    bill.setUpdatedAt(randomPaymentTime);
                    bill.setUser(user);
                    bill.setCinema(cinema);  // Cinema từ ghế
                    bill.setShowtime(randomShowtime);  // Showtime đã chọn

                    billRepository.save(bill);  // Lưu hóa đơn trước

                    // Bước 5: Tạo BillSeat và tính giá vé
                    long totalPrice = 0;
                    DayType dayType = getDayType(randomShowtime.getScreeningDate());  // Xác định loại ngày (Weekday hoặc Weekend)

                    for (Seat seat : selectedSeats) {
                        long ticketPrice = baseTicketPriceService.getPrice(
                            seat.getType(),
                            randomShowtime.getGraphicsType(),
                            randomShowtime.getScreeningTimeType(),
                            dayType,
                            randomShowtime.getAuditoriumType(),
                            cinema
                        );

                        BillSeat billSeat = new BillSeat();
                        billSeat.setPrice(ticketPrice);
                        billSeat.setCreatedAt(randomPaymentTime);
                        billSeat.setUpdatedAt(randomPaymentTime);
                        billSeat.setBill(bill);
                        billSeat.setSeat(seat);
                        billSeatRepository.save(billSeat);

                        totalPrice += ticketPrice;
                    }

                    // Bước 6: Tạo BillCombo
                    for (Combo combo : selectedCombos) {
                        BillCombo billCombo = new BillCombo();
                        billCombo.setQuantity(random.nextInt(3) + 1);  // Random số lượng combo
                        billCombo.setPrice(combo.getPrice());
                        billCombo.setCreatedAt(randomPaymentTime);
                        billCombo.setUpdatedAt(randomPaymentTime);
                        billCombo.setBill(bill);
                        billCombo.setCombo(combo);
                        billComboRepository.save(billCombo);

                        totalPrice += billCombo.getPrice() * billCombo.getQuantity();
                    }

                    String transactionNo = faker.random().nextInt(8, 10).toString();
                    // Cập nhật tổng giá trị của bill
                    bill.setTotalPrice(totalPrice - bill.getDiscount());// Trừ đi giảm giá nếu có
                    bill.setBarcode("https://quickchart.io/barcode?type=code128&text=" + transactionNo + "&width=600&height=200");
                    billRepository.save(bill);  // Cập nhật lại hóa đơn

                    // Tạo transaction ngẫu nhiên tương ứng với hóa đơn
                    transactionRepository.save(
                        Transaction.builder()
                            .status(true)
                            .bankCode("NCB")
                            .payDate(randomPaymentTime)
                            .updatedAt(randomPaymentTime)
                            .createdAt(randomPaymentTime)
                            .transactionNo(transactionNo)
                            .paymentMethod(PaymentMethod.VNPAY)
                            .responseCodeVNPAY(ResponseCodeVNPAY.PAYMENT_SUCCESS)
                            .bill(bill)
                            .cinema(cinema)
                            .build()
                    );
                }
            }
            // Chuyển sang tháng tiếp theo
            baseTime = baseTime.plusMonths(1);
        }
    }

    void employeeCreateData() {
        List<Cinema> cinemas = cinemaRepository.findAll();

        cinemas.forEach(c -> {
            EmployeePosition[] positions = EmployeePosition.values();
            List<User> employee = generateUsers(5);

            for (int i = 0; i < employee.size(); i++) {
                try {
                    employeeRepository.save(
                        Employee.builder()
                            .position(positions[i])
                            .status(EmployeeStatus.OFFICIAL_EMPLOYEE)
                            .salary(5000L)
                            .joinDate(now())
                            .createdAt(now())
                            .updatedAt(now())
                            .user(employee.get(i))
                            .cinema(c)
                            .build()
                    );
                } catch (Exception e) {
                    System.out.println("Error saving employee: " + e.getMessage());
                }

            }
        });
    }

    private DayType getDayType(LocalDate screeningDate) {
        // Giả định: ngày cuối tuần là Saturday (6) và Sunday (7)
        DayOfWeek dayOfWeek = screeningDate.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) ? DayType.WEEKEND : DayType.WEEKDAY;
    }

}


