package ru.sid0renk0.webtest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sid0renk0.webtest.beans.request.Request;
import ru.sid0renk0.webtest.beans.response.Balance;
import ru.sid0renk0.webtest.beans.response.Response;
import ru.sid0renk0.webtest.domain.Client;
import ru.sid0renk0.webtest.domain.ClientRepository;

import javax.transaction.Transactional;

@Controller
@ResponseBody
@RequestMapping(path = "/clients",
    consumes = "application/xml",
    produces = "application/xml")
public class ClientController {
    @SuppressWarnings({"UnusedDeclaration"})
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    private final ClientRepository clientRepository;
    private final PasswordEncoder  passwordEncoder;

    @Autowired
    public ClientController(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response request(@RequestBody Request request) {
        switch (request.getRequestType()) {
            case CREATE_AGT:
                return createAccount(request);
            case GET_BALANCE:
                return getBalance(request);
        }
        return new Response(2);
    }

    @Transactional
    private Response getBalance(Request request) {
        Client client = clientRepository.findByLogin(normalizeString(request.getLogin()));
        if (client == null) {
            return new Response(3);
        }

        if (isAuthorized(request, client)) {
            Balance balance = new Balance();
            balance.setBalance(client.getBalance());
            return balance;
        } else {
            return new Response(4);
        }
    }

    private boolean isAuthorized(Request request, Client client) {
        return passwordEncoder.matches(request.getPassword(), client.getPassHash());
    }

    @Transactional
    private Response createAccount(Request request) {
        String login = normalizeString(request.getLogin());
        if (clientRepository.existByLogin(login)) {
            LOGGER.warn("User already exists, {}", login);
            return new Response(1);
        }
        Client client = assembleClient(request);
        clientRepository.save(client);
        LOGGER.warn("Created user {}", login);
        return new Response(0);
    }

    /**
     * Трим + lowercase
     */
    private String normalizeString(String login) {
        return login.toLowerCase().trim();
    }

    private Client assembleClient(Request request) {
        String passHash = passwordEncoder.encode(request.getPassword());

        Client client = new Client();
        client.setLogin(normalizeString(request.getLogin()));
        client.setPassHash(passHash);
        return client;
    }

    @ExceptionHandler()
    public Response handleError(Exception ex) {
        LOGGER.warn("Unhandled exception", ex);
        return new Response(2);
    }
}
