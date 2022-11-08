package com.alkemy.wallet.mapper;

import com.alkemy.wallet.dto.FixedTermDepositDTO;
import com.alkemy.wallet.model.FixedTermDeposit;

import com.alkemy.wallet.repository.IFixedTermDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FixedTermDepositMapper {

    @Autowired

    private IFixedTermDepositRepository fixedTermDepositRepository;

    // Mapper DTO to Entity
    // This mapper is used when a new Fixed-Term Deposit is created
    public FixedTermDeposit fixedTermDepositDTO2Entity(FixedTermDepositDTO dto){
        FixedTermDeposit entity = new FixedTermDeposit();
        entity.setAmount(dto.getAmount());
        entity.setUserId(dto.getUserId());
        entity.setAccountId(dto.getAccountId());
        entity.setInterest(dto.getInterest());
        entity.setCreationDate(string2Timestamp(dto.getCreationDate()));
        entity.setClosingDate(string2Timestamp(dto.getClosingDate()));
        return entity;
    }

    // Mapper Entity to DTO
    // This mapper is used when the user wants to get a Fixed-Term Deposit
    public FixedTermDepositDTO fixedTermDepositEntity2DTO(FixedTermDeposit entity){
        FixedTermDepositDTO dto = new FixedTermDepositDTO();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setUserId(entity.getUserId());
        dto.setAccountId(entity.getAccountId());
        dto.setInterest(entity.getInterest());
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setClosingDate(entity.getClosingDate().toString());
        return dto;
    }

    // Method to convert a String to Timestamp format
    private Timestamp string2Timestamp(String stringDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(stringDate);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return timestamp;
        } catch(Exception e) {
            //TODO: RECORDAR CAPTURAR ESTE EXCEPTION!!!!
        }
        return null;
    }
}