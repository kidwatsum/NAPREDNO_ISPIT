package zad1;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

class Item implements Comparable<Item>{
    String city;
    String code;
    String name;
    int age;

    public Item(String city,String code, String name, int age) {
        this.city=city;
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("+++++ %s +++++\n%s %s %d\n",city,code,name,age);
    }

    @Override
    public int compareTo(Item o) {
        return Comparator.comparing(Item::getName).thenComparing(Item::getAge).compare(this,o);
    }
}

class Audition{
    Map<String,Item> participants;

    public Audition( ) {
        this.participants = new HashMap<>();
    }
    void addParticpant(String city,String code,String name,int age){
        Item participant=new Item(city,code,name,age);
        if(!participants.isEmpty()){
            if(participants.get(city).equals(city)){
                long val=0;
                val=participants.values().stream().filter(p->p.getCode().equals(code)).count();
                if(val==0){
                    participants.put(city,participant);
                }
            }
        }else participants.put(city,participant);
    }
    void listByCity(String city){
        participants.values().stream().filter(x->x.city.equals(city)).forEach(System.out::println);
    }
}

public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticpant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}

