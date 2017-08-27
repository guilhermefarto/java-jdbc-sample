package farto.cleva.guilherme.vo;

public class CursoVO extends AbstractVO {

	private String codigo;
	private String descricao;
	private String sigla;
	private int quantidadeAnos;

	public CursoVO() {
		super();
	}

	public CursoVO(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public int getQuantidadeAnos() {
		return quantidadeAnos;
	}

	public void setQuantidadeAnos(int quantidadeAnos) {
		this.quantidadeAnos = quantidadeAnos;
	}

}
