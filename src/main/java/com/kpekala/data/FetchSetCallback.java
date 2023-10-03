package com.kpekala.data;

import com.kpekala.data.model.SetModel;

public interface FetchSetCallback {
    void onFetchSuccessful(SetModel setModel);
    void onFetchError(String message);
}
