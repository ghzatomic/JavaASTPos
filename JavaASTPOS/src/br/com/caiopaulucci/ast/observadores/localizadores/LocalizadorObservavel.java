package br.com.caiopaulucci.ast.observadores.localizadores;

import java.util.Observable;

import br.com.caiopaulucci.ast.model.dto.ResultadoBuscaDTO;

public class LocalizadorObservavel extends Observable {

	public void entrouEmUmaFuncao(ResultadoBuscaDTO nome){
		setChanged();
		notifyObservers(nome);
	}
	
}
