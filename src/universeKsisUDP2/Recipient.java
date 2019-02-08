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
	System.out.println("����� �������");
	try { // ����� �����
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
	     * ��������� ������� ��������: ���� � ������� 10 ������ �� ������� �� ������
	     * ������, ����� ������ �������������
	     */
	    s.setSoTimeout(10000);
	    while (true) {
		s.receive(pac);
		os.write(data);
		os.flush();
	    }

	} catch (SocketTimeoutException e) {
	    // ���� ����� �������� �����
	    os.close();
	    System.out.println("������� ����� ��������, ����� ������ ��������");
	}
    }
}