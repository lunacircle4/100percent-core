package com.main.koko_main_api.Dtos;

import com.main.koko_main_api.Models.Bpm;
import com.main.koko_main_api.Models.Music;
import com.main.koko_main_api.Models.Playable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayablesSaveDto {
    private Integer level;
    private Set<Bpm> bpms;
    private Music music;

    public Playable toEntity() {
        return Playable.builder()
                .level(level)
                .bpms(bpms)
                .music(music).build();
    }
}
