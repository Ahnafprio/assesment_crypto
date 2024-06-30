package com.example.myapplication.utils;

import android.app.Activity;
import android.content.Context;

import com.google.zxing.integration.android.IntentIntegrator;

public class QRCodeUtils {

    public static void startQRScanner(Context context) {
        IntentIntegrator integrator = new IntentIntegrator((Activity) context);
        integrator.setPrompt("Scan Ethereum Address QR Code");
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }
}



