package com.fiap.upmentor.usuarios;


public class Usuario {
	private String codigo;
	private String nome;
	private String cargo;
	private boolean adm;
	
	public Usuario() {
		this.codigo = "0001";
		this.nome = "Default";
		this.cargo = "Default";
		this.adm = false;
	}
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public boolean isAdm() {
		return adm;
	}
	public void setAdm(boolean adm) {
		this.adm = adm;
	}
}
