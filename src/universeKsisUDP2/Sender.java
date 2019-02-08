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

	    /* ���� � ������ example.mp3 ������ ������ � ����� ������� */
	    FileInputStream fr = new FileInputStream(new File("example.mp3"));
	    DatagramPacket pac;

	    while (fr.read(data) != -1) {
		// �������� ������ ������
		pac = new DatagramPacket(data, data.length, addr, 18020);
		s.send(pac);// ����������� ������
	    }
	    fr.close();
	    System.out.println("���� ���������");

	} catch (UnknownHostException e) {
	    // �������� ����� ����������
	    e.printStackTrace();

	} catch (SocketException e) {
	    // �������� ������ ��� �������� ������
	    e.printStackTrace();

	} catch (FileNotFoundException e) {
	    // �� ������ ������������ ����
	    e.printStackTrace();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}