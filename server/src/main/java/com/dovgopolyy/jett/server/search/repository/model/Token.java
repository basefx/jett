package com.dovgopolyy.jett.server.search.repository.model;


import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column(nullable = false, unique = true)
    private String word;

    @ManyToMany(targetEntity = Document.class, fetch = EAGER)
    private Set<Document> documents;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
}
