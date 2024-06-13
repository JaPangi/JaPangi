package io.github.japangiserver.domains.admin.persistence.repository;

import io.github.japangiserver.domains.admin.persistence.entity.AdminEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<AdminEntity,Long> {
    boolean existsByUsername(String username);
    boolean existsByPassword(String password);
    Optional<AdminEntity> findByUsernameAndPassword(String username, String password);

    @Query("select count (a.adminId) > 0 "
        + "from AdminEntity a "
        + "where a.adminId =:adminId and a.password =:password")
    boolean existsByAdminIdAndPassword(
        @Param(value = "adminId") Long adminId,
        @Param(value = "password") String password
    );
}
