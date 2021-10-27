package com.nuchange;

import com.google.gson.Gson;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;



public class Main {

    public static void main(String[] args) {
        System.out.println("ENTER COMMAND :");
        Scanner sc = new Scanner(System.in);
        String url, key;
        String[] command;
        ArrayList<UrlClass> urlDetails = new ArrayList<>();
        UrlClass urlClass = new UrlClass();
        command = sc.nextLine().split(" ");
        while (command[0] != "exit") {
            switch (command[0]) {
                case "storeurl":
                    urlDetails.add(inserturl(command[1]));
                    break;

                case "get":
                    geturl(command[1], urlDetails);
                    break;

                case "count":
                    counturl(command[1], urlDetails);
                    break;

                case "list":
                    listurls(urlDetails);
                    break;
                case "exit":
                    command[0] = "exit";

                default:
                    command[0] = "exit";
            }
            System.out.println("Enter the next operation: ");

            command = sc.nextLine().split(" ");

        }
    }
    private static UrlClass inserturl(String url) {
        UrlClass urlClass = new UrlClass();
        urlClass.url = url;
        urlClass.key = shortUUID();
        urlClass.count =0;
        System.out.println("Inserted");
        return  urlClass;


    }

    private static void geturl(String url, ArrayList<UrlClass> urlClass) {
        if(urlClass.isEmpty()){
            System.out.println("Sorry the url does not exist in the database!");
            return;
        }
        for(UrlClass eachUrlClass : urlClass){
            if(eachUrlClass.url.equals(url)){
                eachUrlClass.count=eachUrlClass.count+1;
                System.out.println("Unique Short key: "+eachUrlClass.key);
                return;
            }

        }
        System.out.println("URL does not exist");
    }

    public static void counturl(String url, ArrayList<UrlClass> urlClass){
        if(urlClass.isEmpty()){
            System.out.println("Sorry the url does not exist in the database!");
            return;
        }
        for(UrlClass eachUrlClass : urlClass){
            if(eachUrlClass.url.equals(url)){
                System.out.println("The count value : "+eachUrlClass.count);
                return;
            }

        }
        System.out.println("URL does not exist");
    }

    public static void listurls(ArrayList<UrlClass> urlClass){
        if(urlClass.isEmpty()){
            System.out.println("Sorry the database is empty! \n");
            return;
        }
        Gson gson = new Gson();
        String jsonArray = gson.toJson(urlClass);
        System.out.println(jsonArray);

    }



    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }


}
class UrlClass{

    String url;
    String key;
    Integer count;
}


