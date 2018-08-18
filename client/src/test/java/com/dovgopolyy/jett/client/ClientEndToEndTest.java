package com.dovgopolyy.jett.client;

import com.dovgopolyy.jett.client.runner.ClientRunner;
import com.dovgopolyy.jett.client.runner.ReaderSupplier;
import com.dovgopolyy.jett.client.service.impl.RestTemplateSupplier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@Import({
        ClientApplication.class,
        ClientEndToEndTest.TestCLientConfig.class
})
@PropertySource("application-test.properties")
@Configuration
public class ClientEndToEndTest {



    @Configuration
    public static class TestCLientConfig {

        @Bean
        @Primary
        public ReaderSupplier readerSupplier() {
            return mock(ReaderSupplier.class, RETURNS_DEEP_STUBS);
        }
    }

    @Autowired
    private ClientRunner clientRunner;

    @Autowired
    private ReaderSupplier readerSupplier;

    @Autowired
    private RestTemplateSupplier restTemplateSupplier;

    @Test
    public void testStartAndFinish() throws Exception {
        when(readerSupplier.get().readLine())
                .thenReturn("wrong")
                .thenReturn("end");

        clientRunner.run();

        verify(readerSupplier.get(), times(2)).readLine();
    }

    @Test
    public void test() {
        assertNotNull(restTemplateSupplier.get());
    }
}
