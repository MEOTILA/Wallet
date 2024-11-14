package org.example;

import org.example.entity.User;
import org.example.entity.Wallet;
import org.example.exeption.WalletException;
import org.example.repository.TransactionRepository;
import org.example.repository.UserRepository;
import org.example.repository.WalletRepository;
import org.example.services.AuthenticationServices;
import org.example.services.UserServices;
import org.example.services.WalletServices;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        WalletRepository walletRepository = new WalletRepository();
        TransactionRepository transactionRepository = new TransactionRepository();
        UserServices userServices = new UserServices();
        WalletServices walletServices = new WalletServices();

//
//        try {
//            userServices.userSignUp("amir", "123");
//            userServices.userSignUp("sattar", "345");
//            userServices.userSignUp("amir", "123");
//        } catch (WalletException e) {
//
//            e.getMessage();
//            e.printStackTrace();
//        }

        try {
            userServices.userLogin("sattar", "345");
            userServices.deleteUser(1);
        } catch (SQLException e) {
            System.out.println("**"+ e.getMessage());
            e.printStackTrace();
        }

        //      Wallet wallet1 = new Wallet(0,"999",5.91);
//       Wallet wallet2 = new Wallet ();
//       wallet2.setWalletAddress("1000");
//       wallet2.setBalance(9.66);*/
//       walletRepository.save(wallet1);
//        var user = userRepository.findById(5);
//        System.out.println(user);

//        var wallet = walletRepository.findById(1);
//        System.out.println(wallet);
//
//        walletRepository.deleteById(1);
//         User user1 = new User();
//         user1.setUsername("sattar");
//         user1.setPassword("123");
//         userRepository.save(user1);



        /*var contact = contactRepository.findById(2);
        System.out.println(contact);*/

        // contactRepository.deleteById(10);

        // Delete -> costly, Add -> costly, get -> fast
        //List<Contact> list1 = new ArrayList<Contact>();

        // Pointer, Add, Remove -> Fast, get -> Slow and costly
        //List<Contact> list2 = new LinkedList<Contact>();

        // List<Contact> l3 = new ArrayList<>(list2);
    }
}