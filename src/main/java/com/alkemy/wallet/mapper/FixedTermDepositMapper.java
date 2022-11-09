package com.alkemy.wallet.mapper;

import com.alkemy.wallet.dto.FixedTermDepositDto;
import com.alkemy.wallet.model.FixedTermDeposit;
import com.alkemy.wallet.repository.FixedTermDepositRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FixedTermDepositMapper {

    @Autowired
    private FixedTermDepositRepository fixedTermDepositRepository;
    @Autowired
    private ModelMapper modelMapper;

    // Mapper DTO to Entity
    // This mapper is used when a new Fixed-Term Deposit is created
    public FixedTermDeposit fixedTermDepositDTO2Entity(FixedTermDepositDto dto, long accountId, long userId){
        FixedTermDeposit entity = new FixedTermDeposit();
        entity.setAmount(dto.getAmount());
        entity.setUserId(userId);
        entity.setAccountId(accountId);
        entity.setClosingDate(string2Timestamp(dto.getClosingDate()));

        // TODO: WITH MODEL MAPPER
        // entity = modelMapper.map(dto, FixedTermDeposit.class);
        return entity;
    }

    // Mapper Entity to DTO
    // This mapper is used when the user wants to get a Fixed-Term Deposit
    public FixedTermDepositDto fixedTermDepositEntity2DTO(FixedTermDeposit entity){
        FixedTermDepositDto dto = modelMapper.map(entity, FixedTermDepositDto.class);


        //TODO: SIN MODEL MAPPER
        /*dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setUserEntityId(entity.getUserEntityId());
        dto.setAccountId(entity.getAccountId());
        dto.setInterest(entity.getInterest());
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setClosingDate(entity.getClosingDate().toString());*/
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