package com.dovgopolyy.jett.server.search.repository.dao;

import com.dovgopolyy.jett.server.search.repository.model.Document;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DocumentDao extends CrudRepository<Document, Long> {

    @Query(value = "select * from document join \n" +
            "(select documents_id from token_documents \n" +
            "join Token on token.id = token_id \n" +
            "where word in (:words)\n" +
            "group by documents_id having count(*) >= :wordCount)\n" +
            "  on document.id = documents_id", nativeQuery = true)
    List<Document> findByWords(@Param("words") Collection<String> words,
                               @Param("wordCount") int wordCount);

}
