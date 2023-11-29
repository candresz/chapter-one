package com.chapterone.chapteroneapp.controllers;

import com.chapterone.chapteroneapp.dto.ChargeResponseDTO;
import com.chapterone.chapteroneapp.models.ChargeRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Value("${stripe.api.key}")
    private String apiKey;

    @PostMapping("/charge")
    public ChargeResponseDTO chargeCard(@RequestBody ChargeRequest chargeRequest) throws StripeException {
        Stripe.apiKey = apiKey;
        long amountInCents = Math.round(chargeRequest.getAmount() * 100);

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amountInCents);
        chargeParams.put("currency", "usd");
        chargeParams.put("description", "Example charge");
        chargeParams.put("source", chargeRequest.getToken());

        Charge charge = Charge.create(chargeParams);

        // Crear y devolver un DTO con la información relevante
        return new ChargeResponseDTO(charge.getId(), amountInCents, charge.getStatus());
    }


}

