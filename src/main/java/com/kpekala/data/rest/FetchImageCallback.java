package com.kpekala.data.rest;

import java.io.InputStream;

public interface FetchImageCallback {
    void onFetchImageSuccessful(InputStream imageByteStream);
    void onFetchImageFailed();
}
