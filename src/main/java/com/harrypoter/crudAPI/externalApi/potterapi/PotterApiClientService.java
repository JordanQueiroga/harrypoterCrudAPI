/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.externalApi.potterapi;

import com.harrypoter.crudAPI.exceptionConfig.BadRequesException;
import com.harrypoter.crudAPI.externalApi.potterapi.dto.input.HousePotterApiInputDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service
public class PotterApiClientService implements PortterApiRepository {

    @Value("${potterapi.key}")
    private String potterApiKey;
    @Value("${potterapi.baseurl}")
    private String BASE_URL_POTTERAPI;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public HousePotterApiInputDto findHouseById(String houseId) {
        ResponseEntity<List<HousePotterApiInputDto>> exchange;
        try {
            exchange = restTemplate.exchange(BASE_URL_POTTERAPI + "/houses/{houseId}?key={potterApiKey}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<HousePotterApiInputDto>>() {
            }, houseId, potterApiKey);
        } catch (Exception ex) {
            throw new BadRequesException("Error when request house in potterapi");
        }

        if (exchange.getBody().isEmpty()) {
            return null;
        }

        return exchange.getBody().get(0);
    }

    //Desativa verificação SSL
    @Bean
    private RestTemplate restTemplate() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        return restTemplate;
    }

}
