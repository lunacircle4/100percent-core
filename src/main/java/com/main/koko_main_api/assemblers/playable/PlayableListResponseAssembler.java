package com.main.koko_main_api.assemblers.playable;

import com.main.koko_main_api.controllers.PlayableController;
import com.main.koko_main_api.domainDtos.playable.PlayableListResponseEntityDto;
import com.main.koko_main_api.payloads.playable.PlayableListResponsePayload;
import com.main.koko_main_api.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/*
 * RepresentationModelAssemblerSupport 는 RepresentationModelAssembler 의 구현체
 * self.link들이 이미잘탑재 되어있다.
 * 여기서는 그냥 수동으로 만들었다.
 * model메소드를 직접 구현한다?!
 */
@Component
public class PlayableListResponseAssembler implements
        RepresentationModelAssembler<PlayableListResponseEntityDto, PlayableListResponsePayload> {

    @Autowired
    private RepositoryEntityLinks linkHelper;

    @Override
    public PlayableListResponsePayload toModel(PlayableListResponseEntityDto p) {
        PlayableListResponsePayload payload = new PlayableListResponsePayload(p);

        /* add link */
        payload.add(linkTo(methodOn(PlayableController.class
            ).findById(payload.getId())).withSelfRel());
        payload.add(linkHelper.linkToItemResource(
                MusicRepository.class, payload.getMusic().getId()));
        return payload;
    }
}
