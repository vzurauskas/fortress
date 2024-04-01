package com.vzurauskas.fortress.citadel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.vzurauskas.nereides.jackson.Json;
import com.vzurauskas.nereides.jackson.MutableJson;
import com.vzurauskas.nereides.jackson.SmartJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/citadels")
public class CitadelsEndpoint {
    private final Realm realm;

    public CitadelsEndpoint(CitadelRepo repo) {
        realm = new Realm(repo);
    }

    record PostCitadels(String name) { }

    @PostMapping
    public ResponseEntity<Json> post(@RequestBody PostCitadels request) {
        realm.create(UUID.randomUUID(), request.name).save();
        return new ResponseEntity<>(
            new MutableJson().with("result", "Created " + request.name + "!"),
            HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ArrayNode> get() {
        ArrayNode node = new ObjectMapper().createArrayNode();
        realm.all().forEach(
            citadel -> node.add(new SmartJson(citadel.json()).objectNode())
        );
        return new ResponseEntity<>(node, HttpStatus.OK);
    }
}


