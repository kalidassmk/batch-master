package com.friendsurance.reader.impl;

import com.friendsurance.exception.AppException;
import com.friendsurance.reader.ItemReader;
import com.friendsurance.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * UserItemReader read the user data and send to email process
 *
 * @author Kalidass Mahalingam 2/2/2019
 */
@Component
public class UserItemReader implements ItemReader<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserItemReader.class);

    private static final String USER_QUERY = "SELECT * FROM USER";

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    /**
     * Retrieves <b>next</b> item which can be processed by ItemProcessing.
     * When there are no more new items it will return null.
     *
     * @return the completion stage
     * @throws AppException the app exception
     */
    public CompletionStage<List<User>> read() throws AppException {
        try {
            LOGGER.info("Start the reading...............................");
            return getUser();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Exception while the data reading", e);
            throw new AppException("Exception while the data reading" + e.getMessage());
        }
    }

    private CompletionStage<List<User>> getUser() {
        return CompletableFuture.supplyAsync(() -> jdbcTemplateObject.query(USER_QUERY, new UserRowMapper()));
    }


    /**
     * The type User row mapper.
     */
    public static class UserRowMapper implements RowMapper {
        /**
         * The Email.
         */
        static final String EMAIL = "EMAIL";
        /**
         * The Has contract.
         */
        static final String HAS_CONTRACT = "HAS_CONTRACT";
        /**
         * The Friends number.
         */
        static final String FRIENDS_NUMBER = "FRIENDS_NUMBER";
        /**
         * The Sent invitations number.
         */
        static final String SENT_INVITATIONS_NUMBER = "SENT_INVITATIONS_NUMBER";

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setEmail(rs.getString(EMAIL));
            user.setHasContract(rs.getBoolean(HAS_CONTRACT));
            user.setFriendsNumber(rs.getInt(FRIENDS_NUMBER));
            user.setSentInvitationsNumber(rs.getInt(SENT_INVITATIONS_NUMBER));
            return user;
        }
    }


}
