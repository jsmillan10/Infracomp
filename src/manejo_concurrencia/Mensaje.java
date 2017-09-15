package manejo_concurrencia;

public class Mensaje {

	public static Buffer buffer;
	private int contenido;

	public Mensaje(int con)
	{
		contenido = con;
	}

	public void enviar() throws InterruptedException
	{
		boolean seMando = false;
		while(!seMando)
		{
			seMando = buffer.depositar(this);
		}
		
	}


}
