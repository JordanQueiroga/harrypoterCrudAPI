/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.character.dto.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class CharacterCreateUpdateInputDto {

    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Role cannot be empty")
    private String role;
    @NotEmpty(message = "School cannot be empty")
    private String school;
    @Size(min = 24, max = 24, message = "House invalid")
    private String house;
    @NotEmpty(message = "Patronus cannot be empty")
    private String patronus;

    public CharacterCreateUpdateInputDto() {
    }

    public CharacterCreateUpdateInputDto(String name, String role, String school, String house, String patronus) {
        this.name = name;
        this.role = role;
        this.school = school;
        this.house = house;
        this.patronus = patronus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getPatronus() {
        return patronus;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

}
