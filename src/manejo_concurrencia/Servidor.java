package manejo_concurrencia;

public class Servidor extends Thread{

	public static Buffer buffer;		

	public void run() 
	{
		try {
//			sleep(1000); //Si los servidores se demoran en arrancar -- OK.
			while(buffer.numClientes!=0)
			{
				Mensaje men = buffer.consumir();
//				sleep(1000); // Si el servidor se demora en responder -- OK.
				if (men != null){
					synchronized (men) {
						men.setContenido(men.getContenido()+1);
						men.notify();
					}
				}
			}
			synchronized (buffer) {				
				buffer.notifyAll();
			}
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
