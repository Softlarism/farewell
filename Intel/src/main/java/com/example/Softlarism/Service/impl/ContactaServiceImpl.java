package com.example.Softlarism.Service.impl;

import com.example.Softlarism.Model.Contacta;
import com.example.Softlarism.Repository.ContactaRepository;
import com.example.Softlarism.Service.ContactaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ContactaServiceImpl implements ContactaService {
    private final ContactaRepository contactaRepository;
    
    @Override
    public List<Contacta> getAll(){
        return contactaRepository.findAll();
    }
    @Override
    public Contacta getById(Integer id){
        return contactaRepository.findById(id).orElse(null);

    }
    @Override
    public Contacta save ( Contacta contacta){
        return contactaRepository.save(contacta);
    }
    @Override
    public void  delete(Integer id){
         contactaRepository.deleteById(id);
    }
    @Override
    public  Contacta update (Integer id,Contacta contacta){
        Contacta aux = contactaRepository.getById(id);
        contacta.setId_usuario(contacta.getId_usuario());
        contacta.setId_autoridad(contacta.getId_autoridad());
        contactaRepository.save(aux);
        return aux;
    }
}
