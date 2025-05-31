/* package com.fretoplanton.service;

import org.springframework.stereotype.Service;


@Service
public class MercadoPagoService {

    static {
        MercadoPago.SDK.setAccessToken("TU_ACCESS_TOKEN");
    }

    public payment crearPago(Double monto) {
        Payment payment = new Payment();
        payment.setTransactionAmount(monto);
        // Más configuraciones del pago...
        try {
            payment.save();
        } catch (MPException e) {
            e.printStackTrace();
        }
        return payment;
    }
}
 */