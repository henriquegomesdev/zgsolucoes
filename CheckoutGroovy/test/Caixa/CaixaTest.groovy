import groovy.Caixa.Caixa
import groovy.Produto.Produto
import groovy.Promocao.PromocaoCompraLeva
import groovy.Promocao.PromocaoCompraPague
import spock.lang.Specification

class CaixaTest extends Specification {

	static Caixa checkout1
	static Caixa checkout2
	static Caixa checkout3
	static Caixa checkout4
	static Caixa checkout5

	static Produto produtoA
	static Produto produtoB
	static Produto produtoC
	static Produto produtoD
	static Produto produtoE


	def setupSpec() {

		produtoA = new Produto(id: "A", precoUnitario: 50, promocao: new PromocaoCompraPague(3, 130))
		produtoB = new Produto(id: "B", precoUnitario: 30, promocao: new PromocaoCompraPague(2, 45))
		produtoC = new Produto(id: "C", precoUnitario: 20, promocao: new PromocaoCompraLeva(3, 2))
		produtoD = new Produto(id: "D", precoUnitario: 15)
		produtoE = new Produto(id: "E", precoUnitario: 20, promocao: new PromocaoCompraPague(1, 10))

		checkout1 = novoCaixa()
		checkout2 = novoCaixa()
		checkout3 = novoCaixa()
		checkout4 = novoCaixa()
		checkout5 = novoCaixa()

	}

	def "teste1"() {
		when:
		if (operacao == "add") {
			checkout1.add(nome)
		} else if (operacao == "remove") {
			checkout1.remove(nome)
		}
		then:
		checkout1.getTotalPrice() == price
		checkout1.getTotalDiscount() == discount
		where:
		operacao | nome | price || discount
		"add"    | "A"  | 50    || 0
		"add"    | "A"  | 100   || 0
		"add"    | "A"  | 130   || 20
		"add"    | "A"  | 180   || 20
		"add"    | "A"  | 230   || 20
		"add"    | "A"  | 260   || 40
		"remove" | "A"  | 230   || 20

	}

	def "teste2"() {
		when:
		if (operacao == "add") {
			checkout2.add(nome)
		} else if (operacao == "remove") {
			checkout2.remove(nome)
		}
		then:
		checkout2.getTotalPrice() == price
		checkout2.getTotalDiscount() == discount
		where:
		operacao | nome | price || discount
		"add"    | "D"  | 15    || 0
		"add"    | "A"  | 65    || 0
		"add"    | "B"  | 95    || 0
		"add"    | "A"  | 145   || 0
		"add"    | "B"  | 160   || 15
		"add"    | "A"  | 190   || 35
		"remove" | "A"  | 160   || 15
		"remove" | "B"  | 145   || 0
	}

	def "teste3"() {
		when:
		if (operacao == "add") {
			checkout3.add(nome)
		} else if (operacao == "remove") {
			checkout3.remove(nome)
		}
		then:
		checkout3.getTotalPrice() == price
		checkout3.getTotalDiscount() == discount
		where:
		operacao | nome | price | discount
		"add"    | "C"  | 20    | 0
		"add"    | "C"  | 40    | 0
		"add"    | "C"  | 40    | 20
		"add"    | "C"  | 60    | 20
		"remove" | "C"  | 40    | 20
		"remove" | "C"  | 40    | 0
	}

	def "teste4"() {
		when:
		if (operacao == "add") {
			checkout4.add(nome)
		} else if (operacao == "remove") {
			checkout4.remove(nome)
		}
		then:
		checkout4.getTotalPrice() == price
		checkout4.getTotalDiscount() == discount
		where:
		operacao | nome | price | discount
		"add"    | "C"  | 20    | 0
		"add"    | "B"  | 50    | 0
		"add"    | "B"  | 65    | 15
		"remove" | "B"  | 50    | 0

	}


	def "teste5"() {
		when:
		if (operacao == "add") {
			checkout5.add(nome)
		} else if (operacao == "remove") {
			checkout5.remove(nome)
		}
		then:
		checkout5.getTotalPrice() == price
		checkout5.getTotalDiscount() == discount
		where:
		operacao | nome | price | discount
		"add"    | "E"  | 10    | 10
		"add"    | "E"  | 20    | 20
		"add"    | "E"  | 30    | 30
		"add"    | "E"  | 40    | 40
		"add"    | "E"  | 50    | 50
		"add"    | "E"  | 60    | 60
		"add"    | "E"  | 70    | 70
		"add"    | "E"  | 80    | 80


	}

	Caixa novoCaixa() {

		Caixa checkout = new Caixa()

		checkout.adicionarProduto(produtoA)
		checkout.adicionarProduto(produtoB)
		checkout.adicionarProduto(produtoC)
		checkout.adicionarProduto(produtoD)
		checkout.adicionarProduto(produtoE)

		return checkout;

	}


}
