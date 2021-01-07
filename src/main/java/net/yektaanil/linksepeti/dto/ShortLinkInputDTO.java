package net.yektaanil.linksepeti.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public class ShortLinkInputDTO {


    @URL
    private String url;
    @NotNull
    private LocalDate expiryDate;


    public ShortLinkInputDTO(String url, LocalDate expiryDate) {
        super();
        this.url = url;
        this.expiryDate = expiryDate;
    }

    public ShortLinkInputDTO() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "ShortUrlDTO [url=" + url + ", expiryDate=" + expiryDate + "]";
    }
}
