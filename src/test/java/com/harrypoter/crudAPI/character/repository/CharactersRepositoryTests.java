/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.character.repository;

import com.harrypoter.crudAPI.character.entity.Character;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author JORDAN QUEIROGA
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CharactersRepositoryTests {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void saveCharacter() {
        Character character = new Character("name", "rome", "school", "house", "patronus");
        Character saveCharacter = characterRepository.save(character);

        Character findCharacter = testEntityManager.find(Character.class, saveCharacter.getId());

        Assertions.assertThat(findCharacter).isNotNull();
        Assertions.assertThat(findCharacter.getName()).isEqualTo(saveCharacter.getName());
        Assertions.assertThat(findCharacter.getHouse()).isEqualTo(saveCharacter.getHouse());
        Assertions.assertThat(findCharacter.getPatronus()).isEqualTo(saveCharacter.getPatronus());
        Assertions.assertThat(findCharacter.getSchool()).isEqualTo(saveCharacter.getSchool());
        Assertions.assertThat(findCharacter.getPatronus()).isEqualTo(saveCharacter.getPatronus());
    }

    @Test
    public void findByHomeLikeCharacter() {
        CharactersRepositoryTestsSetup charactersRepositoryTestsSetup = new CharactersRepositoryTestsSetup(testEntityManager);
        charactersRepositoryTestsSetup.createCharactersSetup();

        List<Character> listCharacters = characterRepository.findByHouseLike("house");

        Assertions.assertThat(listCharacters).isNotEmpty();
        Assertions.assertThat(listCharacters).hasSize(2);
    }

    @Test
    public void findByHomeLikeCharacterAll() {
        CharactersRepositoryTestsSetup charactersRepositoryTestsSetup = new CharactersRepositoryTestsSetup(testEntityManager);
        charactersRepositoryTestsSetup.createCharactersSetup();

        List<Character> listCharacters = characterRepository.findByHouseLike("%");

        Assertions.assertThat(listCharacters).isNotEmpty();
        Assertions.assertThat(listCharacters).hasSize(4);
    }

}
