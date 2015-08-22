package br.com.caiopaulucci.ast.util;

import java.util.Observer;

import br.com.caiopaulucci.ast.model.dto.ResultadoBuscaDTO;
import br.com.caiopaulucci.ast.observadores.localizadores.LocalizadorObservavel;

public class ObserverUtil {

	private static LocalizadorObservavel localizadorObservavel ;
	
	static{
		localizadorObservavel = new LocalizadorObservavel();
	}
	
	public static void addObservadorAoLocalizador(Observer o){
		localizadorObservavel.addObserver(o);
	}
	
	public static void notificaObservers(ResultadoBuscaDTO teste){
		localizadorObservavel.entrouEmUmaFuncao(teste);		
		
	}
	
}
