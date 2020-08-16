/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.externalApi.potterapi;

import com.harrypoter.crudAPI.externalApi.potterapi.dto.input.HousePotterApiInputDto;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service()
@Primary()
@Profile("test")
public class PotterApiClientServiceTest implements PortterApiRepository {

    private List<HousePotterApiInputDto> listHouse = Arrays.asList(
            new HousePotterApiInputDto("5a05e2b252f721a3cf2ea33f", "Gryffindor"),
            new HousePotterApiInputDto("5a05da69d45bd0a11bd5e06f", "Ravenclaw"),
            new HousePotterApiInputDto("5a05dc8cd45bd0a11bd5e071", "Slytherin"),
            new HousePotterApiInputDto("5a05dc58d45bd0a11bd5e070", "Hufflepuff")
    );

    @Override
    public HousePotterApiInputDto findHouseById(String houseId) {
        HousePotterApiInputDto housePotterApiInputDto = listHouse.stream()
                .filter(house -> house.getId().equals(houseId))
                .findFirst()
                .orElse(null);

        return housePotterApiInputDto;
    }

}
