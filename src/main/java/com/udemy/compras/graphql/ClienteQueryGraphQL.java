package com.udemy.compras.graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udemy.compras.domain.entity.Cliente;
import com.udemy.compras.domain.vo.ClienteInput;
import com.udemy.compras.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ClienteQueryGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cliente(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public List<Cliente> clientes() {
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente saveCliente(ClienteInput clienteInput) {
        ModelMapper modelMapper = new ModelMapper();
        return clienteRepository.save(modelMapper.map(clienteInput, Cliente.class));
    }

    @Transactional
    public Boolean deleteCliente(Long id) {
        if (clienteRepository.findById(id).isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        }

        return false;
    }

}
