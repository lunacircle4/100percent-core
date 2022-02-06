package com.main.koko_main_api.payloads.playable;

import com.main.koko_main_api.domainDtos.playable.bpm.PlayableBpmSaveEntityDto;
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
public class PlayableSavePayload {
    private Integer level;
    private List<PlayableBpmSaveEntityDto> bpms;
    private URI music;
}
