package com.jdriven.example.order;

import java.net.MalformedURLException;
import java.net.URL;

// Violates rule 1: uses the deprecated URL(String) constructor
public class OrderImporter {

    public URL source(String location) throws MalformedURLException {
        return new URL(location);
    }
}
