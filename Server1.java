import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;import java.net.*;
import java.io.*;

public class Server1 /*implements Runnable*/ {

   static class kek implements Runnable {

        private Socket socket;

        public kek(Socket socket) {
            this.socket = socket;
        }

       String LoginName;
       String Password;

        // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
        public void run() {

            try {
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();


                // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
                DataInputStream in = new DataInputStream(sin);
                DataOutputStream out = new DataOutputStream(sout);


                String line = null;

                while (true)

                {

                    try {
                        line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                        System.out.println("прислали : " + line);
                        //line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.

                        out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                        System.out.flush(); // заставляем поток закончить передачу данных.
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    System.out.println("ждем новое смс...");
                    System.out.println();

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }


    public static void main(String args[]) {
        int port = 5555; // случайный порт (может быть любое число от 1025 до 65535)

        try {
            // BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Ждем народ...");

            while (true) {
                Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
                System.out.println("Новый клиент");

                new Thread(new kek(socket)).start();
                /*Runnable r = new Server
                Thread t = new Thread(r);
                t.start();*/
           }

        } catch (Exception x) {
            x.printStackTrace();

        }
    }

}