package br.com.tecnonoticias.HangmanJudge;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

class Main {

	public static void main(String[] args) {

		Main uva498 = new Main();
		uva498.Begin();

	}
	
	static String ReadLn(int maxLg) {// utility function to read from stdin
		
		byte lin[] = new byte[maxLg];
		int lg = 0, car = -1;
		String line = "";

		try {
			while (lg < maxLg) {
				car = System.in.read();
				if ((car < 0) || (car == '\n'))
					break;
				lin[lg++] += car;
			}
		} catch (IOException e) {
			return (null);
		}

		if ((car < 0) && (lg == 0))
			return (null); // eof
		return (new String(lin, 0, lg));
	}

	void Begin() {
		String linha = "";

		Hashtable<String, Integer> judge = new Hashtable<String, Integer>();

		try {
			FileInputStream entrada;
			entrada = new FileInputStream("input1");
			InputStreamReader entradaFormatada = new InputStreamReader(entrada);
			BufferedReader entradaString = new BufferedReader(entradaFormatada);

			while (entradaString.ready()) {
				linha = entradaString.readLine();

				judge.clear();
				Integer round = Integer.parseInt(linha);
				if (round != -1) {

					linha = entradaString.readLine();

					int size = linha.length();
					Main.hashTable(judge, linha);

					linha = entradaString.readLine();

					String result = Main.forca(judge, linha, size);
					System.out.println("Round " + round.toString());
					System.out.println(result);
				}

			}
			entradaString.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	static String retiraRepet(String retirar){
		String[] name = retirar.split("");
		String retira = "";
		
		for (int i = 0; i < retirar.length(); i++) {
			for (int j = i+1; j < retirar.length(); j++) {
				if (name[i].equals(name[j])) {
					name[j] = "";
				}
			}
			retira += name[i];
		}
		return retira;
	}

	static String forca(Hashtable<String, Integer> judge, String line, int size) {
		String retirar = retiraRepet(line);
		String[] name = retirar.split("");
//		int size = retirar.length();
		int dead = 0;
		for (int i = 0; i < name.length; i++) {
			
			if (judge.containsKey(name[i])) {

				size = (size - judge.get(name[i]));

			} else {

				dead = (dead + 1);
			}
			if (size <= 0) {

				return "You win.";

			} else if (dead == 7) {

				return "You lose.";
			}

		}

		return "You chickened out.";
	}

	static void hashTable(Hashtable<String, Integer> judge, String line) {

		String[] name = line.split("");

		for (int i = 0; i < name.length; i++) {

			if (judge.containsKey(name[i])) {

				judge.put(name[i], judge.get(name[i]) + 1);

			} else {
				judge.put(name[i], 1);
			}
		}

	}

}
