package com.dovgopolyy.jett.server.search.data;

import com.dovgopolyy.jett.server.search.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitTestDataOnStartup implements ApplicationRunner {

    @Autowired
    private SearchRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        repository.create("Lorem ipsum dolorem");
        repository.create("Ipsum Dolorem theophrastus");
        repository.create("Modus theophrastus Ipsum eos id");
        repository.create("Ut quo deserunt lobortis, te porro ipsum fugit duo");
        repository.create("congue porro ipsum fugit lorem ipsum volumus in sed, te duo");
    }
}
