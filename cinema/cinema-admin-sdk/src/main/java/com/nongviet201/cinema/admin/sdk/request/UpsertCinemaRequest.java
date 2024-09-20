package com.nongviet201.cinema.admin.sdk.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Value
public class UpsertCinemaRequest {
    String name;
    String address;
    boolean enabled;
    Integer city;
    Double lat;
    Double lng;
    Integer managerId;
}