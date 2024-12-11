package Repository;

import Entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    Optional<Tenant> findByUuid(UUID uuid);

    @Modifying
    @Transactional
    @Query("DELETE FROM Tenant ten WHERE ten.id = :id")
    void deleteById(Long id);
}
