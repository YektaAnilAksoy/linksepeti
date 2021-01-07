package net.yektaanil.linksepeti.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import net.yektaanil.linksepeti.dto.ShortLinkInputDTO;
import net.yektaanil.linksepeti.dto.ShortLinkOutputDTO;
import net.yektaanil.linksepeti.entity.ShortLinkEntity;
import net.yektaanil.linksepeti.exception.HashCodeCollisonException;
import net.yektaanil.linksepeti.exception.HashCodeExpiredException;
import net.yektaanil.linksepeti.exception.LinkNotFoundException;
import net.yektaanil.linksepeti.repository.ShortLinkRepository;
import net.yektaanil.linksepeti.util.ShortLinkUtil;

@ExtendWith(MockitoExtension.class)
class ShortLinkServiceTest {

    @InjectMocks
    private ShortLinkService shortUrlService = new ShortLinkServiceImpl();

    @Mock
    private ShortLinkRepository shortLinkRepository;

    @Spy
    private ModelMapper modelMapper;

    private ShortLinkEntity shortUrlEntity;

    @BeforeEach
    void setUp() {
        shortUrlEntity = ShortLinkUtil.getShortLink();
    }

    @Test
    void shouldReturnHashedUrlSuccessfully()
            throws HashCodeExpiredException, LinkNotFoundException {

        when(shortLinkRepository.findByHashCode(anyString()))
                .thenReturn(Optional.of(shortUrlEntity));

        ShortLinkOutputDTO shortLinkOutputDTO = shortUrlService.getByHashCode("qwerty123");

        assertEquals(shortUrlEntity.getUrl(), shortLinkOutputDTO.getUrl());
    }

    @Test
    void shouldCreateHashedUrlSuccessfully() throws HashCodeCollisonException {
        final ShortLinkInputDTO shortUrlDTO = ShortLinkUtil.getShortLinkDTO();
        when(shortLinkRepository.save(any())).thenReturn(shortUrlEntity);

        final ShortLinkOutputDTO hashedUrlDTO = shortUrlService.createShortUrl(shortUrlDTO);

        assertNotNull(hashedUrlDTO.getUrl());
    }

    @Test
    void shouldThrowHashCodeExpiredException() {
        when(shortLinkRepository.findByHashCode(anyString()))
                .thenReturn(Optional.of(ShortLinkUtil.getExpiredShortLink()));

        Assertions.assertThrows(HashCodeExpiredException.class, () -> {
            shortUrlService.getByHashCode("qwerty123");
        });
    }

    @Test
    void shouldThrowHashNotFoundWhenHashNotExist() {

    }

    @Test
    void shouldThrowInvalidUrlWhenCreate() {

    }
}
