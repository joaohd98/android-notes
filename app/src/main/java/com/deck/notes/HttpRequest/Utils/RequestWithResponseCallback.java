package com.deck.notes.HttpRequest.Utils;

public interface RequestWithResponseCallback<Type> {

    void success(Type response);
    void error(Exception exception);

}
