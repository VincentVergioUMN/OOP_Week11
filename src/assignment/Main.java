package assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

class ExcessiveFailedLoginException extends Exception {
    public ExcessiveFailedLoginException(String msg) {
        super(msg);
    }
}

class AuthenticationException extends Exception {
    public AuthenticationException(String msg) {
        super(msg);
    }
}

class User {
    private String firstName;
    private String lastName;
    private char gender;
    private String address;
    private String username;
    private String password;

    public User(String firstName, String lastName, char gender, String address, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = Character.toUpperCase(gender);
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public String getFullName() { return firstName + " " + lastName; }

    public void printDetails() {
        System.out.println("Nama Depan : " + firstName);
        System.out.println("Nama Belakang : " + lastName);
        System.out.println("Jenis Kelamin (L/P) : " + gender);
        System.out.println("Alamat : " + address);
        System.out.println("Username : " + username);
        System.out.println("Password : " + password);
    }
}

public class Main {
    private List<User> listOfUser = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private int failedLoginCounter = 0;
    private final int MAX_FAILED_LOGIN = 5;

    public void initialize() {
        User admin = new User("John", "Doe", 'L', "Jl. Merpati No. 1 RT 1 RW 1, Banten", "admin", "admin");
        listOfUser.add(admin);
    }

    private boolean login(User u, String username, String password) {
        return u.getUsername().equals(username) && u.getPassword().equals(password);
    }

    public void handleLogin() {
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.print("Pilihan : ");
        String pilihan = sc.nextLine().trim();

        if (pilihan.equals("2")) {
            handleSignUp();
            return;
        }

        System.out.print("Username : ");
        String username = sc.nextLine().trim();
        System.out.print("Password : ");
        String password = sc.nextLine().trim();

        try {
            boolean success = false;
            for (User u : listOfUser) {
                if (login(u, username, password)) {
                    System.out.println();
                    System.out.println("Selamat Datang!" + u.getFullName());
                    success = true;
                    failedLoginCounter = 0;
                    break;
                } else {
                }
            }

            if (!success) {
                failedLoginCounter++;
                if (failedLoginCounter >= MAX_FAILED_LOGIN) {
                    throw new ExcessiveFailedLoginException("Anda telah mencapai jumlah batas login");
                } else {
                    throw new AuthenticationException("Username / password salah");
                }
            }
        } catch (ExcessiveFailedLoginException ex) {
            System.out.println(ex.getMessage());
        } catch (AuthenticationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void handleSignUp() {
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.print("Pilihan : ");
        String pilihan = sc.nextLine().trim();

        if (!pilihan.equals("2")) {
            return;
        }

        System.out.print("Nama Depan : ");
        String firstName = sc.nextLine().trim();
        System.out.print("Nama Belakang : ");
        String lastName = sc.nextLine().trim();
        System.out.print("Jenis Kelamin (L/P) : ");
        String genderInput = sc.nextLine().trim();
        char gender = (genderInput.isEmpty() ? 'L' : genderInput.toUpperCase().charAt(0));
        System.out.print("Alamat : ");
        String address = sc.nextLine().trim();
        System.out.print("Username : ");
        String username = sc.nextLine().trim();

        if (username.length() <= 8) {
            System.out.println("Username harus lebih dari 8 karakter");
            return;
        }

        System.out.print("Password : ");
        String password = sc.nextLine().trim();

        if (!isValidPassword(password)) {
            System.out.println("Password harus mengandung huruf besar, angka, minimum 6 karakter dan maksimum 16 karakter");
            return;
        }

        User newUser = new User(firstName, lastName, gender, address, username, password);
        listOfUser.add(newUser);
        System.out.println("User telah berhasil didaftarkan");
        System.out.println();
        newUser.printDetails();
    }

    private boolean isValidPassword(String pw) {
        if (pw.length() < 6 || pw.length() > 16) return false;
        boolean hasUpper = Pattern.compile("[A-Z]").matcher(pw).find();
        boolean hasDigit = Pattern.compile("[0-9]").matcher(pw).find();
        return hasUpper && hasDigit;
    }

    public void run() {
        initialize();

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.print("Pilihan : ");
            String choice = sc.nextLine().trim();

            if (choice.equals("1")) {
                handleLogin();
            } else if (choice.equals("2")) {
                handleSignUp();
            } else {
                System.out.println("Pilihan tidak valid. Ketik 1 atau 2.");
            }

            System.out.println();
            System.out.print("Lanjutkan program? (y/n) : ");
            String lanjut = sc.nextLine().trim();
            if (!lanjut.equalsIgnoreCase("y")) {
                System.out.println("Terima kasih.");
                break;
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }
}