package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.TransferRecord;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TransferService {

    private AccountRepository accountRepository;
    private TransferRepository transferRepository;

    public TransferService(AccountRepository accountRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {

        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        fromAccount.debitAccount(amount);
        toAccount.creditAccount(amount);
        transferRepository.save(new TransferRecord(
                fromAccountId, toAccountId, amount, LocalDate.now()
        ));
    }
}
