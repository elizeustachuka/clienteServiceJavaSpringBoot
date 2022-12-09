package br.com.elizeustachuka.cliente.repository;

import br.com.elizeustachuka.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
