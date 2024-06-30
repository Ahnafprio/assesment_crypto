package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class MainActivity extends AppCompatActivity {

    private EditText addressInput;
    private Button fetchBalanceButton;
    private Button scanQRButton;
    private TextView balanceTextView;
    private TextView nonceTextView;
    private Web3j web3j;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        addressInput = findViewById(R.id.addressInput);
        fetchBalanceButton = findViewById(R.id.fetchBalanceButton);
        scanQRButton = findViewById(R.id.scanQRButton);
        balanceTextView = findViewById(R.id.balanceTextView);
        nonceTextView = findViewById(R.id.nonceTextView);

        // Initialize Web3j instance
        web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/51c5592de44b4de7b504f15ea1b73450"));

        fetchBalanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = addressInput.getText().toString().trim();
                if (!address.isEmpty()) {
                    fetchBalanceAndNonce(address);
                }
            }
        });

        scanQRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQRScanner();
            }
        });
    }

    private void startQRScanner() {
        IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
        integrator.setPrompt("Scan Ethereum Address QR Code");
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            String scannedAddress = result.getContents();
            if (scannedAddress != null && !scannedAddress.isEmpty()) {
                addressInput.setText(scannedAddress);
            }
        }
    }

    private void fetchBalanceAndNonce(String address) {
        EthereumService.fetchBalanceAndNonce(web3j, address, new EthereumService.Callback() {
            @Override
            public void onSuccess(String balance, String nonce) {
                updateUI(balance, nonce);
            }

            @Override
            public void onFailure(String errorMessage) {
                // Handle error
            }
        });
    }

    private void updateUI(String balance, String nonce) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                balanceTextView.setText("Balance: " + balance);
                nonceTextView.setText("Nonce: " + nonce);
            }
   });
}

}