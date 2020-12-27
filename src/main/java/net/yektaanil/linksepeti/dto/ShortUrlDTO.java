package net.yektaanil.linksepeti.dto;

import java.time.LocalDateTime;

public class ShortUrlDTO {

    private String url;
    private LocalDateTime expiryDate;


    public ShortUrlDTO(String url, LocalDateTime expiryDate) {
        super();
        this.url = url;
        this.expiryDate = expiryDate;
    }

    public ShortUrlDTO() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "ShortUrlDTO [url=" + url + ", expiryDate=" + expiryDate + "]";
    }
}
