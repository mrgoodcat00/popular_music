package com.goodcat.popularmusicapp.core.callback;

public interface NetCallback {

    void onSuccess();
    void onError();
    void putData(Object data);
    <T> T getData();
}
