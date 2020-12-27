package net.yektaanil.linksepeti.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ShortUrl")
public class ShortUrlEntity extends BaseEntity {

    private static final long serialVersionUID = -3150350189080739231L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @NotNull(message = "url cannot be null")
    @Column(name = "Url")
    private String url;

    @NotNull(message = "hashedId cannot be null")
    @Column(name = "HashedId")
    private String hashedId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ExpiryDate")
    private LocalDate expiryDate;


    public ShortUrlEntity(Long id, String url, String hashedId, LocalDate expiryDate) {
        this.id = id;
        this.url = url;
        this.hashedId = hashedId;
        this.expiryDate = expiryDate;
    }

    public ShortUrlEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHashedId() {
        return hashedId;
    }

    public void setHashedId(String hashedId) {
        this.hashedId = hashedId;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }


    @Override
    public String toString() {
        return "ShortUrlEntity [id=" + id + ", url=" + url + ", hashedId=" + hashedId
                + ", expiryDate=" + expiryDate + ", getStatus()=" + getStatus()
                + ", getCreateDate()=" + getCreateDate() + ", getCreatedBy()=" + getCreatedBy()
                + ", getUpdateDate()=" + getUpdateDate() + ", getUpdatedBy()=" + getUpdatedBy()
                + "]";
    }
}
