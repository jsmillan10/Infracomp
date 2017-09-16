package manejo_concurrencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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

	public static void main(String[] args) throws NumberFormatException, IOException {

		Properties pro = new Properties();
		
		pro.load(new FileInputStream(new File("datos/datos.properties")));
		int numClientes = Integer.parseInt(pro.getProperty("numClientes"));
		int numServidores = Integer.parseInt(pro.getProperty("numServidores"));
		int capacidadBuffer = Integer.parseInt(pro.getProperty("capacidadBuffer"));
		
		int[] mensajesPorCliente = new int[numClientes];
		
		for (int i = 0; i < mensajesPorCliente.length; i++)  {
			mensajesPorCliente[i] = Integer.parseInt(pro.getProperty("cliente" + (i+1)));
		}
		
		Buffer buffer = new Buffer(capacidadBuffer,numClientes);
		Mensaje.buffer = buffer;
		Servidor.buffer = buffer;

		for (int i = 0; i < numServidores; i++) {
			(new Servidor()).start();
		}

		for (int i = 0; i < numClientes; i++) {
			(new Cliente(mensajesPorCliente[i])).start();
		}
	}

}
