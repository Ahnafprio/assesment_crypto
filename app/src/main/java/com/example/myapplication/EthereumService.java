package com.example.myapplication;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public class EthereumService {

    public interface Callback {
        void onSuccess(String balance, String nonce);
        void onFailure(String errorMessage);
    }

    public static void fetchBalanceAndNonce(Web3j web3j, String address, Callback callback) {
        CompletableFuture.runAsync(() -> {
            try {
                // Fetch balance
                BigInteger balance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().getBalance();
                String balanceEth = Convert.fromWei(new BigDecimal(balance), Convert.Unit.ETHER).toPlainString() + " ETH";

                // Fetch nonce
                BigInteger nonce = web3j.ethGetTransactionCount(address, DefaultBlockParameterName.LATEST).send().getTransactionCount();
                String nonceValue = nonce.toString();

                // Callback success
                callback.onSuccess(balanceEth, nonceValue);

            } catch (Exception e) {
                // Callback failure
                callback.onFailure(e.getMessage());
            }
   });
}


}
