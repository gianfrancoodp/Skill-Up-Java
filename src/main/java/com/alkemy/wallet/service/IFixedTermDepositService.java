package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.FixedTermDepositDTO;

import java.util.List;

public interface IFixedTermDepositService {

    FixedTermDepositDTO save(FixedTermDepositDTO fixedTermDepositDto);

    List<FixedTermDepositDTO> getAll();

    FixedTermDepositDTO getById(long id);

    FixedTermDepositDTO update(long id, FixedTermDepositDTO fixedTermDepositDto);

    void delete(long id);

}
