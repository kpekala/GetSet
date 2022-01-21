package org.example.data;

import org.example.data.model.SetModel;

import java.util.Map;

public interface FetchSetCallback {
    void onFetchSuccessful(SetModel setModel);
    void onFetchError(String message);
}
