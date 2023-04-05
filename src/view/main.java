package view;

import java.util.concurrent.Semaphore;

import controller.CozimentoController;

public class main {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		
		for(int i=1;i<6;i++) {
			Thread pratos = new CozimentoController(i, semaforo);
			pratos.start();
		}
	}

}
