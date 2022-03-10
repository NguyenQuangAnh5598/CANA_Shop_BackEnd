package com.example.cana_be.service.implement;

import com.example.cana_be.model.Status;
import com.example.cana_be.repository.IStatusRepo;
import com.example.cana_be.service.extend.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements IStatusService {
    @Autowired
    IStatusRepo statusRepo;

    @Override
    public List<Status> findAll() {
        return statusRepo.findAll();
    }

    @Override
    public Optional<Status> findById(Long id) {
        return statusRepo.findById(id);
    }

    @Override
    public void save(Status status) {
        statusRepo.save(status);
    }

    @Override
    public void remove(Long id) {
        statusRepo.deleteById(id);
    }
}
