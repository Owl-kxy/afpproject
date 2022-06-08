package com.afpproject.afpproject.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("client")
@Data
@NoArgsConstructor
public class Client
{
    @Id
    private String numberAccountClient;
    private String dniClient;
    private String nameClient;
    private String surnameClient;
    private String phoneClient;
    private String emailClient;
    private String afpClient;
    private Double balanceClient; //Registra el monto actual
}
