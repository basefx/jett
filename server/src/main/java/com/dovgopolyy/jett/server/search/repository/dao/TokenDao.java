package com.dovgopolyy.jett.server.search.repository.dao;

import com.dovgopolyy.jett.server.search.repository.model.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TokenDao extends CrudRepository<Token, Long> {

    List<Token> findByWordIn(Collection<String> tokens);


}
