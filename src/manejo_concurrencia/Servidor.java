package manejo_concurrencia;

public class Servidor extends Thread{

	public static Buffer buffer;		

	public void run() 
	{
		try {
			
			while(buffer.numClientes!=0)
			{
				Mensaje men = buffer.consumir();
				synchronized (men) {
					men.setContenido(men.getContenido()+1);
					men.notify();
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
