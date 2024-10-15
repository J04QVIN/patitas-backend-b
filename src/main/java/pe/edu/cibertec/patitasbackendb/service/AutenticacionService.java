package pe.edu.cibertec.patitasbackendb.service;


import pe.edu.cibertec.patitasbackendb.dto.LoginRequestDTO;
import pe.edu.cibertec.patitasbackendb.dto.LogoutRequestDTO;

import java.io.IOException;
import java.util.Date;

public interface AutenticacionService {

    String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException;

    Date cerrarSesion(LogoutRequestDTO logoutRequestDTO) throws IOException;
}

