package com.dovgopolyy.jett.server.search.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public final class InMemoryDataStore {

    private static volatile InMemoryDataStore instance;

    private final Map<Long, String> documents =
            Collections.synchronizedMap(new HashMap<>());
    private final Map<String, Set<Long>> tokens =
            Collections.synchronizedMap(new HashMap<>());

    private final AtomicLong newId = new AtomicLong(0);

    private InMemoryDataStore() {}

    public static InMemoryDataStore getInstance() {
        InMemoryDataStore result = instance;
        if (result == null) {
            synchronized (InMemoryDataStore.class) {
                result = instance;
                if (result == null)
                    instance = result = new InMemoryDataStore();
            }
        }
        return result;
    }

    public Map<Long, String> getDocuments() {
        return documents;
    }

    public Long getNewId() {
        return newId.addAndGet(1);
    }

    public Map<String, Set<Long>> getTokens() {
        return tokens;
    }

    public void clear() {
        documents.clear();
        tokens.clear();
    }
}
