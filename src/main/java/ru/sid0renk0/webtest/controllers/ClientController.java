package ru.sid0renk0.webtest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sid0renk0.webtest.beans.request.Request;
import ru.sid0renk0.webtest.beans.response.Balance;
import ru.sid0renk0.webtest.beans.response.Response;

import java.math.BigDecimal;

@Controller
@ResponseBody
@RequestMapping(path = "/clients",
    consumes = "application/xml",
    produces = "application/xml")
public class ClientController {
    @SuppressWarnings({"UnusedDeclaration"})
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @RequestMapping(method = RequestMethod.POST)
    public Response request(@RequestBody Request request) {
        switch (request.getRequestType()) {
            case CREATE_AGT:
                return createAccount(request);
            case GET_BALANCE:
                return getBalance(request);
        }
        return new Response();
    }

    private Response getBalance(Request request) {
        //TODO: Implement ru.sid0renk0.webtest.controllers.ClientController#getBalance
        throw new UnsupportedOperationException("Not implemented");
        //return null;
    }

    private Response createAccount(Request request) {
        //TODO: Implement ru.sid0renk0.webtest.controllers.ClientController#createAccount
        throw new UnsupportedOperationException("Not implemented");
        //return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Response getUserBalance() {
        Balance balance = new Balance();
        balance.setResultCode(0);
        balance.setBalance(BigDecimal.valueOf(10));
        return balance;
    }

    @ExceptionHandler()
    public Response handleError(Exception ex) {
        LOGGER.warn("Unhandled exception", ex);
        Response response = new Response();
        response.setResultCode(2);
        return response;
    }
}
