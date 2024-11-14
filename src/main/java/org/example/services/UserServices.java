package org.example.services;

import org.example.entity.User;
import org.example.entity.Wallet;
import org.example.exeption.WalletException;
import org.example.repository.UserRepository;
import org.example.repository.WalletRepository;

import java.sql.SQLException;

public class UserServices {

    WalletRepository walletRepository;
    UserRepository userRepository;

    public UserServices() throws SQLException {
        walletRepository = new WalletRepository();
        userRepository = new UserRepository();
    }

    public User userSignUp(String username, String password) throws SQLException, WalletException {
        var checkingUser = userRepository.findByUsername(username);
        if (checkingUser != null) {
            throw new WalletException("Username is already taken!");
            //  System.out.println("Username is already taken!");
            //  return null;
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
    public void deleteUser(int id) {
        int idUser = AuthenticationServices.getLoggedInUser().getUserId();
        try {
            if (id == idUser) {
                userRepository.deleteById(id);
            } else {
                throw new WalletException("User is not logged in!");
            }

        }catch (SQLException e) {
            System.out.println("+++"+e.getMessage());
            e.printStackTrace();
        }

    }
}
