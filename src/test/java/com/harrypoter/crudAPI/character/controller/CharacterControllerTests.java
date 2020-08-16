/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.character.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harrypoter.crudAPI.character.dto.input.CharacterCreateUpdateInputDto;
import com.harrypoter.crudAPI.character.entity.Character;
import com.harrypoter.crudAPI.character.repository.CharacterRepository;
import java.net.URI;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriTemplate;
import static org.hamcrest.CoreMatchers.containsString;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.http.MediaType;

/**
 *
 * @author JORDAN QUEIROGA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class CharacterControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CharacterRepository characterRepository;

    private static final String ENDPOINT = "/characters";

    private Long characterId;

    @BeforeEach
    public void onSetUp() {
        Character persist = characterRepository.save(new Character("name1", "role", "school", "house2", "patronus"));
        characterRepository.save(new Character("Jordan Pereira de Queiro", "dev", "school", "house2", "Spring"));
        characterRepository.save(new Character("Jordan 2", "dev", "school", "A-house", "Spring"));
        this.characterId = persist.getId();
    }

    @Test
    public void shouldProcessSuccessfullyShowCharacterRequest() throws Exception {
        URI uri = new UriTemplate(ENDPOINT + "/{characterId}").expand(this.characterId);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(uri).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("name1")))
                .andExpect(MockMvcResultMatchers.content().string(containsString("role")));
    }

    @Test
    public void shouldProcessNotFoundShowCharacterRequest() throws Exception {
        URI uri = new UriTemplate(ENDPOINT + "/{characterId}").expand(0);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(uri).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldProcessSuccessfullyIndexAllCharacterRequest() throws Exception {
        URI uri = new UriTemplate(ENDPOINT).expand();
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(uri).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.content().string(containsString("Jordan Pereira de Queiro")));

    }

    @Test
    public void shouldProcessSuccessfullyIndexFilterCharacterRequest() throws Exception {
        URI uri = new UriTemplate(ENDPOINT).expand();
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(uri)
                .param("house", "hou")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.content().string(containsString("Jordan Pereira de Queiro")))
                .andExpect(MockMvcResultMatchers.content().string(containsString("name1")));

    }

    @Test
    public void shouldProcessSuccessfullyCreateCharacterRequest() throws Exception {
        URI uri = new UriTemplate(ENDPOINT).expand();
        CharacterCreateUpdateInputDto inputDto = new CharacterCreateUpdateInputDto("Jordan", "Role", "School", "5a05e2b252f721a3cf2ea33f", "Patronus");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(inputDto));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(containsString(inputDto.getHouse())));
    }

    @Test
    public void shouldProcessErrorCreateCharacterRequest() throws Exception {
        URI uri = new UriTemplate(ENDPOINT).expand();
        CharacterCreateUpdateInputDto inputDto = new CharacterCreateUpdateInputDto("Jordan", "Role", "School", "5a05e2b252f721a3cf2ea33f", "Patronus");
        inputDto.setName(null);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(inputDto));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldProcessSuccessfullyUpdateCharacterRequest() throws Exception {
        URI uri = new UriTemplate(ENDPOINT + "/{characterId}").expand(this.characterId);
        CharacterCreateUpdateInputDto inputDto = new CharacterCreateUpdateInputDto("Jordan", "Role", "School", "5a05e2b252f721a3cf2ea33f", "Patronus");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(inputDto));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(inputDto.getHouse())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(inputDto.getName())));
    }

    @Test
    public void shouldProcessErrorNotFoundUpdateCharacterRequest() throws Exception {
        URI uri = new UriTemplate(ENDPOINT + "/{characterId}").expand(0);
        CharacterCreateUpdateInputDto inputDto = new CharacterCreateUpdateInputDto("Jordan", "Role", "School", "5a05e2b252f721a3cf2ea33f", "Patronus");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(inputDto));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldProcessErrorUpdateCharacterRequest() throws Exception {
        URI uri = new UriTemplate(ENDPOINT + "/{id}").expand(this.characterId);
        CharacterCreateUpdateInputDto inputDto = new CharacterCreateUpdateInputDto(null, "Role", "School", "5a05e2b252f721a3cf2ea33f", "Patronus");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(inputDto));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldProcessSuccessfullyDeleteCharacterRequest() throws Exception {
        URI uri = new UriTemplate(ENDPOINT + "/{characterId}").expand(this.characterId);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(uri).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void shouldProcessErrorNotFoundDeleteCharacterRequest() throws Exception {
        URI uri = new UriTemplate(ENDPOINT + "/{characterId}").expand(0);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(uri).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
