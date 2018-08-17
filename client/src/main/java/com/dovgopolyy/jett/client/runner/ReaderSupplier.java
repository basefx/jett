package com.dovgopolyy.jett.client.runner;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Supplier;

@Component
public class ReaderSupplier implements Supplier<BufferedReader> {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public BufferedReader get() {
        return reader;
    }

    @PreDestroy
    public void onDestroy() throws IOException {
        reader.close();
    }
}
