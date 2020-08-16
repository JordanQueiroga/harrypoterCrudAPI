/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.character.service;

import com.harrypoter.crudAPI.exceptionConfig.NotFoundException;
import com.harrypoter.crudAPI.character.dto.input.CharacterCreateUpdateInputDto;
import com.harrypoter.crudAPI.character.entity.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.harrypoter.crudAPI.character.repository.CharacterRepository;
import com.harrypoter.crudAPI.externalApi.potterapi.PortterApiRepository;
import com.harrypoter.crudAPI.externalApi.potterapi.dto.input.HousePotterApiInputDto;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service
public class CharacterUpdateService {

    @Autowired
    private CharacterRepository personageRepository;
    @Autowired
    private PortterApiRepository portterApiRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public Character execute(Long idPersonage, CharacterCreateUpdateInputDto inputDto) {
        Character personage = personageRepository.findById(idPersonage).orElseThrow(() -> new NotFoundException("Personage not found"));

        HousePotterApiInputDto housePotter = portterApiRepository.findHouseById(inputDto.getHouse());
        if (housePotter == null) {
            throw new NotFoundException("House not found");
        }
        personage.setName(inputDto.getName());
        personage.setHouse(inputDto.getHouse());
        personage.setPatronus(inputDto.getPatronus());
        personage.setRole(inputDto.getRole());
        personage.setSchool(inputDto.getSchool());
        return personageRepository.save(personage);
    }

}
