import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    private static final String PATHKEY = "C://ИБ//ЛР1//КАЮ_key.key";

    private static void writeToFile(String path, byte[] buffer) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {

        FileInputStream fin1 = new FileInputStream(args[0]);
        FileInputStream fin2 = new FileInputStream(PATHKEY);
        try {
            byte[] bufferValue = new byte[8];
            byte[] bufferKey = new byte[16];

            fin1.read(bufferValue, 0, bufferValue.length);
            int[] value = Transfer.byteToInt(bufferValue);

            fin2.read(bufferKey, 0, bufferKey.length);
            int[] key = Transfer.byteToInt(bufferKey);

            writeToFile(args[1], Tea.decrypt(value, key));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            fin1.close();
            fin2.close();
        }
    }
}
