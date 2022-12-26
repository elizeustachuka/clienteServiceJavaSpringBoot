package br.com.elizeustachuka.cliente.service;

import br.com.elizeustachuka.cliente.entity.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CEPService {

    @Autowired
    private RestTemplate template = new RestTemplate();

    public Endereco[] findCEP(String cep){
        String viaCEPUrl = "https://viacep.com.br/ws/"+ cep + "/json/";
        return new Endereco[]{template.getForObject(viaCEPUrl, Endereco.class)};
    }
}