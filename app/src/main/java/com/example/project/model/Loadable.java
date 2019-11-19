package com.example.project.model;

import java.io.IOException;

public interface Loadable {
    void load(String path) throws IOException;
}
