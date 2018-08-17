package com.dovgopolyy.jett.server.search.service.impl;

import com.dovgopolyy.jett.server.search.repository.SearchRepository;
import com.dovgopolyy.jett.server.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    private SearchRepository repository;

    public Long addDocument(String document) {
        return repository.create(document);
    }

    @Override
    public Optional<String> getDocument(Long id) {
        return Optional.ofNullable(repository.read(id));
    }

    @Override
    public Optional<Collection<Long>> search(String tokens) {
        return Optional.ofNullable(repository.find(tokens));
    }
}
