package com.example.demo3.dao.impl;

import com.example.demo3.dao.MajorDao;
import com.example.demo3.db.DBUtil;
import com.example.demo3.entity.Major;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MajorDaoImpl implements MajorDao {
    @Override
    public void add(Major major) {
        DBUtil dbUtil = new DBUtil();
        String mid = major.getMid();
        String mname = major.getMname();
        dbUtil.executeUpdate("insert into major(mid, mname) values('" + mid + "','" + mname + "')");
        dbUtil.close();
    }

    @Override
    public void modify(Major major) {
        DBUtil dbUtil = new DBUtil();
        String mid = major.getMid();
        String mname = major.getMname();
        dbUtil.executeUpdate("update major set mname = '" + mname + "' where mid = '" + mid + "'");
        dbUtil.close();
    }

    @Override
    public void remove(String mid) {
        DBUtil dbUtil = new DBUtil();
        dbUtil.executeUpdate("delete from major where mid = '" + mid + "'");
        dbUtil.close();
    }

    @Override
    public Major getByMid(String mid) {
        DBUtil dbUtil = new DBUtil();
        ResultSet rs = dbUtil.executeQuery("select mname from major where mid = '" + mid + "'");
        String mname;
        try {
            if (rs.next()) {
                mname = rs.getString(1);
                return new Major(mid, mname);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            dbUtil.close();
            return null;
        }
    }

    @Override
    public List<Major> getAll(String sql) {
        DBUtil dbUtil = new DBUtil();
        ResultSet rs = dbUtil.executeQuery(sql);
        List<Major> majorList = new ArrayList<>();
        String mid, mname;

        try {
            while (rs.next()) {
                mid = rs.getString(1);
                mname = rs.getString(2);
                majorList.add(new Major(mid, mname));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            dbUtil.close();
            return majorList;
        }


    }
}
