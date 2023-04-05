package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CozimentoController extends Thread {
	int pratos;
	Semaphore semaforo;
	
	public CozimentoController(int pratos, Semaphore semaforo) {
		this.pratos=pratos;
		this.semaforo=semaforo;
		}
	
	@Override
	public void run() {
		PrepararPratos();
	}
	
	private void PrepararPratos() {
		if(pratos%2==0) {
			PratosPar();
		}
		if(pratos%2==1) {
			PratosImpar();
		}

	}
	
	private void PratosPar() {
	 Random gerador = new Random(); 
	 double tempototal = gerador.nextDouble(0.61)+0.6;
		System.out.println(tempototal + " " + pratos);
		double progresso = 0.1;
		double percentual;
		System.out.println("O Prato Lasanha a bolanhesa #" + pratos + " iniciou o processo");
		while(progresso<=tempototal) {
			percentual = progresso/tempototal;
			try {
				sleep(100);
				System.out.printf("O Prato Lasanha a bolanhesa #" + pratos + " está em %.2f %n " , percentual );
				progresso +=0.1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("O Prato Lasanha a bolanhesa#" + pratos + " está pronto"  );
		
		//Realiza Entrega
		try {
			semaforo.acquire();
			EntregaPedido();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
	}
	
	
	private void PratosImpar() {
		Random gerador = new Random();
		double tempototal = gerador.nextDouble(0.3)+0.5;
		System.out.println(tempototal + " " + pratos);
		double progresso = 0.1;
		double percentual;
		System.out.println("O Prato Sopa de cebola #" + pratos + " iniciou o processo");
		while(progresso<=tempototal) {
			percentual = progresso/tempototal;
			try {
				sleep(100);
				System.out.printf("O Prato Sopa de Cebola #" + pratos + " está em %.2f %n " , percentual );
				progresso +=0.1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("O Prato Sopa de Cebola #" + pratos + " está pronto"  );
		
		//Realiza Entrega
				try {
					semaforo.acquire();
					EntregaPedido();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					semaforo.release();
				}

	}
	
	//Entrega Pedido
	private void EntregaPedido() {
		try {
			sleep(500);
			if(pratos%2==1) {
				System.out.println("O Prato Sopa de Cebola #" + pratos + " foi ENTREGUE"  );
			}
			if(pratos%2==0) {
				System.out.println("O Prato Lasanha a bolanhesa #" + pratos + " foi ENTREGUE"  );
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
