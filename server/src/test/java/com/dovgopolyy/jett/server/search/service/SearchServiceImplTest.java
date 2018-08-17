package com.dovgopolyy.jett.server.search.service;


import com.dovgopolyy.jett.server.search.repository.SearchRepository;
import com.dovgopolyy.jett.server.search.service.impl.SearchServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import static com.dovgopolyy.jett.server.search.repository.impl.InMemoryRepositoryIntegrationTest.*;
import static org.hamcrest.core.IsCollectionContaining.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceImplTest {

    private static final Long TEST_ID = 22L;

    @InjectMocks
    private SearchServiceImpl service;

    @Mock
    private SearchRepository repository;

    @Test
    public void testAddDocument() {
        when(repository.create(eq(LOREM_IPSUM))).thenReturn(TEST_ID);

        Long result = service.addDocument(LOREM_IPSUM);

        assertEquals(TEST_ID, result);

        verify(repository).create(eq(LOREM_IPSUM));
    }

    @Test
    public void testGetDocument() {
        when(repository.read(eq(TEST_ID))).thenReturn(LOREM_IPSUM);

        Optional<String> result = service.getDocument(TEST_ID);

        assertEquals(LOREM_IPSUM, result.get());

        verify(repository).read(eq(TEST_ID));
    }

    @Test
    public void testSearch() {
        when(repository.find(eq("token")))
                .thenReturn(new HashSet<>(Arrays.asList(TEST_ID, 1234L)));

        Optional<Collection<Long>> result = service.search("token");

        assertThat(result.get(), hasItems(1234L, TEST_ID));

        verify(repository).find(eq("token"));
    }

}
