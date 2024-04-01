package com.vzurauskas.fortress.citadel;

import com.vzurauskas.nereides.jackson.Json;
import com.vzurauskas.nereides.jackson.MutableJson;

import java.util.UUID;

public class PersistedCitadel implements Citadel {
    private final UUID id;
    private final CitadelRepo repo;
    private final String name;
    private int food;

    public PersistedCitadel(UUID id, CitadelRepo repo, String name, int food) {
        this.id = id;
        this.repo = repo;
        this.name = name;
        this.food = food;
    }

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public void storeFood(int food) {
        this.food += food;
    }

    @Override
    public Json json() {
        return new MutableJson()
            .with("id", id.toString())
            .with("name", name)
            .with("foodStores", food);
    }

    @Override
    public void save() {
        repo.save(new CitadelRepo.DbEntry(id, name, food));
    }
}
