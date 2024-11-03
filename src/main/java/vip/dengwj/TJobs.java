package vip.dengwj;

import vip.dengwj.entity.TJobsEntity;
import vip.dengwj.util.DBUtil2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TJobs {
    public static void main(String[] args) throws Exception {
        Connection connection = DBUtil2.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_jobs");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<TJobsEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("job_id");
            String title = resultSet.getString("job_title");
            String minSalary = resultSet.getString("min_salary");
            String maxSalary = resultSet.getString("max_salary");

            TJobsEntity tJobsEntity = new TJobsEntity.Builder()
                .setJobId(id)
                .setJobTitle(title)
                .setMinSalary(minSalary)
                .setMaxSalary(maxSalary)
                .build();

            list.add(tJobsEntity);
        }
        for (TJobsEntity tJobsEntity : list) {
            System.out.println(tJobsEntity);
        }
    }
}
