package groovy.Caixa

import groovy.Produto.Produto

class Caixa extends RepositorioProdutos {

	private List<ItemCaixa> listaCaixa                 //Lista dos produtos e quantidade
	int totalPrice                                //O preço total a pagar
	int totalDiscount                            //O total de desconto ganho

	Caixa() {
		listaCaixa = new ArrayList<>()
	}

	//Adiciona o item na lista dos itens comprados, de acordo com o seu nome
	def add = { nomeItem ->
		Produto obj = getProduto(nomeItem)
		adicionarItem(obj)
		calculaPrecoDescontoTotal()
	}

	//Remove o item da lista dos itens comprados de acordo com o seu nome.
	def remove = { String nomeItem ->
		Produto obj = getProduto(nomeItem)
		removerItem(obj)
		calculaPrecoDescontoTotal()
	}

	//Adiciona um item ao caixa
	private def adicionarItem = { Produto obj ->
		//Se não existe o item, adicoinar o item na lista
		if (!listaCaixa.produto.contains(obj)) {
			ItemCaixa objItem = new ItemCaixa()
			objItem.setProduto(obj)
			objItem.setQuantidade(1)
			listaCaixa.add(objItem)
			return
		}

		listaCaixa.find() { objItem ->
			if (objItem.getProduto() == obj) {
				objItem.setQuantidade(objItem.getQuantidade() + 1)
			}
		}


	}

	//Remove o item do caixa
	private def removerItem = { Produto obj ->
		listaCaixa.find() { objItem ->
			if (objItem.getProduto() == obj) {
				objItem.setQuantidade(objItem.getQuantidade() - 1)
				if (objItem.getQuantidade() == 0) {
					listaCaixa.remove(objItem)
				}
			}
		}
	}

	private def calculaPrecoDescontoTotal = {
		totalPrice = 0
		totalDiscount = 0

		listaCaixa.findAll { objItemCaixa ->
			if (objItemCaixa.produto.promocao != null) {
				//Calcula o desconto de acordo com as promoções
				totalDiscount += objItemCaixa.produto.promocao.desconto(objItemCaixa.quantidade, objItemCaixa.produto.precoUnitario)
			}
			totalPrice += objItemCaixa.produto.precoUnitario * objItemCaixa.quantidade
		}

		totalPrice -= totalDiscount

	}

}