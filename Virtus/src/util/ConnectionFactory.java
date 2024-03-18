package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//Responsável por retornar uma conexão com o banco de dados


public class ConnectionFactory
{
    //O driver é a ponte entro o banco de dados e a aplicação
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    //Caminho da conexão até o banco de dados
    public static final String URL = "jdbc:mysql://localhost:3306/virtus";
    public static final String USER = "root";
    public static final String PASS = "";
    
    // O static diz que o método pode ser chamado sem antes ser criada uma instância desta classe
    
    public static Connection getConection()
    {
        //O try....catch é usado para o tratamento de erros. Erros críticos!
        try
        {
            //Carregar o driver para dentro da aplicação
            Class.forName(DRIVER);
            //Realizando a conexão com o banco de dados
            return DriverManager.getConnection(URL, USER, PASS);
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Erro na conexão com o banco de dados", ex);
        }
    }
    
    public static void closeConnection(Connection connection)
    {
        try
        {
            if(connection != null)
            {
                connection.close();
            }
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
        }
    }
    
    public static void closeConnection(Connection connection, Statement statement)
    {
        try
        {
            if(connection != null)
            {
                connection.close();
            }
            if(statement != null)
            {
                statement.close();
            }
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
        }
    }
    
    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet)
    {
        try
        {
            if(connection != null)
            {
                connection.close();
            }
            if(statement != null)
            {
                statement.close();
            }
            if(resultSet != null)
            {
                resultSet.close();
            }
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
        }
    }
    
    
    
}