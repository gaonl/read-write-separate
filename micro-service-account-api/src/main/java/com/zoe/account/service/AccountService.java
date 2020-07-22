package com.zoe.account.service;

import com.zoe.account.domain.Account;

public interface AccountService {
    Account get(Integer userId);

    Account getMaster(Integer userId);

    Account getSlave(Integer userId);

    Account getReadOnly(Integer userId);
}
