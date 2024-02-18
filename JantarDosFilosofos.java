// Estudando Escalonamento de Processos
// Jantar dos Filosofos

// Em uma mesa redonda, cinco filósofos estão sentados. Cada filósofo tem um prato de espaguete à sua frente.
// Cada filosofo pensa ou come. Enquanto pensa, não interage com os outros filósofos.
// Cada filósofo precisa de dois garfos para comer. Entre cada par de pratos de espaguete, há um garfo.
// Se o garfo estiver disponível, o filósofo o pega e come por um tempo aleatório.Caso contrário, o filósofo espera até que os garfos estejam disponíveis.

// Precisamos criar um algortimo em que os filósofos possam comer e pensar sem que haja deadlock e que o maximo de recursos da mesa esteja sendo utilizados

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

public class JantarDosFilosofos {
	
	public static List<Filosofo> filosofos = new ArrayList<Filosofo>();
	public static List<Garfo> garfos = new ArrayList<Garfo>();
	public static Queue<Filosofo> filaParaComer = new Queue<Filosofo>();

	public static void main(String[] args){
		
		// Inicializa os filósofos e os garfos

		int numeroDeFilosofos = 5;	
		int numeroDeGarfos = numeroDeFilosofos;

		for(int i = 0; i < numeroDeGarfos; i++){
			garfos.add(new Garfo(i));
		}
		

		for(int i = 0; i < numeroDeFilosofos; i++){
				
			Garfo garfoEsquerda;			
			Garfo garfoDireita;
				
			if(i == 0){
				garfoEsquerda = garfos.get(numeroDeFilosofos - 1);
			} else {
				garfoEsquerda = garfos.get(i - 1);
			}
				
			if(i == numeroDeFilosofos - 1){
				garfoDireita = garfos.get(0);
			} else {
				garfoDireita = garfos.get(i + 1);
			}
			
			filosofos.add(new Filosofo(i, Estado.PENSANDO, garfoEsquerda, garfoDireita));
		}

		// Ciclo de Tarefas
		for(true){
			// Verifica se algum filosofo acabou de comer para liberar os garfos
			for(Filosofo filosofo : filosofos){
				if(filosofo.getEstado() == Estado.TERMINOU_DE_COMER){
					filosofo.getGarfoEsquerda().setDisponivel(true);
					filosofo.getGarfoDireita().setDisponivel(true);
					filosofo.setEstado(Estado.PENSANDO);
				}
			}

			// Atualiza a fila de espera para comer
			for(Filosofo filosofo : filosofos){
				if(filaParaComer.isEmpty()){
					if(filosofo.getEstado() == Estado.PENSANDO){
						filaParaComer.add(filosofo);
					}
				}
			}

			// Todos os filosofos que podem comer, comem
		}
	}
}

public class Filosofo {	
	private int id;
	private Estado estado;
	private Garfo garfoEsquerda;
	private Garfo garfoDireita;
	private int numeroDeVezesQueComeu;

	public Filosofo(int id, Estado estado, Garfo garfoEsquerda, Garfo garfoDireita){
		this.id = id;
		this.estado = estado;
		this.garfoEsquerda = garfoEsquerda;
		this.garfoDireita = garfoDireita;
		this.numeroDeVezesQueComeu = 0;
	}	

	public boolean comer(){
		if(this.garfoEsquerda.getDisponivel() && this.garfoDireita.getDisponivel() && (this.estado == Estado.PENSANDO)){
			this.estado = Estado.COMENDO;
			this.garfoEsquerda.setDisponivel(false);
			this.garfoDireita.setDisponivel(false);
			this.numeroDeVezesQueComeu++;
			this.estado = Estado.TERMINOU_DE_COMER;
			return true;
		} else {
			this.estado = Estado.PENSANDO;
			return false;
		}
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

	public int getNumeroDeVezesQueComeu(){
		return this.numeroDeVezesQueComeu;
	}
	public void setNumeroDeVezesQueComeu(int numeroDeVezesQueComeu){
		this.numeroDeVezesQueComeu = numeroDeVezesQueComeu;
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
	
	PENSANDO, COMENDO, TERMINOU_DE_COMER;
	
}
