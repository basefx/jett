package com.dovgopolyy.jett.server.doc;

import org.junit.Test;

import static org.junit.Assert.*;

public class SwaggerConfigTest {

    private final SwaggerConfig swaggerConfig = new SwaggerConfig();

    @Test
    public void apiCreated() {
        assertNotNull(swaggerConfig.api());
    }
}
