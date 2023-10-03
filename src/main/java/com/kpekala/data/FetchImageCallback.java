package com.kpekala.data;

import java.io.InputStream;

public interface FetchImageCallback {
    public void onFetchImageSuccessful(InputStream imageByteStream);
    void onFetchImageFailed();
}
