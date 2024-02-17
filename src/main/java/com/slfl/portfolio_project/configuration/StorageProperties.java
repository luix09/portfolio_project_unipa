package com.slfl.portfolio_project.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
@Getter
@Setter
public class StorageProperties {

    /**
     * Folder location for storing images
     */
    private String location = "images";

}
