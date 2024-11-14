package org.example.services;

import org.example.entity.Wallet;
import org.example.repository.WalletRepository;

import java.sql.SQLException;

public class WalletServices {
    WalletRepository walletRepository;

    public WalletServices() throws SQLException {
        walletRepository = new WalletRepository();
    }

    public void deposit(Wallet wallet, double amount) throws SQLException {
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
    }

    public void withdraw(Wallet wallet, double amount) throws SQLException {
        if(wallet.getBalance() > amount){
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);
        }
        else{
            System.out.println("Your Balance is Not Enough!");
        }
    }
}
