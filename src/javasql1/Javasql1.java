/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasql1;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

/**
 *
 * @author JoseDavid
 */
public class Javasql1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/practicadep?autoReconnect=true&useSSL=false", "root", "1234");
            Statement sentencia = conexion.createStatement();

            int numfilas;
            int op=0;
            while(op!=9){
                System.out.println("\nIntroduce una opción:\n");
                System.out.println("1.Insertar departamento");
                System.out.println("2.Insertar empleado");
                System.out.println("3.Modificar departamento");
                System.out.println("4.Modificar empleado");
                System.out.println("5.Listar departamentos");
                System.out.println("6.Listar empleados");
                System.out.println("7.Borrar departamento");
                System.out.println("8.Borrar empleado");
                System.out.println("9.Salir");
                System.out.println("-------------------------\n");
                op = Integer.parseInt(br.readLine());
                int id=0;
                int error=1;
            
                switch(op){
                    case 1:
                        ResultSet resultado2 = sentencia.executeQuery("select * from DEPARTAMENTOS");
                        while(resultado2.next()){
                            id = Integer.parseInt(resultado2.getString(1));
                        }
                        String dnombre="";
                        String loc="";

                        while(error!=0){

                            System.out.println("-- Nuevo Departamento --");
                            System.out.print("Nombre:");
                            dnombre = br.readLine();
                            System.out.print("Localización:");
                            loc = br.readLine();
                            if(dnombre.equals("") || loc.equals("")){
                                System.out.println("Error, hay campos sin completar.");
                                error=1;
                            }
                            else error=0;
                        }
                            id=id+1;
                            String sql=String.format("INSERT INTO Departamentos(dept_no, dnombre, loc) VALUES(%d, '%s', '%s')", id, dnombre, loc);
                            numfilas = sentencia.executeUpdate(sql);
                            System.out.println("Departamento insertado...");



                        resultado2.close();
                        break;

                    case 2:
                        ResultSet resultado3 = sentencia.executeQuery("select * from EMPLEADOS");
                        while(resultado3.next()){
                            id = Integer.parseInt(resultado3.getString(1));
                        }
                        String apellido="";
                        String oficio="";
                        String fecha_alta="";

                        int dept;
                        error=1;

                            System.out.println("-- Nuevo Empleado --");
                            System.out.print("Apellido:");
                            apellido = br.readLine();
                            System.out.print("Oficio:");
                            oficio = br.readLine();
                            System.out.print("Fecha alta:");
                            fecha_alta = br.readLine();
                            System.out.print("Salario:");
                            int salario = Integer.parseInt(br.readLine());



                        System.out.println("Departamento:");
                        int cont=0;
                        resultado3 = sentencia.executeQuery("select * from Departamentos");
                        while(resultado3.next()){
                            cont++;
                            System.out.println(resultado3.getString(1)+"."+resultado3.getString(2)); 
                        }
                        dept=Integer.parseInt(br.readLine());
                        id=id+1;
                        sql=String.format("INSERT INTO Empleados(emp_no, apellido, oficio, fecha_alta, salario, dept_no) VALUES(%d, '%s', '%s', '%s', %d, %d)", id, apellido, oficio, fecha_alta, salario, dept);
                        numfilas = sentencia.executeUpdate(sql);
                        System.out.println("Empleado insertado...");
                        resultado3.close();
                        break;

                    case 3:
                        ResultSet resultado4 = sentencia.executeQuery("select * from DEPARTAMENTOS");
                        System.out.println("Selecciona un departamento para editarlo:");
                        while(resultado4.next()){
                            System.out.println(resultado4.getString(1)+" - "+resultado4.getString(2)+" - "+resultado4.getString(3));
                        }
                        id = Integer.parseInt(br.readLine());
                        System.out.println("-- Editando Departamento --");
                        System.out.print("Nombre:");
                        dnombre = br.readLine();
                        System.out.print("Localización:");
                        loc = br.readLine();
                        System.out.println(id);
                        sql=String.format("UPDATE DEPARTAMENTOS SET dnombre='%s', loc='%s' where dept_no=%d", dnombre, loc, id);
                        numfilas = sentencia.executeUpdate(sql);
                        System.out.println(sql);
                        System.out.println("Departamento modificado...");
                        resultado4.close();
                        break;

                    case 4:
                        ResultSet resultado5 = sentencia.executeQuery("select * from Empleados");
                        System.out.println("Selecciona un empleado para editarlo:");
                        while(resultado5.next()){
                            System.out.println(resultado5.getString(1)+" - "+resultado5.getString(2)+" - "+resultado5.getString(3));
                        }
                        id = Integer.parseInt(br.readLine());
                        System.out.println("-- Editando Empleado --");
                        System.out.print("Apellido:");
                        apellido = br.readLine();
                        System.out.print("Oficio:");
                        oficio = br.readLine();
                        System.out.print("Fecha alta:");
                        fecha_alta = br.readLine();
                        System.out.print("Salario:");
                        salario = Integer.parseInt(br.readLine());
                        System.out.println(id);
                        System.out.println("Departamento:");
                        cont=0;
                        resultado5 = sentencia.executeQuery("select * from Departamentos");
                        while(resultado5.next()){
                            cont++;
                            System.out.println(resultado5.getString(1)+"."+resultado5.getString(2)); 
                        }
                        dept=Integer.parseInt(br.readLine());
                        sql=String.format("UPDATE Empleados SET apellido='%s', oficio='%s', fecha_alta='%s', salario=%d, dept_no=%d where emp_no=%d", apellido, oficio, fecha_alta, salario, dept, id);
                        numfilas = sentencia.executeUpdate(sql);
                        System.out.println("Empleado modificado...");
                        resultado5.close();
                        break;

                    case 5:
                        ResultSet resultado6 = sentencia.executeQuery("select * from DEPARTAMENTOS");
                        while(resultado6.next()){
                            System.out.println(resultado6.getString(1)+" - Nombre: "+resultado6.getString(2)+" - Localización: "+resultado6.getString(3));
                        }
                        resultado6.close();
                        break;

                    case 6:
                        ResultSet resultado10 = sentencia.executeQuery("select * from DEPARTAMENTOS");
                        System.out.println("Departamento en el que quieres buscar:");
                        int busqueda;
                        while(resultado10.next()){
                            System.out.println(resultado10.getString(1)+" - "+resultado10.getString(2));
                        }
                        
                        busqueda = Integer.parseInt(br.readLine());
                        sql=String.format("select emp_no, apellido, oficio, fecha_alta, salario from EMPLEADOS where dept_no=%d", busqueda);
                        ResultSet resultado7 = sentencia.executeQuery(sql);
                        while(resultado7.next()){
                            System.out.println(resultado7.getString(1)+" - Apellido: "+resultado7.getString(2)+" - Oficio: "+resultado7.getString(3)+
                                    " - Fecha Alta: "+resultado7.getString(4)+" - Salario: "+resultado7.getString(5));
                        }
                        resultado7.close();
                        resultado10.close();
                        break;

                    case 7:
                        ResultSet resultado8 = sentencia.executeQuery("select * from DEPARTAMENTOS");
                        System.out.println("Selecciona un departamento para borrarlo:");
                        while(resultado8.next()){
                            System.out.println(resultado8.getString(1)+" - "+resultado8.getString(2)+" - "+resultado8.getString(3));
                        }
                        id = Integer.parseInt(br.readLine());
                        sql=String.format("Delete from Departamentos where dept_no=%d", id);
                        numfilas = sentencia.executeUpdate(sql);
                        System.out.println("Departamento borrado...");
                        resultado8.close();
                        break;

                    case 8:
                        ResultSet resultado9 = sentencia.executeQuery("select * from Empleados");
                        System.out.println("Selecciona un empleado para borrarlo:");
                        while(resultado9.next()){
                            System.out.println(resultado9.getString(1)+" - "+resultado9.getString(2)+" - "+resultado9.getString(3));
                        }
                        id = Integer.parseInt(br.readLine());
                        sql=String.format("Delete from Empleados where emp_no=%d", id);
                        numfilas = sentencia.executeUpdate(sql);
                        System.out.println("Empleado borrado...");
                        resultado9.close();
                        break;

                    case 9:
                        System.out.println("Adiós...");
                        break;

                    default:
                        System.out.println("Opción incorrecta");
                        break;

                }
            }
            
            

            sentencia.close();
            conexion.close();
        }
        catch(Exception e){}
         
    }
    
}
