package net.yektaanil.linksepeti.service;

import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.yektaanil.linksepeti.common.Constants;
import net.yektaanil.linksepeti.common.DateUtil;
import net.yektaanil.linksepeti.dto.ShortLinkInputDTO;
import net.yektaanil.linksepeti.dto.ShortLinkOutputDTO;
import net.yektaanil.linksepeti.entity.ShortLinkEntity;
import net.yektaanil.linksepeti.exception.HashCodeCollisonException;
import net.yektaanil.linksepeti.exception.HashCodeExpiredException;
import net.yektaanil.linksepeti.exception.LinkNotFoundException;
import net.yektaanil.linksepeti.repository.ShortLinkRepository;

@Service
public class ShortLinkServiceImpl implements ShortLinkService {
    private static final Integer MAX_TRY = 10;
    private static final Integer HASH_SIZE = 8;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ShortLinkRepository shortLinkRepository;

    @Override
    public String getByHashCode(String hashCode) {
        Optional<ShortLinkEntity> shortLinkOptional = shortLinkRepository.findByHashCode(hashCode);
        if (shortLinkOptional.isPresent()) {
            ShortLinkEntity shortLinkEntity = shortLinkOptional.get();
            if (shortLinkEntity.getExpiryDate().isAfter(DateUtil.getCurrentLocalDate())) {
                return shortLinkEntity.getUrl();
            }
            throw new HashCodeExpiredException(Constants.HASH_CODE_EXPIRED);
        }
        throw new LinkNotFoundException(Constants.HASHED_LINK_NOT_FOUND);
    }

    @Override
    public ShortLinkOutputDTO createShortUrl(ShortLinkInputDTO shortLinkInputDTO) {
        ShortLinkEntity shortLinkEntity = modelMapper.map(shortLinkInputDTO, ShortLinkEntity.class);

        shortLinkEntity.setHashCode(createHashCode());
        return modelMapper.map(shortLinkRepository.save(shortLinkEntity), ShortLinkOutputDTO.class);
    }

    private boolean ishashCodeExistence(final String hashCode) {
        return shortLinkRepository.existsByHashCode(hashCode);
    }

    private String createHashCode() {
        for (int i = 0; i < MAX_TRY; i++) {
            final String hashCode = createRandomString();
            if (!ishashCodeExistence(hashCode)) {
                return hashCode;
            }
        }
        throw new HashCodeCollisonException(Constants.HASH_CODE_COLLISION);
    }

    private String createRandomString() {
        return RandomStringUtils.randomAlphanumeric(HASH_SIZE);
    }
}
