import java.net.*;
import java.io.*;

public class Client1 {

    public static void main(String[] ar) {
       // String LoginName;
    //    String Password;
    //    String Command;
        int serverPort = 5555; // здесь обязательно нужно указать порт к которому привязывается сервер.
        String address = "127.0.0.1"; // это IP-адрес компьютера, где исполняется наша серверная программа.
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.

        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Yes! I just got hold of the program.");

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом. 
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            // Создаем поток для чтения с клавиатуры.
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;

            System.out.print("Напиши что-то :");


            while (true) {
                line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                out.writeUTF(line); // отсылаем введенную строку текста серверу.
                out.flush(); // заставляем поток закончить передачу данных.
                line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                System.out.println( line);
                System.out.println();

              /*  if (in.readUTF().equals("ALLOWED"))
                {
                    do
                    {
                        System.out.print("< Telnet Prompt >");
                        Command=keyboard.readLine();
                        out.writeUTF(Command);
                        if(!Command.equals("quit"))
                        {
                            System.out.println(in.readUTF());
                        }
                    }while(!Command.equals("quit"));
                }
                socket.close();*/
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}