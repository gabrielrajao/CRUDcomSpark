package model;
import java.io.Serializable;

public class Planta implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String descricao;
	private float preco;
	private int idade;
	
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public float getPreco() {
		return preco;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDescricao (String descricao) {
		this.descricao = descricao;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public Planta(){
		id = -1;
		nome = "";
		descricao = "";
		preco = 0;
		idade = 0;
	}
	public Planta(int id, String nome, String descricao, float preco, int idade){
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.idade = idade;
	}
	@Override
	public String toString() {
		return "Nome da planta: "+ nome +"Descricao: " + descricao + "Idade: "+idade+" anos   Preço: R$" + preco ;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Planta) obj).getId());
	}
}
