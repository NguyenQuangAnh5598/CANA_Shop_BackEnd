package com.example.cana_be.controller;

import com.example.cana_be.dto.response.ResponseMessage;
import com.example.cana_be.model.Status;
import com.example.cana_be.service.extend.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/status")
public class StatusController {
    @Autowired
    IStatusService statusService;

    @GetMapping
    public ResponseEntity<?> showAllStatus() {
        List<Status> statusList = statusService.findAll();
        if (statusList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(statusList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findStatusById(@PathVariable Long id) {
        Optional<Status> statusOptional = statusService.findById(id);
        if (!statusOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statusOptional.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewStatus(@RequestBody Status status) {
        statusService.save(status);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateStatus(@RequestBody Status status) {
        Optional<Status> statusOptional = statusService.findById(status.getId());
        if (!statusOptional.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("Không Có"),HttpStatus.NOT_FOUND);
        }
        statusService.save(status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatusById(@PathVariable Long id) {
        Optional<Status> statusOptional = statusService.findById(id);
        if (!statusOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        statusService.remove(id);
        return new ResponseEntity<>(new ResponseMessage("Delete completed"),HttpStatus.OK);
    }

}
