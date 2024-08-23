package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.movie.Actor;
import com.nongviet201.cinema.core.repository.ActorRepository;
import com.nongviet201.cinema.core.service.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;
    @Override
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public Actor getActorById(Integer id) {
        return actorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy diễn viên có id: " + id));
    }

    @Override
    public Actor getActorByName(String name) {
        return actorRepository.findByName(name);
    }

    @Override
    public void createActor(String name) {
        if (getActorByName(name) != null) {
            throw new RuntimeException("Diễn viên " + name + " đã tồn tại ");
        }

        actorRepository.save(
            Actor.builder()
               .name(name)
               .build()
        );
    }

    @Override
    public void deleteById(int id) {
        actorRepository.deleteById(id);
    }

    @Override
    public void save(Actor actor) {
        actorRepository.save(actor);
    }
}
