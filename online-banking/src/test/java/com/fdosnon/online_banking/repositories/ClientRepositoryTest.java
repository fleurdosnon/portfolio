package com.fdosnon.online_banking.repositories;

import com.fdosnon.online_banking.entities.Client;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Sql(scripts = {"file:src/test/resources/sql/create_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"file:src/test/resources/sql/drop_tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void whenClientIsSaved_findById_returnClient() {
        // GIVEN
        Client clientToPersist = new Client();
        clientToPersist.setUserName("newUser");
        clientToPersist.setPassword("password");
        Client clientPersisted = clientRepository.save(clientToPersist);
        // WHEN
        Optional<Client> result = clientRepository.findById(clientPersisted.getId());
        // THEN
        assertTrue(result.isPresent());
        assertEquals(clientPersisted, result.get());
    }
}