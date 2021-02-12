package net.yektaanil.linksepeti.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
        name = "ShortLink",
        indexes = {@Index(columnList = "HashCode", unique = true)})
public class ShortLinkEntity extends BaseEntity {

    private static final long serialVersionUID = -3150350189080739231L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Pattern(regexp = "(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?", message = "Url is not valid")
    @NotNull(message = "url cannot be null")
    @Column(name = "Url")
    private String url;

    @Column(name = "HashCode", unique = true)
    private String hashCode;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ExpiryDate")
    private LocalDate expiryDate;

    public ShortLinkEntity(Long id, String url, String hashCode, LocalDate expiryDate) {
        this.id = id;
        this.url = url;
        this.hashCode = hashCode;
        this.expiryDate = expiryDate;
    }

    public ShortLinkEntity() {
    }

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

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "ShortUrlEntity [id="
                + id
                + ", url="
                + url
                + ", hashCode="
                + hashCode
                + ", expiryDate="
                + expiryDate
                + ", getStatus()="
                + getStatus()
                + ", getCreateDate()="
                + getCreateDate()
                + ", getCreatedBy()="
                + getCreatedBy()
                + "]";
    }
}
