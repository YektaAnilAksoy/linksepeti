package net.yektaanil.linksepeti.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import net.yektaanil.linksepeti.common.DateUtil;
import net.yektaanil.linksepeti.entity.ShortLinkEntity;

class ShortLinkEntityTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCreateShortUrlEntitySuccess() {
        ShortLinkEntity shortUrlEntity = new ShortLinkEntity();
        shortUrlEntity.setUrl("http://www.google.com");
        shortUrlEntity.setExpiryDate(DateUtil.parseLocalDate("2040-01-12"));
        shortUrlEntity.setHashCode("qwerty");
        assertEquals("http://www.google.com", shortUrlEntity.getUrl());
        assertEquals("2040-01-12", shortUrlEntity.getExpiryDate().toString());
        assertEquals("qwerty", shortUrlEntity.getHashCode());

        Set<ConstraintViolation<ShortLinkEntity>> violations = validator.validate(shortUrlEntity);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testCreateShortUrlEntityFail() {
        ShortLinkEntity shortUrlEntity = new ShortLinkEntity();
        shortUrlEntity.setExpiryDate(DateUtil.parseLocalDate("2400-12-01"));
        assertEquals("2400-12-01", shortUrlEntity.getExpiryDate().toString());

        Set<ConstraintViolation<ShortLinkEntity>> violations = validator.validate(shortUrlEntity);
        assertFalse(violations.isEmpty());
        assertNull(shortUrlEntity.getHashCode());
        assertNull(shortUrlEntity.getUrl());
    }
}
