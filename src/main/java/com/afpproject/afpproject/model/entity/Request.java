package com.afpproject.afpproject.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.Date;

@Document("request")
@Data
@NoArgsConstructor
public class Request
{
    @Id
    private String idRequest;
    private String dniClient;
    private String afpClient;
    private Double amountWithdrawal;
    private Date dateToday = Calendar.getInstance().getTime(); //Registra la fecha actual no es necesario
    private String numberAccountClient;
    private String statusRequestMoney; //Registra el estado de la solicitud de desembolso
}
