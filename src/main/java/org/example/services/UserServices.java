package org.example.services;

import org.example.entity.User;
import org.example.entity.Wallet;
import org.example.repository.UserRepository;
import org.example.repository.WalletRepository;

import java.sql.SQLException;

public class UserServices {
    UserRepository userRepository;
    WalletRepository walletRepository;
    public UserServices() throws SQLException {
        userRepository = new UserRepository();
        walletRepository = new WalletRepository();
    }

    public User userSignUp(String username, String password) throws SQLException {
        var checkingUser = userRepository.findByUsername(username);

        if (checkingUser != null) {
            System.out.println("Username is already taken!");
            return null;
        }
        User signingUpUser = new User();
        Wallet wallet = new Wallet();
        wallet.setBalance(0);
        signingUpUser.setUsername(username);
        signingUpUser.setPassword(password);
        walletRepository.save(wallet);
        signingUpUser.setWalletId(wallet.getWalletId());
        walletRepository.updateWallet(wallet);
        return userRepository.save(signingUpUser);
    }

    public boolean userLogin(String username, String password) throws SQLException {
        var checkingUser = userRepository.findByUsername(username);
        if (checkingUser != null) {
            if (checkingUser.getPassword().equals(password)) {
                AuthenticationServices.setLoggedUser(checkingUser);
                return true;
            }
        }
        return false;
    }
}
