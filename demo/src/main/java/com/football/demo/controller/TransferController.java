package com.football.demo.controller;


import com.football.demo.dto.TransferDto;
import com.football.demo.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    private TransferService transferService;

    @GetMapping("/most-valuable")
    public List<TransferDto> getMostValuableTransferByYear(@RequestParam int year,@RequestParam int size){
        return transferService.getMostValuableTransferByYear(year,size);
    }


}
