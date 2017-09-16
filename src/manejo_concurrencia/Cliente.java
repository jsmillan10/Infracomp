package manejo_concurrencia;


public class Cliente extends Thread{

	private int numMensajes;

	public Cliente(int numMen)
	{
		numMensajes = numMen;
	}

	public void run()
	{
		try {
//			sleep(1000); //Si los clientes se demoran en arrancar -- OK.
			for (int i = 0; i < numMensajes; i++) {
				boolean seMando = false;
				Mensaje actual = new Mensaje (i); 
				while(!seMando)
				{
					seMando = actual.enviar();
					if(!seMando)
						yield();
				}
//				sleep(1000); Si el cliente se demora en dormirse en el mensaje -- NOT OK.
				synchronized (actual) {
					actual.wait();				
				}
			}
			//TODO conectar servidor
			Mensaje.ultimoMensaje();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		int numClientes = 7;
		int numServidores = 9;
		int capacidadBuffer = 15;

		Buffer buffer = new Buffer(capacidadBuffer,numClientes);
		Mensaje.buffer = buffer;
		Servidor.buffer = buffer;

		for (int i = 0; i < numServidores; i++) {
			(new Servidor()).start();
		}

		for (int i = 0; i < numClientes; i++) {
			(new Cliente(10)).start();
		}
	}

}
