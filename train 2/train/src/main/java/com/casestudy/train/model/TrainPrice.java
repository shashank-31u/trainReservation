package com.casestudy.train.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "train_price")
@NamedQuery(name = "TrainPrice.findAll", query = "SELECT tp FROM TrainPrice tp")
public class TrainPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "train_price_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainPriceId;

    @Column(name = "normal_fare_per_km")
    private Long normalFarePerKM;

    @Column(name = "tatkal_fare_per_km")
    private Long tatkalFarePerKM;

    @Column(name = "premium_tatkal_fare_per_km")
    private Long premiumTatkalFarePerKM;

    public TrainPrice() {
    }

    public Long getTrainPriceId() {
        return this.trainPriceId;
    }

    public void setTrainPriceId(Long trainPriceId) {
        this.trainPriceId = trainPriceId;
    }

    public Long getNormalFarePerKM() {
        return this.normalFarePerKM;
    }

    public void setNormalFarePerKM(Long normalFarePerKM) {
        this.normalFarePerKM = normalFarePerKM;
    }

    public Long getTatkalFarePerKM() {
        return this.tatkalFarePerKM;
    }

    public void setTatkalFarePerKM(Long tatkalFarePerKM) {
        this.tatkalFarePerKM = tatkalFarePerKM;
    }

    public Long getPremiumTatkalFarePerKM() {
        return this.premiumTatkalFarePerKM;
    }

    public void setPremiumTatkalFarePerKM(Long premiumTatkalFarePerKM) {
        this.premiumTatkalFarePerKM = premiumTatkalFarePerKM;
    }

}
