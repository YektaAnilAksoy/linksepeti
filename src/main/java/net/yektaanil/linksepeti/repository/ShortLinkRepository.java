package net.yektaanil.linksepeti.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import net.yektaanil.linksepeti.entity.ShortLinkEntity;

@Repository
public interface ShortLinkRepository extends CrudRepository<ShortLinkEntity, Long> {

    Optional<ShortLinkEntity> findByHashCode(String hashCode);

    Boolean existsByHashCode(String hashCode);
}
