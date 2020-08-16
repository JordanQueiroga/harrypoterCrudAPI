/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.externalApi.potterapi;

import com.harrypoter.crudAPI.externalApi.potterapi.dto.input.HousePotterApiInputDto;

/**
 *
 * @author JORDAN QUEIROGA
 */

public interface PortterApiRepository {

    HousePotterApiInputDto findHouseById(String houseId);

}
