package br.com.elizeustachuka.cliente.http.controller;

import br.com.elizeustachuka.cliente.entity.Cliente;
import br.com.elizeustachuka.cliente.entity.Endereco;
import br.com.elizeustachuka.cliente.exceptions.ResourceIncorrectFormatException;
import br.com.elizeustachuka.cliente.exceptions.ResourceNotFoundException;
import br.com.elizeustachuka.cliente.service.ClienteService;
import br.com.elizeustachuka.cliente.utils.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static br.com.elizeustachuka.cliente.gateway.ViaCepGateway.getEndereco;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

        @Autowired
        private ClienteService clienteService;

        @Autowired
        private ModelMapper modelMapper;

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Cliente salvar(@RequestBody Cliente cliente) throws Exception {

            String cep = cliente.getCep();

            if (cep == null) throw new ResourceIncorrectFormatException("The field 'cep' is required");
            if (!Util.validaCep(cep)) throw new ResourceNotFoundException("The CEP is invalid or format is incorrect");

            Endereco endereco = getEndereco(cliente.getCep());

            cliente.setRua(endereco.getLogradouro());
            cliente.setBairro(endereco.getBairro());
            cliente.setCidade(endereco.getLocalidade());
            cliente.setEstado(endereco.getUf());

            return clienteService.salvar(cliente);
        }

        @GetMapping
        @ResponseStatus(HttpStatus.OK)
        public List<Cliente> listaCliente(){
            return clienteService.listaCliente();
        }

        @GetMapping("/{id}")
        @ResponseStatus(HttpStatus.OK)
        public Cliente buscarClientePorId(@PathVariable("id") Long id){
            return clienteService.buscarPorId(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado."));
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void removerCliente(@PathVariable("id") Long id){
            clienteService.buscarPorId(id)
                    .map(cliente -> {
                        clienteService.removerPorId(cliente.getId());
                        return Void.TYPE;
                    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado."));
        }

        @PutMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente){
            clienteService.buscarPorId(id)
                    .map(clienteBase -> {
                        modelMapper.map(cliente, clienteBase);
                        clienteService.salvar(clienteBase);
                        return Void.TYPE;
                    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado."));
        }
}