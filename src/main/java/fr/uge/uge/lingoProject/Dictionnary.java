package fr.uge.uge.lingoProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class Dictionnary {

  public static TreeSet<String> loadDictionnary(String filepath) throws FileNotFoundException {
    var c = new TreeSet<String>();
    try (Scanner sc = new Scanner(new File(filepath))) {
      while (sc.hasNextLine()) {
        var line = sc.nextLine().trim().toLowerCase();
        c.add(line);
      }
    }
    return c;
  }
}
