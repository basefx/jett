package com.dovgopolyy.jett.server.search.repository.impl;

import com.dovgopolyy.jett.server.search.data.InMemoryDataStore;
import com.dovgopolyy.jett.server.search.repository.SearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.*;

@Component
public class InMemoryRepository implements SearchRepository {




    @Override
    public Long create(String document) {

        if(StringUtils.isEmpty(document)) {
            return null;
        }

        Long id = InMemoryDataStore.getInstance().getNewId();
        InMemoryDataStore.getInstance().getDocuments().put(id, document);

        // Add token to index
        Arrays.stream(document.toLowerCase().split(" "))
                .forEach(token -> {
                    InMemoryDataStore.getInstance()
                            .getTokens()
                            .compute(token, (k, v) -> {
                                if(v == null) {
                                    return new HashSet<>(singletonList(id));
                                } else {
                                    v.add(id);
                                    return v;
                                }
                            });
                });

        return id;
    }

    @Override
    public String read(Long id) {
        return InMemoryDataStore.getInstance().getDocuments().get(id);
    }

    @Override
    public Collection<Long> find(String tokens) {

        if(StringUtils.isEmpty(tokens)) {
            return null;
        }

        Set<Long> result = new HashSet<>();

        for (String token : tokens.toLowerCase().split(" ")) {
            Set<Long> docs = InMemoryDataStore.getInstance().getTokens().get(token);
            if(docs == null) {
                return null;
            } else if(result.isEmpty()) {
                result.addAll(docs);
            } else {
                result.retainAll(docs);
            }

        }

        return result;
    }

}
