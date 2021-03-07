package co.edu.ufps.shell;

import co.edu.ufps.commons.Converter;
import co.edu.ufps.commons.Exception.ConverterException;
import co.edu.ufps.commons.Exception.ValidationException;
import co.edu.ufps.commons.ImageFormat;
import co.edu.ufps.core.ImageConverter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ImageFormat [] extenciones = ImageFormat.values();

        File image;

        String direccion = "C:/Users/Cadm/Downloads/Nueva carpeta/harmony.jpg";
        String nuevadireccion="";

        int opcion=0;

        Scanner sc = new Scanner(System.in);

        Boolean continuar = true;

        while (continuar) {

            System.out.println("Bienvenido ConverterUFPS");


            while(true){

                System.out.println("Por favor ingrese la ubicacion de la imagen que desee convertir:");

                direccion=sc.nextLine();
                image = new File(direccion);

                if(!image.exists()){
                    System.out.println("----HA INGRESADO UNA RUTA INVALIDA INTENTE DE NUEVO----");
                    continue;
                }
                break;
            }


            Converter converter = new ImageConverter();
            converter.source(image);

            while(true) {
                System.out.println("Ingrese la ruta donde desea guardar la imagen: ");

                nuevadireccion = sc.nextLine();
                File ruta = new File(nuevadireccion);

                if (!ruta.exists()) {
                    System.out.println("----HA INGRESADO UNA RUTA INVALIDA INTENTE DE NUEVO----");
                    continue;
                }
                break;
            }
            String ext = Files.probeContentType(image.toPath()).split("/")[1];
            ext = ext.equalsIgnoreCase("jpeg") ? "JPG" : ext;

            ArrayList<String> valids = new ArrayList<>();

            for (ImageFormat extension : extenciones) {
                if (ImageFormat.valueOf(ext.toUpperCase()).compareTo(extension) != 0) {
                    valids.add(extension.toString());
                }
            }



            System.out.println("¿A que formato desea convertir la imagen?");

            int i=1;
            for(String x : valids ) {
                System.out.println(i+". "+x.toString());
                i++;
            }

            opcion= sc.nextInt();
            sc.skip("\n");
            while(opcion != 1 && opcion !=2 && opcion!= 3){
                System.out.println("**** HA INGRESADO UN NUMERO INVALIDO POR FAVOR INTENTE DE NUEVO ****");
                System.out.println("¿A que formato desea convertir la imagen?");

                int j=1;
                for(String x : valids ) {
                    System.out.println(j+". "+x.toString());
                    j++;
                }
                opcion=sc.nextInt();
                System.out.println(""+opcion);
                sc.skip("\n");
            }


            converter.setFolder(nuevadireccion);
            converter.setName("imagenconvertida");

            if(opcion == 1) {

                converter.defineFormat(ImageFormat.valueOf(valids.get(opcion-1).toString()));
                run(converter);

            } else if(opcion == 2) {
                converter.defineFormat(ImageFormat.valueOf(valids.get(opcion-1).toString()));
                run(converter);

            } else {
                converter.defineFormat(ImageFormat.valueOf(valids.get(opcion-1).toString()));
                run(converter);
            }

            System.out.println("Su imagen ha sido convertida :) \n Su imagen se encuentra: "+nuevadireccion);

            System.out.println("¿Desea convertir una nueva imagen? 1.Si 2.No" );

            opcion=sc.nextInt();
            sc.skip("\n");

            if(opcion==2){
                continuar=false;
            }


        }
    }

    public static void run(Converter converter) {
        try {
            File imageOut = converter.startProcess();

        } catch (ConverterException | ValidationException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }
}
