package org.bmn.trinv.service;

import java.util.List;
import java.util.stream.Collectors;
import org.bmn.trinv.domain.Account;
import org.bmn.trinv.domain.User;
import org.bmn.trinv.model.AccountDTO;
import org.bmn.trinv.repos.AccountRepository;
import org.bmn.trinv.repos.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(final AccountRepository accountRepository,
            final UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public List<AccountDTO> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(account -> mapToDTO(account, new AccountDTO()))
                .collect(Collectors.toList());
    }

    public AccountDTO get(final Long id) {
        return accountRepository.findById(id)
                .map(account -> mapToDTO(account, new AccountDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final AccountDTO accountDTO) {
        final Account account = new Account();
        mapToEntity(accountDTO, account);
        return accountRepository.save(account).getId();
    }

    public void update(final Long id, final AccountDTO accountDTO) {
        final Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(accountDTO, account);
        accountRepository.save(account);
    }

    public void delete(final Long id) {
        accountRepository.deleteById(id);
    }

    private AccountDTO mapToDTO(final Account account, final AccountDTO accountDTO) {
        accountDTO.setId(account.getId());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setAccountUID(account.getAccountUID());
        accountDTO.setUserAccounts(account.getUserAccounts() == null ? null : account.getUserAccounts().getId());
        return accountDTO;
    }

    private Account mapToEntity(final AccountDTO accountDTO, final Account account) {
        account.setBalance(accountDTO.getBalance());
        account.setAccountUID(accountDTO.getAccountUID());
        if (accountDTO.getUserAccounts() != null && (account.getUserAccounts() == null || !account.getUserAccounts().getId().equals(accountDTO.getUserAccounts()))) {
            final User userAccounts = userRepository.findById(accountDTO.getUserAccounts())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "userAccounts not found"));
            account.setUserAccounts(userAccounts);
        }
        return account;
    }

}
