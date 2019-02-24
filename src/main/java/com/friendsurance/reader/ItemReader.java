package com.friendsurance.reader;

import com.friendsurance.exception.AppException;
import com.friendsurance.vo.User;

import java.util.List;
import java.util.concurrent.CompletionStage;


/**
 * Provides items to ItemProcessing independently of the source they are
 * taken from.
 *
 * @param <I> the type parameter
 */
public interface ItemReader<I> {

    /**
     * Retrieves <b>next</b> item which can be processed by ItemProcessing.
     * When there are no more new items it will return null.
     *
     * @return the completion stage
     * @throws AppException the app exception
     */
    CompletionStage<List<User>> read() throws AppException;

}
