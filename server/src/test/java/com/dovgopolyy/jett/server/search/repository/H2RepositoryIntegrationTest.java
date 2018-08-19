package com.dovgopolyy.jett.server.search.repository;

import com.dovgopolyy.jett.server.search.repository.impl.H2Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableAutoConfiguration
@Import(H2Repository.class)
public class H2RepositoryIntegrationTest {

    public static final String LOREM_IPSUM = "Lorem ipsum";


    @Autowired
    H2Repository repository;


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
