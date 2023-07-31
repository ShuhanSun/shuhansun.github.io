package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest {
    static class Message{
        int id;
        Type type;

        public Message(int id, Type type) {
            this.id = id;
            this.type = type;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "id=" + id +
                    ", type=" + type +
                    '}';
        }
    }
    enum Type{
        ALG("ALG"), BAN("BAN"), SIM("SIM");

        String name;

        Type(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        List<Message> list = new ArrayList<Message>();
        for (int i = 0; i < 4; i++) {
            list.add(new Message(i, Type.ALG));
        }
        for (int i = 10; i < 14; i++) {
            list.add(new Message(i, Type.BAN));
        }
        for (int i = 20; i < 24; i++) {
            list.add(new Message(i, null));
        }

        Map<Type, List<Message>> collect = list.stream().filter(message -> message.type != null).collect(Collectors.groupingBy(message -> message.type));
        System.out.println(collect);
    }
}
