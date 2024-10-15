package pe.edu.cibertec.patitasbackendb.dto;

public record LoginRequestDTO(String tipoDocumento, String numeroDocumento, String password) {
}