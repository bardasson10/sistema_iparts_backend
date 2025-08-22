package br.com.estoque.iparts.persistence.mapper;

import br.com.estoque.iparts.application.domain.model.Departamento;
import br.com.estoque.iparts.application.domain.model.Role;
import br.com.estoque.iparts.application.domain.model.User;
import br.com.estoque.iparts.persistence.entity.RoleJpaEntity;
import br.com.estoque.iparts.persistence.entity.UserJpaEntity;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T00:53:43-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UserPersistenceMapperImpl implements UserPersistenceMapper {

    @Autowired
    private DepartamentoPersistenceMapper departamentoPersistenceMapper;
    @Autowired
    private RolePersistenceMapper rolePersistenceMapper;

    @Override
    public UserJpaEntity toEntity(User domain) {
        if ( domain == null ) {
            return null;
        }

        UserJpaEntity userJpaEntity = new UserJpaEntity();

        userJpaEntity.setFkDepartamento( departamentoPersistenceMapper.toEntity( domain.getDepartamento() ) );
        userJpaEntity.setRoles( roleSetToRoleJpaEntitySet( domain.getRoles() ) );
        userJpaEntity.setId( domain.getId() );
        userJpaEntity.setNome( domain.getNome() );
        userJpaEntity.setEmail( domain.getEmail() );
        userJpaEntity.setSenha( domain.getSenha() );
        userJpaEntity.setDataUltimaSenha( domain.getDataUltimaSenha() );
        userJpaEntity.setStatus( domain.getStatus() );

        return userJpaEntity;
    }

    @Override
    public User toDomain(UserJpaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Departamento departamento = null;
        Set<Role> roles = null;
        String nome = null;
        String email = null;
        String senha = null;

        departamento = departamentoPersistenceMapper.toDomain( entity.getFkDepartamento() );
        roles = roleJpaEntitySetToRoleSet( entity.getRoles() );
        nome = entity.getNome();
        email = entity.getEmail();
        senha = entity.getSenha();

        User user = new User( nome, email, senha, roles, departamento );

        user.setId( entity.getId() );
        user.setDataUltimaSenha( entity.getDataUltimaSenha() );
        user.setStatus( entity.getStatus() );

        return user;
    }

    protected Set<RoleJpaEntity> roleSetToRoleJpaEntitySet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleJpaEntity> set1 = new LinkedHashSet<RoleJpaEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( rolePersistenceMapper.toEntity( role ) );
        }

        return set1;
    }

    protected Set<Role> roleJpaEntitySetToRoleSet(Set<RoleJpaEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new LinkedHashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleJpaEntity roleJpaEntity : set ) {
            set1.add( rolePersistenceMapper.toDomain( roleJpaEntity ) );
        }

        return set1;
    }
}
