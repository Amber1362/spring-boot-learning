package in.jdbc.repository;

import in.jdbc.model.Student;

import java.sql.*;

public class StudentRepository {

    String url = "jdbc:mysql://localhost:3306/student_db";
    String username = "root";
    String password = "Amber@365";

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public void createStudent(Student student) {

        try {
            connection = DriverManager.getConnection(url, username, password);

            String sql = """
                          
                    INSERT INTO students(name, email, age)
                          VALUES(?, ?, ?)
                          """;

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getAge());

            int rowAffected = preparedStatement.executeUpdate();

            if(rowAffected == 1) {
                System.out.println("Create Operation successful");
            } else {
                System.out.println("Create Operation failed");
            }

        } catch (SQLException e) {

            System.out.println("Database connection failed");
        }
        finally {
            try {
                preparedStatement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

            try {
            connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateStudent() {

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String sql = "UPDATE students SET age = 25 " +
                    "WHERE id = 1";

            int result = statement.executeUpdate(sql);

            if(result == 1) {
                System.out.println("Update Operation successfully");
            } else {
                System.out.println("Update Operation failed");
            }

            connection.close();

        } catch (SQLException e) {

            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }

    public void deleteStudent() {

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String sql = "DELETE from students WHERE id = 1";

            int result = statement.executeUpdate(sql);

            if(result == 1) {
                System.out.println("Delete Operation successfully");
            } else {
                System.out.println("Delete Operation failed");
            }

            connection.close();
        } catch (SQLException e) {

            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }

    public void getStudentById() {

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String sql = "SELECT id, name, email, age" +
                    " FROM students where id = 2";

            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.next();

            Student student = mapRow(resultSet);

            System.out.println(student);

            connection.close();
        } catch (SQLException e) {

            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }

    public void completeCrud() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String sql = "SELECT id, name, email, age" +
                    " FROM students where id = 2";

            boolean result = statement.execute(sql);

            if(result) {
                ResultSet resultSet = statement.getResultSet();
            } else {
                int rowAffected = statement.getUpdateCount();
            }

            connection.close();
        } catch (SQLException e) {

            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }

    private Student mapRow(ResultSet resultSet) throws SQLException {
        Student student = new Student();

        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setAge(resultSet.getInt("age"));

        return student;
    }
}
