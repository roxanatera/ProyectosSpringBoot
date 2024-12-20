package com.formularios.formularios.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor


public class User {
    @Size(min =3,max = 12)
    private String nombre;
    @NotBlank
    @Email(message = "Ingrese un formato de email Valido")
    private String email;
    @NotBlank
    @Size(min = 5, max = 15)
    private String password;
    @NotBlank
    private String genero;
    @Size(min = 5, max = 100)
    private String nota;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    @NotBlank
    private String profession;

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter y Setter para password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter y Setter para genero
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    // Getter y Setter para nota
    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    // Getter y Setter para fechaNacimiento
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getter y Setter para profession
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}

