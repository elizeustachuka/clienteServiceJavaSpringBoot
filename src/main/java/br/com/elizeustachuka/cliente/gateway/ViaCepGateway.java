package br.com.elizeustachuka.cliente.gateway;

import br.com.elizeustachuka.cliente.entity.Endereco;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ViaCepGateway {

    public static Endereco getEndereco(String cep) throws Exception {

        URL url = new URL("https://viacep.com.br/ws/"+cep+"/json");
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        StringBuilder jsonCep = new StringBuilder();

        String cepToRead = "";

        while ((cepToRead = bufferedReader.readLine()) != null){
            jsonCep.append(cepToRead);
        }

        return new Gson().fromJson(String.valueOf(jsonCep), Endereco.class);
    }

}