package net.yektaanil.linksepeti.controller;


import java.nio.charset.StandardCharsets;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.hash.Hashing;

@RestController
@RequestMapping("/rest/url")
public class UrlShortenerController {

    @Autowired
    StringRedisTemplate redisTemplate;

    private final Logger logger = LoggerFactory.getLogger(UrlShortenerController.class);

    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id) {
        String url = redisTemplate.opsForValue().get(id);
        logger.info("Retrieved url: {}", url);

        if (url == null) {
            throw new RuntimeException("There is no shorter URL for : " + id);
        }
        return url;
    }

    @PostMapping
    public String create(@RequestBody String url) {

        UrlValidator urlValidator = new UrlValidator(new String[] {"http", "https"});
        if (urlValidator.isValid(url)) {
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            logger.info("Id generated: {}", id);
            redisTemplate.opsForValue().set(id, url);
            return id;
        }
        throw new RuntimeException("URL invalid: " + url);
    }
}
