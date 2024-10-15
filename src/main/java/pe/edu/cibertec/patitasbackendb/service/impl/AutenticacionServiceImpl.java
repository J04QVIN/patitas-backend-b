package pe.edu.cibertec.patitasbackendb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;


import pe.edu.cibertec.patitasbackendb.dto.LoginRequestDTO;
import pe.edu.cibertec.patitasbackendb.dto.LogoutRequestDTO;
import pe.edu.cibertec.patitasbackendb.service.AutenticacionService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

@Service
public class AutenticacionServiceImpl implements AutenticacionService {
    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public String[] validarUsuario(pe.edu.cibertec.patitasbackendb.dto.LoginRequestDTO loginRequestDTO) throws IOException {
        String[] datosUsuario = null;
        Resource resource = resourceLoader.getResource("classpath:usuarios.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            //Implement

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (loginRequestDTO.tipoDocumento().equals(datos[0]) &&
                        loginRequestDTO.numeroDocumento().equals(datos[1]) &&
                        loginRequestDTO.password().equals(datos[2])) {

                    datosUsuario = new String[4];

                    datosUsuario[0] = datos[0]; //recupera el tipodeDocumento
                    datosUsuario[1] = datos[1];//recupera el numerodeDocumento
                    datosUsuario[2] = datos[3];//recupera nombre
                    datosUsuario[3] = datos[4];//recupera el email
                }
            }

        } catch (IOException e) {
            datosUsuario = null;
            throw new IOException(e);
        }


        return datosUsuario;
    }

    @Override
    public Date cerrarSesion(pe.edu.cibertec.patitasbackendb.dto.LogoutRequestDTO logoutRequestDTO) throws IOException {


        Date fechaLogout = null;
        //Resource resource = resourceLoader.getResource("auditoria.txt");
        Path rutaArchivo = Paths.get("auditoria.txt");
        //File file = resource.getFile();
        try (BufferedWriter bw = Files.newBufferedWriter(rutaArchivo, StandardOpenOption.APPEND)) {

            // definir fecha
            fechaLogout = new Date();

            // preparar linea
            StringBuilder sb = new StringBuilder();
            sb.append(logoutRequestDTO.tipoDocumento());
            sb.append(";");
            sb.append(logoutRequestDTO.numeroDocumento());
            sb.append(";");
            sb.append(fechaLogout);

            // escribir linea
            bw.write(sb.toString());
            bw.newLine();
            System.out.println(sb.toString());

        } catch (IOException e) {

            fechaLogout = null;
            throw new IOException(e);

        }

        return fechaLogout;

    }
}

