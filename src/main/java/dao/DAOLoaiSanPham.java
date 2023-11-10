package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import models.LoaiSanPham;

public class DAOLoaiSanPham {
    private Connection connection;

    public DAOLoaiSanPham() {
        connection = ConnectDB.getinstance().getConnection();
    }

    private void closeResources(PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<LoaiSanPham> getAllLoaiSanPham() {
        ArrayList<LoaiSanPham> dsLoaiSanPham = new ArrayList<>();
        String sql = "SELECT * FROM LoaiSanPham";

        try (PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham();
                lsp.setIdLoaiSanPham(rs.getString("idLoaiSanPham"));
                lsp.setTenLoaiSanPham(rs.getString("tenLoaiSanPham"));
                dsLoaiSanPham.add(lsp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsLoaiSanPham;
    }

    public boolean themLoaiSanPham(LoaiSanPham lsp) {
        String sql = "INSERT INTO LoaiSanPham (idLoaiSanPham, tenLoaiSanPham) VALUES (?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, lsp.getIdLoaiSanPham());
            pst.setString(2, lsp.getTenLoaiSanPham());
            int n = pst.executeUpdate();
            return n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getLoaiSanPhamNameByID(String idLoaiSanPham) {
        String tenLoaiSanPham = null;
        String query = "SELECT tenLoaiSanPham FROM LoaiSanPham WHERE idLoaiSanPham = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, idLoaiSanPham);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    tenLoaiSanPham = resultSet.getString("tenLoaiSanPham");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenLoaiSanPham;
    }

    public LoaiSanPham getLoaiSanPhamByTen(String tenLoaiSanPham) {
        String sql = "SELECT * FROM LoaiSanPham WHERE tenLoaiSanPham = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, tenLoaiSanPham);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    LoaiSanPham loaiSanPham = new LoaiSanPham();
                    loaiSanPham.setIdLoaiSanPham(rs.getString("idLoaiSanPham"));
                    loaiSanPham.setTenLoaiSanPham(rs.getString("tenLoaiSanPham"));
                    return loaiSanPham;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
