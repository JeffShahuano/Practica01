package javamysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JavaMysql {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/tienda?user=root&password=mysqladmin";
            Connection connect = DriverManager.getConnection(url);
            Statement statement = connect.createStatement();
            String query = "SELECT * FROM dispositivo";
            ResultSet resultSet = statement.executeQuery(query);
            String format = "|%1$-3d|%2$-17s|%3$-5d\n";
            while(resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                 int precio = resultSet.getInt("precio");
                int tipo = resultSet.getInt("tipo");
//                System.out.format(format, nombre, precio, tipo);
            }
            Scanner scan = new Scanner(System.in);
            System.out.println("¿Qué deseas hacer: INSERTAR / BORRAR / CONSULTAR / ACTUALIZAR ?");
            String accion = scan.nextLine();
            if(accion.equals("INSERTAR")) {
                scan = new Scanner(System.in);
                System.out.println("Ingresa el nombre");
                String nombre = scan.nextLine();
                
                scan = new Scanner(System.in);
                System.out.println("Ingresa el precio");
                int precio = scan.nextInt();
                
                scan = new Scanner(System.in);
                System.out.println("Ingresa el tipo");
                int tipo = scan.nextInt();
                
                query = "{call insertar_dispositivo(?,?)}";
                CallableStatement stmt = connect.prepareCall(query);
                stmt.setString(1 , nombre);
                stmt.setInt(2, precio);
                stmt.setInt(3, tipo);
                stmt.execute();
                 
//                query = "INSERT INTO producto VALUES (?, ?, ?)";
//                PreparedStatement ps = connect.prepareStatement(query);
//                ps.setInt(1, Integer.parseInt(idProd));
//                ps.setString(2, descProd);
//                ps.setInt(3, Integer.parseInt(precio));
//                ps.executeUpdate();
            } else if (accion.equals("BORRAR")) {
                scan = new Scanner(System.in);
                System.out.println("Ingresa el nombre");
                String nombre = scan.nextLine();
                
                query = "DELETE FROM dispositivo WHERE nombre = ?";
                PreparedStatement ps = connect.prepareStatement(query);
                ps.setString(1,(nombre));
                ps.executeUpdate();
            } else if (accion.equals("ACTUALIZAR")) {
                scan = new Scanner(System.in);
                System.out.println("Ingrese el nombre");
                String nombre = scan.nextLine();
                
                scan = new Scanner(System.in);
                System.out.println("Ingresa el precio");
                String precio = scan.nextLine();
                
                scan = new Scanner(System.in);
                System.out.println("Ingresa el tipo");
                String tipo = scan.nextLine();
                
                query = "UPDATE dispositivo SET precio = ?, tipo = ? WHERE nombre = ?";
                PreparedStatement ps = connect.prepareStatement(query);
                ps.setString(1, nombre);
                ps.setInt(2, Integer.parseInt(precio));
                ps.setInt(3, Integer.parseInt(tipo));
                ps.executeUpdate();
            }
            resultSet.close();
            statement.close();
            connect.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}