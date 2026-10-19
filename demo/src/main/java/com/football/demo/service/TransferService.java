package com.football.demo.service;


import com.football.demo.dto.TransferDto;
import com.football.demo.entity.Transfer;
import com.football.demo.repository.TransferRepository;
import com.football.demo.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {
    @Autowired
    private TransferRepository transferRepository;

    public List<TransferDto> getMostValuableTransferByYear(int year, int size){
        Pageable pageable = PageRequest.of(0, size);
        List<Transfer> transfers = transferRepository.findMostValuableTransfersByYear(String.valueOf(year), pageable);
        return transfers.stream()
                .map(DtoConverter::convertTransferToDto)
                .collect(Collectors.toList());
    }




}

