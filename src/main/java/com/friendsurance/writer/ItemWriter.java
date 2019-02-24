package com.friendsurance.writer;

import com.friendsurance.exception.AppException;
import com.friendsurance.vo.UserStatus;

import java.util.List;
import java.util.concurrent.CompletionStage;

/**
 * Writes result of the processing performed by ItemProcessing.
 *
 * @param <O> the type parameter
 */
public interface ItemWriter<O> {

    /**
     * Writes an item produced by ItemProcessing (see 'O process(I item)' method). This
     * could imply writing into a database or a file or an email provider, depending on
     * the implementation.
     *
     * @param item the item
     * @return the completion stage
     * @throws AppException the app exception
     */
    CompletionStage<Boolean> write(List<UserStatus> item) throws AppException;

}
