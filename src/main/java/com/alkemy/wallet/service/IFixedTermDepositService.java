package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.basicDTO.FixedTermDepositDtoSimulation;
import com.alkemy.wallet.model.FixedTermDeposit;
import com.alkemy.wallet.dto.FixedTermDepositDto;

import java.util.List;

public interface IFixedTermDepositService {

    FixedTermDepositDto save(FixedTermDepositDto fixedTermDepositDto, String userName) throws Exception;

    String creditFixedTermDeposit(long fixedTermDepositId, String userName) throws Exception;

    public FixedTermDepositDtoSimulation simulateFixedTermDeposit(FixedTermDepositDtoSimulation dto) throws Exception;

    List<FixedTermDepositDto> getAll();

    List<FixedTermDeposit> findAll();

    FixedTermDepositDto getById(long id);

}
