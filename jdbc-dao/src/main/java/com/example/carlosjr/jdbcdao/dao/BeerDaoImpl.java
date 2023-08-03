package com.example.carlosjr.jdbcdao.dao;

import com.example.carlosjr.jdbcdao.domain.Beer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
@RequiredArgsConstructor
public class BeerDaoImpl implements BeerDao {

    private final DataSource dataSource;

    @Override
    public Beer getBeerById(Long id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM beer where id = " + id); //SQL Inject exploit

            if (resultSet.next()){
                return Beer.builder()
                        .id(id)
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .build();
            }

        }
        catch (Exception exception){
            exception.printStackTrace();
        } finally {

            try {

                closeConnection(statement, resultSet, null, connection);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public Beer getBeerByIdPrepared(Long id) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {

            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM beer where id = ?"); //Bind value
            ps.setLong(1, id);
            resultSet = ps.executeQuery();

            if (resultSet.next()){
                return Beer.builder()
                        .id(id)
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .build();
            }

        }
        catch (Exception exception){
            exception.printStackTrace();
        } finally {
            try {
                closeConnection(null, resultSet, ps, connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return null;

    }

    @Override
    public Beer saveNewBeer(Beer beer) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("INSERT INTO beer (name, description) VALUES (?, ?)"); //Bind value
            ps.setString(1, beer.getName());
            ps.setString(2, beer.getDescription());

            ps.execute();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");


            if (resultSet.next()){
                Long savedId = resultSet.getLong(1);
                return this.getBeerByIdPrepared(savedId);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeConnection(statement, resultSet, ps, connection);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    public Beer updateBeer(Beer beer) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("UPDATE beer SET name =  ?, description = ? WHERE beer.id = ? "); //Bind value
            ps.setString(1, beer.getName());
            ps.setString(2, beer.getDescription());
            ps.setLong(3, beer.getId());

            ps.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeConnection(null, resultSet, ps, connection);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return this.getBeerByIdPrepared(beer.getId());
    }

    @Override
    public void deleteBeer(Long beerId) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("DELETE FROM beer WHERE id = ? "); //Bind value
            ps.setLong(1, beerId);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeConnection(null, null, ps, connection);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private void closeConnection(Statement statement, ResultSet resultSet, PreparedStatement ps, Connection connection) throws SQLException {
        if (statement != null){
            statement.close();
        }

        if (resultSet != null){
            resultSet.close();
        }

        if (ps != null){
            ps.close();
        }

        if (connection != null){
            connection.close();
        }
    }
}
