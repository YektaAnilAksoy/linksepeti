package net.yektaanil.linksepeti.repository;

import java.util.Optional;
import net.yektaanil.linksepeti.entity.ShortLinkEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortLinkRepository extends CrudRepository<ShortLinkEntity, Long> {

    Optional<ShortLinkEntity> findByHashCode(String hashCode);

    @Modifying
    @Query("update ShortLinkEntity sl set sl.hashCode = :hashCode where sl.id = :id")
    void updateHash(@Param(value = "id") Long id, @Param(value = "hashCode") String hashCode);
}
