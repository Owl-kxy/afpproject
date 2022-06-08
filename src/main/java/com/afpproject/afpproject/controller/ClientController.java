package com.afpproject.afpproject.controller;

import com.afpproject.afpproject.model.entity.Client;
import com.afpproject.afpproject.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController
{
    //Instancia de Slf4j para obtener logs de los metodos
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/createClient")
    @ResponseStatus(HttpStatus.OK)
    public void createClients(@RequestBody Client client)
    {
        LOGGER.info("Inicia la creacion de un cliente");
        clientRepository.save(client);
    }

    @GetMapping("/getAllClient")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients()
    {
        LOGGER.info("Ingresa a la consulta de todos los clientes registrados");
        return clientRepository.findAll();
    }

    @PutMapping("/updateClient/{numberAccountClient}")
    public Client updateClient(@RequestBody Client client, @PathVariable(value="numberAccountClient") String numberAccountClient)
    {
        client.setNumberAccountClient(numberAccountClient);
        clientRepository.save(client);
        LOGGER.info("Actualiza a los clientes usando el numero de cuenta");
        return client;
    }

    @DeleteMapping("/deleteClient/{numberAccountClient}")
    public String deleteClient(@PathVariable(value = "numberAccountClient") String numberAccountClient)
    {
        clientRepository.deleteById(numberAccountClient);
        LOGGER.info("Eliminacion de clientes por numero de cuenta");
        return "Cliente eliminado con exito";
    }

    @GetMapping("/getClientsAccount/{numberAccountClient}")
    public ResponseEntity<Client> getClientsAccount(@PathVariable(value = "numberAccountClient") String numberAccountClient)
    {
        Optional<Client> client = clientRepository.findById(numberAccountClient);
        LOGGER.info("Consulta de clientes por numero de cuenta");
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(client.get(), HttpStatus.NOT_FOUND));
    }
}
