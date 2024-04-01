package com.vzurauskas.fortress.citadel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Realm {
    private final CitadelRepo repo;

    public Realm(CitadelRepo repo) {
        this.repo = repo;
    }

    public Citadel create(UUID id, String name) {
        return new PersistedCitadel(id, repo, name, 0);
    }

    public Citadel byId(UUID id) {
        CitadelRepo.DbEntry citadel = repo.findById(id).orElseThrow();
        return new PersistedCitadel(citadel.id, repo, citadel.name, citadel.food);
    }

    public Collection<Citadel> all() {
        List<Citadel> citadels = new ArrayList<>();
        repo.findAll().forEach(
            citadel -> citadels.add(
                new PersistedCitadel(citadel.id, repo, citadel.name, citadel.food)
            )
        );
        return citadels;
    }

    public int size() {
        return (int) repo.count();
    }
}
