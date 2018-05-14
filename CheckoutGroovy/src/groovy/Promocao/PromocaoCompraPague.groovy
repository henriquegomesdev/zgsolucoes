package groovy.Promocao

//Promoção pegue Compre X e pague Y
class PromocaoCompraPague implements Promocao {

	private int precoEspecial
	private int quantidade

	PromocaoCompraPague(int quantidade, int precoEspecial) {
		this.quantidade = quantidade
		this.precoEspecial = precoEspecial
	}

	//Calculo do desconto de acordo com a quantidade e o preço unitário
	@Override
	int desconto(int quantidadeInformada, int precoUnitario) {
		int quantidadePromocao = quantidadeInformada / quantidade
		return quantidadePromocao * (quantidade * precoUnitario - precoEspecial)

	}

}