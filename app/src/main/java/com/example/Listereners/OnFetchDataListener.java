package com.example.Listereners;


import com.example.Model.Root;
public interface OnFetchDataListener {
    void onFetchData(Root root, String message);
    void onError(String message);
}
