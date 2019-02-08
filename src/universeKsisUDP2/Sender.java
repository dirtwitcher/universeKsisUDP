package universeKsisUDP2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Sender {

    public static void main(String[] args) {
	try {
	    byte[] data = new byte[65507];
	    DatagramSocket s = new DatagramSocket();
	    InetAddress addr = InetAddress.getLocalHost();

	    /* файл с именем example.mp3 должен лежать в корне проекта */
	    FileInputStream fr = new FileInputStream(new File("example.mp3"));
	    DatagramPacket pac;

	    while (fr.read(data) != -1) {
		// создание пакета данных
		pac = new DatagramPacket(data, data.length, addr, 18020);
		s.send(pac);// отправление пакета
	    }
	    fr.close();
	    System.out.println("Файл отправлен");

	} catch (UnknownHostException e) {
	    // неверный адрес получателя
	    e.printStackTrace();

	} catch (SocketException e) {
	    // возникли ошибки при передаче данных
	    e.printStackTrace();

	} catch (FileNotFoundException e) {
	    // не найден отправляемый файл
	    e.printStackTrace();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}