package com.goodcat.popularmusicapp.core.callback;

public class SimpleCallback implements NetCallback{

    Object object;

    @Override
    public void onSuccess() {
    }

    @Override
    public void onError() {
    }

    @Override
    public void putData(Object data) {
        object = data;
    }

    public final <T> T getData() {
        return (T) object;
    }
}
