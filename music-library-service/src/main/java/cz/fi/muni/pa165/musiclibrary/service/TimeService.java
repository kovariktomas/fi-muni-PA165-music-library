package cz.fi.muni.pa165.musiclibrary.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * An interface that defines a service access to the current time.
 */

@Service
public interface TimeService {
    public Date getCurrentTime();
}
