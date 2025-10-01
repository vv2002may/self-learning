package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class MemeReqDto {
    private String id;
}
