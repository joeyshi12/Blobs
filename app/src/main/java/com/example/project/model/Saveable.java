package com.example.project.model;

import java.io.IOException;

public interface Saveable {
    void save(String path) throws IOException;
}
