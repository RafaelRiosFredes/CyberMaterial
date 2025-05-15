package com.farancibia.msvc.clientes.msvc_clientes.repositories;

import com.farancibia.msvc.clientes.msvc_clientes.models.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
