package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.dto.TransactionPaymentDto;
import com.alkemy.wallet.mapper.TransactionAssembler;
import com.alkemy.wallet.mapper.TransactionMapper;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.security.util.JwtUtils;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.ITransactionService;
import com.alkemy.wallet.service.IUserService;
import com.alkemy.wallet.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping
public class  TransactionController {


    @Autowired
    private ITransactionService service;
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private TransactionAssembler transactionAssembler;
    @Autowired
    private PagedResourcesAssembler<Transaction> pagedResourcesAssembler;
    @Autowired
    private IAccountService accountService;



    @GetMapping("/transactions/{userId}")
    public List<TransactionDto> listTransactions(@PathVariable("userId") long id) {
        return service.listUserId(id);
    }

    @GetMapping("/transactions/:id{id}")
    public TransactionDto transactionDetail(@PathVariable("id") long id) {
        String principal = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        TransactionDto transaction = new TransactionDto();
        long idUser = 0;
        for (int i = 0; i < service.listUser().size(); i++) {
            if (service.listUser().get(i).getEmail().toString().equals(principal)) {
                idUser = service.listUser().get(i).getId();
            }
        }
        List<TransactionDto> listTransactions = service.listUserId(idUser);
        for (int j = 0; j < listTransactions.size(); j++) {
            if (listTransactions.get(j).getId() == id) {
                transaction = listTransactions.get(j);
            }
        }
        if (transaction != null) {

            return transaction;
        } else throw new RuntimeException();

    }

    @PatchMapping("/transactions/:id{id}")
    public TransactionDto editTransaction(@PathVariable("id") long id, @RequestBody String description) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getName();
        TransactionDto transaction = service.listDetail(id);
        long idUser = 0;
        for (int i = 0; i < service.listUser().size(); i++) {
            if (service.listUser().get(i).getEmail().equals(principal)) {
                idUser = service.listUser().get(i).getId();
            }
        }
        List<TransactionDto> listTransactions = service.listUserId(idUser);
        if (listTransactions.contains(transaction)) {
            transaction.setDescription(description);
            return service.edit(transaction);
        } else throw new RuntimeException();

    }



    /**
     * Inserts a new payment on table TRANSACTIONS into the database.
     * @param dto
     * @return ResponseEntity
     * @throws Exception
     */
    @PostMapping
    @RequestMapping("transactions/payment")
    public ResponseEntity<Object> payment(@RequestBody TransactionPaymentDto dto, @RequestHeader(name="Authorization") String token) throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        //String email = jwtUtils.extractUsername(token);
        Transaction transaction = new TransactionMapper().dtoToEntity(dto);
        transaction.setUser(userService.findByEmail(email));
        transaction.setAccount(accountService.findById(dto.getAccountId()).get());
        TransactionPaymentDto result = new TransactionMapper().entityToDto(service.savePayment(transaction));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping
    @RequestMapping("transactions/deposit")
    public ResponseEntity<Object> deposit(@RequestBody TransactionPaymentDto dto, @RequestHeader(name="Authorization") String token) throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        //String email = jwtUtils.extractUsername(token);
        Transaction transaction = new TransactionMapper().dtoToEntity(dto);
        transaction.setUser(userService.findByEmail(email));
        transaction.setAccount(accountService.findById(dto.getAccountId()).get());
        TransactionPaymentDto result = new TransactionMapper().entityToDto(service.saveDeposit(transaction));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("transactions/sendusd")
    @Transactional
    public ResponseEntity<?> sendUsd(@RequestParam long accountFromId,
                                     @RequestParam long userId,
                                     @RequestParam double amount,
                                     @RequestParam long accountToId,
                                     @RequestParam Type type) throws Exception {

        return ResponseEntity.ok().body(service.sendUsd(accountFromId, userId, amount, accountToId, type));
    }

    @GetMapping("/transactions/paged/{userId}")
    public ResponseEntity<PagedModel<TransactionDto>> getAll(@PathVariable("userId") Long id, @RequestParam(required = false) Integer pageQuery){
        try{
            PageRequest pageRequest = PageRequest.of(0,10);
            if(pageQuery != null) {
                pageRequest = PageRequest.of(pageQuery, 10);
            }
            Page<Transaction> transactions = service.findByUser(id, pageRequest);
            PagedModel<TransactionDto> transactionDtos = pagedResourcesAssembler.toModel(transactions, transactionAssembler);
            if(pageRequest.hasPrevious())
                transactionDtos.add(linkTo(methodOn(AccountController.class).getAll(pageRequest.getPageNumber()-1)).withSelfRel());
            if (pageRequest.getPageNumber() < transactions.getTotalPages()-1) {
                transactionDtos.add(linkTo(methodOn(AccountController.class).getAll(pageRequest.getPageNumber()+1)).withSelfRel());
            }

            return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
