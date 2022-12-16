package br.com.elizeustachuka.cliente.http.controller;

import br.com.elizeustachuka.cliente.entity.Cliente;
import br.com.elizeustachuka.cliente.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private ClienteService clienteService;

    @BeforeEach
    void printApplicationContext() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    void shouldReturnClienteWithOK() throws Exception {
//        when(clienteService.salvar(eq(1L), eq("Unit Test"), eq("11111111111"))).thenReturn(expectedCliente);
//        var request = mock(ClienteService.class);
//        Cliente expectedCliente = Cliente.builder().id(1L).nome("Unit Test").cpf("11111111111").build();

    }
}
