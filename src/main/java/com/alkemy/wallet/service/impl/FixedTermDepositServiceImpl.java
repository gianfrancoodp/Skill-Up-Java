package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.FixedTermDepositDTO;
import com.alkemy.wallet.model.FixedTermDeposit;
import com.alkemy.wallet.repository.IFixedTermDepositRepository;
import com.alkemy.wallet.service.IFixedTermDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FixedTermDepositServiceImpl implements IFixedTermDepositService {

    @Autowired
    private IFixedTermDepositRepository iFixedTermDepositRepository;
    @Override
    public FixedTermDepositDTO save(FixedTermDepositDTO fixedTermDepositDto) {
        return null;
    }

    @Override
    public List<FixedTermDepositDTO> getAll() {
        return null;
    }
    @Override
    public List<FixedTermDeposit> findAll() {
        return iFixedTermDepositRepository.findAll();
    }

    @Override
    public FixedTermDepositDTO getById(long id) {
        return null;
    }

    @Override
    public FixedTermDepositDTO update(long id, FixedTermDepositDTO fixedTermDepositDto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
