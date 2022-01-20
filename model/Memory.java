package calculator.model;

import java.util.ArrayList;
import java.util.List;

import calculator.view.Calculator;


public class Memory {
	
	private static final Memory instance = new Memory();
	
	private final List<ObserverMemory> observers = 
			new ArrayList<>();
	
	public enum inputType {
		AC, DIV, NUMBER, MULT, MINUS, PLUS, DOT, EQUALS, EXIT, MINIMIZE, SIGNAL
	};
	
	private String displayTxt = ""; //TEXTO MOSTRADO NO INSTANTE
	private String buffer = ""; //ARMAZENA O VALOR ANTERIOR QUANDO SELECIONADA UMA OPERAÇÃO
	private boolean replace; //PARA DIZER QUANDO UMA INPUT IRÁ SUBSTITUIR O VALOR DO DISPLAY 
	private inputType lastOperation; //ARMAZENA ÚLTIMA OPERAÇÃO SELECIONADA PELO USER
	
	private Memory() {
		
	}
	
	public static Memory getInstance() {
		return instance;
	}
	
	public void addObserver(ObserverMemory observer) {
		observers.add(observer);
	}
	
	public String getDisplayTxt() {
		return displayTxt.isEmpty() ? "0" : displayTxt;
	}
	
	public void manageCommands(String value) {
		
		inputType input = manageInputType(value);
		System.out.println(input);
		if(input == null) 
			return;
		else if(input.equals(inputType.EXIT)) {

		}
		else if(input.equals(inputType.MINIMIZE)) {
			return;
		}
		else if("AC".equals(value) || displayTxt.equals("0")) {
			//RESETA TODOS OS PARÂMETROS DE CONTROLE PARA O PADRÃO
			displayTxt = "";
			buffer = "";
			replace = false;
			lastOperation = null;
		}
		else if(input.equals(inputType.SIGNAL) && displayTxt.contains("-")){
			displayTxt = displayTxt.substring(1);
		}
		else if(input.equals(inputType.SIGNAL) && !displayTxt.isEmpty()){
			displayTxt = "-"+displayTxt;
		}
		else if(displayTxt.isEmpty() && input.equals(inputType.DOT)) {
			//LÓGICA PARA APARECER "0.X" QUANDO O USER DESEJA TRABALHAR COM
			//VALORES X TAL QUE 0<X<1
			displayTxt = "0.";
		}
		
		else if(input.equals(inputType.NUMBER) || input.equals(inputType.DOT)) {
			//IMPLEMENTA A LÓGICA DE SUBSTITUIR LOGO APÓS O USER DIGITAR O PRIMEIRO VALOR,
			//SELECIONAR UMA OPERAÇÃO E DEPOIS ENTRAR COM O SEGUNDO VALOR QUE IRÁ SUBSTITUIR
			//O PRIMEIRO...
			displayTxt = replace ? value : displayTxt + value;
			//"SET" PARA FALSO, POIS SUBTITUÇÃO JÁ FOI FEITA...VIDA QUE SEGUE
			replace = false;
		} else { 
			//CASO O USER TENHA DIGITADO ALGO DIFERENTE DE UM NÚMERO OU DE UM PONTO,
			//ENTRAMOS NO CASO EM QUE ELE AGORA ESTÁ FAZENDO UMA OPERAÇÃO...
			replace = true;
			displayTxt = operationResult();
			buffer = displayTxt;
			lastOperation = input;
		}
		
		
		observers.forEach(o -> o.alternateValue(getDisplayTxt()));
		
	}

	private String operationResult() {
		
		if(lastOperation == null || lastOperation.equals(inputType.EQUALS)) {
			return displayTxt;
		}
		
		double bufferNumber = Double.parseDouble(buffer);
		double actualNumber = Double.parseDouble(displayTxt);
		
		double result = 0;
		
		if(lastOperation.equals(inputType.PLUS)) {
			result = bufferNumber + actualNumber;
		}else if(lastOperation.equals(inputType.MINUS)) {
			result = bufferNumber - actualNumber;
		}else if(lastOperation.equals(inputType.MULT)) {
			result = bufferNumber * actualNumber;
		}else if(lastOperation.equals(inputType.DIV)) {
			result = bufferNumber / actualNumber;
		}
		String stringResult = Double.toString(result);
		
		boolean isInteger = stringResult.endsWith(".0");
		
		return isInteger ? stringResult.replace(".0", "") : stringResult;
	}

	private inputType manageInputType(String value) {
		
		try {
			Integer.parseInt(value);
			return inputType.NUMBER;
		} catch(Exception e) {

			if(value.equals("AC"))
				return inputType.AC;
			
			else if(value.equals("/"))
				return inputType.DIV;
			else if(value.equals("*"))
				return inputType.MULT;
			else if(value.equals("-"))
				return inputType.MINUS;
			else if(value.equals("+"))
				return inputType.PLUS;
			else if(value.equals(".") && !displayTxt.contains("."))
				return inputType.DOT;
			else if(value.equals("="))
				return inputType.EQUALS;
			else if(value.equals("\u274c")) 
				return inputType.EXIT;
			else if(value.equals("\u005f"))
				return inputType.MINIMIZE;
			else if(value.equals("\u00b1"))
				return inputType.SIGNAL;
			return null;
			
		}
	}
	
}
