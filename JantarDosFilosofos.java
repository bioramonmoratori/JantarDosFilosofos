// Estudando Escalonamento de Processos
// Jantar dos Filosofos

// Em uma mesa redonda, cinco filósofos estão sentados. Cada filósofo tem um prato de espaguete à sua frente.
// Cada filosofo pensa ou come. Enquanto pensa, não interage com os outros filósofos.
// Cada filósofo precisa de dois garfos para comer. Entre cada par de pratos de espaguete, há um garfo.
// Se o garfo estiver disponível, o filósofo o pega e come por um tempo aleatório.Caso contrário, o filósofo espera até que os garfos estejam disponíveis.

// Precisamos criar um algortimo em que os filósofos possam comer e pensar sem que haja deadlock e que o maximo de recursos da mesa esteja sendo utilizados


public class JantarDosFilosofos {

	public static void main(String[] args){
		
		Garfo garfo1 = new Garfo(1);
		Garfo garfo2 = new Garfo(2);
		Estado estado = Estado.PENSANDO;
		Filosofo filosofo1 = new Filosofo(1, estado, garfo1, garfo2);	
	}

}

public class Filosofo {
	
	private int id;
	private Estado estado;
	private Garfo garfoEsquerda;
	private Garfo garfoDireita;
	
	public Filosofo(int id, Estado estado, Garfo garfoEsquerda, Garfo garfoDireita){
		this.id = id;
		this.estado = estado;
		this.garfoEsquerda = garfoEsquerda;
		this.garfoDireita = garfoDireita;
	}	

	public void comer(){
	
	}

	// Getters e Setters
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	public Estado getEstado(){
		return this.estado;
	}
	public void setEstado(Estado estado){
		this.estado = estado;
	}
	
	public Garfo getGarfoEsquerda(){
		return this.garfoEsquerda;
	}	
	public void setGarfoEsquerda(Garfo garfoEsquerda){
		this.garfoEsquerda = garfoEsquerda;
	}

	public Garfo getGarfoDireita(){
		return this.garfoDireita;
	}
	public void setGarfoDireita(Garfo garfoDireita){
		this.garfoDireita = garfoDireita;
	}
}

public class Garfo {
	
	private int id;
	private boolean disponivel;
	
	public Garfo(int id){
		this.id = id;
		this.disponivel = true;
	}
	
	// Getters e Setters	
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}

	public boolean getDisponivel(){
		return this.disponivel;
	}
	public void setDisponivel(boolean disponivel){
		this.disponivel = disponivel;
	}
}

public enum Estado {
	
	PENSANDO, COMENDO;
	
}
