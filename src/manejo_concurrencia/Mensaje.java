package manejo_concurrencia;

public class Mensaje {

	public static Buffer buffer;
	private int contenido;
	
	public Mensaje(int con)
	{
		contenido = con;
	}

	public boolean  enviar() throws InterruptedException
	{
		
		return buffer.depositar(this);

	}
	
	public static void ultimoMensaje()
	{
		buffer.numClientes--;
	}

	public int getContenido() {
		return contenido;
	}

	public void setContenido(int contenido) {
		this.contenido = contenido;
	}
	
	
}
