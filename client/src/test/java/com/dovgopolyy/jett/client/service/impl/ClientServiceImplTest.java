package com.dovgopolyy.jett.client.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    public static final String TEST_STRING = "11111";
    public static final String TEST_STRING2 = "qwertyuio";
    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private RestTemplateSupplier restTemplateSupplier;

    @Mock
    private RestTemplate restTemplate;



    @Captor
    private ArgumentCaptor<HttpEntity<String>> captor;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Before
    public void setup() {


        when(restTemplateSupplier.get()).thenReturn(restTemplate);
    }

    @Test
    public void testAddDocument() {
        ResponseEntity<String> response = mock(ResponseEntity.class);
        when(response.getBody()).thenReturn(TEST_STRING);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), captor.capture(), eq(String.class)))
                .thenReturn(response);

        clientService.addDocument(TEST_STRING2);

        verify(response).getBody();
        assertEquals(TEST_STRING2, captor.getValue().getBody());
    }

    @Test
    public void testAddDocumentFail() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), captor.capture(), eq(String.class)))
                .thenThrow(new RestClientException("Test exception"));

        clientService.addDocument(TEST_STRING2);

    }

    @Test
    public void testGetDocument() {
        when(restTemplate.getForObject(anyString(), eq(String.class), any(Object.class)))
                .thenReturn(TEST_STRING);

        clientService.getDocument(TEST_STRING2);

        verify(restTemplate).getForObject(anyString(), eq(String.class), any(Object.class));
    }

    @Test
    public void testGetDocumentFail() {
        when(restTemplate.getForObject(anyString(), eq(String.class), any(Object.class)))
                .thenThrow(new RestClientException("Test exception"));

        clientService.getDocument(TEST_STRING2);
    }

    @Test
    public void testSearchDocuments() {
        when(restTemplate.getForObject(captorString.capture(), eq(String.class), any(Object.class)))
                .thenReturn(TEST_STRING);

        clientService.searchDocuments(TEST_STRING2);

        verify(restTemplate).getForObject(anyString(), eq(String.class), eq(TEST_STRING2));
        assertEquals("/search?tokens={tokens}", captorString.getValue());
    }

    @Test
    public void testSearchDocumentsFail() {
        when(restTemplate.getForObject(anyString(), eq(String.class), any(Object.class)))
                .thenThrow(new RestClientException("Test exception"));

        clientService.searchDocuments(TEST_STRING2);

    }

}
