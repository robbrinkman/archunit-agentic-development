package com.jdriven.example.order;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

// Violates rule 5: does its own cryptography outside the security package
public class OrderSigner {

    public byte[] sign(byte[] order, byte[] key) throws GeneralSecurityException {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(key, "HmacSHA256"));
        return mac.doFinal(order);
    }
}
