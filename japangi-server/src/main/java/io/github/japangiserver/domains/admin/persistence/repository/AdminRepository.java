package io.github.japangiserver.domains.admin.persistence.repository;

import io.github.japangiserver.domains.admin.persistence.entity.AdminEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity,Long> {
    boolean existsByUsername(String username);
    boolean existsByPassword(String password);
    Optional<AdminEntity> findByUsernameAndPassword(String username, String password);

    boolean existsByAdminIdAndPassword(Long adminId, String password);
}
