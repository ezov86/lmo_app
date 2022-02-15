package com.komiac.lmo.exceptions;

import android.content.res.Resources;

import com.komiac.lmo.R;

public class NotFoundInRepositoryException extends RepositoryException {
    public NotFoundInRepositoryException() {
        message = Resources.getSystem().getString(R.string.notFound);
    }
}
