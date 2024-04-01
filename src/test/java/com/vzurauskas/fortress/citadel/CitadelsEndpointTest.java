package com.vzurauskas.fortress.citadel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.vzurauskas.nereides.jackson.Json;
import com.vzurauskas.nereides.jackson.SmartJson;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CitadelsEndpointTest {
    private final CitadelsEndpoint endpoint;
    private final Realm realm;

    public CitadelsEndpointTest() {
        CitadelRepo repo = new FakeCitadelRepo();
        this.endpoint = new CitadelsEndpoint(repo);
        this.realm = new Realm(repo);
    }

    @Test
    void gets() {
        UUID camelotId = UUID.randomUUID();
        realm.create(camelotId, "Camelot").save();
        UUID minasTirithId = UUID.randomUUID();
        realm.create(minasTirithId, "Minas Tirith").save();
        ResponseEntity<ArrayNode> response = endpoint.get();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ArrayNode body = response.getBody();
        assertEquals(2, body.size());

        JsonNode camelot = body.get(0);
        assertEquals(camelotId.toString(), camelot.get("id").asText());
        assertEquals("Camelot", camelot.get("name").asText());
        assertEquals(0, camelot.get("foodStores").asInt());

        JsonNode minasTirith = body.get(1);
        assertEquals(minasTirithId.toString(), minasTirith.get("id").asText());
        assertEquals("Minas Tirith", minasTirith.get("name").asText());
        assertEquals(0, minasTirith.get("foodStores").asInt());
    }

    @Test
    void creates() {
        ResponseEntity<Json> response = endpoint.post(
            new CitadelsEndpoint.PostCitadels("Camelot")
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(new SmartJson(response.getBody()).textual().contains("Camelot"));
        assertEquals(1, realm.size());
    }
}