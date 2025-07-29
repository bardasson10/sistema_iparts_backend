package br.com.estoque.iparts.infrastructure.configs;

import br.com.estoque.iparts.application.domain.model.Departamento;
import br.com.estoque.iparts.application.domain.model.Role;
import br.com.estoque.iparts.application.ports.out.DepartamentoRepositoryPort;
import br.com.estoque.iparts.application.ports.out.RoleRepositositoryPort;
import br.com.estoque.iparts.security.enums.RoleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component // <-- Essencial para o Spring encontrar e executar esta classe
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    // Injetamos a porta do repositório, mantendo o desacoplamento
    private final DepartamentoRepositoryPort departamentoRepository;

    private final RoleRepositositoryPort roleRepositository;

    public DataSeeder(DepartamentoRepositoryPort departamentoRepository,  RoleRepositositoryPort roleRepositository) {
        this.departamentoRepository = departamentoRepository;
        this.roleRepositository = roleRepositository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Iniciando o seeder de dados...");
        seedDepartamentos();
        seedRoles();
        // Você pode chamar outros métodos de seed aqui, como seedRoles(), seedAdminUser(), etc.
        log.info("Seeder de dados finalizado.");
    }

    private void seedDepartamentos() {
        String nomeDepartamento = "Desenvolvimento";
        // Verifica se o departamento já não existe para não criar duplicatas
        if (!departamentoRepository.existePorNome(nomeDepartamento)) {
            log.info("Departamento '{}' não encontrado, criando...", nomeDepartamento);
            Departamento devDepartamento = new Departamento();
            devDepartamento.setNome(nomeDepartamento);

            departamentoRepository.salvar(devDepartamento);
            log.info("Departamento '{}' criado com sucesso.", nomeDepartamento);
        } else {
            log.info("Departamento '{}' já existe. Nenhuma ação necessária.", nomeDepartamento);
        }
    }


    private void seedRoles(){
        String nomeRoleAdmin = "ROLE_ADMIN";
        String nomeRoleUser = "ROLE_USER";

        if (!roleRepositository.existePorNome(nomeRoleAdmin)) {
            log.info("Role '{}' não encontrada, criando...", nomeRoleAdmin);
            Role roleAdmin = new Role();
            roleAdmin.setNome(RoleEnum.valueOf(nomeRoleAdmin));
            roleRepositository.salvar(roleAdmin);
            log.info("Role '{}' criada com sucesso.", nomeRoleAdmin);
        } else {
            log.info("Role '{}' já existe. Nenhuma ação necessária.", nomeRoleAdmin);
        }

        if (!roleRepositository.existePorNome(nomeRoleUser)) {
            log.info("Role '{}' não encontrada, criando...", nomeRoleUser);
            Role roleUser = new Role();
            roleUser.setNome(RoleEnum.valueOf(nomeRoleUser));
            roleRepositository.salvar(roleUser);
            log.info("Role '{}' criada com sucesso.", nomeRoleUser);
        } else {
            log.info("Role '{}' já existe. Nenhuma ação necessária.", nomeRoleUser);
        }

    }
}