/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.character.repository;

import com.harrypoter.crudAPI.character.entity.Character;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    List<Character> findByHouseLike(String homeId);
    
}
