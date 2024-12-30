package com.udaan.Kam.services.implementation;

import com.udaan.Kam.entity.POC;
import com.udaan.Kam.repository.PocRepository;
import com.udaan.Kam.services.PocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PocServiceImpl implements PocService {

    @Autowired PocRepository pocRepository;
    @Override
    public void registerPoc(POC poc) { // we can use Dto instead of direct using of entity which should not directly access to clients
    pocRepository.save(poc);
    }
}
