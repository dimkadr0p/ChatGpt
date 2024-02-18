package com.khachidze.ChatGPT.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/*
*     mongodb:
      host: 172.19.0.2
      port: 27017
      database: ChatGpt
      username: root
      password: Wecehrfcf

* */
@ConfigurationProperties
@Getter
@Setter
public class YandexApiConfig {
    public static final String API_KEY = "t1.9euelZrGy5GWz42Pm5KVmMyTjZWeie3rnpWay5LJzJWJnZ7Ky5LKmsiaysrl9PcNJDxR-e8DfDXD3fT3TVI5UfnvA3w1w83n9euelZqejJbGlcickp6XkI-djZ3Jxu_8xeuelZqejJbGlcickp6XkI-djZ3Jxg.hhhKG0H7B6exzHHjuRbEAZ5aHyGxxxeisXsYEXSIcqkq6nkLz2bMCd8Elu5COEqv8CZqhNM-Ch5zujUKQuX6Dg";
    public static final String FOLDER_ID = "b1g5kg34bf79cpn48uc2";
}
