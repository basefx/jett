package com.dovgopolyy.jett.client.runner;

import com.dovgopolyy.jett.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;

@Component
public class ClientRunner implements CommandLineRunner {

    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_GET = "get";
    private static final String COMMAND_SEARCH = "search";
    private static final String COMMAND_END = "end";

    @Autowired
    private ReaderSupplier readerSupplier;

    @Autowired
    private ClientService clientService;



    public void run(String... args) throws Exception {
        BufferedReader reader = readerSupplier.get();

        while (true) {
            System.out.println("Enter Command (add, get, search, end)");
            String s = reader.readLine();

            if (s.equalsIgnoreCase(COMMAND_ADD)) {
                System.out.println("Enter tokens");
                s = reader.readLine();
                clientService.addDocument(s);
            } else if (s.equalsIgnoreCase(COMMAND_GET)) {
                System.out.println("Enter id");
                s = reader.readLine();
                clientService.getDocument(s);
            } else if (s.equalsIgnoreCase(COMMAND_SEARCH)) {
                System.out.println("Enter search string");
                s = reader.readLine();
                clientService.searchDocuments(s);
            } else if (s.equalsIgnoreCase(COMMAND_END)) {
                break;
            } else {
                System.out.println("Wrong command!");
            }

        }
    }

}
