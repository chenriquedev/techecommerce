package com.henrique.ecommerce_back.config;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

        @Value("${product.image.path}")
        private String productImagePath;

        @Value("${brand.image.path}")
        private String brandImagePath;

        @Value("${category.image.path}")
        private String categoryImagePath;

        @Value("${default.image.path}")
        private String defaultImagePath;

        @Override
        public void addResourceHandlers(
                        ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/products-images/**")
                                .addResourceLocations("file:" + Path.of(productImagePath).toAbsolutePath() + "/")
                                .addResourceLocations("file:" + Path.of(defaultImagePath).toAbsolutePath() + "/");

                registry.addResourceHandler("/brands-images/**")
                                .addResourceLocations("file:" + Path.of(brandImagePath).toAbsolutePath() + "/")
                                .addResourceLocations("file:" + Path.of(defaultImagePath).toAbsolutePath() + "/");

                registry.addResourceHandler("/categories-images/**")
                                .addResourceLocations("file:" + Path.of(categoryImagePath).toAbsolutePath() + "/")
                                .addResourceLocations("file:" + Path.of(defaultImagePath).toAbsolutePath() + "/");

        }
}
