package com.nongviet201.cinema.core;

import com.nongviet201.cinema.core.entity.Post;
import com.nongviet201.cinema.core.entity.bill.BaseTicketPrice;
import com.nongviet201.cinema.core.entity.bill.Combo;
import com.nongviet201.cinema.core.entity.bill.coupon.Coupon;
import com.nongviet201.cinema.core.entity.bill.coupon.CouponRoles;
import com.nongviet201.cinema.core.entity.bill.coupon.OneTimeCoupon;
import com.nongviet201.cinema.core.entity.cinema.*;
import com.nongviet201.cinema.core.entity.movie.*;
import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.model.enums.PostType;
import com.nongviet201.cinema.core.model.enums.cinema.AuditoriumType;
import com.nongviet201.cinema.core.model.enums.cinema.SeatType;
import com.nongviet201.cinema.core.model.enums.coupon.DiscountCouponType;
import com.nongviet201.cinema.core.model.enums.coupon.UserType;
import com.nongviet201.cinema.core.model.enums.movie.GraphicsType;
import com.nongviet201.cinema.core.model.enums.movie.MovieAge;
import com.nongviet201.cinema.core.model.enums.movie.TranslationType;
import com.nongviet201.cinema.core.model.enums.showtime.DayType;
import com.nongviet201.cinema.core.model.enums.showtime.ScreeningTimeType;
import com.nongviet201.cinema.core.model.enums.user.UserRole;
import com.nongviet201.cinema.core.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.time.LocalDate.now;

@SpringBootTest
class CinemaCoreApplicationTests {

}
