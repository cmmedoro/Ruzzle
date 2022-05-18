package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	
	//qui metto i metodi ricorsivi
	public List<Pos> trovaParola(String parola, Board board) {
		//la ricorsione parte solo se trovo la prima lettera di 'parola' nella matrice
		for(Pos p : board.getPositions()) {
			if(board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0)) {
				//posso far partire la ricorsione
				List<Pos> parziale = new ArrayList<Pos>();
				parziale.add(p);
				if(cerca(parola, board, 1, parziale))
					return parziale;
			}
		}
		return null; //se parola assente nella matrice 
	}
	
	/**
	 * Metodo ricorsivo per cercare se una parola del dizionario si trova nella matrice
	 * @param parola La parola del dizionario da cercare nella matrice
	 * @param board La matrice del gioco
	 * @param livello Il numero di lettere già identificate
	 * @param parziale L'insieme delle posizioni corrispondenti alle lettere già trovate
	 */
	
	public boolean cerca(String parola, Board board, int livello, List<Pos> percorso) {
		//caso terminale
		if(livello == parola.length()) {
			return true;
		}
		//caso normale
		//Prendo l'ultima lettera inserita, tutte le lettere adiacenti e vediamo se una di esse corrisponde alla seguente nella parola
		Pos ultima = percorso.get(percorso.size()-1);
		List<Pos> adiacenti = board.getAdjacencies(ultima);
		for(Pos a : adiacenti) {
			//devo controllare che la posizione non sia già stata usata in precedenza, oltre a controllare la lettera
			if(!percorso.contains(a) && board.getCellValueProperty(a).get().charAt(0) == parola.charAt(livello)) {
				//continuo il percorso: faccio andare avanti la ricorsione
				percorso.add(a);
				//inutile fare backtracking se ho trovato la parola:
				if(cerca(parola, board, livello+1, percorso))
					return true; //uscita rapida dalla ricorsione
				percorso.remove(percorso.size()-1); //backtracking: tolgo ultimo elemento inserito perchè potrebbe non essere poi valido
			}
		}
		return false; //non ho trovato la parola nella matrice
	}
}
