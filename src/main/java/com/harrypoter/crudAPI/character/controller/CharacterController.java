/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.character.controller;

import com.harrypoter.crudAPI.exceptionConfig.NotFoundException;
import com.harrypoter.crudAPI.character.dto.input.CharacterCreateUpdateInputDto;
import com.harrypoter.crudAPI.character.entity.Character;
import com.harrypoter.crudAPI.character.service.CharacterCreateService;
import com.harrypoter.crudAPI.character.service.CharacterDeleteService;
import com.harrypoter.crudAPI.character.service.CharacterUpdateService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.harrypoter.crudAPI.character.repository.CharacterRepository;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

/**
 *
 * @author JORDAN QUEIROGA
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/characters")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterCreateService characterCreateService;
    @Autowired
    private CharacterUpdateService charactersUpdateService;
    @Autowired
    private CharacterDeleteService charactersDeleteService;

    @Cacheable(value = "character", key = "#id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Character show(@PathVariable Long id) {
        return characterRepository.findById(id).orElseThrow(() -> new NotFoundException("Character not found"));
    }

    @Cacheable(value = "characterIndex", key = "#house")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Character> index(@RequestParam(required = false, defaultValue = "") String house) {
        return characterRepository.findByHouseLike(house + "%");
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Character create(@Valid @RequestBody CharacterCreateUpdateInputDto inputDto) {
        return characterCreateService.execute(inputDto);
    }

    @Caching(evict = {
        @CacheEvict(value = "character", key = "#id")
        ,
    @CacheEvict(value = "characterIndex", allEntries = true)
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Character update(@PathVariable Long id, @Valid @RequestBody CharacterCreateUpdateInputDto inputDto) {
        return charactersUpdateService.execute(id, inputDto);
    }

    @Caching(evict = {
        @CacheEvict(value = "character", key = "#id")
        ,
    @CacheEvict(value = "characterIndex", allEntries = true)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        charactersDeleteService.execute(id);
    }

}
