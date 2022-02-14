package com.main.koko_main_api.repositories.playable;

import com.main.koko_main_api.configs.RepositoryConfig;
import com.main.koko_main_api.domainDtos.playable.bpm.PlayableBpmSaveEntityDto;
import com.main.koko_main_api.domains.Bpm;
import com.main.koko_main_api.domains.Music;
import com.main.koko_main_api.domains.Playable;
import com.main.koko_main_api.repositories.BpmRepository;
import com.main.koko_main_api.repositories.MusicRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({PlayableSearchRepositoryImpl.class, RepositoryConfig.class})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlayableSearchRepositoryTest {
    @Autowired
    private PlayableRepository playableRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private BpmRepository bpmRepository;

    @Test
    void findById() {
        /*
         * given
         */
        Music music = Music.builder().title("music").build();
        musicRepository.save(music);

        Playable playable = Playable.builder().level(2).music(music).build();
        playableRepository.save(playable);

        List<PlayableBpmSaveEntityDto> bpm_datas = new ArrayList() {
            { add(PlayableBpmSaveEntityDto.builder().value(100).build());
                add(PlayableBpmSaveEntityDto.builder().value(150).build());}};
        List<Bpm> bpms = new ArrayList<>();
        bpms.add(bpm_datas.get(0).toEntity(playable));
        bpms.add(bpm_datas.get(1).toEntity(playable));
        bpmRepository.saveAll(bpms);
        playable.add_bpms_for_save_request(bpms);

        //clear 하고 동작됨
        //entityManager.clear();

        /*
         * when
         */
        Optional<Playable> p = playableRepository.findById(playable.getId());
        playable = p.get();

        /*
         * then
         */
        assertThat(playable.getLevel()).isEqualTo(2);
        assertThat(playable.getMusic().getTitle()).isEqualTo("music");
        assertThat(playable.getBpms().size()).isEqualTo(2);
        assertThat(playable.getBpms().get(0).getValue()).isEqualTo(100);
    }

    @Test
    void findAll() {
        /*
         * given
         */
        final int N_PLAYABLES = 5;

        Music music = Music.builder().title("music").build();
        musicRepository.save(music);

        List<Playable> playables = new ArrayList<>();
        for(int i = 0; i < N_PLAYABLES; ++i) {
            playables.add(Playable.builder()
                    .level(2).music(music).build());
        }
        playableRepository.saveAll(playables);

        for(Playable p : playables) {
            List<PlayableBpmSaveEntityDto> bpm_datas = new ArrayList() {
                { add(PlayableBpmSaveEntityDto.builder().value(100).build());
                    add(PlayableBpmSaveEntityDto.builder().value(150).build());}};
            List<Bpm> bpms = bpm_datas.stream().map(
                    bpm -> bpm.toEntity(p)).collect(Collectors.toList());
            bpmRepository.saveAll(bpms);
            p.add_bpms_for_save_request(bpms);
        }

        /*
         * when
         */
        playables = playableRepository.findAll();

        /*
         * then
         */
        assertThat(playables.size()).isEqualTo(N_PLAYABLES);
        assertThat(playables.get(0).getLevel()).isEqualTo(2);
        assertThat(playables.get(0).getMusic().getTitle()).isEqualTo("music");
        assertThat(playables.get(0).getBpms().size()).isEqualTo(2);
        assertThat(playables.get(0).getBpms().get(0).getValue()).isEqualTo(100);
    }
}
