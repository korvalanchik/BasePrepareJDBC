package com.basejdbc.dao;

import com.basejdbc.model.*;
import com.basejdbc.prefs.Prefs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao {
    public static final String DB_FIND_LONGEST_PROJECT = "longestProject";
    public static final String DB_FIND_MAX_PROJECT_CLIENT = "maxProject";
    public static final String DB_FIND_MAX_SALARY_WORKER = "maxSalary";
    public static final String DB_FIND_YOUNGEST_ELDEST_WORKER = "youngestEldest";
    public static final String DB_PRINT_PROJECT_PRICE = "projectPrice";

    private final PreparedStatement crfindMaxProjectsClientSt;
    private final PreparedStatement findMaxSalaryWorkerSt;
    private final PreparedStatement findYoungestEldestWorkersSt;
    private final PreparedStatement findLongestProjectSt;
    private final PreparedStatement printProjectPriceSt;
    public ServiceDao(Connection connection) throws IOException, SQLException {
        String sql;
        String maxProject = new Prefs().getString(DB_FIND_MAX_PROJECT_CLIENT);
        sql = String.join("\n", Files.readAllLines(Paths.get(maxProject)));
        crfindMaxProjectsClientSt = connection.prepareStatement(sql);

        String maxSalary = new Prefs().getString(DB_FIND_MAX_SALARY_WORKER);
        sql = String.join("\n", Files.readAllLines(Paths.get(maxSalary)));
        findMaxSalaryWorkerSt = connection.prepareStatement(sql);

        String youngestEldest = new Prefs().getString(DB_FIND_YOUNGEST_ELDEST_WORKER);
        sql = String.join("\n", Files.readAllLines(Paths.get(youngestEldest)));
        findYoungestEldestWorkersSt = connection.prepareStatement(sql);

        String longestProj = new Prefs().getString(DB_FIND_LONGEST_PROJECT);
        sql = String.join("\n", Files.readAllLines(Paths.get(longestProj)));
        findLongestProjectSt = connection.prepareStatement(sql);

        String price = new Prefs().getString(DB_PRINT_PROJECT_PRICE);
        sql = String.join("\n", Files.readAllLines(Paths.get(price)));
        printProjectPriceSt = connection.prepareStatement(sql);
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> maxProjectCountClientList = new ArrayList<>();
        try (ResultSet rs = crfindMaxProjectsClientSt.executeQuery()) {
            while (rs.next()) {
                MaxProjectCountClient maxProjectCountClient = new MaxProjectCountClient();
                maxProjectCountClient.setName(rs.getString("NAME"));
                maxProjectCountClient.setProjectCount(rs.getInt("PROJECT_COUNT"));
                maxProjectCountClientList.add(maxProjectCountClient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxProjectCountClientList;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> maxSalaryWorkerList = new ArrayList<>();
        try (ResultSet rs = findMaxSalaryWorkerSt.executeQuery()) {
            while (rs.next()) {
                MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker();
                maxSalaryWorker.setName(rs.getString("NAME"));
                maxSalaryWorker.setSalary(rs.getInt("SALARY"));
                maxSalaryWorkerList.add(maxSalaryWorker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxSalaryWorkerList;
    }

    public List<YoungestEldestWorkers> findYoungestEldestWorkers() {
        List<YoungestEldestWorkers> youngestEldestWorkersList = new ArrayList<>();
        try (ResultSet rs = findYoungestEldestWorkersSt.executeQuery()) {
            while (rs.next()) {
                YoungestEldestWorkers youngestEldestWorkers = new YoungestEldestWorkers();
                youngestEldestWorkers.setType(rs.getString("TYPE"));
                youngestEldestWorkers.setName(rs.getString("NAME"));
                youngestEldestWorkers.setBirthday(LocalDate.parse(rs.getString("BIRTHDAY")));
                youngestEldestWorkersList.add(youngestEldestWorkers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return youngestEldestWorkersList;
    }

    public List<LongestProject> findLongestProject() {
        List<LongestProject> longestProjectList = new ArrayList<>();
        try (ResultSet rs = findLongestProjectSt.executeQuery()) {
            while (rs.next()) {
                LongestProject longestProject = new LongestProject();
                longestProject.setName(rs.getString("NAME"));
                longestProject.setMonthCount(rs.getInt("MONTH_COUNT"));
                longestProjectList.add(longestProject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return longestProjectList;
    }

    public List<ProjectPrice> printProjectPrice() {
        List<ProjectPrice> projectPriceList = new ArrayList<>();
        try (ResultSet rs = printProjectPriceSt.executeQuery()) {
            while (rs.next()) {
                ProjectPrice projectPrice = new ProjectPrice();
                projectPrice.setName(rs.getString("NAME"));
                projectPrice.setPrice(rs.getBigDecimal("PRICE"));
                projectPriceList.add(projectPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectPriceList;
    }
}
