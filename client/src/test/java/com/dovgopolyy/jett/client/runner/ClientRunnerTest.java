package com.dovgopolyy.jett.client.runner;

import com.dovgopolyy.jett.client.service.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientRunnerTest {

    private static final String TEST_DOC = "sdsd sdsd";
    @InjectMocks
    private ClientRunner runner;

    @Mock
    private ReaderSupplier readerSupplier;

    @Mock
    private BufferedReader reader;

    @Mock
    private ClientService clientService;

    @Before
    public void setup() {
        when(readerSupplier.get()).thenReturn(reader);
    }

    @Test(timeout = 1000L)
    public void testAdd() throws Exception {
        when(reader.readLine())
                .thenReturn("add")
                .thenReturn(TEST_DOC)
                .thenReturn("end");

        runner.run();

        verify(clientService).addDocument(eq(TEST_DOC));
    }

    @Test(timeout = 1000L)
    public void testGet() throws Exception {
        when(reader.readLine())
                .thenReturn("get")
                .thenReturn("7575")
                .thenReturn("end");

        runner.run();

        verify(clientService).getDocument(eq("7575"));
    }

    @Test(timeout = 1000L)
    public void testSearch() throws Exception {
        when(reader.readLine())
                .thenReturn("search")
                .thenReturn(TEST_DOC)
                .thenReturn("end");

        runner.run();

        verify(clientService).searchDocuments(eq(TEST_DOC));
    }

}
