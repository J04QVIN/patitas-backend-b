package pe.edu.cibertec.patitasbackendb.dto;

public record LoginResponseDTO(String codigo, String mensaje, String tipoDocumento, String numeroDocumento, String nombreUsuario, String correoUsuario) {
}