package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Repository;

import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}
