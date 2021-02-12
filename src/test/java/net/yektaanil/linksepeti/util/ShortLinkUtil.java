package net.yektaanil.linksepeti.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import net.yektaanil.linksepeti.common.DateUtil;
import net.yektaanil.linksepeti.dto.ShortLinkInputDTO;
import net.yektaanil.linksepeti.dto.ShortLinkOutputDTO;
import net.yektaanil.linksepeti.entity.ShortLinkEntity;

public class ShortLinkUtil {

    private static final String url = "www.google.com";
    private static final LocalDate expiryDate = DateUtil.parseLocalDate("2040-01-12");
    private static final LocalDate expiredDate = DateUtil.parseLocalDate("2010-01-12");

    private ShortLinkUtil() {

    }

    public static ShortLinkEntity getShortLink() {
        ShortLinkEntity shortUrlEntity = new ShortLinkEntity();
        shortUrlEntity.setCreateDate(LocalDateTime.of(2020, Month.DECEMBER, 29, 19, 30, 40));
        shortUrlEntity.setCreatedBy("127.0.0.1");
        shortUrlEntity.setExpiryDate(expiryDate);
        shortUrlEntity.setHashCode("qwerty123");
        shortUrlEntity.setStatus(1);
        shortUrlEntity.setUrl(url);

        return shortUrlEntity;
    }

    public static ShortLinkEntity getExpiredShortLink() {
        ShortLinkEntity expiredEntity = getShortLink();
        expiredEntity.setExpiryDate(expiredDate);

        return expiredEntity;
    }

    public static ShortLinkInputDTO getShortLinkDTO() {
        return new ShortLinkInputDTO(url, expiryDate);
    }

    public static ShortLinkOutputDTO getShortLinkOutputDTO() {
        ShortLinkOutputDTO shortLinkOutputDTO = new ShortLinkOutputDTO();
        shortLinkOutputDTO.setUrl("www.google.com");
        return shortLinkOutputDTO;
    }

    /**
     * @param obj is any object that can be stringfied
     * @return a JSON format of given object
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
