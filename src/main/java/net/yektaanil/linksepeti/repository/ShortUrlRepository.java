package net.yektaanil.linksepeti.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import net.yektaanil.linksepeti.entity.ShortUrlEntity;

@Repository
public interface ShortUrlRepository extends CrudRepository<ShortUrlEntity, Long> {

    ShortUrlEntity findByHashedId(String hashedId);
}
