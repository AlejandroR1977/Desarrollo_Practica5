package Array;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ContadorCoincidencias {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        
        int wordCount = 0;
        int palabrasiguales = -1;
        String palabraUsuario;

        if (args.length == 0) {
            System.out.println("Archivo no especificado");
            System.exit(0);
        }

        FileReader fi = null;
        try {
            fi = new FileReader(args[0]);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(-1);
        }
        System.out.println("Introduzca la palabra que desea contar");
        palabraUsuario = lector.nextLine();
        palabraUsuario = palabraUsuario.toLowerCase().trim();

        BufferedReader inputFile = new BufferedReader(fi);

        String textLine = null;


        String delimiters = "\\s+|,\\s*|\\.\\s*|\\;\\s*|\\:\\s*|\\!\\s*|\\¡\\s*|\\¿\\s*|\\?\\s*|\\-\\s*"
                + "|\\[\\s*|\\]\\s*|\\(\\s*|\\)\\s*|\\\"\\s*|\\_\\s*|\\%\\s*|\\+\\s*|\\/\\s*|\\#\\s*|\\$\\s*";
        ArrayList<String> list = new ArrayList<String>();
        try {
            while ((textLine = inputFile.readLine()) != null) {
   

                if (textLine.trim().length() == 0) {
                    continue;
                }
                String words[] = textLine.split( delimiters );

                wordCount += words.length;

                for (String theWord : words) {

                    theWord = theWord.toLowerCase().trim();

                    boolean isNumeric = true;

                    try {
                        Double num = Double.parseDouble(theWord);
                    } catch (NumberFormatException e) {
                        isNumeric = false;
                    }
                    if (isNumeric) {
                        continue;
                    }
                    if ( !list.contains(theWord) ) {
                        list.add(theWord);
                    }
                    else{
                        for(int i = 0; i<list.size();i++){
                            String analizador = list.get(i);
                            if(analizador.equals(palabraUsuario)){
                                palabrasiguales++;
                            }
                        }
                    }
                }
            }
            inputFile.close();
            fi.close();
           if(palabrasiguales <= 0){
               System.out.println("No hay coincidencias");
        }else{
            System.out.println("Hay " + (palabrasiguales) + " coincidencias");}
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
