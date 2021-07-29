package org.test.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class TestUtil {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
