package com.tuul.domain.model.vehicle;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class VehicleRow {
    private String model;
    private String location;

    @Builder.Default
    private Date createdAt = new Date();

    @Builder.Default
    private Date updatedAt = new Date();
}