/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.infnet.at_nicholas_joao_pb_java.controllers;

import br.edu.infnet.at_nicholas_joao_pb_java.Entities.Serie;
import br.edu.infnet.at_nicholas_joao_pb_java.Entities.User;
import br.edu.infnet.at_nicholas_joao_pb_java.repositories.UserRepository;
import br.edu.infnet.at_nicholas_joao_pb_java.services.ApiService;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author nicholas
 */
@Controller
public class MainController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/")
    public String index(Model model) {
        return "index.html";
    }

    @GetMapping("/register")
    public String registrar(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/process_register")
    public String processRegister(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getSenha());
        user.setSenha(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/main_page")
    public String mainPage(Model model){
        List<Serie> list = apiService.getAll();
        model.addAttribute("series", list);
        return "main_page";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id){
        Serie serie = apiService.getById(id);
        model.addAttribute("serie", serie);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id, @ModelAttribute Serie serie, @RequestParam("img") MultipartFile multipartFile, @RequestParam("nome") String nome, @RequestParam("descricao") String descricao){

        String imageBase = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        if (imageBase.contains("..")) {
            System.out.println("arquivo INVALIDO");
        }
        try {
            serie.setImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        serie.setName(nome);
        serie.setDescription(descricao);
        apiService.updateById(id, serie);

        return "redirect:/main_page";
    }

    @PostMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id){
        apiService.deleteById(id);
        return "redirect:/main_page";
    }

    @GetMapping("/create")
    public String createGet(Model model){
        model.addAttribute("serie", new Serie());
        return "create";
    }

    @PostMapping("/create")
    public String createPost(Model model, @ModelAttribute Serie serie, @RequestParam("img") MultipartFile multipartFile, @RequestParam("nome") String nome, @RequestParam("descricao") String descricao) {
        String imageBase = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        if (imageBase.contains("..")) {
            System.out.println("arquivo INVALIDO");
        }
        try {
            serie.setImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        serie.setName(nome);
        serie.setDescription(descricao);
        apiService.insertInto(serie);
        return "redirect:/main_page";
    }
}
