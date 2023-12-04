package com.example.ClinicaOdontologicaHerreroNakayama.security;

import com.example.ClinicaOdontologicaHerreroNakayama.entity.Usuario;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.UsuarioRole;
import com.example.ClinicaOdontologicaHerreroNakayama.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosInicialesSecurity implements ApplicationRunner {
    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        //crear un usuario como si fuese real

        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        String passSinCifrar= "digital";
        String passCifrado= cifrador.encode(passSinCifrar);
        System.out.println("password: "+passCifrado);
        Usuario usuarioInsertar= new Usuario("Jorgito","jpereyra00","jorge.pereyra@digitalhouse.com",passCifrado, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioInsertar);

        BCryptPasswordEncoder cifrador1= new BCryptPasswordEncoder();
        String passSinCifrar1= "betoli";
        String passCifrado1= cifrador.encode(passSinCifrar1);
        System.out.println("password: "+passCifrado1);
        Usuario usuarioInsertar1= new Usuario("juan","jherrero00","juan.herrero@digitalhouse.com",passCifrado1, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuarioInsertar1);

    }
}
