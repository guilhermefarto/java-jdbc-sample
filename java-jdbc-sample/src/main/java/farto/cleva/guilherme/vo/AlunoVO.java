package farto.cleva.guilherme.vo;

import java.util.Date;

public class AlunoVO extends AbstractVO {

	private String ra;
	private String nome;
	private String sexo;
	private Date dataNascimento;
	private String email;
	private CursoVO curso;
	private boolean fgAtivo = true;

	public AlunoVO() {
		super();
	}

	public AlunoVO(long id) {
		this.id = id;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CursoVO getCurso() {
		return curso;
	}

	public void setCurso(CursoVO curso) {
		this.curso = curso;
	}

	public boolean isFgAtivo() {
		return fgAtivo;
	}

	public void setFgAtivo(boolean fgAtivo) {
		this.fgAtivo = fgAtivo;
	}

}
