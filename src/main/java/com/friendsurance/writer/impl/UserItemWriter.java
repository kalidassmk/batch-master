package com.friendsurance.writer.impl;

import com.friendsurance.exception.AppException;
import com.friendsurance.vo.UserStatus;
import com.friendsurance.writer.ItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * com.friendsurance.processing
 *
 * @author Kalidass Mahalingam 2/2/2019
 */
@Component
@Transactional
public class UserItemWriter implements ItemWriter<List<UserStatus>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserItemWriter.class);

    private final String SQL_USER_STATUS_INSERT = "INSERT INTO User_Status(email, mail_type, time_stamp,status) values(?,?,?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public CompletionStage<Boolean> write(List<UserStatus> item) throws AppException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return saveBatch(item);
            } catch (AppException e) {
                e.printStackTrace();
                LOGGER.error("Exception while update the data....", e);
                return Boolean.FALSE;
            }
        });
    }


    private Boolean saveBatch(final List<UserStatus> collection) throws AppException {

        try {
            int chunkSize = 2;

            LOGGER.info("Batch update has started.............................");
            LOGGER.info("Batch size : " + chunkSize);
            // fill collection
            List<List<UserStatus>> batchLists = new ArrayList<>();
            for (int i = 0; i < collection.size(); i += chunkSize) {
                int end = Math.min(collection.size(), i + chunkSize);
                batchLists.add(collection.subList(i, end));
            }
            String timeStamp = String.valueOf(System.currentTimeMillis());
            int[] resp = null;
            for (List<UserStatus> batch : batchLists) {
                resp = jdbcTemplateObject.batchUpdate(SQL_USER_STATUS_INSERT, new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        UserStatus status = batch.get(i);
                        ps.setString(1, status.getEmail());
                        ps.setString(2, status.getMailType());
                        ps.setString(3, timeStamp);
                        ps.setString(4, status.getStatus());
                    }

                    @Override
                    public int getBatchSize() {
                        return batch.size();
                    }
                });
            }

            LOGGER.info(" Write timeStamp + \"Response :\"+ resp = " + timeStamp + "   Total user has updated :" + Arrays.toString(resp));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Exception while update the data....", e);
            throw new AppException("Exception while update the data...." + e.getMessage());
        }
        return Boolean.TRUE;
    }
}
