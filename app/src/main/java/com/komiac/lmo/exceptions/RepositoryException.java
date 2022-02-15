package com.komiac.lmo.exceptions;

import android.content.res.Resources;

import androidx.annotation.Nullable;

import com.komiac.lmo.R;

public class RepositoryException extends Exception {
    protected String message;

    public RepositoryException() {
        message = "";
    }

    public RepositoryException(String message) {
        this.message = message + " " + Resources.getSystem().getString(R.string.tryToRestartApp);
    }

    @Nullable
    @Override
    public String getMessage() {
        if (message.equals(""))
            return Resources.getSystem().getString(R.string.somethingWentWrong);

        return message;
    }
}
