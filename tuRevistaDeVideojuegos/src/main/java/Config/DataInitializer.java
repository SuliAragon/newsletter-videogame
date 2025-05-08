package Config;

import Model.Rol;
import Repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RolRepository rolRepository) {
        return args -> {
            // Verifica si no hay roles en la base de datos
            if (rolRepository.count() == 0) {
                // Crea roles predeterminados
                Rol user = new Rol();
                user.setName("USER");

                Rol admin = new Rol();
                admin.setName("ADMIN");

                Rol moderator = new Rol();
                moderator.setName("MODERATOR");

                // Guarda los roles en la base de datos
                rolRepository.saveAll(Arrays.asList(user, admin, moderator));
                System.out.println("Roles iniciales creados.");
            }
        };
    }
}
