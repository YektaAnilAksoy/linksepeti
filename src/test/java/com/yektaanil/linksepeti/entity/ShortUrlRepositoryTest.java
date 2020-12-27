package com.yektaanil.linksepeti.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import net.yektaanil.linksepeti.LinksepetiApplication;
import net.yektaanil.linksepeti.common.DateUtil;
import net.yektaanil.linksepeti.entity.ShortUrlEntity;
import net.yektaanil.linksepeti.repository.ShortUrlRepository;


@ContextConfiguration(classes = LinksepetiApplication.class)
@DataJpaTest
// @TestPropertySource(locations = "classpath:application-test.properties")
class ShortUrlRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    private Long id;

    @BeforeEach
    public void setUp() {
        ShortUrlEntity shortUrlEntity = new ShortUrlEntity();
        shortUrlEntity.setUrl("http://www.google.com");
        shortUrlEntity.setExpiryDate(DateUtil.parseLocalDate("2100-12-01"));
        shortUrlEntity.setHashedId("qwerty");
        shortUrlEntity.setStatus(1);
        id = entityManager.persistAndGetId(shortUrlEntity, Long.class);
    }

    @Test
    void givenId_whenFindById_thenShouldReturnSavedObject() {
        Optional<ShortUrlEntity> savedEntity = shortUrlRepository.findById(id);
        assertTrue(savedEntity.isPresent());
        assertEquals(id, savedEntity.get().getId());
    }

    @Test
    void givenUrl_whenSave_thenShouldReturnSavedObject() {
        ShortUrlEntity shortUrlEntity2 = new ShortUrlEntity();
        shortUrlEntity2.setUrl("http://www.twitter.com");
        shortUrlEntity2.setExpiryDate(DateUtil.parseLocalDate("2200-12-20"));
        shortUrlEntity2.setHashedId("asd");
        shortUrlEntity2.setStatus(1);

        ShortUrlEntity savedEntity = shortUrlRepository.save(shortUrlEntity2);

        assertNotNull(savedEntity.getId());
        assertEquals(shortUrlEntity2.getHashedId(), savedEntity.getHashedId());
    }
}
