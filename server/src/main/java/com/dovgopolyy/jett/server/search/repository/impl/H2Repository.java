package com.dovgopolyy.jett.server.search.repository.impl;

import com.dovgopolyy.jett.server.search.repository.SearchRepository;
import com.dovgopolyy.jett.server.search.repository.dao.DocumentDao;
import com.dovgopolyy.jett.server.search.repository.dao.TokenDao;
import com.dovgopolyy.jett.server.search.repository.model.Document;
import com.dovgopolyy.jett.server.search.repository.model.Token;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.*;
import static java.util.Collections.*;

@Primary
@Service
public class H2Repository implements SearchRepository{

    @Autowired
    private DocumentDao documentDao;

    @Autowired
    private TokenDao tokenDao;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Long create(String document) {
        if(StringUtils.isEmpty(document)) {
            return null;
        }

        Document documentEntity = new Document();
        documentEntity.setData(document);
        Document savedDocument = documentDao.save(documentEntity);
        saveTokens(savedDocument);
        return documentEntity.getId();
    }

    private void saveTokens(Document document) {
        String[] tokenArray = document.getData().toLowerCase().split(" ");
        List<String> tokenList = asList(tokenArray);

        Lists.partition(tokenList, 1000)
                .stream()
                .map(batch -> {
                    Map<String, Token> oldTokens = tokenDao.findByWordIn(batch)
                            .stream()
                            .filter(token -> token.getDocuments().add(document))
                            .collect(Collectors.toMap(token -> token.getWord(), token -> token));

                    Set<Token> newTokens = batch.stream()
                            .filter(token -> !oldTokens.containsKey(token))
                            .map(token -> {
                                Token tokenEntity = new Token();
                                tokenEntity.setWord(token);
                                tokenEntity.setDocuments(new HashSet<>(singletonList(document)));
                                return tokenEntity;
                            })
                            .collect(Collectors.toCollection(HashSet::new));
                    newTokens.addAll(oldTokens.values());
                    return newTokens;
                })
                .forEach(tokensToSave -> tokenDao.saveAll(tokensToSave));
    }

    @Override
    public String read(Long id) {
        if(id == null) return null;
        return documentDao.findById(id).orElse(new Document()).getData();
    }

    @Override
    public Collection<Long> find(String tokens) {
        if(StringUtils.isEmpty(tokens)) {
            return null;
        }
        String[] tokenArray = tokens.split(" ");

        Set<Long> result = documentDao.findByWords(asList(tokenArray), tokenArray.length)
                .stream()
                .map(Document::getId)
                .collect(Collectors.toSet());

        return result.isEmpty() ? null : result;
    }
}
