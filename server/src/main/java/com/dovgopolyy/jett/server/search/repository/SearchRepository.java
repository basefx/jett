package com.dovgopolyy.jett.server.search.repository;


import java.util.Collection;

public interface SearchRepository {


    /**
     * Add document
     * @param document a document that contains a series of tokens (words) separated by spaces
     * @return id of created document
     */
    Long create(String document);

    /**
     * Read document by id
     * @param id document of existing id
     * @return a document found by the id
     */
    String read(Long id);

    /**
     * Find document ids by tokens
     * @param tokens all tokens must be present in each document
     * @return list of the found documents
     */
    Collection<Long> find(String tokens);

}
