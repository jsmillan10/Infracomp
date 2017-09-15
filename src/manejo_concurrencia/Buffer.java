package manejo_concurrencia;

import sun.misc.Queue;

public class Buffer {

	public int capacidad;
	public int tamCola;
	public Queue<Mensaje> espera;

	public Buffer(int capacidad)
	{
		this.capacidad = capacidad;
		tamCola = 0;
	}

	public synchronized boolean depositar(Mensaje m) throws InterruptedException
	{
		boolean seMando = false;
		if(capacidad > 0)
		{
			capacidad--;	
			tamCola++;
			espera.enqueue(m);
			seMando = true;
			wait();
		}
		return seMando;
	}

}
