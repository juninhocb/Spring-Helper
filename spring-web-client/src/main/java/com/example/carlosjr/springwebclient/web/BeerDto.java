package com.example.carlosjr.springwebclient.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

    private Long id;

    private String beerName;

    private String beerStyle;

    private String upc;

    private BigDecimal price;

    private Integer quantityOnHand;

    private Date createdDate;
    private Date lastUpdatedDate;

}
