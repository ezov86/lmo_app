package com.komiac.lmo.exceptions;

import android.content.res.Resources;

import com.komiac.lmo.R;

public class RepositoryHttpException extends RepositoryException {
    private final int code;

    public RepositoryHttpException(int code, String message) {
        super();
        this.message = generateMessage(code, message);
        this.code = code;
    }

    private String generateMessage(int code, String message) {
        StringBuilder builder = new StringBuilder();

        builder.append(Resources.getSystem().getString(R.string.httpError));

        if (code != 0) {
            builder.append(" ");
            builder.append(Resources.getSystem().getString(R.string.code));
            builder.append(code);
        }

        if (message != null) {
            builder.append(": ");
            builder.append(message);
        }

        return builder.toString();
    }


    public int getCode() {
        return code;
    }
}
