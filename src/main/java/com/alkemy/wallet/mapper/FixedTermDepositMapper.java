package com.alkemy.wallet.mapper;

import com.alkemy.wallet.dto.FixedTermDepositDto;
import com.alkemy.wallet.model.FixedTermDeposit;
import com.alkemy.wallet.repository.IFixedTermDepositRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FixedTermDepositMapper {

    @Autowired
    private IFixedTermDepositRepository fixedTermDepositRepository;

    // Mapper DTO to Entity
    // This mapper is used when a new Fixed-Term Deposit is created
    public FixedTermDeposit fixedTermDepositDTO2Entity(FixedTermDepositDto dto, long accountId, long userId){
        FixedTermDeposit entity = new FixedTermDeposit();
        entity.setAmount(dto.getAmount());
        entity.setUserId(userId);
        entity.setAccountId(accountId);
        entity.setClosingDate(new Timestamp(dto.getClosingDate().getTime()));
        return entity;
    }

    // Mapper Entity to DTO
    // This mapper is used when the user wants to get a Fixed-Term Deposit
    public FixedTermDepositDto fixedTermDepositEntity2DTO(FixedTermDeposit entity){
        FixedTermDepositDto dto = new FixedTermDepositDto();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setUserId(entity.getUserId());
        dto.setAccountId(entity.getAccountId());
        dto.setCreationDate(entity.getCreationDate());
        dto.setClosingDate(entity.getClosingDate());
        return dto;
    }

    public List<FixedTermDepositDto> fixedTermDepositEntityList2DTOList(List<FixedTermDeposit> entities) {
        List<FixedTermDepositDto> dtos = new ArrayList<>();
        for (FixedTermDeposit entity : entities) {
            dtos.add(this.fixedTermDepositEntity2DTO(entity));
        }
        return dtos;
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