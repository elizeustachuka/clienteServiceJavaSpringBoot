package br.com.elizeustachuka.cliente.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> findEnderecoByCEP(String cep) {

        String viaCEPUrl = "https://viacep.com.br/ws/" + cep + "/json/";
        ResponseEntity<String> response;

        try {
            response = restTemplate.getForEntity(viaCEPUrl, String.class);
        } catch (HttpStatusCodeException ex) {
            response = new ResponseEntity(ex.getResponseBodyAsString(), ex.getResponseHeaders(), ex.getStatusCode());
        }
        return response;
    }
}