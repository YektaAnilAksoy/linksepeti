package net.yektaanil.linksepeti.service;

import net.yektaanil.linksepeti.dto.ShortUrlDTO;


public interface ShortUrlService {

    ShortUrlDTO getByHashedId(String hashedId);

    ShortUrlDTO createShortUrl(ShortUrlDTO shortUrlDTO);
}
