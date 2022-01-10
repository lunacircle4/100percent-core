package com.main.koko_main_api.Models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Musics extends BaseTimeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "albums_id")
    private Albums album;


//    @ManyToMany(mappedBy = "musics")
//    private List<Composers> composers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "composer_music",
            joinColumns = @JoinColumn(name = "composer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"))
    private List<Composers> composers;

    @Builder
    public Musics(String title) {
        this.title = title;
    }
}
