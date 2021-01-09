package net.yektaanil.linksepeti.service;

import net.yektaanil.linksepeti.dto.ShortLinkInputDTO;
import net.yektaanil.linksepeti.dto.ShortLinkOutputDTO;
import net.yektaanil.linksepeti.exception.HashCodeCollisonException;
import net.yektaanil.linksepeti.exception.HashCodeExpiredException;
import net.yektaanil.linksepeti.exception.LinkNotFoundException;


public interface ShortLinkService {

    String getByHashCode(String hashCode) throws HashCodeExpiredException, LinkNotFoundException;

    ShortLinkOutputDTO createShortUrl(ShortLinkInputDTO shortLinkInputDTO)
            throws HashCodeCollisonException;
}
