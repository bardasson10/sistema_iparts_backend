package br.com.estoque.iparts.persistence.repository.adapter;

import br.com.estoque.iparts.application.domain.model.User;
import br.com.estoque.iparts.application.ports.out.UserRepositoryPort;
import br.com.estoque.iparts.persistence.entity.UserJpaEntity;
import br.com.estoque.iparts.persistence.mapper.UserPersistenceMapper;
import br.com.estoque.iparts.persistence.repository.jpa.UserSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {


    private final UserSpringDataRepository jpaRepository;
    private  final UserPersistenceMapper mapper;


    public  UserRepositoryAdapter(UserSpringDataRepository jpaRepository, UserPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public User salvar(User user) {
        var userEntity = mapper.toEntity(user);
        var savedUser = jpaRepository.save(userEntity);
        return mapper.toDomain(savedUser);
    }

    @Override
    public boolean existePorEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> buscarPorId(Integer id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<User> buscarTodos() {
        return jpaRepository.findAllWithDepartamento()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
