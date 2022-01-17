package org.example.data;

import java.util.Map;

public interface FetchSetCallback {
    void onFetchSuccessful(String name);
    void onFetchError(String message);
}
