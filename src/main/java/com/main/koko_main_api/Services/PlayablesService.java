package com.main.koko_main_api.Services;
import com.main.koko_main_api.Dtos.PlayablesResponseDto;
import com.main.koko_main_api.Dtos.PlayablesSaveDto;
import com.main.koko_main_api.Models.Playable;
import com.main.koko_main_api.Repositories.PlayablesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PlayablesService {
    private final PlayablesRepository playablesRepository;

    @Transactional
    public PlayablesResponseDto save(PlayablesSaveDto dto) {
        Playable playable = playablesRepository.save(dto.toEntity());
        return new PlayablesResponseDto(playable);
    }
}
