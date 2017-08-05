package com.example.usuario.cheasytaxi;


import java.util.List;

public class Usuario {
  int Id;
    String Nombre;
    String Usuario;
    String Password;
    String Ciudad;
    String Email;
    int Telefono;

    public Usuario(){
        Id =this.Id;
        Nombre = this.Nombre;
        Usuario = this.Usuario;
        Password = this.Password;
        Ciudad = this.Ciudad;
        Email = this.Email;
        Telefono = this.Telefono;
    }
    public void setId(int Id)
    {
        this.Id = Id;
    }

    public void setNombre(String nombre)
    {
        this.Nombre = nombre;
    }

    public void setUsuario(String user)
    {
        this.Usuario = user;
    }

    public void setPassword(String pass)
    {
        this.Password = pass;
    }

    public void setCiudad(String ciudad)
    {
        this.Ciudad = ciudad;
    }


    public void setEmail(String email)
    {
        this.Email = email;
    }
    public void setTelefono(int telefono)
    {

        this.Telefono = telefono;
    }
    public int getId(){
        return  Id;
    }

    public String getNombre(){
        return Nombre;
    }

    public String getUsuario(){
        return  Usuario;
    }

    public String getCiudad(){
        return Ciudad;
    }



    public String getEmail(){
        return Email;
    }

    public int getTelefono(){
        return Telefono;
    }

    public String getPassword(){
        return Password;
    }





}
