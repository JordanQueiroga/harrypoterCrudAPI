/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.character.service;

import com.harrypoter.crudAPI.character.dto.input.CharacterCreateUpdateInputDto;
import com.harrypoter.crudAPI.character.entity.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.harrypoter.crudAPI.character.repository.CharacterRepository;
import com.harrypoter.crudAPI.exceptionConfig.NotFoundException;
import com.harrypoter.crudAPI.externalApi.potterapi.PortterApiRepository;
import com.harrypoter.crudAPI.externalApi.potterapi.dto.input.HousePotterApiInputDto;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service
public class CharacterCreateService {

    @Autowired
    private CharacterRepository personageRepository;
    @Autowired
    private PortterApiRepository portterApiRepository;

    @Transactional
    public Character execute(CharacterCreateUpdateInputDto inputDto) {
        HousePotterApiInputDto housePotter = portterApiRepository.findHouseById(inputDto.getHouse());

        if (housePotter == null) {
            throw new NotFoundException("House not found");
        }

        Character personage = new Character();
        personage.setName(inputDto.getName());
        personage.setHouse(inputDto.getHouse());
        personage.setPatronus(inputDto.getPatronus());
        personage.setRole(inputDto.getRole());
        personage.setSchool(inputDto.getSchool());
        return personageRepository.save(personage);
    }
}
