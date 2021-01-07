package net.yektaanil.linksepeti.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import net.yektaanil.linksepeti.common.DateUtil;
import net.yektaanil.linksepeti.entity.ShortLinkEntity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ShortLinkRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShortLinkRepository shortUrlRepository;

    private Long id;
    private final String hashCode = "qwerty";

    @BeforeEach
    public void setUp() {
        ShortLinkEntity shortLinkEntity = new ShortLinkEntity();
        shortLinkEntity.setUrl("http://www.google.com");
        shortLinkEntity.setExpiryDate(DateUtil.parseLocalDate("2100-12-01"));
        shortLinkEntity.setHashCode(hashCode);
        shortLinkEntity.setStatus(1);
        id = entityManager.persistAndGetId(shortLinkEntity, Long.class);
    }

    @Test
    void givenId_whenFindById_thenShouldReturnSavedObject() {
        Optional<ShortLinkEntity> savedEntity = shortUrlRepository.findById(id);
        assertTrue(savedEntity.isPresent());
        assertEquals(id, savedEntity.get().getId());
    }

    @Test
    void givenUrl_whenSave_thenShouldReturnSavedObject() {
        ShortLinkEntity shortLinkEntity2 = new ShortLinkEntity();
        shortLinkEntity2.setUrl("http://www.twitter.com");
        shortLinkEntity2.setExpiryDate(DateUtil.parseLocalDate("2200-12-20"));
        shortLinkEntity2.setHashCode("asd");
        shortLinkEntity2.setStatus(1);

        ShortLinkEntity savedEntity = shortUrlRepository.save(shortLinkEntity2);

        assertNotNull(savedEntity.getId());
        assertEquals(shortLinkEntity2.getHashCode(), savedEntity.getHashCode());
    }

    @Test
    void givenHashCode_whenFindByHashCode_thenShouldReturnSavedObjecT() {
        Optional<ShortLinkEntity> savedEntity = shortUrlRepository.findByHashCode(hashCode);
        assertTrue(savedEntity.isPresent());
    }
}
