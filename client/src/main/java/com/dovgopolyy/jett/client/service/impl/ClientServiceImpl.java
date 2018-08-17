package com.dovgopolyy.jett.client.service.impl;

import com.dovgopolyy.jett.client.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static java.util.Collections.*;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private RestTemplateSupplier restTemplateSupplier;

    public void addDocument(String tokens) {

        try {

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            headers.setAccept(singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> requestEntity = new HttpEntity<>(tokens, headers);
            ResponseEntity<String> result =
                    restTemplateSupplier.get().exchange("/document", HttpMethod.POST, requestEntity, String.class);

            System.out.println("Id: " + result.getBody());

        } catch (RestClientException e) {
            log.info(e.getMessage(), e);
        }
    }

    public void getDocument(String id) {
        try {


            String result = restTemplateSupplier.get().getForObject("/document/"+id, String.class);

            System.out.println("Document: " + result);

        } catch (RestClientException e) {
            log.info(e.getMessage(), e);
        }
    }

    public void searchDocuments(String tokens) {
        try {

            String result = restTemplateSupplier.get().getForObject("?tokens="+ URLEncoder.encode(tokens, "UTF-8"), String.class);

            System.out.println("Document: " + result);

        } catch (RestClientException e) {
            log.warn(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
    }

}
