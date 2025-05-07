package jdev.mentoria.lojavirtual;

import jdev.mentoria.lojavirtual.util.ValidaCNPJ;
import jdev.mentoria.lojavirtual.util.ValidaCPF;

public class TesteCPFCNPJ {
	
	public static void main(String[] args) {
		
		boolean isCnpj = ValidaCNPJ.isCNPJ("31.465.436/0001-81");
		
		System.out.println("CNPJ Válido: " + isCnpj);
		
		boolean isCpf = ValidaCPF.isCPF("356.783.810-51");
		
		System.out.println("CPF Válido: " + isCpf);
		
		
	}

}
