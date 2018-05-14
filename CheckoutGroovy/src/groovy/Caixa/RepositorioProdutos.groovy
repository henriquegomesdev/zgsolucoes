package groovy.Caixa

import groovy.Produto.Produto

class RepositorioProdutos {

	private Map<String, Produto> mapProdutos            //Lista dos produtos que estão cadastrados

	RepositorioProdutos() {
		mapProdutos = new HashMap<>()
	}

	void adicionarProduto(Produto produto) {
		mapProdutos.put(produto.getId(), produto)
	}

	Produto getProduto(String id) {
		return mapProdutos.get(id)
	}


}