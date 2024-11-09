package com.banking.bo;


import com.banking.dao.AccountRepository;
import com.banking.dao.CustomerRepository;
import com.banking.dao.TransactionRepository;
import com.banking.dto.TransactionDTO;
import com.banking.dto.TransactionRequestDTO;
import com.banking.dto.TransferDTO;
import com.banking.entity.Account;
import com.banking.entity.Customer;
import com.banking.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TransactionBO {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;
    public void doTransaction(TransactionRequestDTO transactionRequestDTO) {

        Optional<Account> byAccountNumber = accountRepository.findByAccountNumber(transactionRequestDTO.getAccountNumber());


        if (byAccountNumber.isPresent() ){



            TransactionDTO transactionRequest = transactionRequestDTO.getTransaction();


            Account existingAccount = byAccountNumber.get();

            Transaction newTransaction  =new Transaction();


 // deposit logic

            if (transactionRequest.getTransactionType()==Transaction.TransactionType.DEPOSIT){


                BigDecimal currentBalance = existingAccount.getBalance();

                // Get the transaction amount
                BigDecimal transactionAmount = transactionRequest.getAmount();

                // Add the transaction amount to the current balance

                BigDecimal updatedBalance = currentBalance.add(transactionAmount);


                existingAccount.setBalance(updatedBalance);


                newTransaction.setBalance(updatedBalance);
                newTransaction.setAmount(transactionRequest.getAmount());
                newTransaction.setAccount(existingAccount);
                newTransaction.setTransactionType(Transaction.TransactionType.DEPOSIT);

                existingAccount.getTransactions().add(newTransaction);



                transactionRepository.save(newTransaction);


            }  else {

                // handle when it is for withdrawal
            }


        }




    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void amountTransfer(TransferDTO transferDTO) {

        Optional<Account> fromAccountNumber = accountRepository.findByAccountNumber(transferDTO.getFromAccount());
        Optional<Account> toAccountNumber = accountRepository.findByAccountNumber(transferDTO.getToAccount());

        if (fromAccountNumber.isPresent()&&toAccountNumber.isPresent()) {


            TransactionDTO transactionRequest = transferDTO.getTransaction();


            if (transactionRequest.getTransactionType() == Transaction.TransactionType.TRANSFER){


                Account Sender = fromAccountNumber.get();


                BigDecimal transactionAmount = transactionRequest.getAmount();

                BigDecimal fromAccountcurrentBalance = Sender.getBalance();

                // Check if the transaction amount is greater than the current balance

                if (transactionAmount.compareTo(fromAccountcurrentBalance) > 0) {

                    // Handle insufficient funds scenario

                    System.out.println("Insufficient funds for this transaction.");

                    // Additional logic here (e.g., throw an exception, return an error response, etc.)
                } else {

                    // Proceed with the transaction

                    // Additional logic here

                    Account Receiver = toAccountNumber.get();

                    BigDecimal toAccountCurrentBalance = Receiver.getBalance();


                    // Add the transaction amount to the current balance

                   //amount  transferred

                    BigDecimal toAccountUpdatedBalance = toAccountCurrentBalance.add(transactionAmount);



                    Receiver.setBalance(toAccountUpdatedBalance);
                    accountRepository.save(Receiver);

                    // update the existing from account balance
        BigDecimal fromAccountUpdatedBalance     =  fromAccountcurrentBalance.subtract(transactionAmount);


 //balance updated  of fromAccount


                    Sender.setBalance(fromAccountUpdatedBalance);





                   accountRepository.save(Sender);



                   Transaction senderTransaction = new Transaction();


                    senderTransaction.setBalance(fromAccountUpdatedBalance);

                    senderTransaction.setAmount(transactionAmount.negate());
                    senderTransaction.setTransactionType(Transaction.TransactionType.TRANSFER);

                    senderTransaction.setAccount(Sender);

                    Sender.getTransactions().add(senderTransaction);

                    transactionRepository.save(senderTransaction);




                    Transaction recieverTransaction = new Transaction();

                    recieverTransaction.setBalance(toAccountUpdatedBalance);

                    recieverTransaction.setAmount(transactionAmount.abs());
                    recieverTransaction.setTransactionType(Transaction.TransactionType.TRANSFER);

                    recieverTransaction.setAccount(Receiver);

                    Receiver.getTransactions().add(senderTransaction);

                    transactionRepository.save(recieverTransaction);






                }



            }

        }

    }
}
