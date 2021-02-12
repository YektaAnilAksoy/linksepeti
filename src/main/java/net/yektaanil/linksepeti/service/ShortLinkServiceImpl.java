package net.yektaanil.linksepeti.service;

import javax.transaction.Transactional;
import net.yektaanil.linksepeti.common.Constants;
import net.yektaanil.linksepeti.common.DateUtil;
import net.yektaanil.linksepeti.dto.ShortLinkInputDTO;
import net.yektaanil.linksepeti.dto.ShortLinkOutputDTO;
import net.yektaanil.linksepeti.entity.ShortLinkEntity;
import net.yektaanil.linksepeti.exception.HashCodeExpiredException;
import net.yektaanil.linksepeti.exception.LinkNotFoundException;
import net.yektaanil.linksepeti.repository.ShortLinkRepository;
import org.hashids.Hashids;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShortLinkServiceImpl implements ShortLinkService {

    @Value("${app.localname}")
    private String localName;

    private ModelMapper modelMapper;
    private ShortLinkRepository shortLinkRepository;
    private Hashids hashids;

    @Autowired
    public ShortLinkServiceImpl(
            ModelMapper modelMapper, ShortLinkRepository shortLinkRepository, Hashids hashids) {
        this.modelMapper = modelMapper;
        this.shortLinkRepository = shortLinkRepository;
        this.hashids = hashids;
    }

    @Override
    public String getByHashCode(String hashCode) {
        ShortLinkEntity shortLinkEntity = shortLinkRepository.findByHashCode(hashCode)
                .orElseThrow(() -> new LinkNotFoundException(Constants.HASHED_LINK_NOT_FOUND));
        if (shortLinkEntity.getExpiryDate().isAfter(DateUtil.getCurrentLocalDate())) {
            return shortLinkEntity.getUrl();
        }
        throw new HashCodeExpiredException(Constants.HASH_CODE_EXPIRED);
    }

    @Override
    @Transactional
    public ShortLinkOutputDTO createShortUrl(ShortLinkInputDTO shortLinkInputDTO) {
        ShortLinkEntity shortLinkEntity = modelMapper.map(shortLinkInputDTO, ShortLinkEntity.class);

        Long savedEntityId = shortLinkRepository.save(shortLinkEntity).getId();
        String hashCode = hashids.encode(savedEntityId);
        shortLinkRepository.updateHash(savedEntityId, hashCode);
        return new ShortLinkOutputDTO(localName + "/" + hashCode);
    }
}
