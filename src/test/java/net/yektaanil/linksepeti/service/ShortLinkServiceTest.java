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
import net.yektaanil.linksepeti.exception.HashCodeExpiredException;
import net.yektaanil.linksepeti.exception.LinkNotFoundException;
import net.yektaanil.linksepeti.repository.ShortLinkRepository;
import net.yektaanil.linksepeti.util.ShortLinkUtil;

@ExtendWith(MockitoExtension.class)
class ShortLinkServiceTest {

    @InjectMocks
    private ShortLinkService shortLinkService = new ShortLinkServiceImpl();

    @Mock
    private ShortLinkRepository shortLinkRepository;

    @Spy
    private ModelMapper modelMapper;

    private ShortLinkEntity shortLinkEntity;

    @BeforeEach
    void setUp() {
        shortLinkEntity = ShortLinkUtil.getShortLink();
    }

    @Test
    void shouldReturnHashedUrlSuccessfully() {

        when(shortLinkRepository.findByHashCode(anyString()))
                .thenReturn(Optional.of(shortLinkEntity));

        String actualUrl = shortLinkService.getByHashCode("qwerty123");

        assertEquals(shortLinkEntity.getUrl(), actualUrl);
    }

    @Test
    void shouldCreateHashedUrlSuccessfully() {
        final ShortLinkInputDTO shortUrlDTO = ShortLinkUtil.getShortLinkDTO();
        when(shortLinkRepository.save(any())).thenReturn(shortLinkEntity);

        final ShortLinkOutputDTO hashedUrlDTO = shortLinkService.createShortUrl(shortUrlDTO);

        assertNotNull(hashedUrlDTO.getUrl());
    }

    @Test
    void shouldThrowHashCodeExpiredException() {
        when(shortLinkRepository.findByHashCode(anyString()))
                .thenReturn(Optional.of(ShortLinkUtil.getExpiredShortLink()));

        Assertions.assertThrows(HashCodeExpiredException.class, () -> {
            shortLinkService.getByHashCode("qwerty123");
        });
    }

    @Test
    void shouldThrowLinkNotFoundExceptionWhenLinkNotExist() {
        when(shortLinkRepository.findByHashCode(anyString())).thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(LinkNotFoundException.class, () -> {
            shortLinkService.getByHashCode("qwerty123");
        });
    }

    @Test
    void shouldThrowHashCodeExpiredExceptionWhenHashCodeExpired() {

        when(shortLinkRepository.findByHashCode(anyString()))
                .thenReturn(Optional.of(ShortLinkUtil.getExpiredShortLink()));

        Assertions.assertThrows(HashCodeExpiredException.class, () -> {
            shortLinkService.getByHashCode("qwerty123");
        });
    }
}
