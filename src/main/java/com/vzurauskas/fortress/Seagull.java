package com.vzurauskas.fortress;

import com.vzurauskas.nereides.jackson.Json;

import java.util.UUID;

public interface Seagull {
    UUID id();
    void fly(int height);
    Json json();
    void save();
}
