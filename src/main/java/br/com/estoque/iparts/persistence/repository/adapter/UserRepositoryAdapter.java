package br.com.estoque.iparts.persistence.repository.adapter;

import br.com.estoque.iparts.application.domain.model.User;
import br.com.estoque.iparts.application.ports.out.UserRepositoryPort;
import br.com.estoque.iparts.persistence.mapper.UserPersistenceMapper;
import br.com.estoque.iparts.persistence.repository.jpa.UserSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    @Autowired
    private UserSpringDataRepository jpaRepository;

    @Autowired
    private UserPersistenceMapper mapper;

//    private final UserSpringDataRepository jpaRepository;
//    private  final UserPersistenceMapper mapper;
//
//
//    public  UserRepositoryAdapter(UserSpringDataRepository jpaRepository, UserPersistenceMapper mapper) {
//        this.jpaRepository = jpaRepository;
//        this.mapper = mapper;
//    }

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
}
