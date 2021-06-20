/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.infnet.at_nicholas_joao_pb_java.services;

import br.edu.infnet.at_nicholas_joao_pb_java.Entities.Serie;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Nicholas
 */
@FeignClient(url="localhost:8081/rest/", name="ApiService")
public interface ApiService {
    
    @GetMapping(path = "get/all")
    List<Serie> getAll();
    
    @GetMapping(path = "get/{id}")
    Serie getById(@PathVariable("id") Long id);
    
    @DeleteMapping("delete/{id}")
    ResponseEntity<String> deleteById(@PathVariable("id") Long id);
    
    @PostMapping("insert")
    Serie insertInto(@RequestBody Serie serie);
    
    @PostMapping("update/{id}")
    Serie updateById(@PathVariable("id") Long id, @RequestBody Serie serie);
}
