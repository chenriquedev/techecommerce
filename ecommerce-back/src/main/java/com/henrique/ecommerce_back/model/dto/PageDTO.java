package com.henrique.ecommerce_back.model.dto;

import lombok.Data;

@Data
public class PageDTO {
    private Integer page = 0;
    private Integer size = 10;

    public void sanitizePageParameters() {
        if(page < 0) {
            this.page = 0;
        }
        if(size < 0) {
            this.size = 0;
        }
    }
}
