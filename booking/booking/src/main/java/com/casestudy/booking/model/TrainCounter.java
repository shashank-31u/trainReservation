package com.casestudy.booking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "train_counter")
@NamedQuery(name = "TrainCounter.findAll", query = "SELECT tc FROM TrainCounter tc")
public class TrainCounter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "counter")
    private Long counter;

    public TrainCounter() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCounter() {
        return this.counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

}
