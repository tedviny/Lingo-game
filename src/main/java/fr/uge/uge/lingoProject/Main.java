package fr.uge.uge.lingoProject;

import java.io.*;
import java.text.Normalizer;
import java.util.*;

public class Main {
  static Scanner entree;


  public static void main(String[] args) throws IOException {
    System.out.println("**************** Projet Lingo Game *******************");
    System.out.println();
    String filepath = args[0];
    var dictionary = Dictionnary.loadDictionnary(filepath);
    String lineDic = null;
    InputStreamReader file = null;
    LineNumberReader lineReader = null;
    BufferedWriter bufferwriter = null;
    entree = new Scanner(System.in);
    System.out.print("Entrez le nombre de mot à deviner: ");
    Scanner sc = new Scanner(System.in);
    var extractWord = new TreeSet<String>();
    int lengthWord = sc.nextInt();
    for (String word : dictionary) {
      if (word.length() == lengthWord) {
        extractWord.add(word);
      }
    }
   try {
      String filenameOut = ("./src/Document.txt");
     bufferwriter = new BufferedWriter(new FileWriter(filenameOut));
      for (String mt : extractWord) {
        bufferwriter.write(mt);
        bufferwriter.append("\n");
      }
     bufferwriter.flush();
     bufferwriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    Vector<String> randArray = new Vector<String>();
    file = new InputStreamReader(new FileInputStream("./src/Document.txt"));
    lineReader = new LineNumberReader(file);
    while ((lineDic = lineReader.readLine()) != null) {
      randArray.add(lineDic);
    }
    String wordGenerated = randWord(randArray );
    System.out.println("mot à deviner: " + wordGenerated + "\n");
    wordGenerated=removeAccent(wordGenerated);
    wordGenerated = wordGenerated.toUpperCase();
    System.out.println(""+wordGenerated.length());
    String nMot = "";
    for (int n = 1; n < wordGenerated.length(); n++) {
      nMot += wordGenerated.charAt(n);
    }

    String newWord = nMot.replaceAll("[A-Z]", "*");
    String MotDevine = wordGenerated.charAt(0) + newWord;

    playLingo(wordGenerated, MotDevine);
  }

  public static String randWord(Vector v) {
    Random generator = new Random();
    int lig = generator.nextInt(v.size() - 1);
    return (String) v.get(lig);
  }
  public static String removeAccent(String text)
  {
    return text == null ? null
      : Normalizer.normalize(text, Normalizer.Form.NFD)
      .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
  }

  public static void playLingo(String word, String word1) {
    int len = word.length();
    String numberOfWord = "";
    for (int j = 1; j < word.length(); j++) {
      numberOfWord += word.charAt(j);
    }
    String newWord = numberOfWord.replaceAll("[A-Z]", "*");
    String wordToFind = word.charAt(0) + newWord;
    System.out.println(wordToFind);

   for (int j =0; j<=15; j++) {
        Scanner guessing = new Scanner(System.in);
        CheckLengthWord checkL = new CheckLengthWord();
        char[] cArray = new char[len];
        System.out.println("Proposez un mot: ");
        word1 = guessing.nextLine();
        word1 = word1.toUpperCase();

        if (checkL.checkLengthOfWord(word1, len)) {
        } else {
          System.out.println("Veuillez entrer un mot de: " + len + " caractères");
          continue;
        }
        if (word1.equals(word)) {
          break;
        }

        for (int i = 0; i < len; i++) {
          if (word1.toCharArray()[i] == word.toCharArray()[i]) {
            cArray[i] = word.toCharArray()[i];
          } else {
            cArray[i] = '*';

          }
        }
        System.out.println("Votre essai " + String.valueOf(cArray));

      }

      if (word1.equals(word)) {
        System.out.println("bravo vous avez reussi! " + word);
        return;
      } else
        System.out.println("\n Vous avez rater, le bon mot etait: " + word);
  }

}











