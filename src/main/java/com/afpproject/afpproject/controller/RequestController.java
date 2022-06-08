package com.afpproject.afpproject.controller;

import com.afpproject.afpproject.model.entity.Client;
import com.afpproject.afpproject.model.entity.Request;
import com.afpproject.afpproject.repository.ClientRepository;
import com.afpproject.afpproject.repository.RequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandles;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/request")
public class RequestController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RequestRepository requestRepository;

    //Para usar el endpoint correctamente se debe de ingresar el numero de cuenta del cliente y el monto de retiro

    @PostMapping("/createRequest")
    @ResponseStatus(HttpStatus.OK)
    public String createRequest(@RequestBody Request request)
    {
        LOGGER.info("Validacion de creacion de solicitud");
        String accountNumberClient = request.getNumberAccountClient();
        Double amountWithdrawal = request.getAmountWithdrawal();
        String message = null;

        Optional<Client> client = clientRepository.findById(accountNumberClient);

        //Obtenemos el dinero actual en el fondo del cliente
        Double balanceClient = client.get().getBalanceClient();
        String dniClient = client.get().getDniClient();
        String afpClient = client.get().getAfpClient();

        //Obtenemos el 50% del monto actual de la AFP del cliente
        Double amountAllow = balanceClient*0.50;

        if(amountWithdrawal > balanceClient)
        {
            LOGGER.info("Validacion de monto no permitido");
            request.setStatusRequestMoney("noPermitido");
            message = "No se puede registrar la solicitud. Monto mayor que el permitido";
        }
        else
        {
            if(amountWithdrawal < balanceClient && amountWithdrawal > amountAllow)
            {
                LOGGER.info("Validacion de monto menor al actual pero mayor al permitido");
                request.setStatusRequestMoney("permitido");
                message = "Se registro la solicitud con exito";
            }
            if(amountWithdrawal < balanceClient && amountWithdrawal<=amountAllow)
            {
                LOGGER.info("Validacion de monto permitido a desembolsar");
                request.setStatusRequestMoney("noPermitido");
                message = "Monto mínimo no cubierto por favor revise el monto mínimo a retirar";
            }
        }

        // Autocompletan los campos DNI, afp con los datos del numero de cuenta del cliente

        request.setDniClient(dniClient);
        request.setAfpClient(afpClient);
        requestRepository.save(request);

        return message;
    }

    @GetMapping("/getAllRequest")
    @ResponseStatus(HttpStatus.OK)
    public List<Request> getAllRequest()
    {
        return requestRepository.findAll();
    }

}
