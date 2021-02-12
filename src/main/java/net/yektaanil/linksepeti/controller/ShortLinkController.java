package net.yektaanil.linksepeti.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import net.yektaanil.linksepeti.dto.ShortLinkInputDTO;
import net.yektaanil.linksepeti.dto.ShortLinkOutputDTO;
import net.yektaanil.linksepeti.service.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/urlshortener")
public class ShortLinkController {

    @Autowired
    private ShortLinkService shortLinkService;

    @ResponseStatus(value = HttpStatus.TEMPORARY_REDIRECT)
    @GetMapping(value = "/{hashcode}")
    public String getUrl(@PathVariable(name = "hashcode") @NotNull String hashCode) {
        return shortLinkService.getByHashCode(hashCode);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ShortLinkOutputDTO> create(@RequestBody @Valid ShortLinkInputDTO url) {

        return new ResponseEntity<>(shortLinkService.createShortUrl(url), HttpStatus.OK);
    }
}
