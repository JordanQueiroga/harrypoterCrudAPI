/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.character.repository;

import com.harrypoter.crudAPI.character.entity.Character;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class CharactersRepositoryTestsSetup {

    private TestEntityManager testEntityManager;

    public CharactersRepositoryTestsSetup(TestEntityManager testEntityManager) {
        this.testEntityManager = testEntityManager;
    }

    public void createCharactersSetup() {

        testEntityManager.persist(new Character("name1", "rome", "school", "house", "patronus"));
        testEntityManager.persist(new Character("name2", "rome", "school", "house", "patronus"));
        testEntityManager.persist(new Character("name3", "rome", "school", "house2", "patronus"));
        testEntityManager.persist(new Character("name4", "rome", "school", "house2", "patronus"));

    }

}
