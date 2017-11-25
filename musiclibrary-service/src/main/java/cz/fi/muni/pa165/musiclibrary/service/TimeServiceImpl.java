package cz.fi.muni.pa165.musiclibrary.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * An implementation of the interface that defines a service access to the current time.
 */

@Service
public class TimeServiceImpl implements TimeService {

    @Override
    public Date getCurrentTime() {
        return new Date();
    }

}
