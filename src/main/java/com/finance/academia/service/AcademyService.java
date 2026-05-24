package com.finance.academia.service;

import com.finance.academia.model.Academy;
import com.finance.academia.repository.AcademyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AcademyService {

    private final AcademyRepository academyRepository;

    public Academy saveAcademy(Academy academy){
        academyByemail(academy.getEmail());
        academyByCnpj(academy.getCnpj());
        return academyRepository.save(academy);
    }

    void academyByemail(String email){
        if (academyExistsByEmail(email)){
            throw new RuntimeException("Academia já existe com esse email");
        }
    }

    void academyByCnpj(String cnpj){
        if (academyExistsByCnpj(cnpj)){
            throw new RuntimeException("Academia já existe com esse cnpj");
        }
    }

    boolean academyExistsByCnpj(String cnpj){
        return academyRepository.existsByCnpj(cnpj);
    }

    boolean academyExistsByEmail(String email){
        return academyRepository.existsByEmail(email);
    }

    public List<Academy> findAllAcademies(){
        return academyRepository.findAll();
    }

    public Academy findAcademyByName(String name){
        return academyRepository.findByName(name);
    }






}
