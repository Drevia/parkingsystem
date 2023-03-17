package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

import java.util.concurrent.TimeUnit;

public class FareCalculatorService {

    //rajout d'une boolean discount pour la reduction de 5%
    public void calculateFare(Ticket ticket, boolean discount){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            System.out.println(ticket.getInTime().getTime());
            System.out.println(ticket.getOutTime().getTime());
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        //changement de getHours a getDate pour avoir plus de precision
        long inTime = ticket.getInTime().getTime();
        long outTime = ticket.getOutTime().getTime();

        //TODO: Some tests are failing here. Need to check if this logic is correct
        //Calcul de la différence en millisecondes entre le temps d'entrée et de sortie
        long duration = outTime - inTime;

        //conversion du resultat en minutes
        duration = TimeUnit.MILLISECONDS.toMinutes(duration);

        //Puis en heures
        double durationInHours = (double) duration / 60;
        if (durationInHours < 0.5) {
            ticket.setPrice(0);
        } else {

            switch (ticket.getParkingSpot().getParkingType()){
                case CAR: {
                    ticket.setPrice(durationInHours * Fare.CAR_RATE_PER_HOUR);
                    break;
                }
                case BIKE: {
                    ticket.setPrice(durationInHours * Fare.BIKE_RATE_PER_HOUR);
                    break;
                }
                default: throw new IllegalArgumentException("Unkown Parking Type");
            }
        }
        //set the price with 5% discount if it's true
        if (discount) {
            ticket.setPrice(ticket.getPrice() * 0.95);
        }
    }

    //constructor of calculateFare but without discount
    public void calculateFare(Ticket ticket){
        //verifier l'existence du vehicule pour la reduction
        calculateFare(ticket, false);
    }
}