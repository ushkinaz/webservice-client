package ru.sid0renk0.webtest.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface ClientRepository extends Repository<Client, Long> {
    Client findByLogin(String login);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM Client c WHERE c.login = ?1")
    boolean existByLogin(String login);

    Client save(Client client);
}
