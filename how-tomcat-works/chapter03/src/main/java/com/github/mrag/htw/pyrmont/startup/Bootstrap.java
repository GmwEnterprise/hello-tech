package com.github.mrag.htw.pyrmont.startup;

import com.github.mrag.htw.pyrmont.connector.http.HttpConnector;

public class Bootstrap {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
