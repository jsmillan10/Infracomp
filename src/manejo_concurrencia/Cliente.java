package manejo_concurrencia;


public class Cliente extends Thread{

	
	private Mensaje[] mensajes;

	public Cliente(int numMen)
	{
		mensajes = new Mensaje [numMen];
		for (int i = 0; i < mensajes.length; i++) {
			mensajes[i]= new Mensaje (i);
		}
	}


	
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub

	}

}
