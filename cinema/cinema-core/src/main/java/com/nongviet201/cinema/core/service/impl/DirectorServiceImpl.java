package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.movie.Director;
import com.nongviet201.cinema.core.repository.DirectorRepository;
import com.nongviet201.cinema.core.service.DirectorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;

    @Override
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    @Override
    public Director getDirectorById(Integer id) {
        return directorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin đạo diễn với id: " + id));
    }

    @Override
    public Director getDirectorByName(String name) {
        return directorRepository.findByName(name);
    }

    @Override
    public void createDirector(String name) {
        if (getDirectorByName(name) != null) {
            throw new RuntimeException("Đạo diễn " + name + " đã tồn tại với tên: ");
        }

        directorRepository.save(
            Director.builder()
               .name(name)
               .build()
        );
    }

    @Override
    public void deleteById(int id) {
        directorRepository.deleteById(id);
    }

    @Override
    public void save(Director director) {
        directorRepository.save(director);
    }
}
