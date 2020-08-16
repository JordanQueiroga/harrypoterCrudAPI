/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.character.service;

import com.harrypoter.crudAPI.exceptionConfig.NotFoundException;
import com.harrypoter.crudAPI.character.entity.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.harrypoter.crudAPI.character.repository.CharacterRepository;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service
public class CharacterDeleteService {
    @Autowired
    private CharacterRepository personageRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public void execute(Long idPersonage) {
        Character personage = personageRepository.findById(idPersonage).orElseThrow(() -> new NotFoundException("Personage not found"));
        personageRepository.delete(personage);
    }
}
