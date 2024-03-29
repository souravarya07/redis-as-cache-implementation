package com.test.redis.controller;

import com.test.redis.dto.PersonDTO;
import com.test.redis.service.RedisValueCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private RedisValueCache redisValueCache;

    @PostMapping(value = "/cache")
    public void cachePerson(@RequestBody final PersonDTO personDTO) {
        log.info("Caching person dto : {}", personDTO);
        redisValueCache.cache(personDTO.getId(), personDTO);
    }

    @GetMapping(value = "/{id}")
    public PersonDTO getCacheValue(@PathVariable("id") final String id) {
        log.info("Get cache value for id : {}", id);
        return (PersonDTO) redisValueCache.getCacheValue(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePerson(@PathVariable("id") final String id) {
        log.info("Delete cache value for id : {}", id);
        redisValueCache.deleteValue(id);
    }

}
