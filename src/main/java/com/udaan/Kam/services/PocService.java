package com.udaan.Kam.services;

import com.udaan.Kam.dto.PocDto;
import com.udaan.Kam.entity.POC;

import java.util.List;

public interface PocService {
    void registerPoc(PocDto pocDto);

    List<PocDto> getPocs(Long restaurantId);
}
