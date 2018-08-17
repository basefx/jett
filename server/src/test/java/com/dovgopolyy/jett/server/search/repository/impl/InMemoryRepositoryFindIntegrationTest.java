package com.dovgopolyy.jett.server.search.repository.impl;

import com.dovgopolyy.jett.server.search.data.InMemoryDataStore;
import com.dovgopolyy.jett.server.search.repository.SearchRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static com.dovgopolyy.jett.server.search.repository.impl.InMemoryRepositoryIntegrationTest.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class InMemoryRepositoryFindIntegrationTest {

    private SearchRepository repository = new InMemoryRepository();

    private Long id1;
    private Long id2;

    @Before
    public void setup() {
        id1 = repository.create("word letter Ship LOREM");
        id2 = repository.create(LOREM_IPSUM.toUpperCase());
    }

    @After
    public void tearDown() {
        InMemoryDataStore.getInstance().clear();
    }

    @Test
    public void testFindSingle() {


        Collection<Long> result = repository.find("lorem");

        assertThat(result, hasItems(id1, id2));
    }

    @Test
    public void testFindMultiple() {

        Collection<Long> result = repository.find("lorem word");

        assertTrue(result.contains(id1));
        assertFalse(result.contains(id2));
    }

    @Test
    public void testFindForNull() {

        Collection<Long> result = repository.find(null);

        assertNull(result);
    }



    @Test
    public void testFindForEmpty() {

        Collection<Long> result = repository.find("");

        assertNull(result);
    }

    @Test
    public void testFindForMissingTokens() {

        Collection<Long> result = repository.find("abra kadabra");

        assertNull(result);
    }

    @Test
    public void testFindForSecondMissingToken() {

        Collection<Long> result = repository.find("word kadabra");

        assertNull(result);
    }

    @Test
    public void testFindForFirstMissingToken() {

        Collection<Long> result = repository.find("kadabra word");

        assertNull(result);
    }
}
