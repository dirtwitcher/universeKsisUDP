package universeKsisUDP2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

public class Recipient {

    public static void main(String[] args) {
	File file = new File("example2.mp3");
	System.out.println("ѕрием данныхЕ");
	try { // прием файла
	    acceptFile(file, 18020, 65507);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private static void acceptFile(File file, int port, int pacSize) throws IOException {
	byte data[] = new byte[pacSize];
	DatagramPacket pac = new DatagramPacket(data, data.length);
	DatagramSocket s = new DatagramSocket(port);
	FileOutputStream os = new FileOutputStream(file);
	try {
	    /*
	     * установка времени ожидани€: если в течение 10 секунд не прин€то ни одного
	     * пакета, прием данных заканчиваетс€
	     */
	    s.setSoTimeout(10000);
	    while (true) {
		s.receive(pac);
		os.write(data);
		os.flush();
	    }

	} catch (SocketTimeoutException e) {
	    // если врем€ ожидани€ вышло
	    os.close();
	    System.out.println("»стекло врем€ ожидани€, прием данных закончен");
	}
    }
}