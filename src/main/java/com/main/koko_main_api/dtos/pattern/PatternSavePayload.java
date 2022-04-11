package com.main.koko_main_api.dtos.pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.List;

// https://stackoverflow.com/questions/37186417/resolving-entity-uri-in-custom-controller-spring-hateoas

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PatternSavePayload {
    private Integer level;
    private URI music;
}
