package vti.dtn.account_service.services;

import vti.dtn.account_service.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getListAccounts();
}
