package io.github.japangiserver.domain.change;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeRepository extends JpaRepository<Change, Long> {
}
