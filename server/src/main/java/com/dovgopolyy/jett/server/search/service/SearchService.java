package com.dovgopolyy.jett.server.search.service;

import java.util.Collection;
import java.util.Optional;

public interface SearchService {
    Long addDocument(String document);

    Optional<String> getDocument(Long id);

    Optional<Collection<Long>> search(String tokens);
}
