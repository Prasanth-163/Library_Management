import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        while (true) {
            System.out.println("\n1 Add Book");
            System.out.println("2 View Books");
            System.out.println("3 Remove Book");
            System.out.println("4 Update Book");
            System.out.println("5 Register User");
            System.out.println("6 Issue Book");
            System.out.println("7 Return Book");
            System.out.println("8 Search Book");
            System.out.println("9 Exit");

            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Id: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Title: ");
                String t = sc.nextLine();
                System.out.print("Author: ");
                String a = sc.nextLine();
                lib.addBook(new Book(id, t, a, false));
            }

            else if (ch == 2) {
                lib.viewBooks();
            }

            else if (ch == 3) {
                System.out.print("Id: ");
                int id = sc.nextInt();
                lib.removeBook(id);
            }

            else if (ch == 4) {
                System.out.print("Id: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("New Title: ");
                String t = sc.nextLine();
                System.out.print("New Author: ");
                String a = sc.nextLine();
                lib.updateBook(id, t, a);
            }

            else if (ch == 5) {
                System.out.print("User Id: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                lib.registerUser(new User(id, name));
            }

            else if (ch == 6) {
                System.out.print("Book Id: ");
                int b = sc.nextInt();
                System.out.print("User Id: ");
                int u = sc.nextInt();
                lib.issueBook(b, u);
            }

            else if (ch == 7) {
                System.out.print("Book Id: ");
                int b = sc.nextInt();
                lib.returnBook(b);
            }

            else if (ch == 8) {
                sc.nextLine();
                System.out.print("Keyword: ");
                String k = sc.nextLine();
                lib.searchBook(k);
            }

            else {
                break;
            }
        }
    }
}