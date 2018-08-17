package com.dovgopolyy.jett.server.search.repository.impl;

import com.dovgopolyy.jett.server.search.repository.SearchRepository;
import org.junit.Test;

import static org.junit.Assert.*;


public class InMemoryRepositoryIntegrationTest {

    public static final String LOREM_IPSUM = "Lorem ipsum";
    private SearchRepository repository = new InMemoryRepository();

    @Test
    public void testCreate() {

        Long id = repository.create(LOREM_IPSUM);
        assertNotNull(id);
    }

    @Test
    public void testCreateNull() {
        Long id = repository.create(null);
        assertNull(id);
    }


    @Test
    public void testCreateEmpty() {
        Long id = repository.create("");
        assertNull(id);
    }


    @Test
    public void testRead() {
        Long id = repository.create(LOREM_IPSUM);
        String result = repository.read(id);
        assertNotNull(result);
        assertEquals(LOREM_IPSUM, result);
    }


    @Test
    public void testReadNull() {
        Long id = repository.create(LOREM_IPSUM);
        String result = repository.read(null);
        assertNull(result);
    }


    @Test
    public void testReadMaxMissingId() {
        Long id = repository.create(LOREM_IPSUM);
        String result = repository.read(Long.MAX_VALUE);
        assertNull(result);
    }


    @Test
    public void testReadMinMissingId() {
        Long id = repository.create(LOREM_IPSUM);
        String result = repository.read(Long.MIN_VALUE);
        assertNull(result);
    }
}
