package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoDao {

	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}

	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}

	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}

  public Produto buscarPorId(Long id) {
    return em.find(Produto.class, id);
  }
  public List<Produto> buscarTodos() {
    String JPQL = "SELECT p FROM Produto p";
    return em.createQuery(JPQL, Produto.class).getResultList();

  }
  public List<Produto> buscarporNome(String nome) {
    String JPQL = "SELECT p FROM Produto p WHERE p.nome = :nome";
    return em.createQuery(JPQL, Produto.class)
      .setParameter("nome", nome)
      .getResultList();

  }

  public List<Produto> buscarporCategoria(String nome) {
    String JPQL = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
    return em.createQuery(JPQL, Produto.class)
      .setParameter("nome", nome)
      .getResultList();

  }

  public BigDecimal buscarPreco(String nome) {
    String JPQL = "SELECT p.preco FROM Produto p WHERE p.categoria.nome = :nome";
    return em.createQuery(JPQL, BigDecimal.class)
      .setParameter("nome", nome)
      .getSingleResult();

  }
}
