package manejo_concurrencia;

import sun.misc.Queue;

public class Buffer {

	
	public int tamCola;
	public int capacidad;
	public Queue<Mensaje> espera;
	public int numClientes;

	public Buffer(int capacidad, int num)
	{
		espera = new Queue<>();
		this.tamCola = 0;
		this.capacidad = capacidad;
		numClientes = num;
		
	}

	public synchronized boolean depositar(Mensaje m) throws InterruptedException
	{
		boolean seMando = false;
		if(tamCola < capacidad)
		{
			System.out.println(m.getContenido());
			tamCola++;	
			espera.enqueue(m);
			seMando = true;
			notifyAll();
//			synchronized (m) {
//				m.wait();				
//			}			
		}
		return seMando;
	}
	
	public synchronized Mensaje consumir() throws InterruptedException
	{
		while(tamCola == 0)
		{
			wait();
			if(numClientes==0)
			{
				return null;
			}
		}
		tamCola--;
		return espera.dequeue();
		
	}
	
	public synchronized int darNumClientes() {
		return numClientes;
	}

}
