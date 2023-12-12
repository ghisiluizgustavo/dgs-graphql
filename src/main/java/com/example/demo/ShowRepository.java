package com.example.demo;


import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public class ShowRepository {

    private Flux<Show> shows = Flux.just(
            new Show("Stranger Things", 2016),
            new Show("Ozark", 2017),
            new Show("The Crown", 2016),
            new Show("Dead to Me", 2019),
            new Show("Orange is the New Black", 2013)
        );


    public Flux<Show> findAll(){
        return shows;
    }

    public void addShow(Show show) {
        shows = shows.concatWith(Flux.just(show));
    }
}
