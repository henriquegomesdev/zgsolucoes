package groovy.Promocao

//Promoção pegue Compre 2 e Leve 3
class PromocaoCompraLeva implements Promocao {

	private int quantidadeLeve
	private int quantidadePaga

	PromocaoCompraLeva(int quantidadeLeve, int quantidadePaga) {
		this.quantidadeLeve = quantidadeLeve
		this.quantidadePaga = quantidadePaga
	}

	//Calculo do desconto de acordo com a quantidade e o preço unitário
	@Override
	int desconto(int quantidadeInformada, int precoUnitario) {
		int quantidadePromocao = quantidadeInformada / quantidadeLeve
		return quantidadePromocao * (quantidadeLeve - quantidadePaga) * precoUnitario

	}

}