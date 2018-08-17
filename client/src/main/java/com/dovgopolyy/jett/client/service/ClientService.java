package com.dovgopolyy.jett.client.service;

public interface ClientService {

    void addDocument(String tokens);

    void getDocument(String id);

    void searchDocuments(String tokens);
}
