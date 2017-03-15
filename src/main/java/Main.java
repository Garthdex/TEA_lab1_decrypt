import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    private static void writeToFile(String path, byte[] buffer) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printHelpToConsole() {
        System.out.println("Вы должны ввести параметры в таком порядке:" + "\n"
                + "java -jar decrypt.jar param1 param2 param3" + "\n"
                + "где param1 - полный путь к зашифрованному файлу" + "\n"
                + "пример: C:/ИБ/ЛР1/1.txt.enc" + "\n"
                + "где param2 - полный путь к записи расшифрованного файла" + "\n"
                + "пример: C:/ИБ/ЛР1/1.txt.dec" + "\n"
                + "где param3 - полный путь к файлу ключа" + "\n"
                + "пример: C:/ИБ/ЛР1/Key.key" + "\n");
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0 || args[0].equals("?")) {
            printHelpToConsole();
            return;
        }

        FileInputStream fin1 = new FileInputStream(args[0]);
        FileInputStream fin2 = new FileInputStream(args[2]);
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
