package com.vzurauskas.fortress.citadel;

import com.vzurauskas.nereides.jackson.Json;

import java.util.UUID;

public interface Citadel {
    UUID id();
    void storeFood(int food);
    Json json();
    void save();
}
