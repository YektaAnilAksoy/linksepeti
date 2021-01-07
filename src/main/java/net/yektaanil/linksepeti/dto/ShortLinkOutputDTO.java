package net.yektaanil.linksepeti.dto;

import org.hibernate.validator.constraints.URL;

public class ShortLinkOutputDTO {

    @URL
    private String url;

    public ShortLinkOutputDTO(String url) {
        this.url = url;
    }

    public ShortLinkOutputDTO() {}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
